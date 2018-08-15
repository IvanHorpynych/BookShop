package ua.demo.service.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;
import ua.demo.service.entity.models.User;
import ua.demo.service.repositories.BooksRepository;
import ua.demo.service.services.BookService;
import ua.demo.service.services.TokenService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    TokenService tokenService;

    @Override
    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    @Override
    public Optional<Book> findById(Long id) {
        return booksRepository.findById(id);
    }

    @Override
    public List<Book> save(List<Book> books) {
        return booksRepository.save(books);
    }

    @Override
    public Book save(Book book) {
        return booksRepository.save(book);
    }

    @Override
    @Transactional
    public DataWrapper addBook(Book book, TokenDto tokenDto) {

        User user = tokenService.findByValue(
                tokenDto.getToken()).get().getUser();

        save(book);

        return DataWrapper.builder()
                .allowedBooks(findAll())
                .currentUser(user)
                .build();
    }
}
