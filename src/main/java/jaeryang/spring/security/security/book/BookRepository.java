package jaeryang.spring.security.security.book;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    //security 의 기능 사용
    @Query("select b from Book b where b.author.id = ?#{principal.account.id}")
    List<Book> findCurrentUserBooks();
}
