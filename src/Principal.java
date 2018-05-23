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
	static int jugador1 = 0;
	static int jugador2 = 0;
	static int contadorFilaColumna = 0;

	public static void main(String[] args) {
		menu();
	}

	public static void jugar() {
		/*
		 * Metodo que permite interactuar con las opciones del Jugador.
		 */
		
		int tamanio = 0;
		int cantidadCaracteres = 0;

		System.out.println("Seleccione el tama�o del tablero: ");
		Scanner scanner = new Scanner(System.in);
		String tamanioTablero = scanner.nextLine();
		try {
			tamanio = Integer.parseInt(tamanioTablero);

		} catch (Exception e) {
			System.out.flush();
			System.err.println("Ingrese una opcion valida");
			jugar();
		}

		char matrizJuego[][] = new char[tamanio][tamanio];

		if (tamanio % 2 == 0) {
			cantidadCaracteres = (tamanio * tamanio) / 2;
			if (cantidadCaracteres <= cantidadMaximaCaracteres) {
				char matrizCragada[][] = cargar(tamanio, obtenerCaracteres(cantidadCaracteres));
				matrizJuego = cargarMatrizJuego(matrizJuego);

				matricesInteraccion(matrizJuego, matrizCragada);

			} else {
				System.out.flush();
				System.err.println("El tama�o del tablero no es valido porque supera la cantidad maxima ("
						+ cantidadMaximaCaracteres + ") de caracteres");
				jugar();
			}
		} else {
			System.out.flush();
			System.err.println("El tama�o del tablero no es valido");
			jugar();
		}
	}

	public static void menu() {
		/*
		 * Metodo que permite seleccionar las opciones del menu
		 */
		int opcion = 0;
		System.out.println("�Bienvenido al juego concentrese!\n Seleccione: \n 1.Jugar \n 2.Salir ");
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
		/*
		 * Metodo que permite imprimir una matriz
		 */
		
		for (int filas = 0; filas < matriz.length; filas++) {
			for (int columnas = 0; columnas < matriz.length; columnas++) {
				System.out.print("[ " + matriz[filas][columnas] + " ]");
				if (columnas == (matriz.length - 1)) {
					System.out.print("\n");
				}
			}

		}
	}

	public static char[][] cargarMatrizJuego(char matriz[][]) {
		/*
		 * Metodo que carga la matriz con el simbolo '-' de la siguiente manera [-] 
		 */
		
		for (int filas = 0; filas < matriz.length; filas++) {
			for (int columnas = 0; columnas < matriz.length; columnas++) {
				matriz[filas][columnas] = (char) 45;
			}

		}
		return matriz;
	}

	public static boolean validarMatrizLlena(char matriz[][]) {
		/*
		 * Metodo que permite verificar si todas las parejas han sido descubiertas 
		 */
		
		int contadorCoinciden = 0;

		for (int filas = 0; filas < matriz.length; filas++) {
			for (int columnas = 0; columnas < matriz.length; columnas++) {
				if (matriz[filas][columnas] == (char) 45) {
					contadorCoinciden++;
				}
			}

		}
		if (contadorCoinciden >= 1) {
			return false;
		} else {
			return true;
		}

	}

	public static char[][] cargar(int tamanioTablero, ArrayList<Character> caracteres) {
		/*
		 * Metodo que permite cargar la matriz con los caracteres generados
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
				filas = filas - 1;
				for (int filasContrarias = (filas * 2) + 1; filasContrarias > filas; filasContrarias--) {
					for (int columnasContrarias = (matriz.length - 1); columnasContrarias >= 0; columnasContrarias--) {
						matriz[filasContrarias][columnasContrarias] = caracteres.get(contador);
						contador++;
					}
				}
			}
		}
		return matriz;

	}

	public static void matricesInteraccion(char matrizVacia[][], char matrizConCaracteres[][]) {
		/*
		 * Metodo de interacción entre la matriz que se muestra y la matriz oculta.
		 * Realiza la comparación de las posiciones y llena la matriz que se muestra.
		 * Permite calcular el puntaje de cada uno de los jugadores. 
		 */		
		
		imprimir(matrizVacia);
		char validarCaracter = '\0';
		int fila = 0;
		int columna = 0;
		int Jugador = 1;
		int contadorPosicion = 0;
		int[] posicion;
		int[] posicionInicial = null;
		boolean terminarJuego = false;

		while (terminarJuego == false) {
			posicion = solicitarPosicion(Jugador, matrizConCaracteres.length);
			fila = posicion[0];
			columna = posicion[1];

			if (matrizVacia[fila][columna] == (char) 45) {
				matrizVacia[fila][columna] = matrizConCaracteres[fila][columna];

				if (contadorPosicion == 0) {
					validarCaracter = matrizVacia[fila][columna];
					posicionInicial = posicion;
					contadorPosicion++;
				} else {
					if (posicion[0] == posicionInicial[0] && posicion[1] == posicionInicial[1]) {
						System.err.println("Ingrese una posicion diferente");						
					} else {
						if (validarCaracter == matrizVacia[fila][columna]) {
							if (Jugador == 1) {
								contadorPosicion = 0;
								jugador1++;
								terminarJuego = validarMatrizLlena(matrizVacia);
							} else {
								jugador2++;
								terminarJuego = validarMatrizLlena(matrizVacia);
							}
						} else {
							System.err.println("No coninciden los caracteres");
							contadorPosicion = 0;
							matrizVacia[posicionInicial[0]][posicionInicial[1]] = (char) 45;
							matrizVacia[fila][columna] = (char) 45;
							if (Jugador == 1) {
								Jugador = 2;
							} else {
								Jugador = 1;
							}
						}
					}

				}
			} else {
				System.err.println("Esta posicion ya fue descubierta");
			}

			try {
				Thread.sleep(500);
				imprimir(matrizVacia);
			} catch (Exception e) {
				System.err.println("Ha ocurrido un error con la impresion de la matriz " + e);
			}

		}
		if (jugador1 > jugador2) {
			System.out.println("El ganador es el Jugador 1 con " + jugador1 + " puntos");
		} else if (jugador2 > jugador1) {
			System.out.println("El ganador es el Jugador 2 con " + jugador2 + " puntos");
		} else if (jugador1 == jugador2) {
			System.out.println("Esto es una empate cada jugador posee" + jugador1 + " puntos");
		}
		System.out.println("Fin del juego");

	}

	public static int[] solicitarPosicion(int numeroJugador, int tamanioTablero) {
		/*
		 * Metodo que solicita al usuario la posicion a validar. 
		 */
		
		int filaColumna[] = new int[2];
		boolean terminaTurno = false;

		while (terminaTurno == false) {
			System.out.println("Puntaje J1: "+jugador1+", puntaje J2: "+jugador2);
			if (contadorFilaColumna == 0) {
				System.out.println("Turno Jugador " + numeroJugador);
				System.out.println("Digite la Fila:");
			} else if (contadorFilaColumna == 1) {
				System.out.println("Digite la Columna:");
			}
			Scanner scanner = new Scanner(System.in);
			String filaColumnaEntrada = scanner.nextLine();

			try {
				int valorIngresado = Integer.parseInt(filaColumnaEntrada);
				if (valorIngresado <= tamanioTablero) {
					filaColumna[contadorFilaColumna] = valorIngresado - 1;
					contadorFilaColumna++;
				} else {
					System.err
							.println("Ingrese una opcion valida teniendo en cuenta el tamaño del tablero seleccionado: "
									+ tamanioTablero);
				}
			} catch (Exception e) {
				System.out.flush();
				System.err.println("Ingrese una opcion valida");
				solicitarPosicion(numeroJugador, tamanioTablero);
			}
			if (contadorFilaColumna == 2) {
				contadorFilaColumna = 0;
				terminaTurno = true;
			}
		}
		return filaColumna;
	}
}
