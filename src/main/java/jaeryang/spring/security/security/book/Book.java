package jaeryang.spring.security.security.book;

import jaeryang.spring.security.security.account.Account;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Book {
    @Id
    @GeneratedValue
    private Integer id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account author;
}
