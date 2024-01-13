package com.codegym.controller;

import com.codegym.exception.NotAvailableException;
import com.codegym.exception.WrongCodeException;
import com.codegym.model.Book;
import com.codegym.service.IBookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("")
    public ModelAndView showList() {
        ModelAndView view = new ModelAndView("layout");
        return view;
    }

    @GetMapping("/list")
    public String getAllBooks(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "list";
    }

    @GetMapping("/{id}")
    public String getBookById(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        return "view";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.save(book);
        return "redirect:/books";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.delete(id);
        return "redirect:/books";
    }

    @GetMapping("/borrow/{id}")
    public String borrowBookForm(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("borrowQuantity", 1);
        return "borrow";
    }

    @PostMapping("/borrow/{id}")
    public String borrowBook(@PathVariable int id, @RequestParam int borrowQuantity, Model model) {
        try {
            Book book = bookService.findById(id);
            bookService.borrow(book, borrowQuantity);
            return "redirect:/books";
        } catch (NotAvailableException e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @GetMapping("/return/{id}")
    public String returnBookForm(@PathVariable int id, Model model) {
        Book book = bookService.findById(id);
        System.out.println(book.getId());
        model.addAttribute("book", book);
        return "return";
    }

    @PostMapping("/return/{id}")
    public String returnBook(@ModelAttribute Book book, @RequestParam int returnCode, Model model) {
        try {
            bookService.returnBook(book, returnCode);
            System.out.println(book.getId());
            return "redirect:/books/list";
        } catch (NotAvailableException | WrongCodeException e) {
            model.addAttribute("error", e.getMessage());
            return "return";
        }
    }

    @GetMapping("/generate-code/{id}")
    public String generateBorrowCode(@PathVariable Long id, Model model) {
        int borrowCode = bookService.getNextAvailableCode();
        model.addAttribute("borrowCode", borrowCode);
        model.addAttribute("bookId", id);
        return "generate";
    }

    @GetMapping("/check-code/{id}")
    public String checkBorrowCodeForm(@PathVariable Long id, Model model) {
        model.addAttribute("bookId", id);
        return "check";
    }

    @PostMapping("/check-code/{id}")
    public String checkBorrowCode(@RequestParam int borrowCode, @PathVariable Long id, Model model) {
        boolean isValid = bookService.checkCode(borrowCode);
        model.addAttribute("isValid", isValid);
        return "check";
    }
}
