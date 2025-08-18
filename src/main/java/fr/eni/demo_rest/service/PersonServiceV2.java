package fr.eni.demo_rest.service;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.DAOPerson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceV2 {

    @Autowired
    private DAOPerson daoPerson;

    public ServiceResponse<List<Person>> displayOffAgePersons(){

        // Le service response instancier (celui qui contient le code traitement et la liste des personnes)
        ServiceResponse<List<Person>> serviceResponse = new ServiceResponse<>();

        // Récupérer les personnes de plus de 18 ans
        List<Person> results = daoPerson.selectAll();

        results = results.stream().filter(person -> person.age >= 18).toList();

        // code succès : 2889
        serviceResponse.code = "2889";
        // la data c'est la liste des personnes de plus de 18 ans
        serviceResponse.data = results;

        return serviceResponse;
    }
}
