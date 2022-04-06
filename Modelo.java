package examenADConectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Modelo {

	Connection con;

	public Modelo() {
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

	public Producto getProducto(String nombre) {
		openConnection();
		Producto producto = null;
		String sql = "select * from producto";
		try {
			Statement statement = con.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				producto = new Producto();
				producto.setDescripcion(resultset.getString("descripcion"));
				producto.setNombre(resultset.getString("nombre"));
				producto.setPrecio(resultset.getInt("precio"));
				producto.setId(resultset.getLong("id"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return producto;
	}
	
	public void addProducto(Producto producto) {
		openConnection();
		String sql = "insert into producto (id, nombre, descripcion, precio) values (?,?,?,?)";
		try {
			PreparedStatement prepared = con.prepareStatement(sql);
			prepared.setLong(1, producto.getId());
			prepared.setString(2, producto.getNombre());
			prepared.setString(3, producto.getDescripcion());
			prepared.setInt(4, producto.getPrecio());
			prepared.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean updateProducto(Producto producto) {
		openConnection();
		Boolean updateCompleted = false;
		int resultado = -1;
		String sql = "update producto set precio=" + producto.getPrecio() + ", `descripcion`='" + producto.getDescripcion() + "' where id like " + producto.getId();
//		UPDATE `producto` SET `id`='[value-1]',`nombre`='[value-2]',`descripcion`='[value-3]',`precio`='[value-4]' WHERE 1
		try {
			Statement statement = con.createStatement();
			resultado = statement.executeUpdate(sql);
//			PreparedStatement prepared = con.prepareStatement(sql);
//			prepared.setInt(1, producto.getPrecio());
//			prepared.setString(2, producto.getDescripcion());
//			prepared.setLong(3, producto.getId());			
//			resultado = prepared.executeUpdate(sql);			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (resultado == -1) {
			updateCompleted = false;
		} else if(resultado == 1) {
			updateCompleted = true;
		}
		return updateCompleted;
	}
	
	public List<Producto> getListaProductos(Producto producto) {
		openConnection();
		List<Producto> productos = new ArrayList<>();
		String sql = "select * from producto ";
		if (producto.getNombre() != null) {
			sql += "where nombre like '" + producto.getNombre() + "' ";
		}
		if (producto.getPrecio() != 0) {
			if(sql.contains("where")) {
				sql += " and precio like '" + producto.getPrecio() + "'";
			} else
				sql += " where precio like '" + producto.getPrecio() + "'";
		}
		
		try {
			Statement statement = con.createStatement();
			ResultSet resultset = statement.executeQuery(sql);
			while (resultset.next()) {
				Producto producto2 = new Producto();
				producto2.setDescripcion(resultset.getString("descripcion"));
				producto2.setNombre(resultset.getString("nombre"));
				producto2.setPrecio(resultset.getInt("precio"));
				producto2.setId(resultset.getLong("id"));
				
				productos.add(producto2);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return productos;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
