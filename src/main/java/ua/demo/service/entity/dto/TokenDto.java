package ua.demo.service.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ua.demo.service.entity.models.Token;


@Data
@AllArgsConstructor
public class TokenDto {

    private String value;

    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }

}
