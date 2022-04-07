package com.bookstore.bookstore.repository.book;

import com.bookstore.bookstore.model.book.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IBookRepo extends JpaRepository<Book, String> {
//lỗi fulltext thì nhớ vào trong mysql rồi viết câu lệnh này trong bảng table
//ALTER TABLE `book_store`.`books`
//    ADD FULLTEXT INDEX `Search` (`author`, `name`, `publishing_company`, `translator`) VISIBLE;
//;
//
//    @Query(value = "select * from books where books.deleted=0 and concat ( author,`name`, publishing_company,translator) like ?1 limit ?2,9",
//            nativeQuery = true)
//    List<Book> findAllBook(String keyword, Integer page);

    @Query(value = "select * from books where books.deleted=0 and concat ( author,`name`, publishing_company,translator) like ?1",
            countQuery = "select count(*) from books where books.deleted=0 and concat ( author,`name`, publishing_company,translator) like ?1",
            nativeQuery = true)
    Page<Book> findAllBookWithPage(String keyword, Pageable page);

    @Query(value = "update book_store.books a join orderdetails b on a.id=b.book_id SET a.deleted = 1 where a.id = :id",
            nativeQuery = true)
    @Modifying
    @Transactional
    void DeleteBookById(@Param("id") String id);


}
