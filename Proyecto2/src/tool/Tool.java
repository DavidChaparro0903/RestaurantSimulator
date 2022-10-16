package tool;

public class Tool {
	
	
	public static int getRandom(int lowerLimit,int upperLimit) {
		return(int) (Math.random() * (upperLimit + 1 - lowerLimit)) + lowerLimit;
		
	}

}
