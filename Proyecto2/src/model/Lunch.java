package model;

public class Lunch {

	private static int countLunch = 1;
	private String idLunch;

	public Lunch() {
		this.idLunch = "M" + countLunch++;
	}

	public String getIdLunch() {
		return idLunch;
	}

	public void setIdLunch(String idLunch) {
		this.idLunch = idLunch;
	}

}
