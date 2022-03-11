package com.bookstore.bookstore.repository.book;

import com.bookstore.bookstore.model.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Repository
public interface IBookRepo extends JpaRepository<Book, String> {

    @Query(value = "select * from books where match ( author,`name`, publishing_company,translator) AGAINST(?1) limit ?2,5 and deleted=0",
            nativeQuery = true)
    List<Book> findAllBook(String keyword, Integer page);

    @Query(value = "update book_store.books a join orderdetails b on a.id=b.book_id SET a.deleted = 1 where a.id = :id",
            nativeQuery = true)
    @Modifying
    @Transactional
    void DeleteBookById(@Param("id") String id);


}
