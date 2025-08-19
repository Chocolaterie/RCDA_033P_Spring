package fr.eni.demo_rest.service;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.dao.DAOPerson;
import fr.eni.demo_rest.locale.LocaleHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
public class PersonServiceV2 {

    @Autowired
    private DAOPerson daoPerson;

    @Autowired
    MessageSource messageSource;

    @Autowired
    LocaleHelper localeHelper;

    public ServiceResponse<List<Person>> displayOffAgePersons(){

        // Le service response instancier (celui qui contient le code traitement et la liste des personnes)
        ServiceResponse<List<Person>> serviceResponse = new ServiceResponse<>();

        // Récupérer les personnes de plus de 18 ans
        List<Person> results = daoPerson.selectAll();

        results = results.stream().filter(person -> person.age >= 18).toList();

        /* Demo Trad 1 : A la main sans helper*/
        // TODO : Test traduction
        // 1 : La clé de trad
        // 2 : Les args dans le message (Ex: Bonjour {0}, arg 0 = "Isaac")
        // 3 : La langue activée
        String message = messageSource.getMessage("DisplayOffAgePersons_200_Success", null, LocaleContextHolder.getLocale());
        System.out.println(message);

        String message_2 = messageSource.getMessage("DisplayOffAgePersons_200_Success", null, Locale.ENGLISH);
        System.out.println(message_2);

        /* Demo Trad 2 : Avec helper */
        String message_3 = localeHelper.i18n("DisplayOffAgePersons_200_Success");
        System.out.println(message_3);

        // code succès : 2889
        serviceResponse.code = "2889";
        serviceResponse.message = localeHelper.i18n("DisplayOffAgePersons_200_Success");

        // la data c'est la liste des personnes de plus de 18 ans
        serviceResponse.data = results;

        return serviceResponse;
    }

    public ServiceResponse<Person> getById(int id){
        // Cas 1 : Erreur 703
        if (id < 1){
            ServiceResponse<Person> serviceResponse = new ServiceResponse<>();
            serviceResponse.code = "703";

            return serviceResponse;
        }

        // Cas 2 : 202
        ServiceResponse<Person> serviceResponse = new ServiceResponse<>();
        serviceResponse.code = "202";

        return serviceResponse;
    }
}
