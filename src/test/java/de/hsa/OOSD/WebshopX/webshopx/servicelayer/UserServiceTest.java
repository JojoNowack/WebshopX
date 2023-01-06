package de.hsa.OOSD.WebshopX.webshopx.servicelayer;

import de.hsa.OOSD.WebshopX.webshopx.models.User;
import de.hsa.OOSD.WebshopX.webshopx.repositories.RoleRepository;
import de.hsa.OOSD.WebshopX.webshopx.repositories.UserRepository;
import de.hsa.OOSD.WebshopX.webshopx.services.user.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L,
                        "test",
                        "test@test.de",
                        "test",
                        Arrays.asList(roleRepository.findRoleByName("ROLE_ADMIN")),
                        new ArrayList<>());
    }

    @Test
    void testFindUserByEmail() {
        given(userRepository.findUserByEmail("test@test.de")).willReturn(user);
        User savedUser = userService.findUserByEmail(user.getEmail());
        assertThat(savedUser).isNotNull();
    }
}
