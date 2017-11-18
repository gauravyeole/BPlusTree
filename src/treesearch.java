import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

public class treesearch{
	public static void main(String[] args){

		BPlusTree mytree = new BPlusTree(3);

		mytree.insert(1,"val1");
		mytree.insert(2,"val2");
		mytree.insert(3,"val3");
		//mytree.insert(5,"val5");
		//mytree.insert(8,"val8");
		//mytree.insert(80,"val80");
		//mytree.insert(43,"val43");
		//mytree.insert(34,"val34");
		//mytree.insert(76,"val76");

		mytree.printTree(mytree.root);
		mytree.search(2);
		mytree.search(43);

		return;
	}
}