package com.example.repository;

import com.example.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testFindByEmail() {
        User user = new User();
        user.setEmail("test@example.com");
        userRepository.save(user);

        assertThat(userRepository.findByEmail("test@example.com")).isPresent();
    }
}
