package fr.eni.demo_rest.dao.mongo;

import fr.eni.demo_rest.bo.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonMongoRepository extends MongoRepository<Person, String> {
}
