package com.phuc.routeschedulingnote;

import com.phuc.routeschedulingnote.model.Role;
import com.phuc.routeschedulingnote.model.RoleEnum;
import com.phuc.routeschedulingnote.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableJpaAuditing
public class RouteSchedulingNoteApplication {

    public static void main(String[] args) {
        SpringApplication.run(RouteSchedulingNoteApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public CommandLineRunner roleCreator(RoleRepository roleRepository) {
        return args -> {
            if (!roleRepository.existsByName(RoleEnum.ROLE_USER)){
                Role userRole = new Role();
                userRole.setName(RoleEnum.ROLE_USER);
                roleRepository.save(userRole);
            }

            if (!roleRepository.existsByName(RoleEnum.ROLE_ADMIN)){
                Role adminRole = new Role();
                adminRole.setName(RoleEnum.ROLE_ADMIN);
                roleRepository.save(adminRole);
            }
        };
    }
}
