package model;

public class Lunch {

	/*Contador estatico que empieza en 1*/
	private static int countLunch = 1;
	
	
	private String idLunch;

	
	/*Cada vez que se crea un almuerzo va a tener de prefijo una M, y el contador que aumenta en uno dependiendo
	 * al numero de instancias de la clase creadas
	 * */
	public Lunch() {
		this.idLunch = "M" + countLunch++;
	}

	public String getIdLunch() {
		return idLunch;
	}

	public void setIdLunch(String idLunch) {
		this.idLunch = idLunch;
	}
	
	public static void initalizedCountLunch() {
		 countLunch = 1;
	}
	
	
	

}
