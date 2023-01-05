package de.hsa.OOSD.WebshopX.webshopx.services.user;

import de.hsa.OOSD.WebshopX.webshopx.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    void saveUser(User user);

    User findByEmail(String email);

    List<UserDto> findAllUsers();

    User findById(Long id);

    User getCurrentUser();
}
