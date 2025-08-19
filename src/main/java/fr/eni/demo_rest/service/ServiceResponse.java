package fr.eni.demo_rest.service;

public class ServiceResponse<T> {

    public String code;
    public String message;
    public T data;
}