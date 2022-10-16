package tool;

public class Tool {
	
	
	/*Metodo encargado de generar el numero random entre dos rangos*/
	public static int getRandom(int lowerLimit,int upperLimit) {
		return(int) (Math.random() * (upperLimit + 1 - lowerLimit)) + lowerLimit;
		
	}

}
