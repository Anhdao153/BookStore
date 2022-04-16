package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.book.BookDTO;
import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(value = "http://localhost:4200/", allowCredentials = "true")
public class BookController {

    @Autowired
    private IBookService iBookService;

    @GetMapping("/search")
    public ResponseEntity<Page<Book>> search(@RequestParam("keyWord") String keyword,
                                             @PageableDefault(value = 9) Pageable pageable) {
        Page<Book> bookPageable = iBookService.searchBookWithPage(keyword, pageable);
        if (bookPageable.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bookPageable, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Book>> findBookById(@RequestParam("id") String id) {
        Optional<Book> book = iBookService.FindBookById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<Optional<Book>> detailBookById(@RequestParam("id") String id) {
        Optional<Book> book = iBookService.FindBookById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    //Hiện vẫn còn đang thiếu phần upload hình ảnh file base
    @PostMapping("/save")
    public ResponseEntity<Book> SaveBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        new BookDTO().validate(bookDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book = iBookService.save(bookDTO);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @PatchMapping("/edit")
    public ResponseEntity<Book> EditBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult) {
        new BookDTO().validate(bookDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Book> book = iBookService.FindBookById(bookDTO.getId());
        if (book.isPresent()) {
            iBookService.save(bookDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Book> deleteBook(@RequestParam("id") String id) {
        Optional<Book> book = iBookService.FindBookById(id);
        if (!book.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        iBookService.deleted(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}