package model;

import tool.Tool;

public class Service {
	
	
	private Student student;
	private Lunch lunch;
	private int timeService;
	
	
	public Service(Student student, Lunch lunch) {
		this.student = student;
		this.lunch = lunch;
		this.timeService = Tool.getRandom(5, 15);
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
	

	
	
	
	
	

}
