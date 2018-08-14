package ua.demo.service.services;


import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.models.Book;
import ua.demo.service.entity.models.DataWrapper;

import java.util.List;

public interface FrontMetaDataService {

    DataWrapper getFrontData(TokenDto token);

    DataWrapper buyBooks(List<Long> booksInBasket, TokenDto tokenDto);
}
