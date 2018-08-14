package ua.demo.service.services;

import ua.demo.service.entity.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
     List<Book> findAll();

     Optional<Book> findById(Long id);
}
