package pl.dk.sdnaturalkeys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.dk.sdnaturalkeys.base.BaseRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class SDNaturalKeys {

    public static void main(String[] args) {
        SpringApplication.run(SDNaturalKeys.class, args);
    }

}
