// Gaurav Anil Yeole
// University of Florida
// COP 5536 : Advanced Data Structures
// gauravyeole@ufl.edu
// UFID 54473949

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataNode extends TreeNode{

	//DataNode classs extends the class TreeNode.
	// This is class of LeafNodes in the tree.
	// It contains the next and prev DataNode and nested ArrayList of Strings - which stores all the value of the keys.

	DataNode next;
	DataNode prev;
	ArrayList<ArrayList<String>> values;

	// Constructor of DataNode
	public DataNode(){
		isLeafNode = true;
		values = new ArrayList<>();
		next = null;
		prev = null;
		noKeys = 0;
	}

	//Setter method of nextNode of DataNode
	public void setNext(DataNode next){
		this.next = next;
	}

	//Setter method of prevNode of DataNode
	public void setPrev(DataNode prev){
		this.prev = prev;
	}

	//getter method of next of DataNode
	public DataNode getNext(){
		return	this.next; 
	}

	//getter method of prev of DataNode
	public DataNode getPrev(){
		return this.prev;
	}

	//getter method of values, returns the list of list of string.
	public ArrayList<ArrayList<String>> getValues(){
		return values;
	}

	// InsertKey method of TreeNode is overrided here for inserting the keys in datanode
	@Override
	public void insertKey(double key, String val){
		int index = Collections.binarySearch(keys,key); //get index of the key
		
		if(index >= 0){
			values.get(index).add(val); // if key is already present just append the list of values. 
		}
		else{
			index = -index - 1; // if key is not there, add new key and value. 
			keys.add(index,key);
			noKeys++;

			ArrayList<String> value = new ArrayList<>();
			value.add(val);
			values.add(index,value);			
		}
	}

	// split() method of the TreeNode is overrided for the DataNode
	@Override
	public TreeNode[] split(){

		// returns the array of DataNodes, which points to each other. 
		DataNode[] output = new DataNode[2];

		output[0] = new DataNode();
		output[1] = new DataNode();

		output[0].keys = new ArrayList<>(this.keys.subList(0,this.keys.size()/2));
		output[0].values = new ArrayList<>(this.values.subList(0,this.keys.size()/2));
		output[0].noKeys = output[0].keys.size();
		output[0].next = output[1];
		output[0].prev = this.prev;
		// if prev of the node is not null then the first partition of the node should point to that node and that node should point to first partition
		if(this.prev != null)	((DataNode)this.prev).next = output[0];

		output[1].keys = new ArrayList<>(this.keys.subList(this.keys.size()/2, this.keys.size()));
		output[1].values = new ArrayList<>(this.values.subList(this.keys.size()/2, this.keys.size()));
		output[1].noKeys = output[1].keys.size();
		output[1].prev = output[0];
		output[1].next = this.next;
		// if next of the node is not null, then next of second partition should point to that node and that node should point to second parition.
		if(this.next != null)	((DataNode)this.next).prev = output[1];

		return output;
	}
}