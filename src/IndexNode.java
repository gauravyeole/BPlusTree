import java.util.ArrayList;
import java.util.Collections;

public class IndexNode extends TreeNode{

	ArrayList<TreeNode>	children;
	
	public IndexNode(){
		children = new ArrayList<TreeNode>();
		isLeafNode = false;
		noKeys = 0;
	}

	public ArrayList<TreeNode> getChildren(){
		return children;
	}

	@Override
	public void insertKey(double key, TreeNode leftNode, TreeNode rightNode){
		int index = Collections.binarySearch(keys,key);
		index = -index - 1;
		System.out.println("--------index inside insertKey of indexNode----------");
		System.out.print(key);
		System.out.print(keys);
		System.out.println(index);
		this.keys.add(index,key);
		if(children.size() > 0)	children.remove(index);

		children.add(index,rightNode);
		children.add(index,leftNode);

		System.out.println("-----------Print children---------");
		for(TreeNode child : this.children){
			System.out.print(child.keys);
		}
		System.out.println("");
	}

	@Override
	public TreeNode[] split(){
		IndexNode[] output = new IndexNode[2];
		output[0] = new IndexNode();
		output[1] = new IndexNode();
		int length = this.keys.size();

		output[0].keys = new ArrayList<>(this.keys.subList(0,length/2));
		output[0].children = new ArrayList<TreeNode>(this.children.subList(0,length/2));

		output[1].keys = new ArrayList<>(this.keys.subList(length/2 + 1, length));
		output[1].children = new ArrayList<TreeNode>(this.children.subList(length/2 + 1, length));

		return output;
	}

}