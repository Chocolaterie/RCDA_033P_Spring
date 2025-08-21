package fr.eni.demo_rest.dao.mock;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.IDAOPerson;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Profile("mock")
@Component
public class DAOPersonMock implements IDAOPerson {

    List<Person> DB_Persons;

    public DAOPersonMock(){
        // Liste de vide
        DB_Persons = new ArrayList<>();

        // générer 10 personnes
        for (int i = 1; i < 11; i++){

            Person p = new Person();
            p.id = Integer.toString(i);
            p.pseudo = String.format("Prénom %d", i);
            p.email = String.format("test%d@gmail.com", i);
            p.password = String.format("password%d", i);
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

    @Override
    public Person selectPersonByLogin(String email, String password) {
        Optional<Person> foundPerson = DB_Persons.stream()
                .filter(person -> person.email.equals(email) && person.password.equals(password))
                .findFirst();

        return foundPerson.orElse(null);
    }
}
