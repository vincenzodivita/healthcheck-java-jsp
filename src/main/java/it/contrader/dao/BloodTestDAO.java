package it.contrader.dao;

import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.BloodTest;
import it.contrader.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BloodTestDAO {

    private final String QUERY_ALL = "SELECT bloodtest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM bloodtest  join registry t1_1 on bloodtest.idUser = t1_1.id  join registry t1_2 on bloodtest.idAdmin = t1_2.id WHERE bloodtest.idAdmin=t1_2.id;";
    private final String QUERY_ALL_ADMIN = "SELECT bloodtest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM bloodtest  join registry t1_1 on bloodtest.idUser = t1_1.id  join registry t1_2 on bloodtest.idAdmin = t1_2.id WHERE bloodtest.idAdmin=?";
    private final String QUERY_ALL_USER = "SELECT bloodtest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM bloodtest  join registry t1_1 on bloodtest.idUser = t1_1.id  join registry t1_2 on bloodtest.idAdmin = t1_2.id WHERE bloodtest.idUser=?";
    private final String QUERY_ALL_ADMIN_CHECKED = "SELECT * FROM bloodTest WHERE idAdmin=? AND isChecked=false";
    private final String QUERY_VALIDATION_ADMIN = "UPDATE bloodtest SET isChecked=? WHERE id=?";
    private final String QUERY_CREATE = "INSERT INTO bloodtest (redBloodCell, whiteBloodCell, platelets,hemoglobin,idAdmin,idUser,dataInsert) VALUES (?,?,?,?,?,?,?)";
    private final String QUERY_READ = "SELECT bloodtest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM bloodtest  join registry t1_1 on bloodtest.idUser = t1_1.id  join registry t1_2 on bloodtest.idAdmin = t1_2.id WHERE bloodtest.id=?";
    private final String QUERY_READ_USER = "SELECT bloodtest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM bloodtest  join registry t1_1 on bloodtest.idUser = t1_1.id  join registry t1_2 on bloodtest.idAdmin = t1_2.id WHERE bloodtest.id=? and bloodtest.idUser=?";
    private final String QUERY_READ_ADMIN = "SELECT bloodtest.*, t1_1.surname as patienceSurname, t1_1.name as patienceName, t1_2.surname as doctorSurname, t1_2.name doctorName FROM bloodtest  join registry t1_1 on bloodtest.idUser = t1_1.id  join registry t1_2 on bloodtest.idAdmin = t1_2.id WHERE bloodtest.id=? and bloodtest.idAdmin=?";
    private final String QUERY_UPDATE = "UPDATE bloodtest SET redBloodCell=?, whiteBloodCell=?, platelets=?,hemoglobin=?, dataInsert=? WHERE id=? AND isChecked=false";
    private final String QUERY_DELETE = "DELETE FROM bloodtest WHERE id=?";

    public BloodTestDAO() {
    }

    public boolean insert(BloodTest BloodTestToInsert) {
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_CREATE);
            preparedStatement.setFloat(1, BloodTestToInsert.getRedBloodCell());
            preparedStatement.setFloat(2, BloodTestToInsert.getWhiteBloodCell());
            preparedStatement.setFloat(3, BloodTestToInsert.getPlatelets());
            preparedStatement.setFloat(4, BloodTestToInsert.getHemoglobin());
            preparedStatement.setInt(5, BloodTestToInsert.getIdAdmin());
            preparedStatement.setInt(6, BloodTestToInsert.getIdUser());
            preparedStatement.setString(7,BloodTestToInsert.getDataInsert());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_CREATE);
            e.printStackTrace();
            return false;
        }

    }
    public BloodTest read(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
            String dataInsert, doctor, patience;
            Integer idAdmin, idUser;
            Boolean isChecked;

            redBloodCell = resultSet.getFloat("redBloodCell");
            whiteBloodCell = resultSet.getFloat("whiteBloodCell");
            platelets = resultSet.getFloat("platelets");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idAdmin = resultSet.getInt("idAdmin");
            idUser = resultSet.getInt("idUser");
            isChecked= resultSet.getBoolean("isChecked");
            dataInsert = resultSet.getString("dataInsert");
            doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
            patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

            BloodTest test = new BloodTest(redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked,dataInsert,doctor,patience);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_READ);
            e.printStackTrace();
            return null;
        }
    }

    public BloodTest readUser(int id, int idUser) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_USER);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            String dataInsert, doctor, patience;
            Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
            Integer idAdmin;
            Boolean isChecked;

            redBloodCell = resultSet.getFloat("redBloodCell");
            whiteBloodCell = resultSet.getFloat("whiteBloodCell");
            platelets = resultSet.getFloat("platelets");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idAdmin = resultSet.getInt("idAdmin");
            isChecked= resultSet.getBoolean("isChecked");
            dataInsert= resultSet.getString("dataInsert");
            doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
            patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

            BloodTest test = new BloodTest(redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked,dataInsert, doctor, patience);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            e.printStackTrace();
            return null;
        }
    }


    public boolean update(BloodTest bloodTestToUpdate) {
        Connection connection = ConnectionSingleton.getInstance();

        if (bloodTestToUpdate.getId() == 0)
            return false;

        BloodTest bloodTestRead = read(bloodTestToUpdate.getId());



        if (!bloodTestRead.equals(bloodTestToUpdate)) {
            try {
                if (bloodTestToUpdate.getRedBloodCell() == null || bloodTestToUpdate.getRedBloodCell().equals("")) {
                    bloodTestToUpdate.setRedBloodCell(bloodTestRead.getRedBloodCell());
                }
                if (bloodTestToUpdate.getWhiteBloodCell() == null || bloodTestToUpdate.getWhiteBloodCell().equals("")) {
                    bloodTestToUpdate.setWhiteBloodCell(bloodTestRead.getWhiteBloodCell());
                }
                if (bloodTestToUpdate.getPlatelets() == null || bloodTestToUpdate.getPlatelets().equals("")) {
                    bloodTestToUpdate.setPlatelets(bloodTestRead.getPlatelets());
                }
                if (bloodTestToUpdate.getHemoglobin() == null || bloodTestToUpdate.getHemoglobin().equals("")) {
                    bloodTestToUpdate.setHemoglobin(bloodTestRead.getHemoglobin());
                }
                if (bloodTestToUpdate.getIdAdmin() == null || bloodTestToUpdate.getIdAdmin().equals("")) {
                    bloodTestToUpdate.setIdAdmin(bloodTestRead.getIdAdmin());
                }
                if (bloodTestToUpdate.getIdUser() == null || bloodTestToUpdate.getIdUser().equals("")) {
                    bloodTestToUpdate.setIdUser(bloodTestRead.getIdUser());
                }
                if (bloodTestToUpdate.getChecked() == null || bloodTestToUpdate.getChecked().equals("")) {
                    bloodTestToUpdate.setChecked(bloodTestRead.getChecked());
                }
                if (bloodTestToUpdate.getDataInsert() == null || bloodTestToUpdate.getDataInsert().equals("")) {
                    bloodTestToUpdate.setDataInsert(bloodTestRead.getDataInsert());
                }


                PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(QUERY_UPDATE);
                preparedStatement.setFloat(1, bloodTestToUpdate.getRedBloodCell());
                preparedStatement.setFloat(2, bloodTestToUpdate.getWhiteBloodCell());
                preparedStatement.setFloat(3, bloodTestToUpdate.getPlatelets());
                preparedStatement.setFloat(4, bloodTestToUpdate.getHemoglobin());
                preparedStatement.setString(5, bloodTestToUpdate.getDataInsert());
                preparedStatement.setInt(6, bloodTestToUpdate.getId());


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
    public List<BloodTest> getAll() {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(QUERY_ALL);
            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                String dataInsert,doctor, patience;
                Integer idAdmin, idUser, id;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idAdmin = resultSet.getInt("idAdmin");
                idUser = resultSet.getInt("idUser");
                isChecked= resultSet.getBoolean("isChecked");
                id= resultSet.getInt("id");
                dataInsert = resultSet.getString("dataInsert");
                doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
                patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");


                test = new BloodTest(id, redBloodCell, whiteBloodCell, platelets, hemoglobin, idAdmin, idUser, isChecked, dataInsert, doctor, patience);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }

    public List<BloodTest> getAll(int idAdmin) {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_ADMIN);
            preparedStatement.setInt(1, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();

            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                String dataInsert,doctor, patience;
                Integer id, idUser;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idUser = resultSet.getInt("idUser");
                isChecked= resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");
                dataInsert = resultSet.getString("dataInsert");
                doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
                patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

                test = new BloodTest(id,redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked,dataInsert, doctor,patience);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_ADMIN);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }

    public List<BloodTest> getAllUser (int idUser) {
        List<BloodTest> bloodTestList = new ArrayList<>();
        Connection connection = ConnectionSingleton.getInstance();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_ALL_USER);
            preparedStatement.setInt(1, idUser);
            ResultSet resultSet = preparedStatement.executeQuery();

            BloodTest test;
            while (resultSet.next()) {
                Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
                String dataInsert, doctor, patience;
                Integer id, idAdmin;
                Boolean isChecked;

                redBloodCell = resultSet.getFloat("redBloodCell");
                whiteBloodCell = resultSet.getFloat("whiteBloodCell");
                platelets = resultSet.getFloat("platelets");
                hemoglobin = resultSet.getFloat("hemoglobin");
                idAdmin = resultSet.getInt("idAdmin");
                isChecked= resultSet.getBoolean("isChecked");
                id = resultSet.getInt("id");
                dataInsert = resultSet.getString("dataInsert");
                doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
                patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

                test = new BloodTest(id,redBloodCell,whiteBloodCell,platelets,hemoglobin,idAdmin,idUser,isChecked,dataInsert, doctor, patience);
                bloodTestList.add(test);
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_ALL_USER);
            e.printStackTrace();
            return null;
        }
        return bloodTestList;
    }

    public boolean delete(int id) {
        Connection connection = ConnectionSingleton.getInstance();
        BloodTest bloodTestRead = read(id);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_DELETE);
            preparedStatement.setInt(1, id);
            int n = preparedStatement.executeUpdate();
            if (n != 0) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_DELETE);
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public BloodTest readAdmin(int id, int idAdmin) {
        Connection connection = ConnectionSingleton.getInstance();
        try {

            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_READ_ADMIN);
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, idAdmin);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            Float redBloodCell, whiteBloodCell, platelets, hemoglobin;
            Integer idUser;
            Boolean isChecked;
            String dataInsert, doctor, patience;

            redBloodCell = resultSet.getFloat("redBloodCell");
            whiteBloodCell = resultSet.getFloat("whiteBloodCell");
            platelets = resultSet.getFloat("platelets");
            hemoglobin = resultSet.getFloat("hemoglobin");
            idUser = resultSet.getInt("idUser");
            isChecked = resultSet.getBoolean("isChecked");
            dataInsert = resultSet.getString("dataInsert");
            doctor = resultSet.getString("doctorSurname") + " " + resultSet.getString("doctorName");
            patience = resultSet.getString("patienceSurname") + " " + resultSet.getString("patienceName");

            BloodTest test = new BloodTest(redBloodCell, whiteBloodCell, platelets, hemoglobin, idAdmin, idUser, isChecked, dataInsert,doctor, patience);
            test.setId(resultSet.getInt("id"));

            return test;
        } catch (SQLException e) {
            System.out.println("Hai inserito un numero di referto non esistente!");
            e.printStackTrace();
            return null;
        }
    }
    public boolean check(int id){
        Connection connection = ConnectionSingleton.getInstance();
        BloodTest bloodTestRead = read(id);

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

    }}