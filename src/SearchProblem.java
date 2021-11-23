package Code;

public abstract class SearchProblem {
	String [] operators = {"up", "down", "left", "right", "carry", "drop", "takePill", "kill", "fly"};
	String initialState;
	String [] stateSpace;
	Node goalTest;
	int pathCostFunction;
	
	public SearchProblem(String [] operators, String initialState, String [] stateSpace, Node goalTest, int pathCostFunction) {
		this.operators = operators;
		this.initialState = initialState;
		this.stateSpace = stateSpace;
		this.goalTest = goalTest;
		this.pathCostFunction = pathCostFunction;
	}
	
	public void GeneralSearch(SearchProblem problem, String strategy) {
		
	}
	
	
	public void searchStragtegy(String strategy) {
		
		switch (strategy) {
		case "BF":
			//BF Function
			break;

		case "DF":
			//DF Function
			break;

		case "ID":
			//ID Function
			break;

		case "UC":
			//UC Function
			break;

		case "GR1":
			//GR1 Function
			break;

		case "GR2":
			//GR2 Function
			break;

		case "AS1":
			//AS1 Function
			break;

		case "AS2":
			//AS2 Function
			break;
			
			
		}
		
	}
}
