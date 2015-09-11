package package1;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**********************************************************************
 * CIS 163
 * @author bensonb and Jesse Roe
 * Creates an instance of the Linked List, and implements methods
 * within the linked List class.
 *********************************************************************/
public class SimpleDatabase implements ISimpleDatabase, Serializable{
	
	private static final long serialVersionUID = 2941668517197960301L;
	private LinkedList gvDataBase;
	
	public SimpleDatabase(){
		gvDataBase = new LinkedList();
	}
	
	public String toString(){
		return gvDataBase.toString();
	}
	
	public String display(){
		return gvDataBase.toString()+"\n"+
		"-----------------------------------";
		
	}
	
	@Override
	public void insert(Student student) {
		gvDataBase.addTop(1,student);
	}

	@Override
	public boolean delete(String gNumber) {
		return gvDataBase.delStudent(1,gNumber);
	}

	@Override
	public boolean update(String gNumber, Student student) {
		return gvDataBase.update(1,gNumber, student);
	}

	@Override
	public Student find(String gNumber) {
		return gvDataBase.find(gNumber);
	}

	@Override
	public void reverseList() {
		gvDataBase.reverseDatabase(1);
	}

	@Override
	public void removeDuplicates() {
		gvDataBase.removeDuplicates();
	}

	@Override
	public void Sort() {
		gvDataBase.sort();
	}
	
	@Override
	public void undo() {
		gvDataBase.undo();
	}

	@Override
	public void saveDB(String filepath) {
		try{
			FileOutputStream fos = new FileOutputStream(filepath);
			ObjectOutputStream os = new ObjectOutputStream(fos);
			os.writeObject(gvDataBase);
			os.close();
		}catch(IOException exception){
			exception.printStackTrace();
		}
		
	}

	@Override
	public void loadDB(String filepath) {
		try{
			FileInputStream fis = new FileInputStream(filepath);
			ObjectInputStream is = new ObjectInputStream(fis);
			gvDataBase = (LinkedList)is.readObject();
			is.close();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	public Node getHead(){
		return gvDataBase.getHead();
	}
	
	public Node getTail(){
		return gvDataBase.getTail();
	}
}

