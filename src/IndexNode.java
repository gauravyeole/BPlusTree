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

}