import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class treesearch{
	public static void main(String[] args){

		BPlusTree mytree = new BPlusTree(3);

		System.out.println("----------------Insert 1--------------------");
		mytree.insert(1,"val1");
		System.out.println("----------------Insert 3--------------------");
		mytree.insert(3,"val3");
		System.out.println("----------------Insert 5--------------------");
		mytree.insert(5,"val5");
		
		System.out.println("----------------Insert 6--------------------");
		
		mytree.insert(6,"val6");
		System.out.println("----------------Insert 9--------------------");
		mytree.insert(9,"val9");
		System.out.println("----------------Insert 16--------------------");
		mytree.insert(16,"val16");
		System.out.println("----------------Insert 17--------------------");
		mytree.insert(17,"val17");
		System.out.println("----------------Insert 30--------------------");
		mytree.insert(30,"val30");
		System.out.println("----------------Insert 40--------------------");
		mytree.insert(40,"val40");
		//mytree.insert(6,"val6B");
		 mytree.insert(1,"val1B");

		
		mytree.search(9);
		mytree.search(5);
		mytree.search(2,7);
		mytree.search(3);
		mytree.search(2);


		return;
	}
}