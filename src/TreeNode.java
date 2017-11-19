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

	public ArrayList<Double> getKeys(){
		return keys;
	}

	public void insertKey(double key, TreeNode leftNode, TreeNode rightNode){
		
	}

	public void insertKey(double key, String val){
		
	}

	public TreeNode[] split(){
		return new TreeNode[2];
	}

	
}