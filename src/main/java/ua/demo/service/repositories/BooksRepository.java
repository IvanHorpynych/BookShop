package ua.demo.service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.demo.service.entity.models.Book;

import java.util.List;
import java.util.Optional;

public interface BooksRepository extends JpaRepository<Book, Long> {

    @Override
    List<Book> findAll();

    Optional<Book> findById(Long id);
}
