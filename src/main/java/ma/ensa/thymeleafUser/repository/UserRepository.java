package ma.ensa.thymeleafUser.repository;

import ma.ensa.thymeleafUser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}
