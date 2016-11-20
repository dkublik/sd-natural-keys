package pl.dk.sdnaturalkeys.persistable

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.transaction.PlatformTransactionManager
import org.springframework.transaction.support.TransactionCallback
import org.springframework.transaction.support.TransactionTemplate
import pl.dk.sdnaturalkeys.SDNaturalKeys
import pl.dk.sdnaturalkeys.querystat.QueryStatistics
import spock.lang.Specification
import spock.lang.Subject

@ContextConfiguration(classes = [SDNaturalKeys])
@SpringBootTest
class PersistableWorkerRepositorySpec extends Specification {

    static final String PERSONAL_ID = '11111111111'

    @Autowired
    QueryStatistics queryStatistics

    @Autowired
    protected PlatformTransactionManager transactionManager

    @Autowired
    @Subject
    PersistableWorkerRepository workerRepository

    def setup() {
        workerRepository.deleteAll()
        queryStatistics.clear()
        println('--- initialized')
    }

    def "should save with one query for new object"() {
        given:
            PersistableWorker worker = new PersistableWorker(PERSONAL_ID, true);

        when:
            workerRepository.save(worker)

        then:
            queryStatistics.nrOfQueries() == 1
    }

    def "should save with two queries for existing object"() {
        given:
            PersistableWorker worker = new PersistableWorker(PERSONAL_ID, true);
            workerRepository.save(worker)
            queryStatistics.clear()

        when:
            worker = new PersistableWorker(PERSONAL_ID, false);
            worker.surname = 'Smith'
            workerRepository.save(worker)

        then:
            queryStatistics.nrOfQueries() == 2
    }

    def "should save with one query for existing object"() {
        given:
            PersistableWorker worker = new PersistableWorker(PERSONAL_ID, true);
            workerRepository.save(worker)

        when:
            doInTx {
                worker = workerRepository.findOne(PERSONAL_ID)
                queryStatistics.clear()
                worker.surname = 'Smith'
            }

        then:
            queryStatistics.nrOfQueries() == 1
            workerRepository.findOne(PERSONAL_ID).surname == 'Smith'
    }

    private void doInTx(TransactionCallback<?> callback) {
        TransactionTemplate tt = new TransactionTemplate(transactionManager);
        tt.execute(callback);
    }

}
