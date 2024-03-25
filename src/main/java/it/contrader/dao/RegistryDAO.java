package it.contrader.dao;

import it.contrader.enums.Usertype;
import it.contrader.model.Registry;
import it.contrader.model.User;
import it.contrader.utils.ConnectionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistryDAO implements DAO<Registry>{

    private final String QUERY_JOIN = "SELECT distinct bloodtest.idUser, registry.name, registry.surname from bloodtest right Join registry on bloodtest.idUser=registry.idUser where bloodtest.idAdmin=? Union SELECT urinetest.idUser, registry.name, registry.surname from urinetest right join registry on urinetest.idUser=registry.idUser where urinetest.idAdmin=?";

    private final String QUERY_CREATE = "INSERT INTO registry (name, surname, birthDate,email,nationality,city,address,cf,idUser) VALUES (?,?,?,?,?,?,?,?,?)";
    private final String QUERY_READ = "SELECT * FROM registry WHERE idUser=?";
    private final String QUERY_ALL = "SELECT * FROM registry";
    private final String QUERY_UPDATE = "UPDATE registry SET name=?, surname=?, birthDate=?, email=?, nationality=?, city=?, address=?, cf=? WHERE idUser=?";
    private final String DOCTOR_LIST = "SELECT registry.id, registry.surname FROM user join registry on user.id = registry.id WHERE user.usertype = 1";


    public List<Registry> getDoctorList() {
        List<Registry> registryList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(DOCTOR_LIST);
            Registry registry;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String surname = resultSet.getString("surname");
                registry = new Registry(id,surname);
                registryList.add(registry);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + DOCTOR_LIST);
            e.printStackTrace();
            return null;
        }
        return registryList;
    }

    @Override
    public List<Registry> getAll() {
        List<Registry> registryList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            Registry registry;
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String birthDate = resultSet.getString("birthDate");
                String email = resultSet.getString("email");
                String nationality = resultSet.getString("nationality");
                String city = resultSet.getString("city");
                String address = resultSet.getString("address");
                String cf = resultSet.getString("cf");
                int idUser = resultSet.getInt("idUser");

                registry = new Registry(id, name,surname,birthDate,email,nationality,city,address,cf,idUser);
                registryList.add(registry);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return registryList;
    }

    @Override
    public Registry read(int userId) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id;
            String name, surname, birthDate,email,nationality,city,address,cf;


            id = resultSet.getInt("id");
            name = resultSet.getString("name");
            surname = resultSet.getString("surname");
            birthDate = resultSet.getString("birthDate");
            email = resultSet.getString("email");
            nationality = resultSet.getString("nationality");
            city = resultSet.getString("city");
            address = resultSet.getString("address");
            cf = resultSet.getString("cf");

            Registry registry = new Registry(id,name, surname, birthDate,email,nationality,city,address,cf);
            registry.setIdUser(userId);

            return registry;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Registry registry) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setString(1, registry.getName());
            preparedStatement.setString(2, registry.getSurname());
            preparedStatement.setString(3, registry.getBirthDate());
            preparedStatement.setString(4, registry.getEmail());
            preparedStatement.setString(5, registry.getNationality());
            preparedStatement.setString(6, registry.getCity());
            preparedStatement.setString(7, registry.getAddress());
            preparedStatement.setString(8, registry.getCf());
            preparedStatement.setInt(9, registry.getIdUser());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Registry registry) {
        Connection connection = ConnectionSingleton.getInstance();

        // Check if id is present
        if (registry.getIdUser() == 0)
            return false;

        Registry registryRead = read(registry.getIdUser());
        if (!registryRead.equals(registry)) {
            try {
                // Fill the userToUpdate object

                if (registry.getName() == null || registry.getName().equals("")) {
                    registry.setName(registryRead.getName());
                }

                if (registry.getSurname() == null || registry.getSurname().equals("")) {
                    registry.setSurname(registryRead.getSurname());
                }

                if (registry.getBirthDate() == null || registry.getBirthDate().equals("")) {
                    registry.setBirthDate(registryRead.getBirthDate());
                }
                if (registry.getEmail() == null || registry.getEmail().equals("")) {
                    registry.setEmail(registryRead.getEmail());
                }
                if (registry.getNationality() == null || registry.getNationality().equals("")) {
                    registry.setNationality(registryRead.getNationality());
                }
                if (registry.getCity() == null || registry.getCity().equals("")) {
                    registry.setCity(registryRead.getCity());
                }
                if (registry.getAddress() == null || registry.getAddress().equals("")) {
                    registry.setAddress(registryRead.getAddress());
                }
                if (registry.getCf() == null || registry.getCf().equals("")) {
                    registry.setCf(registryRead.getCf());
                }

                // Update the user
                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setString(1, registry.getName());
                preparedStatement.setString(2, registry.getSurname());
                preparedStatement.setString(3, registry.getBirthDate());
                preparedStatement.setString(4, registry.getEmail());
                preparedStatement.setString(5, registry.getNationality());
                preparedStatement.setString(6, registry.getCity());
                preparedStatement.setString(7, registry.getAddress());
                preparedStatement.setString(8, registry.getCf());
                preparedStatement.setInt(9, registry.getIdUser());

                int a = preparedStatement.executeUpdate();
                return a > 0;

            } catch (SQLException e) {
                System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_UPDATE);
                e.printStackTrace();
                return false;
            }
        }

        return false;

    }

    public List<Registry> getAllPatient(int idAdmin) {
        List<Registry> patientList = new ArrayList<>();

        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_JOIN);

            preparedStatement.setInt(1, idAdmin);
            preparedStatement.setInt(2, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            Registry item;
            while (resultSet.next()) {
                String name, surname;
                Integer idUser;

                name = resultSet.getString("name");
                surname = resultSet.getString("surname");
                idUser = resultSet.getInt("idUser");


                item = new Registry(idUser, name, surname);
                patientList.add(item);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_JOIN);
            e.printStackTrace();
            return null;
        }

        return patientList;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
