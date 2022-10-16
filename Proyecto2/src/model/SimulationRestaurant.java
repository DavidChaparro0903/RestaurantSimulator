package model;

import java.util.Iterator;
import java.util.Stack;

import comparator.StudentComparator;
import listSimple.SimpleList;
import queu.Queu;
import tool.Tool;

public class SimulationRestaurant {

	private Queu<Student> arrivalQueu;
	private SimpleList<Student> listAux;
	private Queu<Student> queuService;
	private Stack<Lunch> stackLunch;
	private PayPoint payPointOne;
	private PayPoint payPointTwo;
	private static int countGenerateStudent;
	private static int countGenerateLunch;

	public SimulationRestaurant() {
		arrivalQueu = new Queu<Student>();
		listAux = new SimpleList<Student>(new StudentComparator());
		queuService = new Queu<Student>();
		payPointOne = new PayPoint();
		payPointTwo = new PayPoint();
		stackLunch = new Stack<Lunch>();

	}

	public void generateSimulation(int simulation) throws InterruptedException {
		countGenerateStudent = Tool.getRandom(0, 15);
		countGenerateLunch = 5;
		System.out.println("Contador generado al empezar la simulación : " + countGenerateStudent);
		for (int i = 1; i <= simulation; i++) {
			System.out.println("---------------Tiempo del simulador : " + i);
			generateStudent();
			generateLunch();
			attendPayPoint();
			traverseAuxiliaryQueu();
			System.out.println("Tiempo restante del contador : " + countGenerateStudent);
			//Thread.sleep(5000);
		}

	}

	public void generateStudent() {
		substractGeneratorStudentTime();
		if (isTimeGenerateStudent()) {
			Student student = new Student();
			arrivalQueu.push(student);
			System.out.println("Estudiante creado: " + student.getIdStudent());
			countGenerateStudent = Tool.getRandom(0, 15);
			System.out.println("Nuevo random generador al contador: " + countGenerateStudent);
		}
	}
	
	public void generateLunch() {
		 substractGeneratorLunchTime();
		 if(isTimeGenerateLunch()) {
			Lunch lunch = new Lunch();
			stackLunch.push(lunch);
			System.out.println("Almuerzo apilado: " + lunch.getIdLunch());
			countGenerateLunch = 5;
			System.out.println("Nuevo contador almuerzo: " + countGenerateLunch);
		 }
	}

	public boolean isTimeGenerateStudent() {
		return countGenerateStudent <= 0 ? true : false;
	}
	


	public int substractGeneratorStudentTime() {
		return countGenerateStudent--;
	}

	
	public boolean isTimeGenerateLunch() {
		return countGenerateLunch == 0 ? true : false;
	}

	public int substractGeneratorLunchTime() {
		return countGenerateLunch--;
	}
	
	public void attendPayPoint() {
		attendPayPointOne();
		attendPayPointTwo();
	}

	public void attendPayPointOne() {
		System.out.println("--------------------Punto de pago Uno--------------------");
		if (!arrivalQueu.isEmpty()) {
			if (!payPointOne.isBusy()) {
				payPointOne.serveStudent(arrivalQueu.remove());
			}
		}
		listAux.insert(payPointOne.updateTimePayPoint());
		System.out.println("----------------------------------------");

	}

	public void attendPayPointTwo() {
		System.out.println("--------------------Punto de pago Dos--------------------");
		if (!arrivalQueu.isEmpty()) {
			if (!payPointTwo.isBusy()) {
				payPointTwo.serveStudent(arrivalQueu.remove());
			}
		
		}
		listAux.insert(payPointTwo.updateTimePayPoint());
		System.out.println("----------------------------------------");
	}
	

	
	
	public void traverseAuxiliaryQueu() {
		Iterator<Student> iterator = listAux.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			System.out.println("Tiempo restante de desplazamiento del estudiante: " + student.getDisplacementTime()+ " " + student.getIdStudent());
			student.subtractDisplacementTime();
			if(student.isInTheServiceLine()) {
				listAux.delete(student);
				System.out.println(" LLego a la cola de servicio " + " el estudiante " + student.getIdStudent());
				queuService.push(student);
			}			
		}
	}
	

	public PayPoint getPayPointOne() {
		return payPointOne;
	}

	public void setPayPointOne(PayPoint payPointOne) {
		this.payPointOne = payPointOne;
	}

	public PayPoint getPayPointTwo() {
		return payPointTwo;
	}

	public void setPayPointTwo(PayPoint payPointTwo) {
		this.payPointTwo = payPointTwo;
	}

	public Queu<Student> getQueu() {
		return arrivalQueu;
	}
	
	public Queu<Student> getQueuService() {
		return queuService;
	}
	
	
	


//	public Stack<Student> getStack(){
//		return stack;
//	}

	

	

	public SimpleList<Student> getListAux() {
		return listAux;
	}

	public static void main(String[] args) {
		SimulationRestaurant simulation = new SimulationRestaurant();
		// simulation.generateStudent(20,1,3);
		try {
			simulation.generateSimulation(40);
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
		
		//System.out.println("Lista auxiliar:  " +  simulation.getListAux().getSize());
//		Stack<Student> stack = simulation.getStack();
//		System.out.println(stack.size());

	}
}
