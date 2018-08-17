package ua.demo.service.services;


import ua.demo.service.entity.dto.TokenDto;
import ua.demo.service.entity.forms.OrderForm;
import ua.demo.service.entity.models.DataWrapper;

import java.util.List;

public interface BasketService {

    DataWrapper getFrontData(TokenDto token);

    DataWrapper makePurchase(List<OrderForm> booksInBasket, TokenDto tokenDto);

}
