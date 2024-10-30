package jaeryang.spring.security.security.account;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
public class Account {

    @Id @GeneratedValue
    private Integer id;

    @Column(unique=true)
    private String username;
    private String password;
    private String role;
}
