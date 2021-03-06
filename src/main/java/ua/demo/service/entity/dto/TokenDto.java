package ua.demo.service.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ua.demo.service.entity.models.Token;


@Data
@AllArgsConstructor
public class TokenDto {

    private String token;

    public static TokenDto from(Token token) {
        return new TokenDto(token.getValue());
    }

}
