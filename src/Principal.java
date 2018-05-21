import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Principal {
	/*
	 * Declaración de variables globales
	 */
	static int cantidadMaximaCaracteres = 127;
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
			System.err.println("Ingrese una opción valida");
			jugar();
		}

		if (tamanio % 2 == 0) {
			cantidadCaracteres = (tamanio * tamanio) /2; 
			if (cantidadCaracteres <= cantidadMaximaCaracteres) {
				String matriz[][] = new String[tamanio][tamanio];
				// cargar(matriz, cantidadCaracteres);
				imprimir(prueba(cantidadCaracteres));
			}else {
				System.out.flush();
				System.err.println("El tamaño del tablero no es valido");
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
		 * Metodo que permite seleccionar las opciones del menú
		 */
		int opcion = 0;
		System.out.println("¡Bienvenido al juego concéntrese!\n Seleccione: \n 1.Jugar \n 2.Salir ");
		Scanner scanner = new Scanner(System.in);
		String ingreso_opcion = scanner.nextLine();

		try {
			opcion = Integer.parseInt(ingreso_opcion);
		} catch (Exception e) {
			System.out.flush();
			System.err.println("Ingrese una opción valida");
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
			System.err.println("Ingrese una opción valida\n");
			menu();
		}
	}

	public static String[][] cargar(String matriz[][], int cantidadCaracteres) {

		return matriz;

	}

	public static ArrayList<Character> prueba(int cantidadCaracteres) {		
		
		boolean numeroIgual = false;
		char caracterAleatorio;
		int numeroAleatorio, contador = 0;
		boolean encontrado = false;
		ArrayList<Character> letras = new ArrayList<Character>();		
		ArrayList<Integer> posiciones = new ArrayList<Integer>();
	
		while (encontrado == false) {			
			numeroAleatorio = random.nextInt(cantidadMaximaCaracteres);			
			if (posiciones.size()>0) {
				for (int i = 0; i < posiciones.size(); i++) {
					if(numeroAleatorio == posiciones.get(i)) {
						numeroIgual = true;
					}
				}
			}			
			if(numeroIgual==false) {				
				posiciones.add(numeroAleatorio);							
				caracterAleatorio=(char)numeroAleatorio;
				letras.add(caracterAleatorio);
				contador ++;
			}else {
				numeroIgual = false;
			}			
			if(cantidadCaracteres==contador) {
				encontrado = true;
			}

		}
		return letras;
	}
	public static void imprimir(ArrayList<Character>letras) {
         for (int i = 0; i < letras.size(); i++) {
			System.out.println(letras.get(i));
		}
	}

}
