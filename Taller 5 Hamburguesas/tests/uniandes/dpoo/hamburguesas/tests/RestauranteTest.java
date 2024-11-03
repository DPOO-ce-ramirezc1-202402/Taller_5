package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.excepciones.HamburguesaException;
import uniandes.dpoo.hamburguesas.excepciones.IngredienteRepetidoException;
import uniandes.dpoo.hamburguesas.excepciones.NoHayPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.excepciones.YaHayUnPedidoEnCursoException;
import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Restaurante;

import java.io.File;
import java.io.IOException;

public class RestauranteTest {
	
	private Restaurante restaurante;
	
	@BeforeEach
	public void setUp() {
		restaurante = new Restaurante();
	}
	
	@Test
	public void testIniciarPedido() throws YaHayUnPedidoEnCursoException{
		restaurante.iniciarPedido("Jose Ovalle", "Calle 145 #21-77");
		Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
		assertNotNull(pedidoEnCurso);
		assertEquals("Jose Ovalle", pedidoEnCurso.getNombreCliente());
	}
	
	@Test
	public void testIniciarPedidoEnCurso() {
		assertThrows(YaHayUnPedidoEnCursoException.class, () -> {restaurante.iniciarPedido("Jose Ovalle", "Calle 145 #21-77"); restaurante.iniciarPedido("Miguel Alba", "Calle 165 #21-77"); });
	}
	
	@Test
	public void testCerrarYGuardarPedido() throws YaHayUnPedidoEnCursoException, NoHayPedidoEnCursoException, IOException{
		restaurante.iniciarPedido("Jose Ovalle", "Calle 145 #21-77");
		Pedido pedidoEnCurso = restaurante.getPedidoEnCurso();
		if (pedidoEnCurso == null) {
			throw new NoHayPedidoEnCursoException();
		}
		pedidoEnCurso.guardarFactura(new File("data/factura.txt"));
		pedidoEnCurso = null;
	}
	
	@Test
	public void testCerrarYGuardarPedidoNoEnCurso() {
		assertThrows(NoHayPedidoEnCursoException.class, () -> {restaurante.cerrarYGuardarPedido();});
	}
	
	@Test
	public void testCargarInformacionRestaurante() throws HamburguesaException, IOException {
		File ingredientes = new File("data/ingredientes.txt");
		File menu = new File("data/menu.txt");
		File combos = new File("data/combos.txt");
		
		restaurante.cargarInformacionRestaurante(ingredientes, menu, combos);
		
		assertFalse(restaurante.getIngredientes().isEmpty(), "Los ingredientes se deberian haber guardado");
		assertFalse(restaurante.getMenuBase().isEmpty(), "El menu se deberia haber guardado");
		assertFalse(restaurante.getMenuCombos().isEmpty(), "El menu de combos se deberia haber guardado");
		
		Ingrediente ingrediente1 =restaurante.getIngredientes().get(0);
		assertEquals("lechuga", ingrediente1.getNombre(), "el primer ingrediente deberia ser 'lechuga'");
		assertEquals(1000, ingrediente1.getCostoAdicional(), "el primer ingrediente deberia valer 1000");
	}

}
