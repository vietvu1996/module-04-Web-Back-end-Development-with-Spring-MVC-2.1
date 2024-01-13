package com.codegym.controller;

import com.codegym.model.Code;
import com.codegym.service.IBookService;
import com.codegym.service.ICodeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/code-borrow")
public class CodeBorrowController {
    private final IBookService bookService;
    private final ICodeService codeService;

    public CodeBorrowController(IBookService bookService, ICodeService codeService) {
        this.bookService = bookService;
        this.codeService = codeService;
    }

    @GetMapping("/{bookId}")
    public String showCodeBorrowList(@PathVariable int bookId, Model model) {
        List<Code> allCodes = codeService.findAllCodesByBookId(bookId);
        model.addAttribute("allCodes", allCodes);
        return "code-borrow-list";
    }
}
