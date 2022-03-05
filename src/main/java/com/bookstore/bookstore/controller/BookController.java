package com.bookstore.bookstore.controller;

import com.bookstore.bookstore.dto.book.BookDTO;
import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.service.book.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/book")
public class BookController {
    @Autowired
    private IBookService iBookService;

    @GetMapping("/search")
    public ResponseEntity<Page<Book>>  search(@RequestParam("q") String keyword,
                                              @RequestParam(name ="page",defaultValue = "0") Integer page){
      Pageable pageable= PageRequest.of(page,5);
       List<Book> bookPageable= iBookService.searchBook(keyword,page);

       Page<Book> books= new PageImpl<>(bookPageable,pageable,bookPageable.size());

       if (bookPageable.isEmpty()){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(books,HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Book>> findBookById(@RequestParam("id") String id){
        Optional<Book> book= iBookService.FindBookById(id);
        if (!book.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @PostMapping("/save")
    public ResponseEntity<Book> SaveBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        new BookDTO().validate(bookDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Book book= iBookService.save(bookDTO);
        return new ResponseEntity<>(book,HttpStatus.OK);
    }
    @PatchMapping("/edit")
    public ResponseEntity<Book> EditBook(@RequestBody @Valid BookDTO bookDTO, BindingResult bindingResult){
        new BookDTO().validate(bookDTO,bindingResult);
        if (bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Book> book=iBookService.FindBookById(bookDTO.getId());
        if (book.isPresent()) {
            iBookService.save(bookDTO);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}