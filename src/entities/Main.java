package entities;

public class Main {

	public static void main(String[] args) {
		
		//prueba ingresar direccion por teclado 
		boolean [] dir = {true, false, true, false};
		Casilla casilla = new CasillaParalizar(0,0,dir,3);
		casilla.mostrarDireccionesPosibles();
		System.out.println(casilla.ingresarDireccion());

	}

}
