package ua.demo.service.entity.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
public class DataWrapper {
    private List<Book> allowedBooks;
    private User currentUser;
}
