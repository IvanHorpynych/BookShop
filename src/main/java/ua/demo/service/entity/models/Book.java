package ua.demo.service.entity.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;


@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(required = true)
    private Long id = 0L;
    @NotNull
    private String name;
    @NotNull
    private BigDecimal price;
    @JsonProperty(required = true)
    private Integer timesBought = 0;

    public void incrementTimesBought(){
        timesBought++;
    }
}
