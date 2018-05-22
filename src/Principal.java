import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {
	/*
	 * Declaracion de variables globales
	 */
	static int cantidadMaximaCaracteres = 93;
	static Random random = new Random();

	public static void main(String[] args) {
		menu();
	}

	public static void jugar() {
		int tamanio = 0;
		int cantidadCaracteres = 0;

		System.out.println("Seleccione el tamaño del tablero: ");
		Scanner scanner = new Scanner(System.in);
		String tamanioTablero = scanner.nextLine();
		try {
			tamanio = Integer.parseInt(tamanioTablero);

		} catch (Exception e) {
			System.out.flush();
			System.err.println("Ingrese una opcion valida");
			jugar();
		}

		if (tamanio % 2 == 0) {
			cantidadCaracteres = (tamanio * tamanio) / 2;
			if (cantidadCaracteres <= cantidadMaximaCaracteres) {
				cargar(tamanio, obtenerCaracteres(cantidadCaracteres));
				imprimir(cargar(tamanio, obtenerCaracteres(cantidadCaracteres)));
			} else {
				System.out.flush();
				System.err.println("El tamaño del tablero no es valido porque supera la cantidad maxima ("
						+ cantidadMaximaCaracteres + ") de caracteres");
				jugar();
			}
		} else {
			System.out.flush();
			System.err.println("El tamaño del tablero no es valido");
			jugar();
		}
	}

	public static void menu() {
		/*
		 * Metodo que permite seleccionar las opciones del menu
		 */
		int opcion = 0;
		System.out.println("¡Bienvenido al juego concentrese!\n Seleccione: \n 1.Jugar \n 2.Salir ");
		Scanner scanner = new Scanner(System.in);
		String ingreso_opcion = scanner.nextLine();
		try {
			opcion = Integer.parseInt(ingreso_opcion);
		} catch (Exception e) {
			System.out.flush();
			System.err.println("Ingrese una opcion valida");
			menu();
		}

		if (opcion == 1) {
			System.out.flush();
			jugar();
		} else if (opcion == 2) {
			System.out.println("Gracias por Jugar");
			System.exit(0);
		} else {
			System.out.flush();
			System.err.println("Ingrese una opcion valida\n");
			menu();
		}
	}

	public static ArrayList<Character> obtenerCaracteres(int cantidadCaracteres) {
		/*
		 * Metodo que permite crear los caracteres para llenar la matriz, tomando como
		 * base los caracteres imprimibles (33-126) del codigo ASCII
		 * 
		 * Retorna un ArrayList con los caracteres.
		 */
		boolean numeroIgual = false;
		char caracterAleatorio;
		int numeroAleatorio, contador = 0;
		boolean encontrado = false;
		ArrayList<Character> letras = new ArrayList<Character>();
		ArrayList<Integer> posiciones = new ArrayList<Integer>();

		while (encontrado == false) {
			numeroAleatorio = ThreadLocalRandom.current().nextInt(33, 126);
			if (posiciones.size() > 0) {
				for (int i = 0; i < posiciones.size(); i++) {
					if (numeroAleatorio == posiciones.get(i)) {
						numeroIgual = true;
					}
				}
			}
			if (numeroIgual == false) {
				posiciones.add(numeroAleatorio);
				caracterAleatorio = (char) numeroAleatorio;
				letras.add(caracterAleatorio);
				contador++;
			} else {
				numeroIgual = false;
			}
			if (cantidadCaracteres == contador) {
				encontrado = true;
			}

		}
		return letras;
	}

	public static void imprimir(char matriz[][]) {
		for (int filas = 0; filas < matriz.length; filas++) {
			for (int columnas = 0; columnas < matriz.length; columnas++) {
				System.out.print("[ " + matriz[filas][columnas] + "]");
				if (columnas == (matriz.length - 1)) {
					System.out.print("\n");
				}
			}

		}
	}

	public static char[][] cargar(int tamanioTablero, ArrayList<Character> caracteres) {
		/*
		 *  
		 */
		
		char matriz[][] = new char[tamanioTablero][tamanioTablero];
		int tamanioFinalTablero = tamanioTablero / 2;
		int contador = 0, filas = 0;

		for (int ciclosCarga = 0; ciclosCarga < 2; ciclosCarga++) {
			contador = 0;
			if (ciclosCarga == 0) {
				for (int i = 0; i < tamanioFinalTablero; i++) {
					for (int columnas = 0; columnas < tamanioTablero; columnas++) {
						matriz[filas][columnas] = caracteres.get(contador);
						contador++;
					}
					filas++;
	
				}
			} else {
				filas = filas -1;
				for (int filasContrarias = (filas * 2)+1; filasContrarias > filas ; filasContrarias--) {
					for (int columnasContrarias = (matriz.length-1); columnasContrarias >= 0; columnasContrarias--) {
						matriz[filasContrarias][columnasContrarias] = caracteres.get(contador);
						contador++;
					}
				}
			}
		}
		return matriz;

	}
}
