import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataNode extends TreeNode{

	DataNode next;
	DataNode prev;
	ArrayList<ArrayList<String>> values;

	public DataNode(){
		isLeafNode = true;
		values = new ArrayList<>();
		next = null;
		prev = null;
		noKeys = 0;
	}

	public void setNext(DataNode next){
		this.next = next;
	}

	public void setPrev(DataNode prev){
		this.prev = prev;
	}

	public DataNode getNext(){
		return	this.next; 
	}

	public DataNode getPrev(){
		return this.prev;
	}

	public ArrayList<ArrayList<String>> getValues(){
		return values;
	}

	@Override
	public void insertKey(double key, String val){
		int index = Collections.binarySearch(keys,key);
		

		if(index > 0){
			values.get(index).add(val);
		}
		else{
			index = -index - 1;
			keys.add(index,key);
			noKeys++;

			ArrayList<String> value = new ArrayList<>();
			value.add(val);
			values.add(index,value);			
		}
	}


	@Override
	public TreeNode[] split(){
		DataNode[] output = new DataNode[2];

		output[0] = new DataNode();
		output[1] = new DataNode();

		output[0].keys = new ArrayList<>(this.keys.subList(0,this.noKeys/2));
		output[0].values = new ArrayList<>(this.values.subList(0,this.noKeys/2));
		output[0].noKeys = output[0].keys.size();
		output[0].next = output[1];
		output[0].prev = this.prev;
		if(this.prev != null)	((DataNode)this.prev).next = output[0];

		output[1].keys = new ArrayList<>(this.keys.subList(this.noKeys/2 , this.noKeys));
		output[1].values = new ArrayList<>(this.values.subList(this.noKeys/2 , this.noKeys));
		output[1].noKeys = output[1].keys.size();
		output[1].prev = output[0];
		output[1].next = this.next;
		if(this.next != null)	((DataNode)this.next).prev = output[1];

		return output;
	}
}