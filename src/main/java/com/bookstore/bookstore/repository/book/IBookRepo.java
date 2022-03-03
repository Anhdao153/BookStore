package com.bookstore.bookstore.repository.book;

import com.bookstore.bookstore.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepo extends JpaRepository<Book,String> {
}
