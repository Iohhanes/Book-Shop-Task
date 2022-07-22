package com.academia.bookshop.controller;

import com.academia.bookshop.model.entity.Book;
import com.academia.bookshop.serive.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class TestController {
    private final BookService bookService;

    @GetMapping
    public List<Book> test(){
        return bookService.findAll();
    }
}
