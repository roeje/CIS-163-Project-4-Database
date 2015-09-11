package package1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * A database structure class based on the concept of references.
 *********************************************************************/
public class LinkedList implements Serializable {
	
	/**The serializable ID number of this class.**/
	private static final long serialVersionUID = 1L;
	
	/**The number of nodes within the list**/
	private int count;  
	
	/**The first index of the list head of the list**/
	private Node head;      
	
	/**The last index of the list the tail of the list**/
	private Node tail;
	
	/**Storage for all the method calls and students deleted**/
	private Stack<String> stack;
	
	public LinkedList(){
		stack = new Stack<String>();
		head = null;
		tail = null;
		count = 0;
	}
	
	/*****************************************************************
	 * No, java at a bar will not get you laid.
	 * @param data
	 * The student you want to add to the top of the linked list.
	 * @param unlock
	 * pass in 1 to unlock the push to stack functionality.
	 * @param data
	 * Student object
	 ****************************************************************/
	public void addTop(int unlock, Student data){
	
		Node temp = null;
		
		// Case were list is empty
		if(head == null){
			temp = new Node(data, null, null);
			if(unlock==1){
				stack.push(saveStudentData(temp));
				stack.push("addTop ");
			}
			head = temp;
			tail = temp;
			count++;
			return;	
		}		
		
		// Case were list has at least one item
		temp = new Node(data);
		temp.setNext(head);
		head.setPrev(temp);
		head = temp;
		if(unlock==1){
			stack.push(saveStudentData(temp));
			stack.push("addTop ");
		}
		//increment the size of the list.
		count++;
	}
	
	/******************************************************************
	 * Inserts a node after the specified index.
	 * @param node
	 * The node you're inserting after
	 * @param student
	 * The students data you're inserting into the Linked List
	 * @param node
	 * Node object 
	 *****************************************************************/
	public void insertAfter(Node node, Student student){
		Node tempNew =  new Node(student, null, null);
		
		Node temp = head;
		if(head == null && tail == null){
			head = tempNew;
			return;
		}
		
		if(head == node && tail == node){
			tempNew.setPrev(head);
			tempNew.setNext(head.getNext());			
			head.setNext(tempNew);
			tail = tempNew;
			return;			
		}
		
		if(head == node && tail != node){
			tempNew.setPrev(head);
			tempNew.setNext(head.getNext());
			head.getNext().setPrev(tempNew);
			head.setNext(tempNew);
			
			return;	
		}
		
		if(tail == node){
			tempNew.setPrev(tail);
			tail.setNext(tempNew);
			tail = tempNew;	
			return;
		}		
		
		while(temp != null){
			if(temp == node){
				tempNew.setPrev(temp);
				tempNew.setNext(temp.getNext());
				temp.getNext().setPrev(tempNew);
				temp.setNext(tempNew);
				return;
			}
			temp = temp.getNext();	
		}	
		
	}
	
	/*****************************************************************
	 * The head of the linked list.
	 * @return
	 * The head of the linked list.
	 ****************************************************************/
	public Node getHead(){
		return head;
	}
	
	/******************************************************************
	 * Gets the tail of the Linked linked, and whatever it is pointing
	 * to.
	 * @return
	 * Node tail
	 *****************************************************************/
	public Node getTail(){
		return tail;
	}
	
	/******************************************************************
	 * Deletes a student with a matching GNumber. Only deletes first
	 * student within the linked that's found with the matching
	 * GNumber.
	 * @param data
	 * The student you're trying to delete
	 * @param unlock
	 * pass in 1 to unlock the push to stack functionality.
	 * @return boolean
	 * Return true if the student you wanted to delete was found.
	 * Return false if the student you wanted to delete was not found.
	 *****************************************************************/
	public boolean delStudent(int unlock, String data){
		
		Node temp = findNode(data);
		//if there was nobody found with that GNumber.
		if(temp == null){
			return false;
		}
		if(head == temp && tail == temp){
			head = null;
			tail = null;
			if(unlock == 1){
				stack.push(saveStudentData(temp));
				stack.push("delete "+getIndex(temp.getData()));
			}
			return true;
			
		}
		//if there is no list
		else if(head == null && tail == null){
			return false;
		}
		//if the student is at the top
		if(temp == head){
			count--;
			head = head.getNext();
			head.setPrev(null);
			if(unlock == 1){
				stack.push(saveStudentData(temp));
				stack.push("delete "+getIndex(temp.getData()));
				
			}
			return true;
		}
		//the tail is the node that we need to delete
		else if(temp == tail){
			count--;
			tail = tail.getPrev();
			tail.setNext(null);
			if(unlock == 1){
				stack.push(saveStudentData(temp));
				stack.push("delete "+getIndex(temp.getData()));
			}
			return true;
		}
		if(unlock == 1){
			stack.push(saveStudentData(temp));
			stack.push("delete "+getIndex(temp.getData()));
		}
		//the student is in the middle
		temp.getNext().setPrev(temp.getPrev());
		temp.getPrev().setNext(temp.getNext());
		return true;
	}
	
