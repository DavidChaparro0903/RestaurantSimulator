package model;

import constants.Constants;
import tool.Tool;

public class Service {
	
	
	private Student student;
	private Lunch lunch;
	private int timeService;
	private String messageResult;
	
	public Service() {
		
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Lunch getLunch() {
		return lunch;
	}
	public void setLunch(Lunch lunch) {
		this.lunch = lunch;
	}
	public int getTimeService() {
		return timeService;
	}
	
	public int substractService() {
		return timeService--;
	}
	public void setTimeService(int timeService) {
		this.timeService = timeService;
	}

	public boolean isBusy() {
		return this.student != null && this.lunch != null;
	}

	public String liberateService() {
		String result = this.toString();
		this.student = null;
		this.lunch = null;
		return result;
	}
	
	
	public boolean finishService() {
		return timeService == 0;
	}
	
	
	/*Metodo encargado de generar el tiempo de servicio dependiendo los rangos*/
	public int generateTimeService() {
		return Tool.getRandom(Constants.LOWER_RANGE_TIME_SERVICE, Constants.UPPER_RANGE_TIME_SERVICE);
	}
	
	
	/*
	 * Metodo encargado de actualizar el tiempo de servicio, dodnde si llega a cero se libera el servicio, 
	 * dandole paso a otro estudiante para que entre por el el tiempo que se genero, cuandos se libera se elimina
	 * el estudiante y el almuerzo que se tenai en ese momento
	 * 
	 * */
	
	public String updateTimeService() {
		messageResult = "";
		if(this.isBusy()) {
			 this.substractService();
			messageResult += "Tiempo de restante de atencion almuerzo: "+ this. getTimeService() + "  Para el estudiante: " + student.getIdStudent() + " Recibio el almuerzo: " +  lunch.getIdLunch() +"\n";
			//System.out.println("Tiempo de restante de atencion almuerzo: "+ this. getTimeService() + "  Para el estudiante: " + student.getIdStudent() + " Recibio el almuerzo: " +  lunch.getIdLunch());
			//System.out.println("Entro a recibir el almuerzo el estudiante: " + student.getIdStudent() + " El almuerzo: " + lunch.getIdLunch());
			 if(this.finishService()) {
				 messageResult += "Termino la atencion para el estudiante: " + student.getIdStudent();
				return liberateService();
			 }
		}
		return null;
	}
	
	
	
	
	
	public String getMessageResult() {
		return messageResult;
	}
	
	
	@Override
	public String toString() {
		return "El estudiante: " + student.getIdStudent() +" Salio con el menaje :"+ lunch.getIdLunch();
	}
	
	
	
}
