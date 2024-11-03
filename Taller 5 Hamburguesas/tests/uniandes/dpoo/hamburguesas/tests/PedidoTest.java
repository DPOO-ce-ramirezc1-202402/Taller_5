package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Scanner;

public class PedidoTest {
	
	private Pedido pedido;
	private Producto producto1;
	private Producto producto2;
	
	@BeforeEach
	public void setUp() throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		Field numeroPedidosField = Pedido.class.getDeclaredField("numeroPedidos");
        numeroPedidosField.setAccessible(true);
        numeroPedidosField.setInt(null, 0);
		
		pedido = new Pedido("Jose Ovalle", "Calle 145 #21-77");
		producto1 = new ProductoMenu("corral", 14000);
		producto2 = new ProductoMenu("papas medianas", 5500);
	}
	
	@Test
	public void testGetIdPedido() {
		assertEquals(0, pedido.getIdPedido(), "El primer id deberia ser 0.");
		
		Pedido otroPedido = new Pedido("Carlos Ramirez", "Calle 146");
		
		assertEquals(1, otroPedido.getIdPedido(), "El segundo id deberia ser 1.");
	}
	
	@Test
	public void testGetNombre() {
		assertEquals("Jose Ovalle", pedido.getNombreCliente(), "El nombre del cliente deberia ser 'Jose Ovalle'.");
	}
	
	@Test
	public void testAgregarProducto() {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		
		assertEquals(2, pedido.getCantidadProductos(), "El numero de productos deberia ser 2.");
		
	}
	
	@Test
	public void testGetPrecioTotalPedido() {
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		int precioNeto = 14000 + 5500;
		int precioTotalEsperado = precioNeto + (int) (precioNeto * 0.19);
		assertEquals(precioTotalEsperado, pedido.getPrecioTotalPedido(), "El precio total del pedido no coincide.");
	}
	
	@Test
	public void testGenerarFactura() {
		
		int precioNeto = 14000 + 5500;
		int iva = (int) (precioNeto * 0.19);
		int precioTotal = precioNeto + iva;
		
		pedido.agregarProducto(producto1);
		pedido.agregarProducto(producto2);
		
		String esperado = 
				"Cliente: Jose Ovalle\n" +
				"Dirección: Calle 145 #21-77\n" + 
				"----------------\n" + 
				producto1.generarTextoFactura() + 
				producto2.generarTextoFactura() +
				"----------------\n" + 
				"Precio Neto:  " + precioNeto + "\n" +
				"IVA:          " + iva + "\n" + 
				"Precio Total: " + precioTotal + "\n";
		
		assertEquals(esperado, pedido.generarTextoFactura(), "La factura generada no coincide.");
					
	}
}
