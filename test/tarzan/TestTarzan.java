package tarzan;

import java.io.File;

import org.junit.Test;

public class TestTarzan {
	private static final String PATHIN = "Preparacion de Prueba/Lote de Prueba/Entrada/";
	private static final String PATHOUT = "Ejecuccion de Prueba/Salida Obtenida/";

	@Test
	public void testEnunciado() {
		Tarzan tarzan = new Tarzan(new File(PATHIN + "00_enunciado.in"), new File(PATHOUT + "00_enunciado.out"));
		tarzan.resolver();
//		tarzan.grabarArchivo();
	}
}
