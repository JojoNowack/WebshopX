package de.hsa.OOSD.WebshopX.webshopx.services.user;

import de.hsa.OOSD.WebshopX.webshopx.models.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}
