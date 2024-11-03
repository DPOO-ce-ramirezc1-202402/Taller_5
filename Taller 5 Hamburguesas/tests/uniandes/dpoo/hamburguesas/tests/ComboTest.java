package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import uniandes.dpoo.hamburguesas.mundo.Combo;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	
	private ProductoMenu hamburguesa;
	private ProductoMenu papas;
	private ProductoMenu bebida;
	private ArrayList<ProductoMenu> itemsCombo;
	private Combo combo;
	
	@BeforeEach
	public void setUp() {
		hamburguesa = new ProductoMenu("corral", 14000);
		papas = new ProductoMenu("papas medianas", 5500);
		bebida = new ProductoMenu("gaseosa", 5000);
		
		itemsCombo = new ArrayList<>();
		itemsCombo.add(hamburguesa);
		itemsCombo.add(papas);
		itemsCombo.add(bebida);
		
		combo = new Combo("combo corral", 0.9, itemsCombo);
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("combo corral", combo.getNombre(), "El combo debria ser 'combo corral'.");
	}
	
	@Test
	public void testGetPrecio() {
		int esperado = (int)(24500 * 0.9);
		assertEquals(esperado, combo.getPrecio(), "El precio del combo no se calculo correctamente.");
	}
	
	@Test
	public void testGenerarFactura() {
		String esperado = 
				"Combo combo corral\n" + 
		        " Descuento: 0.9\n" +
				"            22050\n";
		assertEquals(esperado, combo.generarTextoFactura(), "El texto de la factura no coincide para el combo.");
	}
}
