package it.contrader.model;

import java.util.List;
import java.util.Map;

public class Registry {
    private int id;
    private String name;
    private String surname;
    private String birthDate;
    private String email;
    private String nationality;
    private String city;
    private String address;
    private String cf;
    private Integer idUser;
    private List<Registry> doctorList;

    public Registry() {

    }



    public Registry ( String name, String surname, String birthDate, String email, String nationality, String city, String address, String cf, Integer idUser) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.address = address;
        this.cf = cf;
        this.idUser = idUser;
    }

    public Registry (int id, String name, String surname, String birthDate,String email,String nationality, String city,String address,String cf,Integer idUser) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.address = address;
        this.cf = cf;
        this.idUser = idUser;
    }


    public Registry(int id, String name, String surname, String birthDate, String email, String nationality, String city, String address, String cf) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.email = email;
        this.nationality = nationality;
        this.city = city;
        this.address = address;
        this.cf = cf;
    }
    public Registry(Integer idUser, String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.idUser = idUser;
    }

    public Registry(int id, String surname) {
        this.id = id;
        this.surname = surname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCf() {
        return cf;
    }

    public void setCf(String cf) {
        this.cf = cf;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public List<Registry> getDoctorList() {
        return doctorList;
    }

    public void setDoctorList(List<Registry> doctorList) {
        this.doctorList = doctorList;
    }
}
