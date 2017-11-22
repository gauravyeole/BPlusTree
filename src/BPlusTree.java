// Gaurav Anil YeoleS
// University of Florida
// COP 5536 : Advanced Data Structures
// gauravyeole@ufl.edu
// UFID 54473949

import java.util.Collections;
import java.util.ArrayList;
import java.util.Stack;

public class BPlusTree{


	public TreeNode root;
	public static int order;

	// Constructor of B-Plus Tree, order of tree is aregument. 
	// sets root of tree as null.
	public BPlusTree(int n){
		BPlusTree.order = n;
		root = null;
	}

	//method to insert the key in the tree.
	public void insert(double key, String value){
	
		// Case 1 - 
		// If root is null, New DataNode is created and key-value pair is sentas a argument in the insertKey method of DataNode.
		if(root == null){
			root = new DataNode();
			root.insertKey(key,value);
		}

		// Case 2 - 
		// if root of tree is not null:
		else{
			// path is a stack containing the TreeNodes in the path to reach the possible node to add the new key. 
			// Node on the top of the stack would be always DataNode. 
			Stack<TreeNode> path = getPath(key);

			// Case 2.i - if adding new key in the possible node causes the overflow 
			//(Number of keys is equal to order-1 i.e. capacityI of number keys is already reached.)

			if (path.peek().keys.size() + 1 == BPlusTree.order) {
I
				int index = Collections.binarySearch(path.peek().keys, key); // get the index of th new key in the possible node.

				// if key is already present: no need to split, just add the value in corresponding index at the datanode, by calling inserKey of datanode.
				if (index >= 0) {
					path.peek().insertKey(key, value);
				}

				// if key is not there index calculated by Collections.binarySearch will be negative. 
				// and since simply addding this key would cause an overflow in the node, we need to split the node. 
				else {

					TreeNode[] splittedNodes = null; // array of two nodes fafter splitting the node, set to null initially
					double splitKey = 0; // splitkey is the key which will be sent to the parent after splitting the node. 

					// keep splitting the node and transmitting the spkitkey to the parent until either root has recached or adding the splitkey in the
					// parent node doesnot cause overflow. 
					while (!path.empty() && path.peek().keys.size() + 1 == BPlusTree.order) {
						// In each iteration get the parent by poping the stack.
						TreeNode parent = path.pop();
						// if its datanode call insertKey of DataNode.
						if (parent.isLeafNode) {
							parent.insertKey(key, value);
						} 
						//otherwise call inserKey of insertKey of IndexNode.
						else {
							parent.insertKey(splitKey, splittedNodes[0], splittedNodes[1]);
						}

						splittedNodes = parent.split();
						splitKey = parent.isLeafNode ? splittedNodes[1].keys.get(0)
								: parent.keys.get(parent.keys.size() / 2);

					}

					// if root is reached while climbing up the path, we need to expand the BPlus tree by creating new root. 
					if (path.empty()) {

						IndexNode newRoot = new IndexNode();
						newRoot.insertKey(splitKey, splittedNodes[0], splittedNodes[1]);
						this.root = newRoot;

					}
					
					// otherwise add the key in the parent node, since it will not overflow. 
					else {
						IndexNode node = (IndexNode) path.peek();
						node.insertKey(splitKey, splittedNodes[0], splittedNodes[1]);
					}
					

				}

			}

			// Casse 2.ii - If number of keys are not equal to maximum limit, insert the key in the possible leaf node
			// which is at the top of the stack.
			else {
				path.peek().insertKey(key, value);
			}

			}
		
		return;
	}

	private Stack<TreeNode> getPath(double key){
		// returns Stack of all the nodes along the path to the key from root of the tree.
		// Top node of the stack is always DataNode(LeafNode)
		Stack<TreeNode> path = new Stack<TreeNode>();
		pathTraverse(root,key,path);
		return path;
	}

	private void pathTraverse(TreeNode root, double key, Stack<TreeNode> path){
		// helper function to het the stack of nodes in the path of the key from the root of the tree.
		if(root.isLeafNode == true){
			path.push(root);
			return;
		}
		else{
			path.push(root);
			int index = Collections.binarySearch(root.keys,key);
			if(index < 0){
				index = -(index+1);
			}
			else{
				index = index+1;
			}
			TreeNode next = ((IndexNode)root).children.get(index);
			pathTraverse(next,key,path);
		}
	}

	public void search(double key){
		// The method prints all the values associated with the input key seperated by ","; prints Null if key is not there.
		if(root == null){
			System.out.print("Null");
		}
		// get the path of the possible node of the key in the tree
		TreeNode node = getPath(key).peek();
		// get the index of the key in the ArrayList of the keys in the node. 
		int index = Collections.binarySearch(node.getKeys(),key);
		// if index is negative implies that key is not there the node.
		if(index < 0){
			System.out.println("Null");
		}
		//othervise get all the values associated with the key in the string builder and print it as a String.
		else{
			StringBuilder sb = new StringBuilder();
			ArrayList<String> listValue = ((DataNode)node).getValues().get(index);
			for(String val : listValue){
				sb.append(val).append(',');
			}

			System.out.println(sb.substring(0,sb.length()-1));
		}
	}

	public void search(double key1, double key2){
		// This method prints all the keys in the tree which lies in the range of key1 and key2. 
		// It is assumed that key1 will be always smaller than key2.

		if(root == null){
			System.out.println("Null");
		}
		// get the path of the possible node of the smaller key in the range - key1 in the tree.
		TreeNode node = getPath(key1).peek();
		// get the index of the key in the ArrayList of the keys of the node. 
		int index = Collections.binarySearch(node.getKeys(),key1);
		// if key is not there in the node then take the index of key prent in the ArrayList higher than the key1.
		index =  (index < 0) ?	-index -1 : index;
		
		// if index comes out to be equal to the number of the keys, i.e., their is no element at that index, then consider first element
		// i.e., 0th key in the next node.
		if(index == node.getNoKeys()){
			node = ((DataNode)node).getNext();
			index = 0;
		}
		// Iterate the Doubly linked list at the datanode till keys are less than or equal to the higher key in the range i.e., key2.
		StringBuilder sb = new StringBuilder();
		double temp = key1;

		while(node != null && temp <= key2){

			ArrayList<Double>	keys = node.keys;
			temp = keys.get(index);
			while(temp <= key2){

				ArrayList<String> listValue = ((DataNode)node).getValues().get(index);
				for(String val : listValue){
					sb.append("(" + temp + ", " + val + "),");
				}
				index ++;
				if(index == node.getNoKeys())	break;
				temp = node.getKeys().get(index);
			}
			index = 0;
			node = ((DataNode)node).getNext();
		}

		if(sb.length() == 0){
			System.out.println("Null");
			return;
		}

		System.out.println(sb.substring(0, sb.length()-1));

	}

	
}