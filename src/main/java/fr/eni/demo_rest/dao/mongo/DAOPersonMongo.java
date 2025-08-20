package fr.eni.demo_rest.dao.mongo;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.IDAOPerson;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.List;

@Profile("mongo")
@Component
public class DAOPersonMongo implements IDAOPerson {

    private final PersonMongoRepository personMongoRepository;

    public DAOPersonMongo(PersonMongoRepository personMongoRepository) {
        this.personMongoRepository = personMongoRepository;
    }

    @Override
    public List<Person> selectAll() {
        return personMongoRepository.findAll();
    }
}
