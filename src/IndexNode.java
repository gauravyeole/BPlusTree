// Gaurav Anil Yeole
// University of Florida
// COP 5536 : Advanced Data Structures
// gauravyeole@ufl.edu
// UFID 54473949


import java.util.ArrayList;
import java.util.Collections;

public class IndexNode extends TreeNode{

	//The class IndexNode extends the TreeNode class. 
	// This is class of all the indexnodes of the tree.
	// It contains references of its children. 

	ArrayList<TreeNode>	children; // children of the IndxNode as a ArrayList 
	
	// Constructor of IndexNode
	public IndexNode(){
		children = new ArrayList<TreeNode>();
		isLeafNode = false;
		noKeys = 0;
	}

	//Getter Methos of childrem
	public ArrayList<TreeNode> getChildren(){
		return children;
	}

	// InsertKey method of TreeNode is overrided here for inserting the keys in indexnodes. 
	@Override
	public void insertKey(double key, TreeNode leftNode, TreeNode rightNode){

		int index = Collections.binarySearch(keys,key); //get the index of the new key to be inserted.
		index = -index - 1; 
	
		this.keys.add(index,key); 

		if(children.size() > 0)	children.remove(index); 

		// Insert the children of the new keys to the node
		this.children.add(index,rightNode); 
		this.children.add(index,leftNode);
	
	}

	// split() method of the TreeNode is overrided for the IndexNode
	@Override
	public TreeNode[] split(){
		//return the array of the IndexNodes. 
		IndexNode[] output = new IndexNode[2]; 
		output[0] = new IndexNode();
		output[1] = new IndexNode();
		int length = this.keys.size();

		output[0].keys = new ArrayList<>(this.keys.subList(0,length/2));
		output[0].children = new ArrayList<TreeNode>(this.children.subList(0,(length/2)+1));

		output[1].keys = new ArrayList<>(this.keys.subList(length/2 + 1, length));
		output[1].children = new ArrayList<TreeNode>(this.children.subList(length/2 + 1, length+1));

		return output;
	}

}