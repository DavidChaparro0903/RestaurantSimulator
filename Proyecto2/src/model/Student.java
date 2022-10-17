package model;

import constants.Constants;
import tool.Tool;

public class Student {

	private static int countStudent = 1;
	private String idStudent;
	
	/*Tiempo de atención de cada estudiante en el punto de pago*/
	private int attentionTime;
	
	/*Tiempo de desplazamiento de cada estudiante */
	private int displacementTime;

	
	
	/*Cada vez que se crea un estudiante va a tener de prefijo una E, y el contador que aumenta en uno dependiendo
	 * al numero de instancias de la clase creadas
	 * 
	 * se inicializa el tiempo de atención y el tiempo de desplzamiento de cada estudiante con un numero aletorio entre dos rangos
	 * */
	
	public Student() {
		this.idStudent = "E" + countStudent++;
		this.attentionTime = Tool.getRandom(Constants.LOWER_RANGE_TIME_ATTENTION, Constants.UPPER_RANGE_TIME_ATTENTION);
		this.displacementTime = Tool.getRandom(Constants.LOWER_RANGE_DISPLACEMENT_TIME, Constants.UPPER_RANGE_DISPLACEMENT_TIME);
	}

	public String getIdStudent() {
		return idStudent;
	}

	public void setIdStudent(String idStudent) {
		this.idStudent = idStudent;
	}

	public int getTimeAttention() {
		return attentionTime;
	}

	public int subtractAttentionTime() {
		return attentionTime--;
	}

	public int getDisplacementTime() {
		return displacementTime;
	}

	public boolean isInTheServiceLine() {
		return displacementTime <= 0;
	}

	public int subtractDisplacementTime() {
		return displacementTime--;
	}


}
