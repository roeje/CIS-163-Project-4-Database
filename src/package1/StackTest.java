package package1;

public class StackTest {

	public static void main(String[] args) {
		LinkedList test = new LinkedList();
		Student ben = new Student("Benson","benjamin","01",3.24);
		Student we = new Student("Jesse","Roe","019391",4.9);
		test.addTop(1,ben);
		test.addTop(1,ben);
		test.addTop(1,ben);
		test.addTop(1,ben);
		test.addTop(1,we);
		System.out.println(test);
		test.sort();
		
		System.out.println(test);
		test.undo();
		System.out.println(test);
	}

}
