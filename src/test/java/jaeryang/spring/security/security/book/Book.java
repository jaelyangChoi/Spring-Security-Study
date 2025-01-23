package jaeryang.spring.security.security.book;

import jaeryang.spring.security.security.account.Account;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id @GeneratedValue
    private Integer id;

    private String title;

    @ManyToOne
    private Account account;
}
