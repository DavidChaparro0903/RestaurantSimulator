package view;

import java.util.Iterator;
import java.util.Scanner;

import constants.Constants;

public class View {
	
	private Scanner scanner;
	
	public View() {
		scanner = new Scanner(System.in);
	}
	
	
	public int getMenu() {
		System.out.println(Constants.MENU);
		return Integer.parseInt(scanner.nextLine());
	}
	
	public int getTime() {
		System.out.println(Constants.TIME_SIMULATION);
		return Integer.parseInt(scanner.nextLine());
	}

	public void showTimeSimulation(int timeSimulation) {
		System.out.println("*************************************** Tiempo simulacion: " + timeSimulation + "**********************************************************************************" );
	}
	
	public void showStudent(String idStudent,int count) {
		System.out.println("Eventos:\n" + "Estudiante generado: " + idStudent + "\n" + "Tiempo restante para generar un nuevo estudiante: " + count);
	}
	
	public void showStudent(int count) {
		System.out.println("Eventos:\n" + "Tiempo restante para generar un nuevo estudiante: " + count);
	}
	
	public void showLunch(String idLunch,int count) {
		System.out.println("Almuerzo creado: " + idLunch + "\nTiempo restante para generar un nuevo almuerzo: " + count);
		
	}
	
	
	public void showLunch(int count) {
		System.out.println("Tiempo restante para generar un nuevo almuerzo: " + count);
	}
	
	public void showPayPointOne(String result,String total) {
		System.out.println("-----------------------------Punto de Pago 1-----------------------------------");
		System.out.println(result == null ? "No hay estudiantes en el punto de pago 1" : result);
		System.out.println(total);
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	public void showPayPointTwo(String result,String total) {
		System.out.println("-----------------------------Punto de Pago 2-----------------------------------");
		System.out.println(result == null ? "No hay estudiantes en el punto de pago 2" : result);
		System.out.println(total);
		System.out.println("-------------------------------------------------------------------------------");
	}
	
	public void showfillAuxiliaryList(String result) {
		System.out.println("\nFila de desplazamiento:\nEventos:");
		System.out.println(result);
	}

	
	public void showQueuService(String result) {
		System.out.println("\nCola de servicio:\nEventos:");
		System.out.println(result);
	
	}
	
	public void showArrivalQueu(String result) {
		System.out.println("\nCola de llegada:\nEventos:");
		System.out.println(result);
	
	}
	
	
	public void showReceiveLunch(String result) {
		System.out.println("\nServicio de atencion para el menaje:\n");
		System.out.println(result);
	}
//	
//	public void showTotalMoneyPayPoint(String result) {
//		System.out.println(result);
//	}
	
	public void showFinishLine() {
		System.out.println("*********************************************************************************************************************************************");
		//System.out.println("----------------------------------------------------------------");
	}


	public void showStudentsWithLunch(String result) {
		System.out.println("\nEstudiantes que ya recibieron menaje:\n");
		System.out.println(result);
		
	}
	
	
	
}
