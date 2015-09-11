package package1;

import java.io.Serializable;

/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * A representation of Data in a Data Structure.
 *********************************************************************/
public class Node implements Serializable{
	
	/**The Serializable ID number for node**/
	private static final long serialVersionUID = 2423002237656226476L;

	/**One of the Students in the list**/
	public Student data;
	
	/**The next index within the Linked List**/
	public Node next;
	
	/**The previous Node**/
	public Node prev;

	/******************************************************************
	 * The previous index in the array
	 * @return the previous node in list
	 * get the Last Node.
	 *****************************************************************/
	public Node getPrev() {
		return prev;
	}

	/******************************************************************
	 * Set the Previous Node's previous Node.
	 * @param prev the prev to set
	 * The node you're point the last object to
	 *****************************************************************/
	public void setPrev(Node prev) {
		this.prev = prev;
	}

	public Node(Student data, Node next, Node prev) {
		super();
		this.data = data;
		this.next = next;
		this.prev = prev;
	}
	
	/******************************************************************
	 * An empty node constructor, use the set Data, and set next
	 * functionality to set up the Rest of the pieces.
	 *****************************************************************/
	Node(){
		super();
	}
	
	/******************************************************************
	 * Sets up a new Node only with some student data. Be sure to set
	 * the next piece of data to the correct Node.
	 * @param data
	 * The node's data contained within in.
	 *****************************************************************/
	public Node(Student data) {
		this.data = data;
	}

	/******************************************************************
	 * Returns the next node in the list.
	 * @return the next
	 * returns the next index of the Linked List
	 *****************************************************************/
	public Node getNext() {
		return next;
	}

	/******************************************************************
	 * sets the next node in the linked list.
	 * @param next the next to set
	 * The node passed in the be the new location of this node.
	 *****************************************************************/
	public void setNext(Node next) {
		this.next = next;
	}
	
	/******************************************************************
	 * Get the student data at this current index
	 * @return
	 * returns the Student object at this position.
	 *****************************************************************/
	public Student getData(){
		return data;
	}
	
	/*****************************************************************
	 * Changes the data of the Student at the current position.
	 * @param pData
	 * The Student data passed in will be used for the new Student.
	 *****************************************************************/
	public void  setData(Student pData){
		this.data = pData;
	}
	
	/*****************************************************************
	 * If this nodes next, and previous nodes are the same
	 * then this.node is the same as other.node.
	 * @param comparison
	 * The node you're comparing this node to.
	 * @return
	 * Return if the refernces to the pointers are the same.
	 * Otherwise return false if the pointers are not the same.
	 *****************************************************************/
	public boolean equals(Node comparison){
		if((this.getNext()==comparison.getNext())&&
			this.getPrev()==comparison.getPrev()){
			return true;
		}
		return false;
	}
}
