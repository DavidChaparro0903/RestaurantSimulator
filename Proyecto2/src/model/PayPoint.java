package model;

public class PayPoint {

	private Student student;
	private int bill;

	public PayPoint() {

	}

	public void serveStudent(Student student) {
		this.student = student;
		this.bill += 2800;

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

	public Student updateTimePayPoint() {
		if (isBusy()) {
			System.out.println("Tiempo de restante de atencion: "+ student.getTimeAttention() + "  Para el estudiante: " + student.getIdStudent());
			student.subtractAttentionTime();
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
	
	public static void main(String[] args) {
		PayPoint payPoint = new PayPoint();
		Student student = new Student();
		//student.setTimeAttention(10);
		payPoint.serveStudent(student);
		for (int i = 0; i < 50; i++) {
			payPoint.updateTimePayPoint();
		}
		
		System.out.println(payPoint.getBill());

	}
		

}
