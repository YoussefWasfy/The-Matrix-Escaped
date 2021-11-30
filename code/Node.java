
public class Node {
	
	String state;
	Node parent;
	String operator;
	int depth;
	int pathCost;

	public Node(String state){
		this.state = state;
		this.depth = 0;
	}
	public Node(String state, Node parent, String operator, int depth, int pathCost) {
		this.state = state;
		this.parent = parent;
		this.operator = operator;
		this.depth = depth;
		this.pathCost = pathCost;
	}

}

