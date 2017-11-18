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
		System.out.println("--------index inside insertKey of dataNode----------");
		System.out.println(index);
		keys.add(index,key);
		if(children.size() > 0)	children.remove(index);

		children.add(index,rightNode);
		children.add(index,leftNode);

		System.out.println("-----------Print children---------");
		for(TreeNode child : this.children){
			System.out.println(child.keys);
		}
	}

	@Override
	public IndexNode[] split(){
		IndexNode[] output = new IndexNode[2];
		output[0] = new IndexNode();
		output[1] = new IndexNode();

		output[0].keys = new ArrayList<>(this.keys.subList(0,this.noKeys/2));
		output[0].children = new ArrayList<TreeNode>(this.children.subList(0,this.noKeys/2));

		output[1].keys = new ArrayList<>(this.keys.subList(this.noKeys/2 + 1, this.noKeys));
		output[1].children = new ArrayList<TreeNode>(this.children.subList(this.noKeys/2 + 1, this.noKeys));

		return output;
	}

}