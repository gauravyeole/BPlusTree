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
}