	/******************************************************************
	 * Will undo any actions within the Linked List.
	 * Uses a switch implementation. Each case represents a different
	 * type of undo situation.
	 *****************************************************************/
	public void undo(){
		if(stack.isEmpty()){
			return;
		}
		String undo = (String) stack.pop();
	
		int index = undo.indexOf(" ");
		
		// String that contains method key
		String method = undo.substring(0,index);
	switch(method){
		case "removeduplicates":
			int numberOfRemoves = Integer.parseInt(undo.
					substring(index+1, undo.length()));
			for(int i=0; i<numberOfRemoves;i++){
				String pop = (String)stack.pop();
				//The position of the Student within the Linked List
				int position = Integer.parseInt(pop.
						substring(pop.indexOf(" ")+1,pop.length()));
			
				String data = (String)stack.pop();
				String[] studentData = data.split(" ");
				Student temp = new Student(studentData[0],
						studentData[1],studentData[2]
						,Double.parseDouble(studentData[3]
								.replaceAll("\\s", "")));
				Node tempNode = findByIndex(position);
				insertAfter(tempNode, temp);
				if(numberOfRemoves-1 == i){
					break;
				}
			}	
			break;
		
		case "addTop":
			String pop = stack.pop();
			String[] studentData = pop.split(" ");
			Student temp = new Student
					(studentData[0],studentData[1],studentData[2]
					,Double.parseDouble
					(studentData[3].replaceAll("\\s", "")));
			delStudent(0,temp.getgNumber());
			break;
			
		case "delete":
			int position = Integer.parseInt(undo.
					substring(undo.indexOf(" ")+1,undo.length()));
			String data = (String)stack.pop();
			String[] studentData1 = data.split(" ");
			Student temp1 = new Student
					(studentData1[0],studentData1[1],studentData1[2]
					,Double.parseDouble(
							studentData1[3].replaceAll("\\s", "")));
			Node tempNode = findByIndex(position);
			insertAfter(tempNode,temp1);
			break;
		case "reverse":
		
			reverseDatabase(0);
			break;
		case "update":
			String data1 = (String)stack.pop();
			String[] studentData11 = data1.split(" ");
			Student temp11 = new Student
					(studentData11[0],studentData11[1]
							,studentData11[2]
					,Double.parseDouble
					(studentData11[3].replaceAll("\\s", "")));
			update(0, temp11.getgNumber(), temp11);
			break;
		case "sort":
			head = null;
			tail = null;
			int indexTemp = Integer.parseInt
					(undo.substring(index+1, undo.length()));
			for(int i = 0; i < indexTemp; i++){
				String student = (String)stack.pop();
				String[] studentArray = student.split(" ");
				Student tempStudent = new Student
						(studentArray[0],studentArray[1],
								studentArray[2]
				,Double.parseDouble(
						studentArray[3].replaceAll("\\s", "")));
				addTop(0, tempStudent);
				}
			break;
			}	
		}	
	
	/****************************************************************
	 * Saves the Students Data as a string
	 * @param temp
	 * The Node you're retrieving the data from.
	 * @return
	 * A String representing the student's data.
	 ***************************************************************/
	private String saveStudentData(Node temp){
		Student data = temp.getData();
		return data.getLast()+" "+data.getFirstName()+
				" "+data.getgNumber()+" "+data.getGpa()+" ";
	} 
	
