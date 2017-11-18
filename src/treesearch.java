import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class treesearch{
	public static void main(String[] args){

		BPlusTree mytree = new BPlusTree(3);

		System.out.println("----------------Insert 9--------------------");
		mytree.insert(9,"val9");
		System.out.println("----------------Insert 5--------------------");
		mytree.insert(5,"val5");
		System.out.println("----------------Insert 4--------------------");
		mytree.insert(4,"val4");
		mytree.printTree(mytree.root);
		System.out.println("----------------Insert 3--------------------");
		
		mytree.insert(3,"val3");
		System.out.println("----------------Insert 1--------------------");
		mytree.insert(1,"val1");
		System.out.println("----------------Insert 8--------------------");
		mytree.insert(8,"val8");
		//mytree.insert(80,"val80");
		//mytree.insert(43,"val43");
		//mytree.insert(34,"val34");
		//mytree.insert(76,"val76");

		mytree.printTree(mytree.root);
		mytree.search(9);
		mytree.search(5);
		mytree.search(4);
		mytree.search(3);
		mytree.search(2);


		return;
	}
}