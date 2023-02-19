package model;

import java.util.Iterator;
import java.util.Stack;

import comparator.StudentComparator;
import constants.Constants;
import listSimple.SimpleList;
import queu.Queu;
import tool.Tool;

public class SimulationRestaurant {
	
	
	/*Cola de estudiantes que van a pasar al punto de pago*/
	private Queu<Student> arrivalQueu;
	/*Lista simple con estudiantes que ya salieron del punto de pago y van a desplazarse a la cola de servicio
	 * 
	 * Objetivo : Esta lista sirve como auxiliar para guardar los estudiantes que ya salieron del punto de pago, 
	 * pero no han llegado a la cola de servicio, ya que primero se debe evaluar el tiempo de desplazamiento del estudiante
	 *  
	 *  */
	private SimpleList<Student> listAux;
	/*
	 * Lista simple que guarda el id del estudiante y el id del menaje, despues de salir del servicio
	 * */
	private SimpleList<String> listResult;
	
	/*Cola de servicio: guarda los estudiantes que ya se evaluo el tiempo de desplzamiento  y ya llegan a formarse a la cola*/
	private Queu<Student> queuService;
	
	/*Pila de almuerzo : Se almacenan los almuerzos que se generan cada 5 segundoa*/
	private Stack<Lunch> stackLunch;
	/*Punto de pago 1*/
	private PayPoint payPointOne;
	/*Punto de pago 2*/
	private PayPoint payPointTwo;
	/*Representa el servicio se tiene en cuenta el estudiante y el menaje, aca el estudiante va a tener un tiempo de atención aleatorio*/
	private Service service;
	
	/*Contador que se decrementa para tener en cuenta cuando generar un estudiante nuevo*/
	private int countGenerateStudent;
	/*Contador que se decrementa para tener en cuenta cuando generar un menaje nuevo*/
	private int countGenerateLunch;

	public SimulationRestaurant() {}
	
	
	public void initializedSimulation() {
		arrivalQueu = new Queu<Student>();
		listAux = new SimpleList<Student>(new StudentComparator());
		queuService = new Queu<Student>();
		payPointOne = new PayPoint();
		payPointTwo = new PayPoint();
		stackLunch = new Stack<Lunch>();
		listResult = new SimpleList<String>();
		service = new Service();
		countGenerateStudent = Tool.getRandom(Constants.LOWER_RANGE_GENERATE_STUDENT, Constants.UPPER_RANGE_GENERATE_STUDENT);
		countGenerateLunch = Constants.LUNCH_TIME;
		initalizedCountStudent();
		initalizedCountLunch();
		
	}
	
	
	
	/*
	 * Metodo principal del programa donde se genera la simulación del sistema
	 * 
	 * Primero se parte de inicializar los contadores countGenerateStudent y countGenerateLunch, el primero
	 * con un  numero aletorio en un rango de 0 - 15, y el segundo con numero fijo con un valor de 5, posteriormente
	 * se entra a un ciclo que se va a repetir dependiendo el tiempo de la simulación, y este llamada a los metodos
	 * generar estudiante, generar almuerzo, atender en punto de pago, llenar lista auxiliar y recibir almuerzo
	 * */

	public void generateSimulation(int init,int simulation) throws InterruptedException {
//		countGenerateStudent = Tool.getRandom(Constants.LOWER_RANGE_GENERATE_STUDENT, Constants.UPPER_RANGE_GENERATE_STUDENT);
//		countGenerateLunch = Constants.LUNCH_TIME;
//		countGenerateStudent = numberGenerateStudent;
//		countGenerateLunch = numberGenerateLunch;
		System.out.println("Contador generado al empezar la simulación : " + countGenerateStudent);
		for (int i = init; i <= simulation; i++) {
			//System.out.println("---------------Tiempo del simulador : " + i);
			generateStudent();
			generateLunch();
			attendPayPoint();
			fillAuxiliaryList();
			receiveLunch();
			//System.out.println("Tiempo restante del contador : " + countGenerateStudent);
			//Thread.sleep(1000);
		}
	}

	
	/*
	 * El metodo generar estudiante tiene el objetivo de que cuando el contador countGenerateStudent llegue a 
	 * cero agregue un estudiante nuevo a la cola de llegada, y cuando lo genere volver a tomar un numero aletorio
	 * 
	 * */
	
	public Student generateStudent() {
		substractGeneratorStudentTime();
		if (isTimeGenerateStudent()) {
			Student student = new Student();
			arrivalQueu.push(student);
			countGenerateStudent = Tool.getRandom(Constants.LOWER_RANGE_GENERATE_STUDENT, Constants.UPPER_RANGE_GENERATE_STUDENT);
			return student;
		}
		return null;
	}
	
	
	/*
	 * El metodo generar almuerzo tiene el objetivo de que cuando el contador countGenerateLunch llegue a 
	 * cero agregue un almuerzo nuevo a la pila de almuerzos, y volver a darle un valor fijo de 5 al contador
	 * 
	 * */
	
	public Lunch generateLunch() {
		 substractGeneratorLunchTime();
		 if(isTimeGenerateLunch()) {
			Lunch lunch = new Lunch();
			stackLunch.push(lunch);
			countGenerateLunch = Constants.LUNCH_TIME;
			return lunch;
		 }
		 return null;
	}
	
	
	/*Si el contador es 0 o menos genere un estudiante*/