	/******************************************************************
	 * Deletes a student base on a node object that is passed in.
	 * @param person
	 * The node of the person you're trying to delete.
	 * @return
	 * true if the person has been deleted from the list.
	 *****************************************************************/
	public boolean delStudent(Node person){
		//if the person we passed in is null
		if(person == null){
			return false;
		}
		//top case
		if(person == head){
			pushStack(person, "remove "+getIndex(person.getData()));
			count--;
			head = head.getNext();
			head.setPrev(null);
			return true;
		}
		//the tail is the node that we need to delete
		else if(person == tail){
			pushStack(person, "remove "+getIndex(person.getData()));
			count--;
			tail = tail.getPrev();
			tail.setNext(null);
			return true;
		}
		pushStack(person, "remove "+getIndex(person.getData()));
		//the student is in the middle
		count--;
		person.getNext().setPrev(person.getPrev());
		person.getPrev().setNext(person.getNext());
		return true;
	}
	
	/*****************************************************************
	 * Pushes a Nodes data onto the top of the stack.
	 * @param temp
	 * The Node's data you wish to push onto the stack.
	 * @param method
	 * The method  you're saving it as.
	 *****************************************************************/
	private void pushStack(Node temp, String method){
		stack.push(saveStudentData(temp));
		stack.push(method);	
	}
	
	
	
	/******************************************************************
	 * Find's a node that the student has been at.
	 * @param studentInfo
	 * Their G Number you're searching for
	 * @return
	 * The node of the student you're trying to find. If the student
	 * is not found the method will return null.
	 *****************************************************************/
	public Node findNode(String studentInfo){
		Node temp = head;
		while(temp != null){
			if(temp.getData().getgNumber().equals(studentInfo)){
				return temp;
			}
			temp = temp.getNext();
		}
		return null;
	}
	
	/******************************************************************
	 * Update a student's information based on what you're given.
	 * @param unlock
	 * pass in 1 to unlock the push to stack functionality.
	 * @param gNumber
	 * The GNumber you're change the student name to.
	 * @param student
	 * The student you're trying to update.
	 * @return
	 * return true if the student's information 
	 * is successfully updated.
	 * return false if the student you were trying to look for was 
	 * not found. 
	 *****************************************************************/
	 public boolean update(int unlock, String gNumber, 
			 							Student student){
		 
		//case where there is no list
		if(gNumber.equals("")){
			return false;
		}else if(head == null){
			return false;
		}
		
		Node temp = findNode(gNumber);  
		if(temp == null){
			return false;
		}
		if(unlock == 1){
			stack.push(saveStudentData(temp));
			stack.push("update ");
		}
		
		// Update the student found with the new info.
		Student update = temp.getData();
		update.setFirst(student.getFirstName());
		update.setLast(student.getLast());
		update.setGpa(student.getGpa());
		return true;
	 }
	 
	 /*****************************************************************
	  * Find a student based on their GNumber.
	  * @param gNumber
	  * The GNumber of the student you wish to find.
	  * @return
	  * Returns a student object if the student you're looking for
	  * is found. Otherwise, will return null if the student is not 
	  * found, or the list is empty.
	  ****************************************************************/
	 public Student find(String gNumber){
		try{
			Integer.parseInt(gNumber);
		}catch(NumberFormatException ex){
			throw new IllegalArgumentException();
		}
		
		if(head == null){
			return null;
		}
		
		Node temp = findNode(gNumber);
		if(temp == null){
			return null;
		}
		return temp.getData();
	 }
	 
	 /*****************************************************************
	  * Will remove any duplicates matching the first and last name
	  * of the individual.
	  ****************************************************************/
	 public void removeDuplicates(){
		 //there's no list. return false;
		 if(head ==null){
			 return;
		 } 
		 
		 Node temp = head;
		 Node temp2 = temp.getNext();
		 int numberOfRemove =0;
		 while(temp != null){
			 while(temp2 != null){
				 if(temp.getData().getName().equals(
						 temp2.getData().getName())){
					 numberOfRemove++;
					 Node temp3 = temp2;
					 temp2 = temp2.getNext();
					 delStudent(temp3);
					 count--;
				 } else {
					 temp2 = temp2.getNext();
				 }
			 }
			 temp = temp.getNext();
			 if (temp == null) {
				 break;
			 } else {
				 temp2 = temp.getNext();
			 }
		 }	 
		 stack.push("removeduplicates "+numberOfRemove);
	 }
	 
