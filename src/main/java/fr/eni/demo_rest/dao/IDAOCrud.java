package fr.eni.demo_rest.dao;

import fr.eni.demo_rest.bo.Person;

import java.util.List;

public interface IDAOCrud<T> {

    List<T> selectAll();
}
