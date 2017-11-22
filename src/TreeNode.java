// Gaurav Anil Yeole
// University of Florida
// COP 5536 : Advanced Data Structures
// gauravyeole@ufl.edu
// UFID 54473949

import java.util.ArrayList;
import java.util.Collections;

public class TreeNode{

	// TreeNode is class of each Node in the B-Plus Tree
	// It has following member variables:
	// keys: It is an ArrayList of all the keys in the node. 
	// isLeafNode: this is a boolean flag, which is set for the 
	// noKeys: it is an integer which stores the number of keys in the node.

	ArrayList<Double>	keys;
	boolean isLeafNode;
	int noKeys;

	public TreeNode(){
		keys = new ArrayList<>();iTp
	}

	// getter method to get the number of keys in the node.
	public int getNoKeys(){
		return noKeys;
	}

	// getter method to get the keys in the node as a ArrayList
	public ArrayList<Double> getKeys(){
		return keys;
	}

	// method to insert key in the IndexNode. This is overrided in the IndexNode class.
	public void insertKey(double key, TreeNode leftNode, TreeNode rightNode){
		
	}

	// method to insert the key in the dataNode. This is overrided in the DataNode class.
	public void insertKey(double key, String val){
		
	}

	// method to split the Node. This is overrided in both DataNode and IndexNode class.
	public TreeNode[] split(){
		return new TreeNode[2];
	}

	
}