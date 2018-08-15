package ua.demo.service.services;

import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;

import java.util.List;
import java.util.Optional;

public interface BookService {
     List<Book> findAll();

     Optional<Book> findById(Long id);

     List<Book> save(List<Book> books);

    Book save(Book book);

    DataWrapper addBook(Book book, TokenDto tokenDto);

}
