package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class ProductoMenuTest {
	
	private ProductoMenu producto;
	
	@BeforeEach
	public void setUp() {
		producto = new ProductoMenu("corral", 14000);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("corral", producto.getNombre(), "El nombre del producto deberia ser 'corral'");
	}
	
	@Test 
	public void testGetPrecioBase() {
		assertEquals(14000, producto.getPrecio(), "El precio del producto deberia ser '14000'");
	}
	
	@Test 
	public void testGenerarTectoFactura() {
		String esperado = "corral\n            14000\n";
		assertEquals(esperado, producto.generarTextoFactura(), "El texto gnerado no es el esperado.");
	} 

}