package fr.eni.demo_rest;

import fr.eni.demo_rest.bo.Person;
import fr.eni.demo_rest.service.PersonServiceV2;
import fr.eni.demo_rest.service.ServiceResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("mock")
class DemoRestApplicationTests {

    @Autowired
    PersonServiceV2 personService;

	@Test
	void contextLoads() {
	}

    @Test
    void displayOffAgePersons_test(){
        // Tester tout les cas possibles, donc tout les return possibles

        ServiceResponse<List<Person>> result_1 = personService.displayOffAgePersons();

        assertThat(result_1.code).isEqualTo("2889");
    }

    @Test
    void getById_test(){
        // Tester tout les cas possibles, donc tout les return possibles

        // Cas 1
        ServiceResponse<Person> result_1 = personService.getById(0);
        assertThat(result_1.code).isEqualTo("703");

        // Cas 2
        ServiceResponse<Person> result_2 = personService.getById(1);
        assertThat(result_2.code).isEqualTo("202");
    }

}
