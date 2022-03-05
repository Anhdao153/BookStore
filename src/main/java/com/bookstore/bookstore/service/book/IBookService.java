package com.bookstore.bookstore.service.book;
import com.bookstore.bookstore.dto.book.BookDTO;
import com.bookstore.bookstore.model.book.Book;
import java.util.List;
import java.util.Optional;


public interface IBookService {
    Optional<Book> FindBookById(String id);

    Book save (BookDTO bookDTO);

    List<Book> searchBook(String keyword, Integer page);
}
