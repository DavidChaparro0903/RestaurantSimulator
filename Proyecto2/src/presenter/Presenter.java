package presenter;

import model.Lunch;
import model.SimulationRestaurant;
import model.Student;
import view.View;

public class Presenter {
	
	private SimulationRestaurant simulation;
	private View view;
	private int time = -1;
	
	
	public Presenter() {
		this.simulation = new SimulationRestaurant();
		this.view = new View();
		init();
	}


	private void init() {
		try{
			int option = view.getMenu();
				switch (option) {
			case 1:
				generateSimulation();
				init();
				break;
			case 2:
				resumeSimulation();
				init();
				break;
			case 3:
				view.messageFinal();
				break;
			default:
				view.messageErrorElection();
				init();
				break;
			}
		}catch(NumberFormatException e) {
			view.messageErrorNumber();
			init();
		}
		
	}
	
	
	public void generateSimulation() {
		int simulationTime = view.getTime();
		simulation.initializedSimulation();
		initializedSimulation(simulationTime);
	}
	
	public void resumeSimulation() {
	
		resumeSimulationAux();
	}
	
	
	public  void initializedSimulation(int simulationTime) {
		try {
			//int simulationTime = view.getTime();
			int value = 0;
			for (int i = 1; i <= simulationTime; i++) {
				view.showTimeSimulation(i);
				isTimeCreateStudent();
				isTimeCreateLuch();
				showArriveQueu();
				showPointPay();
				showfillAuxiliaryList();
				showQueuReceiveLunch();
				showReceiveLunch();
				showStudentsWithLunch();
				view.showFinishLine();	
				value = i;
				Thread.sleep(0);
			}	
			time = value;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public void resumeSimulationAux() {
		if(time >= 0) {
			int simulationTime = view.getTime();
			if(simulationTime > 0) {
				int value = 0;
				int aux = time + 1;
				for (int i = aux; i <= (aux+simulationTime - 1); i++) {
					view.showTimeSimulation(i);
					isTimeCreateStudent();
					isTimeCreateLuch();
					showArriveQueu();
					showPointPay();
					showfillAuxiliaryList();
					showQueuReceiveLunch();
					showReceiveLunch();
					showStudentsWithLunch();
					view.showFinishLine();	
					value = i;
					try {
						Thread.sleep(0);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					time = value;
				}	
			}else {
				view.messageErrorEnterNumberCorrectResume();
			}
			
		}else {
			view.messageErrorResume();
		}
		
	}
	
	
	
	
	
	public  void isTimeCreateStudent() {
		Student student = simulation.generateStudent();
		if(student != null) {
			view.showStudent(student.getIdStudent(),simulation.getCountGenerateStudent());
		}else {
			view.showStudent(simulation.getCountGenerateStudent());
		}
	}
	
	public  void isTimeCreateLuch() {
		Lunch lunch = simulation.generateLunch();
		if(lunch != null) {
			view.showLunch(lunch.getIdLunch(), simulation.getCountGenerateLunch());
		}else {
			view.showLunch(simulation.getCountGenerateLunch());
		}
	}
	
	
	
	public void showPointPay() {
		view.showPayPointOne(simulation.attendPayPointOne(),simulation.getTotalPayPointOne());
		view.showPayPointTwo(simulation.attendPayPointTwo(),simulation.getTotalPayPointTwo()); 
	}
	
	
	public void showfillAuxiliaryList() {
		view.showfillAuxiliaryList(simulation.fillAuxiliaryList());
	}
	
	public void showReceiveLunch() {
		view.showReceiveLunch(simulation.receiveLunch());
	}
	
	
	public void showQueuReceiveLunch() {
		view.showQueuService(simulation.showQueuService());
	}

	
	public void showArriveQueu() {
		view.showArrivalQueu(simulation.showArrivalQueu());
	}
	
	public void showStudentsWithLunch() {
		view.showStudentsWithLunch(simulation.showListResult()); 
	}
	
	
	public static void main(String[] args) {
		new Presenter();
	}
	

}
