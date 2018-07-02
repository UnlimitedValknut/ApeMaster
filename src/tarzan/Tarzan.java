package tarzan;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Tarzan extends EjercicioOIA {
	/**
	 * Matriz de adyacencia del grafo. <br>
	 */
	private int[][] matrizAdyacencia;
	/**
	 * Cantidad de nodos del grafo. <br>
	 */
	private int cantidadNodos = 0;
	/**
	 * Recorrido realizado. <br>
	 */
	private List<Integer> recorrido;
	/**
	 * Aristas del grafo. <br>
	 */
	private Map<Integer, Nodo> nodos = new HashMap<Integer, Nodo>();

	public Tarzan(final File pathEntrada, final File pathSalida) {
		super(pathEntrada, pathSalida);
		try {
			this.leerArchivo(super.entrada);
		} catch (IOException e) {
			System.out.println("Error abrir archivo.");
			e.printStackTrace();
		}
	}

	private void leerArchivo(final File path) throws IOException {
		try {
			Scanner sc = new Scanner(path);
			int posicionX;
			int posicionY;
			while (sc.hasNextLine()) {
				posicionX = sc.nextInt();
				if (posicionX < -10001 || posicionX > 10001) {
					this.limiteSuperado("posición X arbol" + this.cantidadNodos);
				}
				posicionY = sc.nextInt();
				if (posicionY < -10001 || posicionY > 10001) {
					this.limiteSuperado("posición Y arbol" + this.cantidadNodos);
				}
				this.nodos.put(this.cantidadNodos, new Nodo(posicionX, posicionY, cantidadNodos));
				this.cantidadNodos++;
			}
			sc.close();
			this.matrizAdyacencia = new int[this.cantidadNodos][this.cantidadNodos];

			for (Map.Entry<Integer, Nodo> entryActual : this.nodos.entrySet()) {
				Nodo actual = entryActual.getValue();
//				int posicionActual = entryActual.getKey();
				for (Map.Entry<Integer, Nodo> entryOtro : this.nodos.entrySet()) {
					Nodo otro = entryOtro.getValue();
//					int posicionOtro = entryOtro.getKey();
					this.matrizAdyacencia[actual.getNumeroNodo()][otro.getNumeroNodo()] = (int) Math
							.round(Math.sqrt(Math.pow(otro.getPosicionX() - actual.getPosicionX(), 2)
									+ Math.sqrt(Math.pow(otro.getPosicionY() - actual.getPosicionY(), 2))));
					this.matrizAdyacencia[otro.getNumeroNodo()][actual
							.getNumeroNodo()] = this.matrizAdyacencia[actual.getNumeroNodo()][otro.getNumeroNodo()];
					System.out.println(actual.getNumeroNodo() + " "
							+ this.matrizAdyacencia[otro.getNumeroNodo()][actual.getNumeroNodo()]);
				}

				// for (Map.Entry<Integer, Nodo> entryActual :
				// this.nodos.entrySet()) {
				// Nodo actual = entryActual.getValue();
				// int posicionActual = entryActual.getKey();
				// for (Map.Entry<Integer, Nodo> entryOtro :
				// this.nodos.entrySet()) {
				// Nodo otro = entryOtro.getValue();
				// int posicionOtro = entryOtro.getKey();
				// this.matrizAdyacencia[posicionActual][posicionOtro] = (int)
				// Math
				// .round(Math.sqrt(Math.pow(otro.getPosicionX() -
				// actual.getPosicionX(), 2)
				// + Math.sqrt(Math.pow(otro.getPosicionY() -
				// actual.getPosicionY(), 2))));
				// this.matrizAdyacencia[posicionOtro][posicionActual] =
				// this.matrizAdyacencia[posicionActual][posicionOtro];
				// System.out.println(otro.getPosicionX() + " " +
				// actual.getPosicionY() + " "
				// + this.matrizAdyacencia[posicionOtro][posicionActual]);
				// }
			}
		} catch (FileNotFoundException e) {
			throw new IOException(e.getMessage());
		}
	}

	/**
	 * Indica que supera el límite aceptado. <br>
	 */
	private void limiteSuperado(final String text) {
		throw new ArithmeticException("Cantidad superada de " + text + ".");
	}

	@Override
	public void resolver() {
		Dijsktra dijsktra = new Dijsktra(this.matrizAdyacencia, 1);
		// this.recorrido = dijsktra.obtenerRuta(this.cantidadNodos);
		dijsktra.resultado();
		dijsktra.ruta(7);
	}
}
