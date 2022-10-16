package model;

import tool.Tool;

public class Student {

	private static int countStudent = 1;
	private String idStudent;
	private int attentionTime;
	private int displacementTime;

	public Student() {
		this.idStudent = "E" + countStudent++;
		this.attentionTime = Tool.getRandom(5, 15);
		this.displacementTime = Tool.getRandom(10, 15);
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
		return displacementTime == 0;
	}

	public int subtractDisplacementTime() {
		return displacementTime--;
	}

	public static void main(String[] args) {
		Student s1 = new Student();
		System.out.println(s1.getIdStudent());
		Student s2 = new Student();
		System.out.println(s2.getIdStudent());
		Student s3 = new Student();
		System.out.println(s3.getIdStudent());
		Student s4 = new Student();
		System.out.println(s4.getIdStudent());
	}

}
