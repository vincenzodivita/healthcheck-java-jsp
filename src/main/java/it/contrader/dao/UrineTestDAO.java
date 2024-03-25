package it.contrader.dao;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

import it.contrader.model.BloodTest;
import it.contrader.model.User;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.UrineTest;

public class UrineTestDAO implements DAO<UrineTest> {
    private final String QUERY_ALL = "SELECT urinetest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM urinetest join registry t1_1 on urinetest.idUser = t1_1.id  join registry t1_2 on urinetest.idAdmin = t1_2.id;";
    private final String QUERY_ALL_ADMIN = "SELECT urinetest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM urinetest  join registry t1_1 on urinetest.idUser = t1_1.id  join registry t1_2 on urinetest.idAdmin = t1_2.id WHERE urinetest.idAdmin=?";
    private final String QUERY_ALL_USER = "SELECT urinetest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM urinetest  join registry t1_1 on urinetest.idUser = t1_1.id  join registry t1_2 on urinetest.idAdmin = t1_2.id WHERE urinetest.idUser=?";
    private final String QUERY_VALIDATION_ADMIN = "UPDATE urinetest SET isChecked=? WHERE id=?";

    private final String QUERY_READ = "SELECT urinetest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM urinetest  join registry t1_1 on urinetest.idUser = t1_1.id  join registry t1_2 on urinetest.idAdmin = t1_2.id WHERE urinetest.id=?";
    private final String QUERY_READ_USER = "SELECT urinetest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM urinetest  join registry t1_1 on urinetest.idUser = t1_1.id  join registry t1_2 on urinetest.idAdmin = t1_2.id WHERE urinetest.id=? and urinetest.idUser=?";
    private final String QUERY_READ_ADMIN = "SELECT urinetest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM urinetest  join registry t1_1 on urinetest.idUser = t1_1.id  join registry t1_2 on urinetest.idAdmin = t1_2.id WHERE urinetest.id=? and urinetest.idAdmin=?";
    private final String QUERY_UPDATE = "UPDATE urinetest SET color=?, ph=?, protein=?, hemoglobin=?, dataInsert=? WHERE id=? AND isChecked=false";

    private final String QUERY_CREATE = "INSERT INTO urinetest (color, ph, protein, hemoglobin, idAdmin, idUser, dataInsert) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private final String QUERY_DELETE = "DELETE FROM urinetest WHERE id=?";


    public UrineTestDAO() {

    }

    public List<UrineTest> getAll() {
        List<UrineTest> urineTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            UrineTest test;
            while (resultSet.next()) {
                Float color, ph, protein, hemoglobin;
                String dataInsert,doctor, patience;
                Integer id, idAdmin, idUser;
                Boolean isChecked;

                color = resultSet.getFloat("color");
                ph = resultSet.getFloat("ph");
                protein = resultSet.getFloat("protein");
                hemoglobin = resultSet.getFloat("hemoglobin");
                id = resultSet.getInt("id");
                idAdmin = resultSet.getInt("idAdmin");
                idUser = resultSet.getInt("idUser");
                isChecked = resultSet.getBoolean("isChecked");
                dataInsert = resultSet.getString("dataInsert");
                doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
                patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

                test = new UrineTest(id, color, ph, protein, hemoglobin, idAdmin, idUser, isChecked, dataInsert,doctor,patience);
                urineTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n " + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return urineTestList;
    }

    public List<UrineTest> getAll(int idAdmin) {
        List<UrineTest> urineTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_ADMIN);
            preparedStatement.setInt(1, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();
            UrineTest test;
            while (resultSet.next()) {
                Float color, ph, protein, hemoglobin;
                String dataInsert,doctor, patience;
                Integer id, idUser;
                Boolean isChecked;

                color = resultSet.getFloat("color");
                ph = resultSet.getFloat("ph");
                protein = resultSet.getFloat("protein");
                hemoglobin = resultSet.getFloat("hemoglobin");
                id = resultSet.getInt("id");
                idUser = resultSet.getInt("idUser");
                isChecked = resultSet.getBoolean("isChecked");
                dataInsert = resultSet.getString("dataInsert");
                doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
                patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

                test = new UrineTest(id, color, ph, protein, hemoglobin, idAdmin, idUser, isChecked, dataInsert,doctor,patience);
                urineTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_ADMIN);
            e.printStackTrace();
            return null;
        }
        return urineTestList;
    }

