package pl.dk.sdnaturalkeys.natural;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@NoArgsConstructor
@Entity
class NaturalKeyWorker {

    @Id
    private String personalId;

    NaturalKeyWorker(String personalId) {
        this.personalId = personalId;
    }

}
