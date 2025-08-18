package fr.eni.demo_rest.api;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.service.PersonService;
import fr.eni.demo_rest.service.PersonServiceV2;
import fr.eni.demo_rest.service.ServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoRestController {

    @Autowired
    PersonService personService;

    @Autowired
    PersonServiceV2 personServiceV2;

    /**
     * Retourner un Objet/Instance = Du Json (les "new" de quelques choses)
     * Tout sauf les primitives/Les objets literals (String, Boolean)
     * @return
     */
    @GetMapping("api/demo")
    public Person hlshdksdkfhsdf() {

        Person person = new Person();

        person.pseudo = "Incroyable Bulk 2";
        person.age = 26;

        return person;
        // return "Coucou RCDA_033P qui dit chocolatine";
    }

    /*
    Si version majeure déjà publiée mais url incorrect, pour eviter de caser l'api utilisée
    par d'autres personnes, on doit garder l'ancienne signature pendant x temps
    et rediriger sur la version corrigé.
    Il faut documenter pour prevenir tout les acteurs du projet
    @Deprecated("L'url est obselete veuillez utiliser api/display-off-age-persons. ")
    @GetMapping("api/display-off-age-person")
    public List<Person> apiDisplayOffAgePerson(){
        return apiDisplayOffAgePersons();
    }
    */

    @GetMapping("api/display-off-age-persons")
    public List<Person> apiDisplayOffAgePersons(){
        // Appeler le service (récupérer les personnes majeures)
        List<Person> persons = personService.displayOffAgePersons();

        // Retourner le json du service
        return persons;
    }

    @GetMapping("api/v2/display-off-age-persons")
    public ServiceResponse<List<Person>> apiDisplayOffAgePersonsV2(){
        // Appeler le service (récupérer les personnes majeures)
        ServiceResponse<List<Person>> serviceResponse = personServiceV2.displayOffAgePersons();

        // Retourner le json du service
        return serviceResponse;
    }
}