	 /*****************************************************************
	  * Reverse the entire database of student objects.
	  * @param unlock
	  * pass a value of 1 to unlock the push to stack function 
	  ****************************************************************/
	 public void reverseDatabase(int unlock){
		 
		 // Case where list is empty
		 if(head == null){
			 return;
		 }
		 
		 // Case where list has one entry
		 if(head.getNext()==null){
			 stack.push("reverse ");
			 return;
		 }
		 
		 Node current = head.getNext();
		 Node prev = head;
		 Node next = current.getNext();
		 
		 //new bottom of the list
		 prev.setNext(null);
		 current.setNext(prev);
		 prev.setPrev(current);
		 
		 while(next != null){
			 prev = current;
			 current = next;
			 next = next.getNext();
			 if(next==tail){
				 current.setPrev(next);
				 current.setNext(prev);
				 prev = current;
				 current = next;
				 break;
			 }
			 current.setNext(prev);
			 prev.setPrev(current);
		 }
		 
		 tail = head;
		 head = current;
		 current.setPrev(null);
		 current.setNext(prev);
		 if(unlock ==1){
			 stack.push("reverse ");
		 }
	 }
	 
	 /*****************************************************************
	  * Sorts the database based on the name of the student data.
	  ****************************************************************/
	 public void sort(){
		 int count =0;
		 if(head==null){
			 return;
		 }
		 ArrayList <Student> temp = new ArrayList<Student>();
		 Node top = head;
		 while(top != null){
			 stack.push(saveStudentData(top));
			 temp.add(top.getData());
			 top = top.getNext();
			 count++;
		 }
		 stack.push("sort "+count);
		 Collections.sort(temp);		
		 tail = null;
		 head = null;
		 
		 // Restructures list based on data stored in the ArrayList
		 for(int i =0; i<temp.size(); i++){
			 addEnd(temp.get(i));
		 }
	 }
	 
	 /*****************************************************************
	  * Adds the specified node at the end of the Database.
	  * @param data
	  * The students data to be added into the linked list database.
	  ****************************************************************/
	 public void addEnd(Student data){
		 Node temp = new Node(data, null, null);
		 //if there is no list.
		 if(head == null){
				head = tail = temp;
				count++;
				return;	
		 }
		 
		 //the previous bottom
		 Node bottom = tail;
		 
		 //instead of null bottom should point to the new Node
		 bottom.setNext(temp);
		 
		 //The previous node should now be bottom
		 temp.setPrev(bottom);
		 
		 //set the new end's next to null.
		 temp.setNext(null);
		 
		 //tail is now pointing at temp.
		 tail = temp;
		 count++;
	 }
	 
	 

	/******************************************************************
	 * A representation of the current status of the database.
	 * @return
	 * Return a string of the database itself.
	 *****************************************************************/
	public String toString(){
		if(head == null){
			return "There's nobody in this list";
		}
		Node temp = head;
		String toString = "";
		
		while(temp !=null ){
			toString += temp.getData().toString()+"\n";
			temp = temp.getNext();
		}
		
		return toString;
	}
	
	/******************************************************************
	 * Useful method for determining if your getPrev() methods are 
	 * working in the program, and the tail pointer is pointing
	 * at the correct End Node.
	 * @return
	 * Will return a String in Reverse order of the database.
	 *****************************************************************/
	public String toStringReverse(){
		if(tail==null){
			return "There's nobody in this list";
		}
		Node temp = tail;
		String toString ="";
		while(temp !=null){
			toString += temp.getData().toString()+"\n";
			temp = temp.getPrev();
		}
		return toString;
	}
	
	/******************************************************************
	 * get the size of the Linked list
	 * @return
	 * The integer of the size of the linked list.
	 *****************************************************************/
	public int size(){
		return count;
	}
	
	/******************************************************************
	 * Get's a students specific index based on passed student data.
	 * @param tempStu
	 * The students info you're trying to find.
	 * @return
	 * The index of the Linked List.
	 *****************************************************************/
	public int getIndex(Student tempStu){
		Node temp = head;
		int count = 1;		
		
		while(temp != null){
			
			if(temp.getData() == tempStu){
				return count;
			}				
			count++;
			temp = temp.getNext();
		}
		return -1;
	}
	
	/******************************************************************
	 * Finds a student based on the index.
	 * @param index
	 * The index of the person you're trying to trying to find.
	 * @return
	 * The person at that specific index. Will return the tail if
	 * the person is not found. 
	 *****************************************************************/
	public Node findByIndex(int index){
		Node temp = head;
		int count = 1;			
		while(temp != null){
			
			if(count == index){
				return temp;
			}				
			count++;
			temp = temp.getNext();
		}
		return tail;
			
	}
}

