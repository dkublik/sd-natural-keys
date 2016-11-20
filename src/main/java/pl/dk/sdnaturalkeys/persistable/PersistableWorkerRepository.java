package pl.dk.sdnaturalkeys.persistable;

import org.springframework.data.repository.CrudRepository;

interface PersistableWorkerRepository extends CrudRepository<PersistableWorker, String> {
}
