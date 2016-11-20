package pl.dk.sdnaturalkeys.base;

import org.springframework.data.jpa.repository.support.JpaMetamodelEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public BaseRepositoryImpl(JpaMetamodelEntityInformation jpaMetamodelEntityInformation, Object o) {
        super(jpaMetamodelEntityInformation.getJavaType(), (EntityManager) o);
        this.entityManager = (EntityManager) o;
    }

    @Override
    @Transactional
    public <S extends T> S persist(S entity) {
        entityManager.persist(entity);
        return entity;
    }
}
