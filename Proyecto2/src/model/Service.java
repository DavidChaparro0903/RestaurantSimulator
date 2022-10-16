package model;

import tool.Tool;

public class Service {
	
	
	private Student student;
	private Lunch lunch;
	private int timeService;
	
	
//	public Service(Student student, Lunch lunch) {
//		this.student = student;
//		this.lunch = lunch;
//		this.timeService = Tool.getRandom(5, 15);
//	}
	
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
	
	public int generateTimeService() {
		return Tool.getRandom(5, 15);
	}
	
	public String updateTimeService() {
		if(this.isBusy()) {
			 this.substractService();
			System.out.println("Tiempo de restante de atencion almuerzo: "+ this. getTimeService() + "  Para el estudiante: " + student.getIdStudent());
			System.out.println("Entro a recibir el almuerzo el estudiante: " + student.getIdStudent() + " El almuerzo: " + lunch.getIdLunch());
		
			 if(this.finishService()) {
				return  liberateService();
			 }
		}
		return null;
	}
	@Override
	public String toString() {
		return "El estudiante: " + student.getIdStudent() +" Salio con el menaje :"+ lunch.getIdLunch();
	}
	
	
	
}
