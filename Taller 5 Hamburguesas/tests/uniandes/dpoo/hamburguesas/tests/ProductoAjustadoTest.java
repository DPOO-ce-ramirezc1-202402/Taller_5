package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;

import java.util.ArrayList;

public class ProductoAjustadoTest {
	
	private ProductoMenu productoBase;
	private ProductoAjustado productoAjustado;
	private Ingrediente lechuga;
	private Ingrediente quesoMozzarella;
	
	@BeforeEach
	public void setUp() {
		productoBase = new ProductoMenu("corral", 14000);
		productoAjustado = new ProductoAjustado(productoBase);
		lechuga = new Ingrediente("lechuga", 1000);
		quesoMozzarella = new Ingrediente("queso mozzarella", 2500); 
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("corral", productoAjustado.getNombre(), "El nombre del producto ajustado debe de ser igual que el del producto base.");
	}
	
	@Test
	public void testGetPrecio() {
		productoAjustado.agregarIngrediente(lechuga);
		productoAjustado.agregarIngrediente(quesoMozzarella);
		
		int esperado = 14000 + 1000 + 2500;
		assertEquals(esperado, productoAjustado.getPrecio(), "El precio debe incluir el costo de los ingredientes que se adicionaron.");
	}
	
	@Test
	public void testGenerarFacturaConAjustes() {
		productoAjustado.agregarIngrediente(lechuga);
		productoAjustado.agregarIngrediente(quesoMozzarella);
		
		String esperado = 
				"corral\n" + 
				"    +lechuga                1000\n" + 
				"    +queso mozzarella\n                2500\n" + 
				"            17500\n";
		
		assertEquals(esperado, productoAjustado.generarTextoFactura(), "La factura no coincide para un productocon ajustes.");
	}
	
	@Test
	public void testGenerarFacturaConAjustesYEliminados() {
		productoAjustado.agregarIngrediente(lechuga);
		productoAjustado.eliminarIngrediente(quesoMozzarella);
		
		String esperado = 
				"corral\n" + 
				"    +lechuga                1000\n" + 
				"    -queso mozzarella\n" + 
				"            15000\n";
		
		assertEquals(esperado, productoAjustado.generarTextoFactura(), "La factura no coincide para un productocon ajustes.");
	}

}