    public List<UrineTest> getAllUser(int idUser) {
        List<UrineTest> urineTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_USER);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            UrineTest test;
            while (resultSet.next()) {
                Float color, ph, protein, hemoglobin;
                String dataInsert, doctor, patience;
                Integer id, idAdmin;
                Boolean isChecked;

                color = resultSet.getFloat("color");
                ph = resultSet.getFloat("ph");
                protein = resultSet.getFloat("protein");
                hemoglobin = resultSet.getFloat("hemoglobin");
                id = resultSet.getInt("id");
                idAdmin = resultSet.getInt("idAdmin");
                isChecked = resultSet.getBoolean("isChecked");
                dataInsert = resultSet.getString("dataInsert");
                doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
                patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

                test = new UrineTest(id, color, ph, protein, hemoglobin, idAdmin, idUser, isChecked, dataInsert, doctor,patience);
                urineTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_USER);
            e.printStackTrace();
            return null;
        }
        return urineTestList;
    }

    public UrineTest read(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Float color, ph, protein, hemoglobin;
            String dataInsert, doctor, patience;
            Integer idUser, idAdmin;
            Boolean isChecked;

            color = resultSet.getFloat("color");
            ph = resultSet.getFloat("ph");
            protein = resultSet.getFloat("protein");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idAdmin = resultSet.getInt("idAdmin");
            idUser = resultSet.getInt("idUser");
            isChecked = resultSet.getBoolean("isChecked");
            dataInsert = resultSet.getString("dataInsert");
            doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
            patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

            UrineTest test = new UrineTest(color, ph, protein, hemoglobin, idAdmin, idUser, isChecked, dataInsert, doctor,patience);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }
    public UrineTest readUser(int id, int idUser) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Float color, ph, protein, hemoglobin;
            String dataInsert, doctor, patience;
            Integer idAdmin;
            Boolean isChecked;

            color = resultSet.getFloat("color");
            ph = resultSet.getFloat("ph");
            protein = resultSet.getFloat("protein");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idAdmin = resultSet.getInt("idAdmin");
            isChecked= resultSet.getBoolean("isChecked");
            dataInsert= resultSet.getString("dataInsert");
            doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
            patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

            UrineTest test = new UrineTest(color,ph,protein,hemoglobin,idAdmin,idUser,isChecked,dataInsert,doctor,patience);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            e.printStackTrace();
            return null;
        }
    }
    public UrineTest readAdmin(int id, int idAdmin) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_ADMIN);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            Float color, protein, ph, hemoglobin;
            Integer idUser;
            Boolean isChecked;
            String dataInsert, doctor, patience;

            color = resultSet.getFloat("color");
            ph = resultSet.getFloat("ph");
            protein = resultSet.getFloat("protein");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idUser = resultSet.getInt("idUser");
            isChecked = resultSet.getBoolean("isChecked");
            dataInsert = resultSet.getString("dataInsert");
            doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
            patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

            UrineTest test = new UrineTest(color, ph, protein, hemoglobin, idAdmin, idUser, isChecked, dataInsert,doctor,patience);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            e.printStackTrace();
            return null;
        }
    }

    public boolean insert(UrineTest UrineTestToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setFloat(1, UrineTestToInsert.getColor());
            preparedStatement.setFloat(2, UrineTestToInsert.getPh());
            preparedStatement.setFloat(3, UrineTestToInsert.getProtein());
            preparedStatement.setFloat(4, UrineTestToInsert.getHemoglobin());
            preparedStatement.setInt(5, UrineTestToInsert.getIdAdmin());
            preparedStatement.setInt(6, UrineTestToInsert.getIdUser());
            preparedStatement.setString(7, UrineTestToInsert.getDataInsert());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n " + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }
    }

    public boolean update(UrineTest urineTestToUpdate) {

        Connection connection = ConnectionSingleton.getInstance();

        if (urineTestToUpdate.getId() == 0)
            return false;

        UrineTest urineTestRead = read(urineTestToUpdate.getId());


        if (!urineTestRead.equals(urineTestToUpdate)) {
            try {
                if (urineTestToUpdate.getColor() == null || urineTestToUpdate.getColor().equals("")) {
                    urineTestToUpdate.setColor(urineTestRead.getColor());
                }
                if (urineTestToUpdate.getPh() == null || urineTestToUpdate.getPh().equals("")) {
                    urineTestToUpdate.setProtein(urineTestRead.getPh());
                }
                if (urineTestToUpdate.getProtein() == null || urineTestToUpdate.getProtein().equals("")) {
                    urineTestToUpdate.setProtein(urineTestRead.getProtein());
                }
                if (urineTestToUpdate.getHemoglobin() == null || urineTestToUpdate.getHemoglobin().equals("")) {
                    urineTestToUpdate.setHemoglobin(urineTestRead.getHemoglobin());
                }
                if (urineTestToUpdate.getIdAdmin() == null || urineTestToUpdate.getIdAdmin().equals("")) {
                    urineTestToUpdate.setIdAdmin(urineTestRead.getIdAdmin());
                }
                if (urineTestToUpdate.getIdUser() == null || urineTestToUpdate.getIdUser().equals("")) {
                    urineTestToUpdate.setIdUser(urineTestRead.getIdUser());
                }
                if (urineTestToUpdate.getIsChecked() == null || urineTestToUpdate.getIsChecked().equals("")) {
                    urineTestToUpdate.setIsChecked(urineTestRead.getIsChecked());
                }
                if (urineTestToUpdate.getDataInsert() == null || urineTestToUpdate.getDataInsert().equals("")) {
                    urineTestToUpdate.setDataInsert(urineTestRead.getDataInsert());
                }


                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setFloat(1, urineTestToUpdate.getColor());
                preparedStatement.setFloat(2, urineTestToUpdate.getPh());
                preparedStatement.setFloat(3, urineTestToUpdate.getProtein());
                preparedStatement.setFloat(4, urineTestToUpdate.getHemoglobin());
                preparedStatement.setString(5, urineTestToUpdate.getDataInsert());
                preparedStatement.setInt(6, urineTestToUpdate.getId());


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

    public boolean delete(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            int n = preparedStatement.executeUpdate();
            if (n != 0)
                return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_DELETE);
            e.printStackTrace();
            return false;
        }
        return  false;
    }
    public boolean check(int id){
        Connection connection = ConnectionSingleton.getInstance();
        UrineTest urineTestRead = read(id);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_VALIDATION_ADMIN);
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(2, id);
            int n = preparedStatement.executeUpdate();
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_VALIDATION_ADMIN);
            e.printStackTrace();
            return false;
        }
        return false;

    }

}