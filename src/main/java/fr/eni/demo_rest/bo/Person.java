package fr.eni.demo_rest.bo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
public class Person {

    @Id
    public String id;

    public String pseudo;

    public String email;
    public String password;

    public int age = 0;
}
