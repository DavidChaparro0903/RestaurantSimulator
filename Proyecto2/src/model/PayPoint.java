package model;

import constants.Constants;

public class PayPoint {
	
	

	private Student student;
	
	private int bill;

	public PayPoint() {

	}
	
	
	/*Este metodo se encarga de recibir un estudiante como argumento, y sumarle el valor del credito para asi 
	 * ir acumulando en cada punto de pago por cada estudiante que atienda*/

	public void serveStudent(Student student) {
		this.student = student;
		this.bill += Constants.CREDIT_VALUE;

	}

	public Student getStudent() {
		return this.student;
	}

	public boolean isBusy() {
		return student != null;
	}

	public Student liberatePayPoint() {
		Student student = this.student;
		this.student = null;
		return student;
	}

	public boolean isFinishTimeAttention() {
		return student.getTimeAttention() == 0;
	}

	
	/*
	 * Metodo encargado de actualizar, el tiempo que el estudiante va a ser atendido, cuando sea igual a 
	 * cero libera el punto de atención para que entre otro estudiante
	 * 
	 * */
	public Student updateTimePayPoint() {
		if (isBusy()) {
			student.subtractAttentionTime();
			System.out.println("Tiempo de restante de atencion: " + student.getTimeAttention()
					+ "  Para el estudiante: " + student.getIdStudent());
			if (isFinishTimeAttention()) {
				System.out.println("Liberar punto de pago");
				return liberatePayPoint();

			}
		}
		return null;
	}

	public int getBill() {
		return bill;
	}



}
