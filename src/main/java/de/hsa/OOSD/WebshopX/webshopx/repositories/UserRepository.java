package de.hsa.OOSD.WebshopX.webshopx.repositories;

import de.hsa.OOSD.WebshopX.webshopx.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

}
