package com.mnghia.dts.configuration;

import com.mnghia.dts.enums.Role;
import com.mnghia.dts.model.User;
import com.mnghia.dts.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE,makeFinal = true)
public class ApplicationInitConfig {
    private static final Logger log = LoggerFactory.getLogger(ApplicationInitConfig.class);
    PasswordEncoder passwordEncoder;

    @Bean
    ApplicationRunner applicationRunner(UserRepository userRepository){
        return args -> {
            if(!userRepository.existsByUsername("admin")){
                User user = User.builder()
                        .name("admin")
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .email("admin@gmail.com")
                        .role(Role.ADMIN)
                        .deleted(false)
                        .build();
                userRepository.save(user);
                log.warn("admin user has been created with default password: admin, please change password");
            }
        };
    }
}
