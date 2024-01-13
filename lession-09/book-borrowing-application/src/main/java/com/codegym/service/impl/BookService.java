package com.codegym.service.impl;

import com.codegym.exception.NotAvailableException;
import com.codegym.exception.WrongCodeException;
import com.codegym.model.Book;
import com.codegym.model.Code;
import com.codegym.model.Status;
import com.codegym.repository.IBookRepository;
import com.codegym.service.IBookService;
import com.codegym.service.ICodeService;
import com.codegym.service.IStatusService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class BookService implements IBookService {
    private final IBookRepository bookRepository;
    private final ICodeService codeService;
    private final IStatusService statusService;

    public BookService(IBookRepository bookRepository, ICodeService codeService, IStatusService statusService) {
        this.bookRepository = bookRepository;
        this.codeService = codeService;
        this.statusService = statusService;
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        Status availableStatus = statusService.findByStatusName("available");
        if (availableStatus == null) {
            availableStatus = new Status();
            availableStatus.setStatusName("available");
            statusService.save(availableStatus);
        }

        Status usedStatus = statusService.findByStatusName("used");
        if (usedStatus == null) {
            usedStatus.setStatusName("used");
            statusService.save(usedStatus);
        }

        for (int i = 0; i < book.getQuantity(); i++) {
            Code code = new Code();
            code.setBook(book);
            code.setStatus(availableStatus);

            code.setCode(new Random().nextInt(90000) + 10000);
            codeService.save(code);
        }
        return book;
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }

    @Override
    public void borrow(Book book, int borrowQuantity) throws NotAvailableException {
        if (book.getQuantity() == 0) {
            throw new NotAvailableException("The book is not available for borrowing");
        }

        if (book.getQuantity() >= borrowQuantity & borrowQuantity > 0) {
            book.setQuantity(book.getQuantity() - borrowQuantity);
            bookRepository.save(book);

            List<Code> codes = codeService.findAllCodesByBookId(book.getId());
            for (int i = 0; i < borrowQuantity; i++) {
                Integer usedCode = getNextUsedCode(codes);
                if (usedCode != null) {
                    for (Code code : codes) {
                        if (code.getCode() == usedCode) {
                            code.setStatus(statusService.findByStatusName("used"));
                            codeService.save(code);
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public void returnBook(Book book, int returnCode) throws NotAvailableException, WrongCodeException {
        if (book == null) {
            throw new NotAvailableException("The book is not available for returning");
        }
        List<Code> codes = codeService.findAllCodesByBookId(book.getId());
        if (codes != null & !codes.isEmpty()) {
            for (Code code : codes) {
                if (code.getCode() == returnCode) {
                    if (code.getStatus().getStatusName().equals("used")) {
                        code.setStatus(statusService.findByStatusName("available"));
                        codeService.save(code);

                        if (book.getQuantity() == 0) {
                            book.setQuantity(book.getQuantity() + 1);
                            bookRepository.save(book);
                        }
                        return;
                    } else {
                        throw new NotAvailableException("The book with code " + returnCode + " is already available");
                    }
                }
            }
            throw new NotAvailableException("No used code found for code " + returnCode + ".");
        } else {
            throw new WrongCodeException("Wrong return code.");
        }
    }

    @Override
    public int getNextAvailableCode() {
        int borrowCode;
        do {
            borrowCode = 10000 + (int) (Math.random() * 90000);
        } while (codeService.existsByCodeAndStatus_StatusName(borrowCode, "used"));
        return borrowCode;
    }

    @Override
    public boolean checkCode(int borrowCode) {
        return codeService.existsByCode(borrowCode);
    }

    private Integer getNextUsedCode(List<Code> codes) {
        for (Code code : codes) {
            if (code.getStatus().getStatusName().equals("available")) {
                code.setStatus(statusService.findByStatusName("used"));
                codeService.save(code);
                return code.getCode();
            }
        }
        return null;
    }
}
