package examenADConectores;

import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		EjecutaScript script = new EjecutaScript();
		Modelo modelo = new Modelo();
		Producto producto = new Producto();
		Producto producto2 = new Producto();
		producto.setNombre("producto");
		List<Producto> productos = new ArrayList<>();
		List<Producto> productosVacio = new ArrayList<>();
		productos = modelo.getListaProductos(producto);
		productosVacio = modelo.getListaProductos(producto2);
		System.out.println("La lista de productos filtrada por el nombre 'producto' es: " + productos);
		System.out.println("La lista de productos sin filtrar es: " + productosVacio);
		
		Producto producto3 = new Producto();
		producto3.setDescripcion("Gráfica 3070");
		producto3.setId(5);
		producto3.setNombre("Tarjeta Gráfica");
		producto3.setPrecio(1700);
//		modelo.addProducto(producto3);
		
		System.out.println(modelo.getProducto(producto3.getNombre()));
		
		producto3.setPrecio(1699);
		producto3.setDescripcion("En rebaja");
		System.out.println(modelo.updateProducto(producto3));
		script.creaTablaCliente();
	}
}
