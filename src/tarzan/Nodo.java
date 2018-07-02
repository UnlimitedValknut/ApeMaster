package tarzan;

/**
 * Clase que administra un nodo. <br>
 */
public class Nodo {
	/**
	 * Posición X del árbol. <br>
	 */
	private int posicionX;
	/**
	 * Posición Y del árbol. <br>
	 */
	private int posicionY;
	private int numeroNodo;

	/**
	 * Carga un nodo que representa la posición de un árbol. <br>
	 *
	 * @param posicionX
	 *            Posición X del árbol. <br>
	 * @param posicionY
	 *            Posición Y del árbol. <br>
	 */
	public Nodo(final int posicionX, final int posicionY, final int numeroNodo) {
		this.posicionX = posicionX;
		this.posicionY = posicionY;
		this.numeroNodo = numeroNodo;
	}

	/**
	 * Devuelve la posición X del árbol. <br>
	 *
	 * @return Posición X del árbol <br>
	 */
	public int getPosicionX() {
		return posicionX;
	}

	/**
	 * Devuelve la posición Y del árbol. <br>
	 *
	 * @return Posición Y del árbol <br>
	 */
	public int getPosicionY() {
		return posicionY;
	}

	public int getNumeroNodo() {
		return this.numeroNodo;
	}
}
