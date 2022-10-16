package comparator;

import java.util.Comparator;

import model.Student;

public class StudentComparator implements Comparator<Student>{

	@Override
	public int compare(Student o1, Student o2) {
		return o1.getIdStudent().compareTo(o2.getIdStudent());
	}

}
