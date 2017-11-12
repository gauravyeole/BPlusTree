import java.util.ArrayList;
import java.util.Collections;

public class TreeNode{

	ArrayList<Double>	keys;
	boolean isLeafNode;
	int noKeys;

	public TreeNode(){
		keys = new ArrayList<>();
	}

	public int getNoKeys(){
		return noKeys;
	}

	public void insertKey(double key, String val){
		
	}

	public ArrayList<Double> getKeys(){
		return keys;
	}
}