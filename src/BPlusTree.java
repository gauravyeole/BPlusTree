import java.util.Collections;
import java.util.ArrayList;
import java.util.Stack;

public class BPlusTree{

	public TreeNode root;
	public static int order;

	public BPlusTree(int n){
		BPlusTree.order = n;
		root = null;
	}


	public void insert(double key, String val){
		// Case 1 - if tree is empty, then root will be DataNode. 

		if(root == null){
			root = new DataNode();
			root.insertKey(key,val);
		}

		// Case 2 - root is not null. 
		else{
			
			Stack<TreeNode> path = getPath(key);

			// Case 2 - new node is leafnode 
			TreeNode targetNode = path.pop();
			targetNode.insertKey(key,val);

			System.out.println("------TartgetNode Keys----------");
			System.out.println(targetNode.keys);
			System.out.println(targetNode.isLeafNode);

			// Case - If Adding new key at leaf causes overflow
			
			if(targetNode.keys.size() == BPlusTree.order){
				TreeNode[] splittedNodes = null;
				splittedNodes = targetNode.split();
				double splitKey = splittedNodes[1].keys.get(0);
				System.out.print("before while splittedNodes[0].keys =  ");
				System.out.print(splittedNodes[0].keys);
				System.out.println(splittedNodes[1].keys);

				while(!path.empty()  && targetNode.keys.size() == BPlusTree.order){
					System.out.println("---------inside while----------");
					TreeNode parentNode = path.peek();
					
					parentNode.insertKey(splitKey,splittedNodes[0],splittedNodes[1]);
					targetNode = parentNode;

					if(parentNode.keys.size() == BPlusTree.order){
						System.out.println("++++++++++++++inside while ------- if ----parentNode Keys are*********");
						System.out.println(parentNode.keys);
						splittedNodes = targetNode.split();
						splitKey = splittedNodes[1].keys.get(0);
						System.out.print("insede if of while splittedNodes[0].keys =  ");
						System.out.print(splittedNodes[0].keys);
						System.out.println(splittedNodes[1].keys);

						path.pop();
					}
					
				}
				
				if(path.empty()){
					IndexNode newRoot = new IndexNode();
					
					System.out.print("****ROOT CASE*****");
					System.out.print(splittedNodes[0].keys);
					System.out.println(splittedNodes[1].keys);
					System.out.print("splitKey ");
					System.out.println(splitKey);
					newRoot.insertKey(splitKey,splittedNodes[0],splittedNodes[1]);
					this.root = newRoot;
				}

				// else{

				// 	IndexNode currentNode = (IndexNode) path.peek();
				// 	currentNode.insertKey(splitKey,splittedNodes[0],splittedNodes[1]);

				// }

			}
		}
		return;
	}

	private Stack<TreeNode> getPath(double key){
		// returns Stack of all the nodes along the path to the key
		// First node of the stack is DataNode
		Stack<TreeNode> path = new Stack<TreeNode>();
		pathTraverse(root,key,path);
		return path;
	}

	private void pathTraverse(TreeNode root, double key, Stack<TreeNode> path){
		if(root.isLeafNode == true){
			path.push(root);
			return;
		}

		else{
			path.push(root);
			int index = Collections.binarySearch(root.getKeys(),key);
			if(index < 0)	index = -index-1;
			else	index = index+1;
			TreeNode next = ((IndexNode)root).getChildren().get(index);
			pathTraverse(next,key,path);
		}
	}

	public void search(double key){
		// The method prints all the values associated with the input key seperated by ","; prints Null if key is not there.
		if(root == null){
			System.out.print("Null");
		}

		TreeNode node = getPath(key).pop();
		int index = Collections.binarySearch(node.getKeys(),key);

		if(index < 0){
			System.out.println("Null");
		}
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
		if(root == null){
			System.out.println("Null");
		}

		TreeNode node = getPath(key1).pop();
		int index = Collections.binarySearch(node.getKeys(),key1);
		if (index < 0)	index = -index -1 ;

		StringBuilder sb = new StringBuilder();
		double temp = key1;

		while(node != null && temp <= key2){

			ArrayList<Double>	keys = node.getKeys();
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

	public void printTree(TreeNode root) {

		if (root == null)
			return;

		if (root.isLeafNode) {
			for (int i = 0; i < root.keys.size(); i++) {
				System.out.print(root.keys.get(i));
				System.out.print("---");
				System.out.print(((DataNode) root).values.get(i) + " ; ");
			}
			System.out.print(" \n");
			return;

		}

		System.out.println("\nInternal node:" + root.keys);
		for (TreeNode node : ((IndexNode) root).getChildren()) {
			printTree(node);
		}

	}
}