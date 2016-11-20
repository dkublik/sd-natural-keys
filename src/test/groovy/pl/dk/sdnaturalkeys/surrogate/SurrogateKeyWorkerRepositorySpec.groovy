package pl.dk.sdnaturalkeys.surrogate

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import pl.dk.sdnaturalkeys.SDNaturalKeys
import pl.dk.sdnaturalkeys.querystat.QueryStatistics
import spock.lang.Specification
import spock.lang.Subject

@ContextConfiguration(classes = [SDNaturalKeys])
@SpringBootTest
class SurrogateKeyWorkerRepositorySpec extends Specification {

    static final String PERSONAL_ID = '11111111111'

    @Autowired
    QueryStatistics queryStatistics

    @Autowired
    @Subject
    SurrogateKeyWorkerRepository workerRepository

    def setup() {
        workerRepository.deleteAll()
        queryStatistics.clear()
        println('--- initialized')
    }

    def "should save with one query for new object"() {
        given:
            SurrogateKeyWorker worker = new SurrogateKeyWorker(PERSONAL_ID);

        when:
            workerRepository.save(worker)

        then:
            queryStatistics.nrOfQueries() == 1
    }
}
