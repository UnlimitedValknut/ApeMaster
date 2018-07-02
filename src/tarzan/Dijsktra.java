package tarzan;

import java.util.List;
import java.util.Stack;

/**
 * Clase que busca el camino de menor costo de un nodo hacia el resto de los
 * nodos. <br>
 * El grafo puede ser dirigido o no. El grafo debe ser ponderado. <br>
 */
public class Dijsktra {
	/**
	 * Matriz de adyacencia del grafo. <br>
	 */
	private int[][] matrizAdyacente;
	/**
	 * Nodos ya visitados. <br>
	 */
	private boolean[] visitados;
	/**
	 * Costo para llegar a un nodo. <br>
	 */
	private int[] costos;
	/**
	 * Nodo inicial. <br>
	 */
	private int nodoInicio;
	/**
	 * Cantidad de nodos del grafo. <br>
	 */
	private int cantidadNodos;
	/**
	 * Ruta para llegar a un nodo. <br>
	 */
	private int[] ruta;

	/**
	 * Resuelve el camino de Dijkstra de un grafo a partir de un nodo de inicio.
	 * <br>
	 *
	 * @param matrizAdyacencia
	 *            Matriz de adyacencia del grafo. <br>
	 * @param nodoInicio
	 *            Nodo inicial. <br>
	 */
	public Dijsktra(final int[][] matrizAdyacencia, final int nodoInicio) {
		this.matrizAdyacente = matrizAdyacencia;
		this.nodoInicio = nodoInicio - 1;
		this.cantidadNodos = matrizAdyacencia.length;
		this.visitados = new boolean[this.cantidadNodos];
		this.costos = new int[this.cantidadNodos];
		this.ruta = new int[this.cantidadNodos];
		this.inicializarEstructurasInternas();
		this.visitados[this.nodoInicio] = true;
		this.costos[this.nodoInicio] = Integer.MAX_VALUE;
		int i = 0;
		int siguiente = minimo();
		while (i < this.cantidadNodos && siguiente != -1) {
			this.visitados[siguiente] = true;
			for (int adyacente = 0; adyacente < this.cantidadNodos; adyacente++) {
				if (!this.visitados[adyacente]) {
					if (this.matrizAdyacente[siguiente][adyacente] != Integer.MAX_VALUE && (costos[siguiente]
							+ this.matrizAdyacente[siguiente][adyacente]) < this.costos[adyacente]) {
						this.costos[adyacente] = this.costos[siguiente] + this.matrizAdyacente[siguiente][adyacente];
						this.ruta[adyacente] = siguiente;
					}
				}
			}
			siguiente = minimo();
		}
	}

	/**
	 * Encuentra el índice del valor mínimo de un nodo no visitado. <br>
	 *
	 * @return Índice del valor mínimo. <br>
	 *         De no existir camino a los nodos restantes devuelve -1. <br>
	 */
	private int minimo() {
		int valorMinimo = Integer.MAX_VALUE;
		int indiceMinimo = -1;
		for (int i = 0; i < cantidadNodos; i++) {
			if (!visitados[i] && costos[i] < valorMinimo) {
				valorMinimo = costos[i];
				indiceMinimo = i;
			}
		}
		return indiceMinimo;
	}

	/**
	 * Inicializa los vectores de Dijkstra. <br>
	 */
	private void inicializarEstructurasInternas() {
		for (int i = 0; i < cantidadNodos; i++) {
			if (this.matrizAdyacente[this.nodoInicio][i] > 50) {
				this.costos[i] = Integer.MAX_VALUE;
			} else {
				this.costos[i] = this.matrizAdyacente[this.nodoInicio][i];
			}
			System.out.println(this.costos[i]);
			this.ruta[i] = -1;
		}
	}

	/**
	 * Muestra el costo mínimo de un nodo para llegar al resto de los nodos.
	 * <br>
	 */
	public void resultado() {
		System.out.println("Costo para llegar los nodos desde " + (this.nodoInicio + 1) + ":");
		for (int i = 0; i < this.cantidadNodos; i++) {
			if (this.costos[i] != Integer.MAX_VALUE) {
				System.out.println((i + 1) + "\t" + this.costos[i]);
			} else {
				System.out.println((i + 1) + "\t" + '\u221e');
			}

		}
	}

	/**
	 * Muestra la ruta a tomar para llegar a un nodo .<br>
	 *
	 * @param destino
	 *            Nodo a llegar. <br>
	 */
	public void ruta(final int destino) {
		Stack<Integer> pila = new Stack<Integer>();
		int anterior = destino - 1;
		if (this.costos[anterior] != Integer.MAX_VALUE) {
			System.out.print((this.nodoInicio + 1));
			while (ruta[anterior] != -1) {
				pila.push(ruta[anterior]);
				anterior = ruta[anterior];
			}
			while (!pila.isEmpty()) {
				System.out.print(" -> " + (pila.pop() + 1));
			}
			System.out.print(" -> " + destino + "\n");
			System.out.println("Peso de la ruta: " + this.costos[destino - 1]);
		} else {
			System.out.println("No existe camino entre " + (this.nodoInicio + 1) + " y " + destino + ".");
		}
	}
}