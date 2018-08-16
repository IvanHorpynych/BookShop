package ua.demo.service.entity.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ua.demo.service.entity.RegexMappings;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table (name = "fix_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Pattern(regexp = RegexMappings.NAME_SURNAME_REGEX)
    private String name;

    @NotNull
    @Pattern(regexp = RegexMappings.EMAIL_REGEX)
    private String email;

    @NotNull
    private String hashPassword;

    @NotNull
    @JsonProperty(required = true)
    private BigDecimal earnedMoney;

    @Enumerated(value = EnumType.STRING)
    @JsonProperty(required = true)
    private Role role = Role.USER;

    @JsonIgnore
    public String getHashPassword() {
        return hashPassword;
    }

    @JsonProperty("password")
    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

}
