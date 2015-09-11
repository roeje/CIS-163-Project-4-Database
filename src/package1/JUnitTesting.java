/**
 * 
 */
package package1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.Test;

/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * Testing for the Linked List class.
 *********************************************************************/
public class JUnitTesting {

	private Student Ben = new Student("Benson","Benjamin","123",3.25);
	
	private Node temp = new Node(Ben, null, null);
	
	private LinkedList database = new LinkedList();
	
	private Student Jesse = new Student("Jesse","Roe","1081", 3.14);
	
	private Student Adam = new Student("Adam","RosenBerg","3920103"
				, 3.14);
	
	private ArrayList <Student> students;
	
	private String[] Name = {"a","b","c","d","e","f","g","h",
			"i","j","k","l","m","n","o","p","q",
			"r","s","t","u","v","w","x","y","z"};
	
	private SimpleDatabase simpleDB = new SimpleDatabase();
	
	
	/******************************************************************
	 * Adds 3 additional students to the LinkedList.
	 *****************************************************************/
	private void addStudents() {
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		database.addTop(1,Adam);
	}
	
	private String replace(String temp){
		return temp.replace("\\s", "");
	}
	
	/******************************************************************
	 * adding in another set of students
	 *****************************************************************/
	private void addOtherStudents(){
		database.addEnd(new Student("Eric","weisenberg","01",4.0));
		database.addEnd(new Student("things","GV","15",3.5));
		database.addEnd(new Student("stuff","morestuff","56",3.25));
	}
	
	/******************************************************************
	 * Generates and sort 100 random students, and sort them.
	 *****************************************************************/
	@Test
	public void sortDB(){
		students = new ArrayList<Student>();
		for(int i=0;i<99;i++){
			Random ran = new Random();
			int letter = ran.nextInt(Name.length);
			int lastname = ran.nextInt(Name.length);
			Student temp = new Student(Name[letter],
					Name[lastname],"01",3.5);
			database.addTop(1,temp);
			students.add(temp);
		}
		Collections.sort(students);
		database.sort();
		Node temp = database.getHead();
		int i=0;
		while(temp != null){
			assertTrue(students.get(i).equals(temp.getData()));
			temp = temp.getNext();
			i++;
		}
	}
	

	/******************************************************************
	 * Reduces the occurence of repeat code. within the Remove Student.
	 * Tests multiple deletes, and multiple adds.
	 *****************************************************************/
	private void breakStudentHelper() {
		addStudents();
		//Remove the tail, and make sure it points to Jesse.
		database.delStudent(1,Ben.getgNumber());
		assertEquals(database.getTail().getData(), Jesse);

		database.delStudent(1,Adam.getgNumber());
		//head and tail should point to the same node if
		//everyone else has been deleted
		assertEquals(database.getHead().getData(), Jesse);
		assertEquals(database.getTail().getData(), Jesse);
	}
	
	/******************************************************************
	 * Test if there's not student that you're trying to delete.
	 *****************************************************************/
	@Test
	public void deleteNonExistantStudent(){
		addStudents();
		addOtherStudents();
		assertFalse(database.delStudent(1,"19349031853"));	
	}
	
	/******************************************************************
	 * Test where there's nothing to delete
	 *****************************************************************/
	@Test
	public void noListDelete(){
		LinkedList stuff = new LinkedList();
		assertFalse(stuff.delStudent(1,Ben.getgNumber()));
	}
	
	/******************************************************************
	 * test to delete the top and bottom of the list
	 *****************************************************************/
	@Test
	public void deleteTopBottom(){
		database.addTop(1,Ben);
		database.delStudent(1,Ben.getgNumber());
		assertNull(database.getHead());
		assertNull(database.getTail());
	}
	
	@Test
	public void deleteTheEnd(){
		addStudents();
		database.delStudent(1,Ben.getgNumber());
		assertEquals(database.getTail().getData(),Jesse);
		//The top should still be adam.
		assertEquals(database.getHead().getData(),Adam);
	}
	
