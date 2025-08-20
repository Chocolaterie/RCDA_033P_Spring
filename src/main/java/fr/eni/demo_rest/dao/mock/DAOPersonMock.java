package fr.eni.demo_rest.dao.mock;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.IDAOPerson;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Profile("mock")
@Component
public class DAOPersonMock implements IDAOPerson {

    List<Person> DB_Persons;

    public DAOPersonMock(){
        // Liste de vide
        DB_Persons = new ArrayList<>();

        // générer 10 personnes
        for (int i = 0; i < 10; i++){

            Person p = new Person();
            p.pseudo = String.format("Prénom %s", i);
            p.age = 15 + i;

            DB_Persons.add(p);
        }
    }

    /**
     * Récupérer toute les personnes de la base de données
     * @return
     */
    @Override
    public List<Person> selectAll(){
        return DB_Persons;
    }
}
