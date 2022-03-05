package com.bookstore.bookstore.repository.book;

import com.bookstore.bookstore.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepo extends JpaRepository<Book, String> {

    @Query(value = "select * from books where match ( author,`name`, publishing_company,translator) AGAINST(?1) limit ?2,5 ",
            nativeQuery = true)
    List<Book> findAllBook(String keyword,Integer page);

}
