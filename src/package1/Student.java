package package1;

import java.io.Serializable;
import java.text.DecimalFormat;

/**********************************************************************
 * CIS 163
 * @author Benson B and Jesse Roe
 * A represensation of a student.
 *********************************************************************/
public class Student implements Comparable <Student>, Serializable{
	
	private static final long serialVersionUID = -7037578907114249656L;

	/**The last name of the Student**/
	private String firstName;
	
	/**The first Name of the Student**/
	private String lastName;
	
	/**The gNumber of the Student**/
	private String gNumber;
	
	/**The gpa of the student**/
	private double gpa;
	
	/*****************************************************************
	 * Builds a representation of a student
	 * @param lastname
	 * The last name of the student as a String
	 * @param firstname
	 * The first Name of the student as a String
	 * @param pGnumber
	 * The student's GNumber, or unique identifier as a String
	 * @param pGpa
	 * The GPA of the student represented as a double.
	 *****************************************************************/
	Student(String lastname, String firstname, 
			String pGnumber, double pGpa){
		try{
			Integer.parseInt(pGnumber);
		}catch(NumberFormatException ex){
			throw new IllegalArgumentException();
		}
		if(pGpa< 0.0 || pGpa>5.0){
			throw new IllegalArgumentException
			("Can't have a negative GPA or greater than 5.0 GPA");
		}
		this.lastName = lastname;
		this.firstName = firstname;
		this.gNumber = pGnumber;
		this.gpa = pGpa;
	}
	
	/******************************************************************
	 * Compares two student objects
	 * @param temp
	 * The first student.
	 * @param other
	 * The other student.
	 * @return
	 * True if they're equal and false if they're not.
	 *****************************************************************/
	public boolean equals(Student temp, Student other){
		if((temp.getName().equals(other.getName()))&&
		(temp.getgNumber().equals(other.getgNumber()))&&
		(temp.getGpa()==other.getGpa())){
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * Useful for JUnit testing, and checking if a student is equal
	 * to another student in every respect.
	 * @param other
	 * The Student object you're comparing this student with
	 * @return
	 * True if the GPA, last name, first name, GNumber are the same.
	 * Otherwise, return false. 
	 *****************************************************************/
	public boolean equals(Student other){
		if(this.lastName.equals(other.lastName)&&
			(this.firstName.equals(other.firstName))&&
			(this.gNumber.equals(other.gNumber))&&
			(this.gpa == other.gpa)){
			return true;
		}
		return false;
	}
	
	/******************************************************************
	 * Gets the students name.
	 * @return the name
	 * returns the name of the Student as a String
	 *****************************************************************/
	public String getFirstName() {
		return firstName;
	}
	
	/******************************************************************
	 * Sets the first name of the student
	 * @param name
	 * The first name of the student.
	 *****************************************************************/
	public void setLast(String name){
		this.lastName = name;
	}
	
	/*****************************************************************
	 * Return the students first name
	 * @return
	 * The first name of the student.
	 *****************************************************************/
	public String getLast(){
		return lastName;
	}

	/******************************************************************
	 * sets the name of the person.
	 * @param name the name to set
	 * The name name you're changing it to.
	 *****************************************************************/
	public void setFirst(String name) {
		this.firstName = name;
	}

	/******************************************************************
	 * Gets the student G#
	 * @return the gNumber
	 * Returns the students G# as a String of characters.
	 *****************************************************************/
	public String getgNumber() {
		return gNumber;
	}

	/******************************************************************
	 * Sets the G# of the student
	 * @param gNumber the gNumber to set
	 * The String you're changing that person's GNumber to.
	 * Make sure it includes a G followed by 8 digits.
	 *****************************************************************/
	public void setgNumber(String gNumber) {
		this.gNumber = gNumber;
	}

	/******************************************************************
	 * Returns the Gpa of the student
	 * @return the gpa
	 * returns the gpa as a double, would be a good idea to format
	 * the double if it is displayed to the user interface.
	 *****************************************************************/
	public double getGpa() {
		return gpa;
	}

	/******************************************************************
	 * sets the gpa of the student to what you designate.
	 * @param gpa the gpa to set
	 * The gpa passed in must be a double.
	 *****************************************************************/
	public void setGpa(double gpa) {
		this.gpa = gpa;
	}
	
	/*****************************************************************
	 * Returns the full name of the Student
	 * @return
	 * Returns the students name as "Benjamin, Benson" First->Last
	 *****************************************************************/
	public String getName(){
		return this.getFirstName() + ", "+this.getLast();
	}
	
	/******************************************************************
	 * Format's the student's information.
	 * The name of the student, GNumber, and their GPA.
	 * @return
	 * The students information.
	 *****************************************************************/
	public String toString(){
		DecimalFormat dformat = new DecimalFormat("#.##");
		String gpa = dformat.format(getGpa());
		return getName()+" "+getgNumber()+" "+gpa;
	}
	
	/*****************************************************************
	 * Compares two student objects to one another.
	 * @return
	 * A value of 0 if the objects are equal
	 * A value < 0 if one object is deemed higher than the other
	 * A value > 0 if one object is deemed lower than the other
	 ****************************************************************/
	@Override
	public int compareTo(Student other) {
		 int result = 0;
	      if (this.firstName.equalsIgnoreCase(((Student)other)
	    		  .getFirstName())){
	    	  
	    	  //It is crucial to use the compareToIgnoreCase 
	    	  //Java places higher precedence on the UpperCase letters
	         result = lastName.compareToIgnoreCase(((Student)other)
	        		 .lastName);
	      }else{
	         result = firstName.compareToIgnoreCase(((Student)other)
	        		 .firstName);
	      }

		return result;
	}
}
