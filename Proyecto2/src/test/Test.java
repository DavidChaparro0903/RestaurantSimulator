package test;
import java.util.Iterator;

import constants.Constants;
import model.SimulationRestaurant;
import model.Student;
import queu.Queu;
import stack.Stack;
import tool.Tool;

public class Test {

	
	public static void main(String[] args) {
//		
//		for (int i = 0; i < 1000; i++) {
//			 int number = Tool.getRandom(5,10);
//			 if(number > 10 || number < 5) {
//				 System.out.println("Error");
//			 }
//			// System.out.println( i + " "  +number);
//		}
//		
//		System.out.println("Bien");
//	
//		
		
		//Prueba de Pila
//		System.out.println("---------------Pila----------------");
//		Stack<Integer> stack = new Stack<Integer>();
//		System.out.println("¿Esta vacio?: " + stack.isEmpty());
//		stack.push(10);
//		stack.push(20);
//		stack.push(30);
//		System.out.println("¿Esta vacio?: " + stack.isEmpty());
//		System.out.println("Longitud de la pila: "+stack.size());
//		System.out.println("Elemento de la cima: " + stack.top());
//		System.out.println("Se retira: " + stack.pop());
//		System.out.println("Longitud de la pila: "+stack.size());
//		System.out.println("ELemento de la cima: " + stack.top());
//		System.out.println("Se retira: " + stack.pop());
//		System.out.println("Elemento de la cima: " + stack.top());
//		System.out.println("Se retira: " + stack.pop());
//		System.out.println("Se retira: " + stack.pop());
//		System.out.println("Elemento de la cima: " + stack.top());
		//Prueba Cola
		
//		System.out.println("---------------Cola----------------");
//		Queu<Integer> queu = new Queu<Integer>();
//		System.out.println("¿Esta vacio?: " + queu.isEmpty());
//		queu.push(30);
//		queu.push(40);
//		queu.push(50);
//		queu.push(60);
//		System.out.println("¿Esta vacio?: " + queu.isEmpty());
//		System.out.println("Longitud de la cola: "+queu.size());
//		System.out.println("Primer elemento de la cola " + queu.getFirst());
//		System.out.println("Ultimo elemento de la cola " + queu.getLast());
//		System.out.println("Se retira: " + queu.remove());
//		System.out.println("Primer elemento de la cola " + queu.getFirst());
//		System.out.println("Se retira: " + queu.remove());
//		System.out.println("Primer elemento de la cola " + queu.getFirst());
//		System.out.println("Se retira: " + queu.remove());
//		System.out.println("Primer elemento de la cola " + queu.getFirst());
//		System.out.println("Ultimo elemento de la cola " + queu.getLast());
//		
		//Simulacion
		SimulationRestaurant simulation = new SimulationRestaurant();
		simulation.initializedSimulation() ;
		// simulation.generateStudent(20,1,3);
		try {
			//simulation.generateSimulation(1,50,Tool.getRandom(Constants.LOWER_RANGE_GENERATE_STUDENT, Constants.UPPER_RANGE_GENERATE_STUDENT),5);
			simulation.generateSimulation(1,50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(simulation.getPayPointOne().getBill());
		System.out.println(simulation.getPayPointTwo().getBill());
		Iterator<Student> iterator = simulation.getListAux().iterator();
		System.out.println("-----------------Lista enlazada------------------");
		while (iterator.hasNext()) {
			System.out.println(iterator.next().getIdStudent());
			
		}
		System.out.println("-------------------------------------------------");
		Iterator<Student> iteratorQueu = simulation.getQueuService().iterator();
		System.out.println("-----------------Cola de servicio------------------");
		while (iteratorQueu.hasNext()) {
			System.out.println(iteratorQueu.next().getIdStudent());	
		}
		System.out.println("-------------------------------------------------");
		
		System.out.println("-----------------Lista de estudiante y almuerzo------------------");
		Iterator<String> iteratorResult = simulation.getListResult().iterator();
		while (iteratorResult.hasNext()) {
			System.out.println(iteratorResult.next());	
		}
		System.out.println("-------------------------------------------------");
		System.out.println("Ultimo elemento de la cola: " +  simulation.getQueuService().getLast().getIdStudent());
		System.out.println("perimer elemento de la cola: " +  simulation.getQueuService().getFirst().getIdStudent());
		//System.out.println("Lista auxiliar:  " +  simulation.getListAux().getSize());
////		Stack<Student> stack = simulation.getStack();
////		System.out.println(stack.size());

		
	}
	
	


}
