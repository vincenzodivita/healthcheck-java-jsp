package it.contrader.dao;

import java.sql.*;


import it.contrader.enums.Usertype;
import it.contrader.utils.ConnectionSingleton;
import it.contrader.model.User;

/**
 *
 * @author Vittorio
 *
 *Per i dettagli della classe vedi Guida sez 6: DAO
 */
public class LoginDAO {

	private final String QUERY_LOGIN = "SELECT * FROM user WHERE username = ? AND password = ?";
	private final String QUERY_REGISTER = "INSERT INTO user (username, password, usertype) VALUES (?,?,?)";


	public User login (String username, String password) {

		Connection connection = ConnectionSingleton.getInstance();
		try {
			PreparedStatement statement = connection.prepareStatement(QUERY_LOGIN);

			statement.setString(1, username);
			statement.setString(2, password);


			ResultSet resultSet;

			if(statement.executeQuery().next()) {
				resultSet = statement.executeQuery();
				resultSet.next();
				Usertype usertype = Usertype.values()[resultSet.getInt("usertype")];
				int id = resultSet.getInt("id");

				return new User(id, username, password, usertype);
			}

			return null;
		}

		catch (SQLException e) {

			return null;
		}
	}

	public User register (String username, String password, Usertype usertype) {

		Connection connection = ConnectionSingleton.getInstance();
		try {

			PreparedStatement statement = connection.prepareStatement(QUERY_REGISTER, Statement.RETURN_GENERATED_KEYS);

			statement.setString(1, username);
			statement.setString(2, password);
			statement.setInt(3, usertype.ordinal());
			statement.execute();

			ResultSet id = statement.getGeneratedKeys();
			if (id.next()){
				User user = new User(username, password, usertype);
				user.setId(id.getInt(1));
				return user;
			}



			return null;
		}

		catch (SQLException e) {
			System.out.println("Exception durante l'esecuzione della query:\n" + QUERY_REGISTER);
			e.printStackTrace();
			return null;
		}
	}
}
