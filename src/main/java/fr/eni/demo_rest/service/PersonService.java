package fr.eni.demo_rest.service;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.DAOPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    // Injecter en Autowired (obselete)
    @Autowired
    private DAOPerson daoPerson;

    /**
     * La feature qui permet de récupérer la liste des personnes majeures
     * @deprecated displayOffAgePersons est dépréciée, veuillez utiliser {@link PersonServiceV2#displayOffAgePersons()}
     * @return
     */
    public List<Person> displayOffAgePersons(){
        // A NE PAS FAIRE EN SPRING CORE (IOC)
        // ON DOIT INJECTER ET PAS INSTANCIER A LA MAIN NOS COUCHES
        // DAOPerson daoPerson = new DAOPerson();

        // Récupére toute les personnes
        List<Person> results = daoPerson.selectAll();

        // Filter les personnes majeurs
        // Predicate/Stream
        // stream().filter() > a quelle moment je garde les occurances (donc les personnes)
        // ??? -> Avant le fleche: quel est chaque occurance que je test (donc chaque personne)
        // ??? -> Aprés la fleche : true = je garde la personne, sinon je garde pas
        results = results.stream().filter(person -> person.age >= 18).toList();

        return results;
    }
}
