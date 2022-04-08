package com.bookstore.bookstore.service.book;
import com.bookstore.bookstore.dto.book.BookDTO;
import com.bookstore.bookstore.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;


public interface IBookService {
    Optional<Book> FindBookById(String id);

    Book save (BookDTO bookDTO);

    Page<Book> searchBookWithPage(String keyword, Pageable page);

    void deleted(String id);
}