	public boolean isTimeGenerateStudent() {
		return countGenerateStudent <= 0 ? true : false;
	}
	
	/*Disminuir en uno el contador de estudiante*/
	
	public int substractGeneratorStudentTime() {
		return countGenerateStudent--;
	}

	/*Si el contador es 0 genere un almuerzo */
	
	public boolean isTimeGenerateLunch() {
		return countGenerateLunch <= 0 ? true : false;
	}

	/*Disminuir en uno el contador de almuerzo*/
	public int substractGeneratorLunchTime() {
		return countGenerateLunch--;
	}
	
	
	/*Hace el llamado de los dos puntos de pago*/
	
	public void attendPayPoint() {
		attendPayPointOne();
		attendPayPointTwo();
	}
	
	
	/*Representa el punto de pago uno, y observa si la cola de llegada esta vacio y si el punto de pago no esta ocupado, 
	 * Si se cumplen estas condiciones se agrega un estudiante al punto de pago, de lo contrario si se encuentra ocupado 
	 * se resta el tiempo de atención que va a tener el estudiante
	 * 
	 * */

	public String attendPayPointOne() {
		if (!arrivalQueu.isEmpty()) {
			if (!payPointOne.isBusy()) {
				payPointOne.serveStudent(arrivalQueu.remove());
			}
		}
		listAux.insert(payPointOne.updateTimePayPoint());
		return payPointOne.getResult();
	}
	
	/*Representa el punto de pago dos, observa si la cola de llegada esta vacio y si el punto de pago no esta ocupado, 
	 * Si se cumplen estas condiciones se agrega un estudiante al punto de pago, de lo contrario si se encuentra ocupado 
	 * se resta el tiempo de atención que va a tener el estudiante
	 * 
	 * */
	public String attendPayPointTwo() {
		if (!arrivalQueu.isEmpty()) {
			if (!payPointTwo.isBusy()) {
				payPointTwo.serveStudent(arrivalQueu.remove());
			}
		
		}
		listAux.insert(payPointTwo.updateTimePayPoint());
		return payPointTwo.getResult();
	}
		
	/*
	 * Este metodo llena una lista auxiliar, que tiene omo objetivo almacenar los estudiantes que ya pasaron por el punto de 
	 * pago y van hacia la cola de servicio, en este metodo se evalua el tiempo que tarda el estudiante en llegar a esa cola 
	 * de servicio, y se disminuye en uno el tiempo cuando este tiempo llega a cero se elimina de la lista auxiliar y
	 * se agrega a la cola de servicio
	 * 	 
	 * */
	
	public String fillAuxiliaryList() {
		String result = "";
		Iterator<Student> iterator = listAux.iterator();
		while (iterator.hasNext()) {
			Student student = iterator.next();
			student.subtractDisplacementTime();
			result += "Tiempo restante de desplazamiento del estudiante: " + student.getIdStudent() + " es de: "+ student.getDisplacementTime() +"\n";
			if(student.isInTheServiceLine()) {
				listAux.delete(student);
				result += "Llego a la cola de servicio " + " el estudiante " + student.getIdStudent() + "\n";
				queuService.push(student);
			}		
		}
		return result;
	}
	
	
	
	/*Metodo que se encarga de mirar la pila de servicio, y si esta tiene estudiante y existen almuerzos, el primer estudiante 
	 * formado en la cola, pase a recibir su menaje, esto tambien tiene un tiempo de atencion y en el servicio solo se puede tener
	 * un estudiante a la vez, despues de pasado ese tiempo pasa el siguiente de la cola
	 *  */
	
	public String receiveLunch() {
		if(!queuService.isEmpty() && !stackLunch.isEmpty()) {
			if(!service.isBusy()) {
				service.setLunch(stackLunch.pop());
				service.setStudent(queuService.remove());
				service.setTimeService(service.generateTimeService());
			}
		}
		listResult.insert(service.updateTimeService());
		return service.getMessageResult();
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
	
	public String showArrivalQueu() {
		Iterator<Student> iterator = arrivalQueu.iterator();
		String result = "";
		while (iterator.hasNext()) {
			result += iterator.next().getIdStudent() + "\n";
		}
		return result;
	}
	
	
	
	
	public String showQueuService() {
		Iterator<Student> iterator = queuService.iterator();
		String result = "";
		while (iterator.hasNext()) {
			result += iterator.next().getIdStudent() + "\n";
		}
		return result;
	}
	
	
	public SimpleList<Student> getListAux() {
		return listAux;
	}
	
	public SimpleList<String> getListResult(){
		return listResult;
	}

	
	public String showListResult() {
		Iterator<String> iterator = listResult.iterator();
		String result = "";
		while (iterator.hasNext()) {
			result += iterator.next() + "\n";
		}
		return result;
	}
	
	

	public int getCountGenerateStudent() {
		return countGenerateStudent;
	}


	public int getCountGenerateLunch() {
		return countGenerateLunch;
	}
	
	public String getTotalPayPointOne() {
		return "El dinero recogido del punto de pago 1 es: " + payPointOne.getBill();
	}

	
	public String getTotalPayPointTwo() {
		return "El dinero recogido del punto de pago 2 es: " + payPointTwo.getBill();
	}

	public static void initalizedCountStudent() {
		Student.initalizedCountStudent();
	}
	
	public static void initalizedCountLunch() {
		Lunch.initalizedCountLunch();
	}
	
	
	
}
