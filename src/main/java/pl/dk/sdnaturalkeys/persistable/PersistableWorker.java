package pl.dk.sdnaturalkeys.persistable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
class PersistableWorker implements Persistable {

    @Id
    private String personalId;

    @Setter
    private String surname;

    private transient boolean isNew = false;

    PersistableWorker(String personalId, boolean isNew) {
        this.personalId = personalId;
        this.isNew = isNew;
    }

    @Override
    public Serializable getId() {
        return personalId;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }
}
