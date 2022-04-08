package com.bookstore.bookstore.service.book.impl;

import com.bookstore.bookstore.dto.book.BookDTO;
import com.bookstore.bookstore.model.book.Book;
import com.bookstore.bookstore.repository.book.IBookRepo;
import com.bookstore.bookstore.service.book.IBookService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements IBookService {
    @Autowired
    IBookRepo iBookRepo;

    @Override
    public Optional<Book> FindBookById(String id) {
        return iBookRepo.findById(id);
    }

    @Override
    public Book save(BookDTO bookDTO) {
        Book book=new Book();
        BeanUtils.copyProperties(bookDTO,book);
        return iBookRepo.save(book);
    }

    @Override
    public Page<Book> searchBookWithPage(String keyword, Pageable page) {
        return iBookRepo.findAllBookWithPage("%"+keyword+"%", page);
    }

    @Override
    public void deleted(String id) {
        iBookRepo.DeleteBookById(id);
    }
}
