package pl.dk.sdnaturalkeys.querystat

import org.hibernate.Session
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import javax.persistence.EntityManager

@Service
class QueryStatistics {

    private final EntityManager entityManager

    @Autowired
    QueryStatistics(EntityManager entityManager) {
        this.entityManager = entityManager
    }

    Long nrOfQueries() {
        Session hibernateSession = (Session)entityManager.getDelegate()
        return hibernateSession.sessionFactory.statistics.prepareStatementCount
    }

    void clear() {
        Session hibernateSession = (Session)entityManager.getDelegate()
        hibernateSession.sessionFactory.statistics.clear()
    }

}