	@Test
	public void deleteTheTop(){
		addStudents();
		//delete the head
		database.delStudent(1,Adam.getgNumber());
		//test the rest of the list, and the pointers.
		assertEquals(database.getHead().getData(),Jesse);
		assertEquals(database.getTail().getData(),Ben);
	}
	
	@Test 
	public void deleteMiddle(){
		addStudents();
		//delete the middle of the list
		database.delStudent(1,Jesse.getgNumber());
		//check the head and the tail pointers.
		assertEquals(database.getHead().getData(),Adam);
		assertEquals(database.getTail().getData(),Ben);
	}
	
	/******************************************************************
	 * Testing the get Last method within the student class.
	 * and the setter method within the Student class. 
	 *****************************************************************/
	@Test
	public void testGetLast() {
		assertTrue(Ben.getFirstName().equals("Benjamin"));
	}
	
	@Test
	public void testSetLast(){
		Ben.setFirst("Eric");
		assertTrue(Ben.getFirstName().equals("Eric"));
	}
	
	@Test
	public void testSetFirst(){
		Ben.setLast("Something");
		assertTrue(Ben.getLast().equals("Something"));
	}
	
	/******************************************************************
	 * test the GetFirst Method within the student class.
	 *****************************************************************/
	@Test
	public void testGetFirst(){
		assertTrue(Ben.getLast().equals("Benson"));
	}
	
	@Test
	public void testSetGPA(){
		Ben.setGpa(4.500);
		assertTrue(Ben.getGpa()==4.500);
	}
	
	@Test
	public void testSetGNumber(){
		Ben.setgNumber("0910391");
		assertTrue(Ben.getgNumber().equals("0910391"));
	}
	
