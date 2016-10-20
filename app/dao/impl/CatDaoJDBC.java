package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import dao.interfaces.CatDao;
import models.Cat;
import models.CatEntity;
import play.Logger;
import play.db.jpa.JPA;

public class CatDaoJDBC implements CatDao {

	private static final String USER = "postgres";
	private static final String PASSWORD = "postgres";
	private static final String PORT = "5432";
	private static final String SERVER = "localhost";
	private static final String DATABASE = "catabase";
	private static final String DBMS = "postgresql";

	Connection conn = null;
	
	public CatDaoJDBC() {
		try {
			conn = getConnection();
		} catch (SQLException e) {
			Logger.info("SQLException during database connection");
		}
	}
	
	private Connection getConnection() throws SQLException {
	    Connection conn = null;
	    Properties connectionProps = new Properties();
	    connectionProps.put("user", USER);
	    connectionProps.put("password", PASSWORD);
        conn = DriverManager.getConnection("jdbc:" + DBMS + "://" + SERVER + ":" + PORT + "/" + DATABASE, connectionProps);
	    return conn;
	}
	
	@Override
	public boolean saveCat(Cat cat) {
		if (cat == null) {
			return false;
		}
    	try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate("Insert into cats(email_, password_, name_) values ('" + cat.getEmail() + "', '" + cat.getPassword() + "', '" + cat.getName() + "');");
			stmt.close();
			return true;
    	} catch (SQLException e) {
			Logger.info("SQLException when saving a cat " + cat.getEmail() + e.getMessage());
			return false;
		}
	}

	@Override
	public Cat getCatById(String id) {
		if (id == null) {
			return null;
		}
		List<Cat> cats = new ArrayList<Cat>();
		try {
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(
	        		"select * from cats where id_ = '" + id + "';");
	        while (rs.next()) {
		        Cat cat = new Cat();
	            cat.setEmail(rs.getString("email_"));
	            cat.setId(rs.getLong("id_"));
	            cat.setName(rs.getString("name_"));
	            cat.setPassword(rs.getString("password_"));
	            cats.add(cat);
	            break;
	        }
	        stmt.close();
		} catch (SQLException e) {
			Logger.info("SQLException when getting a cat by id " + id);
			return null;
		}
		if (cats.size() == 0) {
			return null;
		} else {
			return cats.get(0);
		}
	}

	@Override
	public Cat getCatByEmail(String email) {
		if (email == null) {
			return null;
		}
		List<Cat> cats = new ArrayList<Cat>();
		try {
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(
	        		"select * from cats where email_ = '" + email + "';");
	        while (rs.next()) {
		        Cat cat = new Cat();
	            cat.setEmail(rs.getString("email_"));
	            cat.setId(rs.getLong("id_"));
	            cat.setName(rs.getString("name_"));
	            cat.setPassword(rs.getString("password_"));
	            cats.add(cat);
	            break;
	        }
	        stmt.close();
		} catch (SQLException e) {
			Logger.info("SQLException when getting a cat by email " + email);
			return null;
		}
		if (cats.size() == 0) {
			return null;
		} else {
			return cats.get(0);
		}
	}

	@Override
	public List<Cat> getAllCats() {
		List<Cat> cats = new ArrayList<Cat>();
		try {
			Statement stmt = conn.createStatement();
	        ResultSet rs = stmt.executeQuery(
	        		"select * from cats");
	        while (rs.next()) {
		        Cat cat = new Cat();
	            cat.setEmail(rs.getString("email_"));
	            cat.setId(rs.getLong("id_"));
	            cat.setName(rs.getString("name_"));
	            cat.setPassword(rs.getString("password_"));
	            cats.add(cat);
	        }
	        stmt.close();
		} catch (SQLException e) {
			Logger.info("SQLException when getting all cats");
			return null;
		}
		return cats;
	}
}
