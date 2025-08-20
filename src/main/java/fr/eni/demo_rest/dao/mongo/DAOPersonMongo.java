package fr.eni.demo_rest.dao.mongo;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.IDAOPerson;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("mongo")
@Component
public class DAOPersonMongo extends DAOMongoGeneric<Person, String> {

    private final PersonMongoRepository personMongoRepository;

    public DAOPersonMongo(PersonMongoRepository personMongoRepository) {
        this.personMongoRepository = personMongoRepository;
    }

    @Override
    public MongoRepository getRepository() {
        return personMongoRepository;
    }
}
