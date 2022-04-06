package examenADConectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EjecutaScript {

	Connection con;
	public EjecutaScript() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Connection openConnection() {
		String url = "jdbc:mysql://localhost:3306/exa2ad";
		String user = "exa2ad";
		String password = "exa2adpass";
		
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	public void creaTablaCliente() {
		openConnection();
		String sql = "create table cliente (id int primary key, nombre varchar(50))";
		try {
			Statement statement = con.createStatement();
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
