package fr.eni.demo_rest.dao.mongo;

import fr.eni.demo_rest.dao.IDAOCrud;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public class DAOMongoGeneric<T, ID> implements IDAOCrud<T> {

    public MongoRepository<T, ID> getRepository(){
        return null;
    }

    @Override
    public List<T> selectAll() {
        return getRepository().findAll();
    }
}
