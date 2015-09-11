package package1;

/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * Simple Interface for Project 4, must implement these methods
 * within the Linked List.
 *********************************************************************/
interface ISimpleDatabase {
	
	/* used to insert a student into the DB, PLACE the STUDENTS
	 *  in order based up */	
	/* the student's name*/
    void insert(Student student);

	/* used to delete a student from the DB, use the to find
	 *  the student	*/
    boolean delete(String gNumber);

	/* Use the gNumber to find the student	*/
	/* you may only update the student's name and GPA.  
	 * Not their gNumber 	    			*/
    boolean update(String gNumber, Student student);

	/* returns a string of the entire DB*/
    String toString();

	/* finds a Student, otherwise returns null */
    Student find(String gNumber);

	/* reverses the database (see notes below) */
    void reverseList();

	/* removes duplicates from the database) */
    void removeDuplicates();

	/* Sort the db using the compareTo method 
	 * contained in the Student class. */
	/* Be sure to implement the comparable interface in the 
	 * Student class 	 */ 
	/* sort by student's name, use the String.compareTo method */ 
    void Sort();

	/* undo the previous command */
    void undo();

	/* Loads/saves using files, JUnit testing will test only these load 
	 * and save methods */
    void loadDB(String fileName);
    void saveDB(String fileName);
}