	/******************************************************************
	 * Test the GNumber getter and setters
	 *****************************************************************/
	@Test
	public void testGNumber(){
		assertTrue(Ben.getgNumber().equals("123"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testStudentConstructor(){
		Student ben = new Student("ben","benson","01",-0.000000001);
	}
	
	public void testMassiveGPA(){
		Student ben = new Student("ben","benson","01",5.0000000000001);
	}
	
	/******************************************************************
	 * Test if the student getter and setter methods are working
	 * properly.
	 *****************************************************************/
	@Test
	public void testGPA(){
		assertTrue(Ben.getGpa()==3.25);
		
	}
	
	/******************************************************************
	 * These two students should be equal to one another.
	 *****************************************************************/
	@Test
	public void testStudentEqual(){
		assertTrue(Ben.equals(Ben));
	}
	
	/******************************************************************
	 * Test if the students are not equal by the last thing that
	 * the Student class compares ensuring it hits the last and 
	 * statement within the if.
	 *****************************************************************/
	@Test
	public void testStudentNotEqual(){
		assertFalse(Ben.equals(new Student("Benson","Benjamin","1"
							,4.00)));
	}
	
	/******************************************************************
	 * Sorting by the last name, using random names generated
	 * by the Internet. Instead of me generating incoherent names
	 * using an array of characters, and a random number generator.
	 *****************************************************************/
	@Test
	public void testLastNameSort(){
		ArrayList <Student> students= new ArrayList<Student>();
		ArrayList <Student> comparison = new ArrayList<Student>();
		students.add(Ben);
		students.add(Ben);
		students.add(new Student("Jeanie","Bohl","5",4.00));
		students.add(new Student("Sal","Zarrellal","5",4.00));
		students.add(new Student("Pilar","Villafane","5",4.00));
		students.add(new Student("Raymond","Colene","5",4.00));
		Collections.sort(students);
		
		//sorting the array manually. By the expected result
		comparison.add(Ben);
		comparison.add(Ben);
		comparison.add(new Student("Jeanie","Bohl","5",4.00));
		comparison.add(new Student("Raymond","Colene","5",4.00));
		comparison.add(new Student("Pilar","Villafane","5",4.00));
		comparison.add(new Student("Sal","Zarrellal","5",4.00));
	
		for(int i =0; i<comparison.size(); i++){
			assertTrue(comparison.get(i).equals(students.get(i)));
		}
	}
	
	/******************************************************************
	 * Testing the first name sort functionality if the last names
	 * are equal to one another. 
	 *****************************************************************/
	@Test
	public void testFirstNameSort(){
		ArrayList <Student> students= new ArrayList<Student>();
		ArrayList <Student> comparison = new ArrayList<Student>();
		Ben.setFirst("Ben");
		
		students.add(Ben);
		students.add(Ben);
		students.add(new Student("Benson","Zed","1",4.00));
		students.add(new Student("Benson","Alpha","2", 3.50));
		students.add(new Student("Benson", "Omega", "3", 4.00));
		Collections.sort(students);
		
		//sorting the comparison array manually by the expected result.
		comparison.add(new Student("Benson","Alpha","2", 3.50));
		comparison.add(Ben);
		comparison.add(Ben);
		comparison.add(new Student("Benson", "Omega", "3", 4.00));
		comparison.add(new Student("Benson","Zed","1",4.00));
		
		for(int i=0; i<comparison.size(); i++){
			assertTrue(students.get(i).getFirstName()
					.equals(comparison.get(i).getFirstName()));
		}
	}
	
	@Test
	public void testCompareToMethodEquals(){
		Student ben = new Student("Ben","Ben","01",4.0);
		Student ben1 = new Student("Ben","Ben","01",4.0);
		assertEquals(0,ben.compareTo(ben1));
	}
	
	@Test
	public void testCompareToMethodLess(){
		Student ben = new Student("Ben","Ben","01",4.0);
		Student ben1 = new Student("C","Ben","01",4.0);
		assertTrue(ben.compareTo(ben1)<0);
	}
	
	@Test
	public void testCompareToMethodGreater(){
		Student ben = new Student("Ben","Ben","01",4.0);
		Student ben1 = new Student("C","Ben","01",4.0);
		assertTrue(ben1.compareTo(ben)>0);
	}
	
	/*****************************************************************
	 * Testing the Node Constructor
	 ****************************************************************/
	@Test
	public void testNodeConstructor(){
		assertNull(temp.getNext());
		assertNull(temp.getPrev());
		assertEquals(temp.getData(), Ben);
	}
	
	/*****************************************************************
	 * Testing the add top functionality with no list. Removing
	 * a student then adding them back onto the top of the list.
	 * Add another one on top of the list. And various other
	 * functionality that should break the code.
	 *****************************************************************/
	@Test
	public void testBreakAddTop(){
		addStudents();
		//Getting the basics down. Making sure Head, and Tail
		//Are pointing to the correct Nodes When multiple 
		//people are added. 
		assertEquals(Adam, database.getHead().getData());
		assertEquals(Jesse, database.getHead().getNext().getData());
		assertEquals(Ben, database.getTail().getData());
		assertEquals(Jesse, database.getTail().getPrev().getData());
	}
	
	@Test
	public void testFindStudentInList(){
		addStudents();
		//test that you've found a student in the DB
		assertEquals(database.find(Ben.getgNumber()),Ben);
	}
	
	@Test 
	public void testFindStudentNotInList(){
		addStudents();
		addOtherStudents();
		
		assertNull(database.find("150000"));
	}
	
	@Test
	public void testNoListFind(){
		LinkedList list = new LinkedList();
		assertNull(list.find(Ben.getgNumber()));
	}
	
	/******************************************************************
	 * Now that there are students added into the LinkedList
	 * we can test the remove Student functionality. And Check the
	 * references to make sure they match up.
	 *****************************************************************/
	@Test
	public void testBreakRemoveStudent(){
		breakStudentHelper();
		
		//Once everyone has been removed there shouldn't be a list.
		database.delStudent(1,Jesse.getgNumber());
		assertNull(database.getHead());
		assertNull(database.getTail());
		breakStudentHelper();
	}
	
	/*****************************************************************
	 * Test the Remove Student who's not in the list.
	 ****************************************************************/
	@Test
	public void testStudentNotInList(){
		addStudents();
		//a student who's not in the list.
		assertFalse(database.delStudent(1,"15"));
	}
	
	@Test
	public void updateAllStudents(){
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		database.update(1,Ben.getgNumber(), Adam);
		Node temp = database.getHead();
		while(temp != null){
			assertTrue(temp.getData().getName().
					equals(Adam.getName()));
			temp = temp.getNext();
		}
	}
	
	/******************************************************************
	 * test the update functionality.
	 *****************************************************************/
	@Test
	public void testUpdateFunctionality(){
		//test a faulty student.
		Student eric = new Student("Eric","w/e","001029",5.0);
		addStudents();
		assertTrue(database.update(1,Ben.getgNumber(),eric));
	}
	
	@Test
	public void testNoListUpdateFunctionality(){
		Student eric = new Student("Eric","w/e","001029",5.0);
		LinkedList stuff = new LinkedList();
		assertFalse(stuff.update(1,"T", eric));
	}
	
	@Test 
	public void testUpdateAllFunctionality(){
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		Student adam = new Student("Rosen","Adam","05",3.98);
		
		Node temp = database.getHead();
		database.update(1,Ben.getgNumber(), adam);
		
		while(temp!=null){
			assertTrue(temp.getData().getFirstName().equals("Adam"));
			assertTrue(temp.getData().getLast().equals("Rosen"));
			assertTrue(temp.getData().getgNumber().equals(Ben
								.getgNumber()));
			assertTrue(temp.getData().getGpa()==3.98);
			temp = temp.getNext();
		}
	}
	
	@Test
	public void testFirstNameUpdate(){
		database.addTop(1,Ben);
		Student adam = new Student("Rosen","Adam","05",3.98);
		Node temp = database.getHead();
		database.update(1,Ben.getgNumber(), adam);
		
		assertTrue(temp.getData().getFirstName().equals("Adam"));
	}
	
	@Test
	public void testLastnameUpdate(){
		database.addTop(1,Ben);
		Student adam = new Student("Rosen","Adam","05",3.98);
		Node temp = database.getHead();
		database.update(1,Ben.getgNumber(), adam);
		
		assertTrue(temp.getData().getLast().equals("Rosen"));
	}
	
	@Test
	public void testGPAUpdate(){
		database.addTop(1,Ben);
		Student adam = new Student("Rosen","Adam","05",3.98);
		Node temp = database.getHead();
		database.update(1,Ben.getgNumber(), adam);
		assertTrue(temp.getData().getGpa()==3.98);
	}
	
	@Test
	public void testLargerDatabaseFindFunctionality(){
		//add student to the top
		addStudents();
		//add students to the bottom
		addOtherStudents();
		//test that you've found a student in the DB
		assertEquals(database.find(Ben.getgNumber()),Ben);
		
		//test that you cannot find a student.
		assertNull(database.find("093821"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testBadGNumberFind(){
		addStudents();
		addOtherStudents();
		database.find("93208532902580");
	}
	
	/******************************************************************
	 * Test the Remove
	 *****************************************************************/
	@Test
	public void testAddTopNullListHead(){
		database.addTop(1,Jesse);
		assertEquals(database.getHead().getData(), Jesse);		
	}
	
	@Test
	public void testAddTopNullListTail(){
		database.addTop(1,Jesse);
		assertEquals(database.getTail().getData(), Jesse);		
	}
	
	@Test
	public void testAddTopOneItemListHead(){
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		assertEquals(database.getHead().getData(), Jesse);		
	}
	
	@Test
	public void testAddTopOneListTail(){
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		assertEquals(database.getTail().getData(), Ben);		
	}
	
	@Test
	public void testAddTopMultListHead(){
		database.addTop(1,Adam);
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		assertEquals(database.getHead().getData(), Jesse);		
	}
	
	@Test
	public void testAddTopMultListTail(){
		database.addTop(1,Adam);
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		assertEquals(database.getTail().getData(), Adam);		
	}
	
	@Test
	public void testAddEndNullListHead(){
		database.addEnd(Jesse);
		assertEquals(database.getHead().getData(), Jesse);		
	}
	
	@Test
	public void testAddEndNullListTail(){
		database.addEnd(Jesse);
		assertEquals(database.getTail().getData(), Jesse);		
	}
	
	@Test
	public void testAddEndOneItemListHead(){
		database.addEnd(Ben);
		database.addEnd(Jesse);
		assertEquals(database.getHead().getData(), Ben);		
	}
	
	@Test
	public void testAddEndOneListTail(){
		database.addEnd(Ben);
		database.addEnd(Jesse);
		assertEquals(database.getTail().getData(), Jesse);		
	}
	
	@Test
	public void testAddEndMultListHead(){
		database.addEnd(Adam);
		database.addEnd(Ben);
		database.addEnd(Jesse);
		assertEquals(database.getHead().getData(), Adam);		
	}
	
	@Test
	public void testAddEndMultListTail(){
		database.addEnd(Adam);
		database.addEnd(Ben);
		database.addEnd(Jesse);
		assertEquals(database.getTail().getData(), Jesse);		
	}
	
	@Test
	public void testToStringOneItemList(){
		database.addEnd(Jesse);
		assertTrue((database.toString().
				replaceAll("\\s", "")).equals("Roe,"
				+ "Jesse10813.14"));
	}
	
	@Test
	public void testToStringTwoItemList(){
		database.addTop(1,Jesse);
		database.addTop(1,Ben);
		assertTrue((database.toString().
				replaceAll("\\s", "")).
				equals("Benjamin,Benson1233.25Roe,"
				+ "Jesse10813.14"));
	}
	
	@Test
	public void testToStringThreeItemList(){
		database.addTop(1,Jesse);
		database.addTop(1,Ben);
		database.addTop(1,Adam);
		assertTrue((database.toString().replaceAll("\\s", "")).
				equals("RosenBerg,Adam"
						+ "39201033.14Benjamin,Benson1233.25Roe,"
				+ "Jesse10813.14"));
	}
	
	@Test
	public void testToStringNullItemList(){
		assertTrue((database.toString().
				replaceAll("\\s", "")).
				equals("There'snobodyinthislist"));
	}
	
	@Test
	public void testNoListRmDuplicates(){
		LinkedList list = new LinkedList();
		list.removeDuplicates();
		assertNull(list.find(Ben.getgNumber()));
	}
	
	@Test
	public void testnoDuplicatesFound(){
		addStudents();
		//There shouldn't be any duplicates.
		database.removeDuplicates();
		//compare how the database should look
		Node temp = database.getHead();
		assertTrue(temp.getData().equals(Adam));
		assertTrue(temp.getNext().getData()
				.equals(Jesse));
		assertTrue(temp.getNext().getNext()
				.getData().equals(Ben));
	}
	
	@Test
	public void testDuplicatesAtEnd(){
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		addOtherStudents();
		database.removeDuplicates();
		Node temp = database.getHead();
		int numberOfDuplicates=0;
		while(temp!=null){
			if(temp.getData().equals(Ben)){
				numberOfDuplicates++;
			}
			temp = temp.getNext();
		}
		//there should only be one Ben object in the database.
		assertTrue(numberOfDuplicates==1);
	}
	
	@Test
	public void testDuplicatesAtTop(){
		addOtherStudents();
		addStudents();
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		database.addTop(1,Ben);
		database.removeDuplicates();
		//Add lots of Ben objects to the top
		Node temp = database.getHead();
		int numberOfDuplicates=0;
		while(temp!=null){
			if(temp.getData().equals(Ben)){
				numberOfDuplicates++;
			}
			temp = temp.getNext();
		}
		//there should only be one Ben object in the database.
		assertTrue(numberOfDuplicates==1);
	}
	
	@Test
	public void testDuplicatesInMiddle(){
		addStudents();
		addOtherStudents();
		database.addEnd(Ben);
		database.addEnd(Ben);
		database.addEnd(Jesse);
		database.addEnd(Jesse);
		database.removeDuplicates();
		
		int numberOfBenDuplicates=0;
		while(temp!=null){
			if(temp.getData().equals(Ben)){
				numberOfBenDuplicates++;
			}
			temp = temp.getNext();
		}
		//there should only be one Ben object in the database.
		assertTrue(numberOfBenDuplicates==1);
	}
	
	@Test
	public void testRemoveDuplicatesEveryOther(){
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		database.addTop(1,Ben);
		database.addTop(1,Jesse);
		database.removeDuplicates();
		
		Node temp = database.getHead();
		int numberOfBenDuplicates=0;
		while(temp!=null){
			if(temp.getData().equals(Ben)){
				numberOfBenDuplicates++;
			}
			temp = temp.getNext();
		}
		//there should only be one Ben object in the database.
		assertTrue(numberOfBenDuplicates==1);
	}	
	
	@Test
	public void testSaveSerializableOneItem(){
		simpleDB.insert(Ben);
		simpleDB.insert(Jesse);
		simpleDB.insert(Ben);
		simpleDB.insert(Jesse);
		simpleDB.insert(Ben);
		simpleDB.insert(Jesse);
		simpleDB.saveDB("/home/bensonb/workspace/stuff");
		simpleDB.loadDB("/home/bensonb/workspace/stuff");
		Node temp = simpleDB.getHead();
		simpleDB.removeDuplicates();
		assertTrue(temp.getData().equals(Jesse));
		assertTrue(temp.getNext().getData().equals(Ben));
	}
	
	@Test
	public void testNodeMainConstructor(){			
		Node temp1 = new Node(Ben, null, null);
		Node temp2 = new Node(Jesse, null, null);
		Node temp3 = new Node(Adam, temp1, temp2);
		assertEquals(temp3.getPrev(), temp2);
		assertEquals(temp3.getNext(), temp1);		
	}
	
	@Test
	public void testNodeGetData(){		
		assertEquals(temp.getData(), Ben);
	}
	
	@Test
	public void testNodeSetData(){
		Node temp1 = new Node(Ben, null, null);
		assertEquals(temp1.getData(), Ben);
		temp1.setData(Jesse);
		assertEquals(temp1.getData(), Jesse);
	}
	
	@Test
	public void testNodeSetNext(){
		Node temp1 = new Node(Ben, null, null);
		Node temp2 = new Node(Jesse, null, null);
		temp1.setNext(temp2);		
		assertEquals(temp1.getNext().getData(), Jesse);
	}
	
	@Test
	public void testNodeSetPrev(){
		Node temp1 = new Node(Ben, null, null);
		Node temp2 = new Node(Jesse, null, null);
		temp2.setPrev(temp1);		
		assertEquals(temp2.getPrev().getData(), Ben);
	}
	
	@Test
	public void testNodeGetNextOneEntry(){
		database.addTop(1,Ben);
		assertNull(database.getHead().getNext());		
	}
	
	@Test
	public void testNodeGetPrevOneEntry(){
		database.addTop(1,Ben);		
		assertNull(database.getTail().getPrev());		
	}
	
	
	@Test
	public void testUndoaddTop(){
		database.addTop(1,Ben);
		database.undo();
		assertNull(database.getHead());
	}
	
	@Test
	public void testUndoMultipleAddTop(){
		database.addTop(1, Ben);
		database.addTop(1, Ben);
		database.addTop(1, Ben);
		database.addTop(1, Ben);
		database.undo();
		database.undo();
		database.undo();
		database.undo();
		assertNull(database.getHead());
		assertNull(database.getTail());
	}
	
	
	@Test
	public void testSortUndo(){
		addOtherStudents();
		database.addTop(1,Ben);
		database.sort();
		database.undo();
		assertTrue(database.getHead().getData().getName()
		.replaceAll("\\s","").equals(Ben.getName()
				.replaceAll("\\s", "")));
	}
	
	@Test
	public void testUndoRemoveDuplicates(){
		addStudents();
		addStudents();
		database.removeDuplicates();
		database.undo();
		assertTrue(database.getHead().getData().
				getName().charAt(0)=='R');
		assertTrue(database.getHead().getNext().
				getData().getName().charAt(0)=='R');
	}
	
	@Test
	public void testUndoRemoveNoDuplicates(){
		addStudents();
		database.removeDuplicates();
		database.undo();
		assertTrue(database.getHead().getData().
				getName().charAt(0)=='R');
		assertTrue(database.getHead().getNext().
				getData().getName().charAt(0)=='R');
	}
	
	@Test
	public void testRemoveManyDuplicates(){
		database.addEnd(Ben);
		database.addEnd(Ben);
		database.addEnd(Ben);
		database.removeDuplicates();
		database.undo();
		assertTrue(database.getHead().getData().
				getName().charAt(0)=='B');
		assertTrue(database.getHead().getNext().getData().
				getName().charAt(0)=='B');
	}
	
	@Test
	public void testSortTwoEntry(){
		database.addTop(1,Ben);	
		database.addTop(1, Jesse);
		database.sort();
		assertEquals(database.getHead().getData(),Ben);
		assertEquals(database.getTail().getData(),Jesse);		
		database.undo();
		assertTrue(database.getHead().
				getData().getFirstName().charAt(0)=='R');
		assertTrue(database.getTail().
				getData().getFirstName().charAt(0)=='B');
	}
	
	
	@Test
	public void testUpdateOneEntry(){
		Student temp = new Student("Benson", "Tom", "123456", 3.2);
		database.addTop(1, Ben);
		database.update(1, "123", temp);
		assertEquals(database.getHead().
				getData().getFirstName(), "Tom");		
		assertEquals(database.getHead().
				getData().getgNumber(), "123");		
		assertTrue(database.getHead().
				getData().getGpa() == 3.2);		
	}
	
	@Test
	public void testUpdateTwoEntry(){
		Student temp = new Student("Benson", "Tom", "123456", 3.2);
		database.addTop(1, Ben);
		database.addTop(1, Ben);
		database.update(1, "123", temp);
		assertEquals(database.getHead().
				getData().getFirstName(), "Tom");		
		assertEquals(database.getHead().
				getData().getgNumber(), "123");		
		assertTrue(database.getHead().
				getData().getGpa() == 3.2);	
		assertEquals(database.getTail().
				getData().getFirstName(), "Tom");		
		assertEquals(database.getTail().
				getData().getgNumber(), "123");		
		assertTrue(database.getTail().
				getData().getGpa() == 3.2);		
	}
	
	@Test
	public void testUpdateThreeEntry(){
		Student temp = new Student("Benson", "Tom", "123456", 3.2);
		database.addTop(1, Jesse);
		database.addTop(1, Ben);
		database.addTop(1, Ben);		
		database.update(1, "123", temp);
		assertEquals(database.getHead().
				getData().getFirstName(), "Tom");		
		assertEquals(database.getHead().
				getData().getgNumber(), "123");		
		assertTrue(database.getHead().
				getData().getGpa() == 3.2);	
		assertEquals(database.getTail().
				getData().getFirstName(), "Roe");		
		assertEquals(database.getTail().
				getData().getgNumber(), "1081");		
		assertTrue(database.getTail().
				getData().getGpa() == 3.14);		
	}
	
	@Test
	public void testReverseOneEntry(){
		database.addTop(1, Ben);
		database.reverseDatabase(1);
		assertEquals(database.getHead().
				getData(), Ben);		
		database.undo();		
		assertEquals(database.getHead().
				getData(), Ben);		
	}
	
	@Test 
	public void testReverseTwoEntry(){
		database.addTop(1, Ben);
		database.addTop(1, Jesse);
		database.reverseDatabase(1);
		assertEquals(database.getHead().
				getData(), Ben);
		assertEquals(database.getTail().
				getData(), Jesse);		
		database.undo();
		assertEquals(database.getHead().
				getData(), Jesse);
		assertEquals(database.getTail().
				getData(), Ben);	
	}
	
	@Test 
	public void testDeleteOneEntry(){
		database.addTop(1, Ben);
		database.delStudent(1, Ben.getgNumber());
		assertNull(database.getHead());
		database.undo();
		assertTrue(database.getHead().getData().
				getFirstName().charAt(0) == 'B');
	}
	
	@Test 
	public void testDeleteTwoEntry(){
		database.addTop(1, Ben);
		database.addTop(1, Jesse);
		database.delStudent(1, Jesse.getgNumber());
		System.out.println(database);
		assertTrue(database.size() == 1);
		database.undo();
		System.out.println(database);
		assertTrue(database.getTail().getData().
				getFirstName().charAt(0) == 'R');
	}	
	
}
