package average.com;

/*
 * Use a DataType Student (name, avg) to return the student with max avg.
Follow this logic.
1. Iterate through the 2D array - get one student array at a time, student[0] is name, student[0] is grade.
2. If student doesn't exist in map (map of String, and ArrayList<Integer>), then
    a. create new integer list and add this student's grade to it,
    b. add student to map.
    c. re-calculate max
3. If student exists,
    a. get student's grades list from map
    b. add current grade to list
    c. add student and updated grades list to map
    d. calculate student's avg
    e. re-calculate max.
4. Return the max student.
 * */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.OptionalDouble;

public class StudentWithMaxAvg {
	
	private static class Student {
		public String studentname;
		public Double average;
		
		Student(String n, Double a) {
			studentname = n;
			average = a;
		}
	}
	
	public static void main(String[] args) {
		String[][] s = { { "Dileep", "50" }, { "Siri", "99" }, { "chethan", "35" }, { "priya", "0" }, };

		Student top = studentWithMaxAvg(s);
		System.out.println(top.studentname + ", average: " + top.average);
	}

	private static Student studentWithMaxAvg(String[][] s) {
		
		Map<String, ArrayList<Integer>> map = new HashMap<String, ArrayList<Integer>>();
		
		Student maxStudent = new Student("",(double)Integer.MIN_VALUE);
		
		for(String[] student : s) {
			if(!map.containsKey(student[0])) {
				String studentname = student[0];
				int grade = Integer.parseInt(student[1]);
				
				ArrayList<Integer> marks = new ArrayList<Integer>(); //create new list
				marks.add(grade);
				map.put(studentname, marks); //add to map to calc avg
				
				Student curr = new Student(studentname, (double) grade);
				
				maxStudent = reCalcMax(maxStudent, curr); //re calculate the avg and maintain to compare with next student entry.
				
			} else {
				String studentname = student[0];
				int grade = Integer.parseInt(student[1]);
				
				ArrayList<Integer> marks = map.get(studentname);
				marks.add(grade);
				map.put(studentname, marks);
				
				double average = getAvgForStudent(marks);
				
				Student curr = new Student(studentname, average);
				
				maxStudent = reCalcMax(maxStudent, curr);
			}
		}
		return maxStudent;
	}

	private static double getAvgForStudent(ArrayList<Integer> marks) {
		OptionalDouble avg = marks.stream().mapToDouble(i->i).average();
		
		return avg.getAsDouble();
	}

	private static Student reCalcMax(Student max, Student curr) {
		
		if(curr.average > max.average) {
			return curr;
		}
		return curr;

	}}
