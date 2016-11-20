package pl.dk.sdnaturalkeys.surrogate;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@NoArgsConstructor
@Entity
class SurrogateKeyWorker {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String personalId;

    SurrogateKeyWorker(String personalId) {
        this.personalId = personalId;
    }

}
