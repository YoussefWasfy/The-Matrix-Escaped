package Code;

import java.util.*;

public class Matrix{
    static Random rand = new Random();
    //Generates Hostage coordinates and Hostage initial damage
    public static int [] HostageCordAndDamage(int gridSize, int onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[3];
        int damage = rand.nextInt(99)+1;
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
        int cellCount = 0;
        coordinates[2] = rand.nextInt(99)+1;
//        Checks whether this cell is empty and if not generates new coordinates
        while (notOngrid)
        {
            if (cellCount > 20)
            {
                for (int i = 0; i < onGrid.length; i++)
                    for (int j = 0; j < onGrid.length; j++)
                    {
                        if(onGrid[i][j] == 0)
                        {
                            x = i;
                            y = j;
                            notOngrid = false;
                        }
                    }
            }
            else if(onGrid[x][y] != 0)
            {
                cellCount ++;
                x = rand.nextInt(gridSize);
                y = rand.nextInt(gridSize);

            }
            else
            {
                notOngrid = false;
            }
        }
        coordinates[0] = x;
        coordinates[1] = y;
        coordinates[2] = damage;
        return coordinates;
    }
    //    Generates agents coordinates
    public static int[] Agents(int gridSize, int onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[2];
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
        //        Checks whether this cell is empty and if not generates new coordinates
        while (notOngrid)
        {
            if(onGrid[x][y] != 0)
            {
                x = rand.nextInt(gridSize);
                y = rand.nextInt(gridSize);

            }
            else
            {
                notOngrid = false;
            }
        }
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }
    //    Generates Pill Coordinates
    public static int[] Pills(int gridSize, int onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[2];
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
//        Checks whether this cell is empty and if not generates new coordinates
        while (notOngrid)
        {
            if(onGrid[x][y] != 0)
            {
                x = rand.nextInt(gridSize);
                y = rand.nextInt(gridSize);

            }
            else
            {
                notOngrid = false;
            }
        }
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }
    //Generate coordinates for startpads and finishpads
    public static int[] Pads(int gridSize, int onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[4];
        int startpadx = rand.nextInt(gridSize);
        int startpady = rand.nextInt(gridSize);
        int finishpadx = rand.nextInt(gridSize);
        int finishpady = rand.nextInt(gridSize);
//        checks whether the cells of the startpad and the finishpad are empty
        while (notOngrid)
        {
            if (startpadx == finishpadx && startpady == finishpady)
            {
                startpadx = rand.nextInt(gridSize);
                startpady = rand.nextInt(gridSize);
                finishpadx = rand.nextInt(gridSize);
                finishpady = rand.nextInt(gridSize);
            }
            if(onGrid[startpadx][startpady] != 0)
            {
                startpadx = rand.nextInt(gridSize);
                startpady = rand.nextInt(gridSize);
            }
            if (onGrid[finishpadx][finishpady] != 0)
            {
                finishpadx = rand.nextInt(gridSize);
                finishpady = rand.nextInt(gridSize);
            }
            if ((onGrid[startpadx][startpady]==0) && (onGrid[finishpadx][finishpady] == 0))
            {
                notOngrid = false;
            }
        }
        coordinates[0] = startpadx;
        coordinates[1] = startpady;
        coordinates[2] = finishpadx;
        coordinates[3] = finishpady;
        return coordinates;

    }

    public static int[] TelephoneBooth(int gridSize, int onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[2];
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
        while (notOngrid)
        {
            if(onGrid[x][y] != 0)
            {
                x = rand.nextInt(gridSize);
                y = rand.nextInt(gridSize);

            }
            else
            {
                notOngrid = false;
            }
        }
        coordinates[0] = x;
        coordinates[1] = y;
        return coordinates;
    }

    public static String GenGrid()
    {
//        neo = 1
//        TB = 2
//        Agents = 3
//        Pills = 4
//        Pads = 5
//        Hostages = 6
        String grid = "";
//        int gridSize = rand.nextInt(11)+5;
        int gridSize = 4;
        int numOfCells = (gridSize * gridSize) - (2*gridSize);
        int onGrid[][]  = new int[gridSize][gridSize];
        int M = gridSize;
        int N = gridSize;
        grid += M + "," + N +";";
        int C = rand.nextInt(4)+1;
        grid += C + ";";
//        int numbOfHostages = rand.nextInt(8)+3;
        int numbOfHostages = 2;
        numOfCells -= numbOfHostages;
        int neox = rand.nextInt(gridSize);
        int neoy = rand.nextInt(gridSize);
        onGrid[neox][neoy] = 1;
        numOfCells -= 1;
        grid += neox + "," + neoy + ";";
        int telephoneCoord [] = TelephoneBooth(gridSize, onGrid);
        int telephonex = telephoneCoord[0];
        int telephoney = telephoneCoord[1];
        onGrid[telephonex][telephoney] = 2;
        grid += telephonex + "," + telephoney + ";";
        numOfCells -= 1;
        int maxAgents = rand.nextInt(gridSize)+1;
        int maxPads = rand.nextInt(gridSize)+ 1;
        int maxPills = rand.nextInt(numbOfHostages) + 1;

//        This loop generates the agents on the grid
        for (int i = 0; i < maxAgents; i++)
        {
            int agentCoord[] = Agents(gridSize, onGrid);
            int agentx = agentCoord[0];
            int agenty = agentCoord[1];
            onGrid[agentx][agenty] = 3;
            if(i == (maxAgents-1))
            {
                grid += agentx + "," + agenty;
            }
            else
            {
                grid += agentx + "," + agenty + ",";
            }
        }
        grid += ";";
        System.out.println("Agents Generated");
//        This loop generates the pills on the grid
        for (int i = 0; i < maxPills; i++)
        {
            int pillCoord[] = Pills(gridSize, onGrid);
            int pillx = pillCoord[0];
            int pilly = pillCoord[1];
            onGrid[pillx][pilly] = 4;
            if (i == maxPills-1)
            {
                grid += pillx + "," + pilly;
            }
            else
            {
                grid += pillx + "," + pilly + ",";
            }

        }
        grid += ";";
        System.out.println("Pills Generated");
//        This loop generates the start and finish pads on the grid
        for (int i = 0; i < maxPads; i++)
        {
            int padCoord[] = Pads(gridSize, onGrid);
            int startpadx = padCoord[0];
            int startpady = padCoord[1];
            int finishpadx = padCoord[2];
            int finishpady = padCoord[3];
            onGrid[startpadx][startpady] = 5;
            onGrid[finishpadx][finishpady] = 5;
            if (i == (maxPads-1))
            {
                grid += startpadx + "," + startpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += startpadx + "," + startpady;
            }
            else
            {
                grid += startpadx + "," + startpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += startpadx + "," + startpady + ",";
            }
        }
        grid += ";";
        System.out.println("Pads Generated");
//        This loop generate the hostages on the grid with the initial damage
        for (int i = 0; i < numbOfHostages; i++)
        {
            int hostageCoord[] = HostageCordAndDamage(gridSize, onGrid);
            int hostagex = hostageCoord[0];
            int hostagey = hostageCoord[1];
            int damage = hostageCoord[2];
            onGrid[hostagex][hostagey] = 6;
            if (i == (numbOfHostages-1))
            {
                grid += hostagex + "," + hostagey + ","+ damage;
            }
            else
            {
                grid += hostagex + "," + hostagey + ","+ damage + ",";
            }
        }
        grid += ";";
        System.out.println("Hostages Generated");
        for (int i =0; i < onGrid.length; i++)
        {
            System.out.println("");
            for (int j = 0; j <onGrid.length; j++)
            {
                System.out.print(onGrid[i][j]+ " ");
            }
        }
        System.out.println();
        System.out.println(grid);

        return grid;
    }
    public static int getAccumalatedPathCost(Node node)
    {
        String state = node.state;
        int death = Integer.parseInt(state.split(";")[6]);
        int kills = Integer.parseInt(state.split(";")[7]);

        int pathcost = (death * 4) + (kills * 100);

        return pathcost;

//        int acc = 0;
//        while (node.pathCost != 0 )
//        {
//            acc += node.pathCost;
//            node = node.parent;
//        }
//        return acc;
    }

    public static int getHeuristic1(Node node, String[]tb){
        String state = node.state;
        int heuristic;
        int death = Integer.parseInt(state.split(";")[6]);
        int kills = Integer.parseInt(state.split(";")[7]);
        String neox = state.split(";")[0].split(",")[0];
        String neoy = state.split(";")[0].split(",")[1];
        String noOfHostagesLeftglobal = state.split(";")[3];
        String noOfHostagesTurnAgentsglobal = state.split(";")[4];
        String noCarriedHostagesglobal = state.split(";")[2];

        int pathcost = (death * 4) + (kills * 100) / 4;

        int distance = Math.abs(Integer.parseInt(neox) - Integer.parseInt(tb[1])) + Math.abs(Integer.parseInt(neoy) - Integer.parseInt(tb[0]));
        if (distance == 0 && noOfHostagesLeftglobal.equals("0") && noOfHostagesTurnAgentsglobal.equals("0")  && (Integer.parseInt(noCarriedHostagesglobal)==0))
            heuristic = 0;
        else
            heuristic = pathcost;
        return heuristic;
    }
    
    public static int getHeuristic2(Node node, String[]tb){
        String state = node.state;
        int heuristic;
        int death = Integer.parseInt(state.split(";")[6]);
        int kills = Integer.parseInt(state.split(";")[7]);
        String neox = state.split(";")[0].split(",")[0];
        String neoy = state.split(";")[0].split(",")[1];
        String noOfHostagesLeftglobal = state.split(";")[3];
        String noOfHostagesTurnAgentsglobal = state.split(";")[4];
        String noCarriedHostagesglobal = state.split(";")[2];

        int pathcost = (death * 4) + (kills * 100) / 6;

        int distance = Math.abs(Integer.parseInt(neox) - Integer.parseInt(tb[1])) + Math.abs(Integer.parseInt(neoy) - Integer.parseInt(tb[0]));
        if (distance == 0 && noOfHostagesLeftglobal.equals("0") && noOfHostagesTurnAgentsglobal.equals("0")  && (Integer.parseInt(noCarriedHostagesglobal)==0))
            heuristic = 0;
        else
            heuristic = pathcost;
        return heuristic;
    }
    
    
    
    public static LinkedList <Node> depthFirst(Node node, LinkedList<Node> nodes)
    {
        nodes.addFirst(node);
        return nodes;
    }
    public static LinkedList<Node> breadthFirst(Node node, LinkedList<Node> nodes)
    {
        nodes.add(node);
        return nodes;
    }

    public static LinkedList<Node> uniformCost(Node node, LinkedList<Node> nodes)
    {
        for(int i = 0; i< nodes.size(); i++){
            if(node.pathCost < nodes.get(i).pathCost){
                Node nodeTemp = nodes.get(i);
                nodes.set(i, node);
                node = nodeTemp;
            }
        }
        nodes.addLast(node);

        //PriorityQueue<Node>pq = new  PriorityQueue(nodes.size(),  );
        return nodes;
    }



    public static LinkedList<Node> greedySearch(Node node, LinkedList<Node> nodes)
    {
        for(int i = 0; i< nodes.size(); i++){
            if(node.pathCost < nodes.get(i).pathCost){
                Node nodeTemp = nodes.get(i);
                nodes.set(i, node);
                node = nodeTemp;
            }
        }
        nodes.addLast(node);

        //PriorityQueue<Node>pq = new  PriorityQueue(nodes.size(),  );
        return nodes;
    }

    public static String getInitialState(String grid)
    {
        String state = "";
        String[] splittedArray  = grid.split(";", 8);
        String neoxy = splittedArray[2];
        String neoHealth = "0";
        String noOfCarriedHostages = "0";
        String[] hostagesSplitted = splittedArray[7].split(",");
        String noOfHostagesLeft = hostagesSplitted.length/3 + "";
        String noOfHostagesTurnAgents = "0";
        String hostTurnToAgentXY = "-1,-1";
        String deathCount = "0";
        String killCount = "0";
        String hostageCarriedDamage = "100";
        String agents = splittedArray[4];
        String pills = splittedArray[5];
        String hostages = splittedArray[7];

        state += neoxy + ";" + neoHealth + ";" + noOfCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXY + ";" + deathCount + ";"+ killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
        System.out.println(state);
        return state;
    }
    public static LinkedList<Node> Strategy(String strategy, LinkedList<Node> nodes, Node node)
    {
        LinkedList<Node> temp = new LinkedList<Node>();
        switch (strategy) {
            case "BF":
                temp = new LinkedList<Node>(breadthFirst(node, nodes));
                break;
            case "DF":
                temp = new LinkedList<Node>(depthFirst(node, nodes));
                break;

            case "ID":
                //ID Function
                break;

            case "UC":
                //UC Function
                temp = new LinkedList<Node>(uniformCost(node, nodes));
                break;

            case "GR1":
                temp = new LinkedList<Node>(greedySearch(node, nodes));
                //GR1 Function
                break;

            case "GR2":
                temp = new LinkedList<Node>(greedySearch(node, nodes));

                //GR2 Function
                break;

            case "AS1":
            	temp = new LinkedList<Node>(greedySearch(node, nodes));
                //AS1 Function
                break;

            case "AS2":
                temp = new LinkedList<Node>(greedySearch(node, nodes));

                //AS2 Function
                break;
        }
        return temp;
    }
    public static String getPlan(Node node)
    {
        String plan= "";
        while ( node.operator != null)
        {
            plan = node.operator + "," + plan;
            node = node.parent;
        }
        return plan;
    }
    public static String GeneralSearch(String grid, ArrayList<String> ops, String strategy)
    {

        //GENRAL SEARCH PROCEDURE:-

        ArrayList<String> opsCopy = new ArrayList<String>(ops);
        int expansionCount = 0;
        HashSet<String> repeatedStates = new HashSet<>();
        String[] gridSplitted = grid.split(";");
        String[] gridSize = gridSplitted[0].split(",");
        String maxHosCarry = gridSplitted[1];
        String[] tB = gridSplitted[3].split(",");
//        String[] agents = gridSplitted[4].split(",");
        String[] pads = gridSplitted[6].split(",");
//       String[] hostages = gridSplitted[7].split(",");
        //Create a node containing the initial state
        Node initialState = new Node(getInitialState(grid));
        //Create a queue and initialize it by putting this node in it
        LinkedList<Node> nodes = new LinkedList<Node>();
        nodes.add(initialState);
        //Keep on looping
        while(true) {
            //If the queue is empty, return Failure!
            if (nodes.isEmpty()) {
                System.out.println("Queue is Empty");
                return "No Solution";
            } else {
                //If not, dequeue the node from the queue, and check if it passes the goal test?
                ops = new ArrayList<String>(opsCopy);
                Node nodeToCheckGoal = nodes.removeFirst();
                expansionCount ++ ;
                System.out.println(nodeToCheckGoal.state);
                System.out.println("\n" + nodeToCheckGoal.operator);
                String[] neoPosInState = nodeToCheckGoal.state.split(";")[0].split(",");
                String neoHealthglobal = nodeToCheckGoal.state.split(";")[1];
                String noCarriedHostagesglobal = nodeToCheckGoal.state.split(";")[2];
                String noOfHostagesLeftglobal = nodeToCheckGoal.state.split(";")[3];
                String noOfHostagesTurnAgentsglobal = nodeToCheckGoal.state.split(";")[4];
                String hostTurnToAgentXY = nodeToCheckGoal.state.split(";")[5];
                String deathCountglobal = nodeToCheckGoal.state.split(";")[6];
                String killCountglobal = nodeToCheckGoal.state.split(";")[7];
                String hostageCarriedDamageglobal = nodeToCheckGoal.state.split(";")[8];
                String pillsglobal = nodeToCheckGoal.state.split(";")[9];
                String agentsglobal = nodeToCheckGoal.state.split(";")[10];
                String hostagesglobal = nodeToCheckGoal.state.split(";")[11];
//                String stateWithoutHostages = nodeToCheckGoal.state.split(";")[0] + ";" + neoHealthglobal + ";" + noCarriedHostagesglobal + ";" + noOfHostagesLeftglobal + ";" + noOfHostagesTurnAgentsglobal + ";" + hostTurnToAgentXY + ";" + deathCountglobal + ";" + killCountglobal + ";" + pillsglobal + ";" + agentsglobal + ";";
//                repeatedStates.add(stateWithoutHostages);
                String[] hostagesglobalArray = hostagesglobal.split(",");
                String[] agentsglobalArray = agentsglobal.split(",");
                String[] pillsglobalArray = pillsglobal.split(",");
                String[] hostTurnToAgentXYArray = hostTurnToAgentXY.split(",");
                //If yes, then return this node as the solution
                if (noOfHostagesLeftglobal.equals("0") && noOfHostagesTurnAgentsglobal.equals("0") && (tB[0].equals(neoPosInState[0]) && tB[1].equals(neoPosInState[1])) && (Integer.parseInt(noCarriedHostagesglobal)==0) ) {
                    String plan = getPlan(nodeToCheckGoal);
                    StringBuilder sb = new StringBuilder(plan);
                    sb.deleteCharAt(plan.length() - 1);
                    plan = sb.toString();
                    System.out.println("Solution Found: " + plan +";"+ deathCountglobal +";"+killCountglobal+";"+expansionCount);
                    System.out.println(nodeToCheckGoal.state);
                    return plan +";"+ deathCountglobal +";"+killCountglobal+";"+expansionCount;

                } else {

                    //if x value is zero no left
                    if (neoPosInState[1].equals("0")) {
                        ops.remove("left");

                    }
                    //if y value is zero no up
                    if (neoPosInState[0].equals("0")) {
                        ops.remove("up");

                    }
                    //if x value is N-1 no right
                    if (neoPosInState[1].equals(Integer.toString((Integer.parseInt(gridSize[0])) - 1))) {
                        ops.remove("right");
                    }
                    //if y value is N-1 no down
                    if (neoPosInState[0].equals(Integer.toString((Integer.parseInt(gridSize[0])) - 1))) {
                        ops.remove("down");
                    }
//                    If not in telephone booth remove drop operator
                    if (!(neoPosInState[1].equals(tB[1]) && neoPosInState[0].equals(tB[0]))) {
                        ops.remove("drop");
                    }
                    if (Integer.parseInt(noCarriedHostagesglobal) == 0) {
                        ops.remove("drop");
                    }
//                    If not in same cell as a pill remove takePill operator
                    boolean takePill = false;
                    for (int i = 0; i < pillsglobalArray.length; i += 2) {
                        if ((pillsglobalArray[i + 1].equals(neoPosInState[1]) && pillsglobalArray[i].equals(neoPosInState[0]))) {
                            takePill = true;
                            break;
                        } else {
                            takePill = false;
                        }

                    }
                    if (!takePill) {
                        ops.remove("takePill");
                    }

                    //if cell i am in does not contain hostage no carry
                    boolean carryHostage = false;
                    if (((Integer.parseInt(noCarriedHostagesglobal)) == (Integer.parseInt(maxHosCarry)))) {
                        ops.remove("carry");
                    } else {
                        for (int i = 0; i < hostagesglobalArray.length; i += 3) {
                            if ((hostagesglobalArray[i].equals(neoPosInState[0]) && hostagesglobalArray[i + 1].equals(neoPosInState[1]))) {
                                carryHostage = true;
                                break;
                            } else {
                                carryHostage = false;
                            }

                        }
                        if (carryHostage == false) {
                            ops.remove("carry");
                        }
                    }
                    for (int i =0 ; i < hostagesglobalArray.length; i+=3)
                    {
                       if ((hostagesglobalArray[i].equals(Integer.toString(Integer.parseInt(neoPosInState[0]) - 1)) && hostagesglobalArray[i + 1].equals(neoPosInState[1])) &&( Integer.parseInt(hostagesglobalArray[i+2]) >= 98))
                       {
                           ops.remove("up");
                       }
                       if ((hostagesglobalArray[i].equals(Integer.toString(Integer.parseInt(neoPosInState[0]) + 1)) && hostagesglobalArray[i + 1].equals(neoPosInState[1])) &&( Integer.parseInt(hostagesglobalArray[i+2]) >= 98))
                       {
                           ops.remove("down");
                       }
                       if ((hostagesglobalArray[i].equals(neoPosInState[0]) && hostagesglobalArray[i + 1].equals(Integer.toString(Integer.parseInt(neoPosInState[1])-1))) &&( Integer.parseInt(hostagesglobalArray[i+2]) >= 98))
                       {
                           ops.remove("left");
                       }

                       if ((hostagesglobalArray[i].equals(neoPosInState[0]) && hostagesglobalArray[i + 1].equals(Integer.toString(Integer.parseInt(neoPosInState[1])+1))) &&( Integer.parseInt(hostagesglobalArray[i+2]) >= 98))
                        {
                            ops.remove("right");
                        }

                    }
//                    If not in cell that contains a pad remove operator fly
                    boolean fly = false;
                    boolean flyOperater = false;
                    for (int i = 0; i < pads.length; i += 2) {
                        if ((pads[i].equals(neoPosInState[0]) && pads[i + 1].equals(neoPosInState[1]))) {
                            fly = true;
                            break;
                        } else {
                            fly = false;
                        }

                    }
                    if (!fly) {
                        ops.remove("fly");
                    } else {
                        try {
                            flyOperater = nodeToCheckGoal.operator.equals("fly");

                        } catch (Exception NullPointerException) {
                            System.out.println(NullPointerException);
                        }
                        if (flyOperater) {
                            ops.remove("fly");
                        }

                    }


                    //if the neighboring cells does not contain agent no kill
                    boolean killRight = false;
                    boolean killLeft = false;
                    boolean killUp = false;
                    boolean killDown = false;
                    boolean killHostageRight = false;
                    boolean killHostageLeft = false;
                    boolean killHostageUp = false;
                    boolean killHostageDown = false;

                    for (int i = 0; i < agentsglobalArray.length; i += 2) {
                        if (agentsglobalArray.length > 1) {
                            if ((agentsglobalArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])) + 1))) && (agentsglobalArray[i].equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                killRight = true;
                                ops.remove("right");
                            }
                            if ((agentsglobalArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])) - 1))) && (agentsglobalArray[i].equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                killLeft = true;
                                ops.remove("left");
                            }
                            if ((Integer.toString((Integer.parseInt(neoPosInState[0])) - 1).equals(agentsglobalArray[i])) && (agentsglobalArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                killUp = true;
                                ops.remove("up");

                            }
                            if ((Integer.toString((Integer.parseInt(neoPosInState[0])) + 1).equals(agentsglobalArray[i])) && (agentsglobalArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                killDown = true;
                                ops.remove("down");
                            }
                        }
                    }
                    for (int i = 0; i < hostTurnToAgentXYArray.length; i += 2) {
                        if (hostTurnToAgentXYArray.length > 1) {
                            if ((hostTurnToAgentXYArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])) + 1))) && (hostTurnToAgentXYArray[i].equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                killHostageRight = true;
                                ops.remove("right");
                            }
                            if ((hostTurnToAgentXYArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])) - 1))) && (hostTurnToAgentXYArray[i].equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                killHostageLeft = true;
                                ops.remove("left");
                            }
                            if ((Integer.toString((Integer.parseInt(neoPosInState[0])) - 1).equals(hostTurnToAgentXYArray[i])) && (hostTurnToAgentXYArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                killHostageUp = true;
                                ops.remove("up");

                            }
                            if ((Integer.toString((Integer.parseInt(neoPosInState[0])) + 1).equals(hostTurnToAgentXYArray[i])) && (hostTurnToAgentXYArray[i + 1].equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                killHostageDown = true;
                                ops.remove("down");
                            }
                        }
                    }
                    if ((killLeft == false && killRight == false && killUp == false && killDown == false) && (killHostageLeft == false && killHostageRight == false && killHostageUp == false && killHostageDown == false)) {
                        ops.remove("kill");
                    }
                    System.out.println(neoPosInState[1] + "," + neoPosInState[0]);
                    for (int i = 0; i < ops.size(); i++) {
                        System.out.print(ops.get(i) + " ");
                    }
                    System.out.println("");

                    //else we will expand possible outcomes
                    //which will give us set of nodes which are the children of the parent node.
                    for (int i = 0; i < ops.size(); i++) {
                        if (ops.get(i).equals("up")) {

                            String state = "";
                            String neoxy = (Integer.parseInt(neoPosInState[0]) - 1) + "," + neoPosInState[1];
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98) {
                                    System.out.println("Up Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;
                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }


                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "up", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "up", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "up", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "up", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "up", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "up", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("down")) {

                            String state = "";
                            String neoxy = (Integer.parseInt(neoPosInState[0]) + 1) + "," + neoPosInState[1];
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98 ) {

                                    System.out.println("down Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;

                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }

                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "down", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "down", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "down", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "down", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "down", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "down", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("left")) {
                            String state = "";
                            String neoxy = neoPosInState[0] + "," + (Integer.parseInt(neoPosInState[1]) - 1);
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if ((Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98)) {
                                    System.out.println("left Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;
                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }

                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "left", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "left", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "left", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "left", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "left", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "left", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("right")) {

                            String state = "";
                            String neoxy = neoPosInState[0] + "," + (Integer.parseInt(neoPosInState[1]) + 1);
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98) {
                                    System.out.println("right Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;
                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }

                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "right", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "right", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "right", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "right", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "right", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "right", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("carry")) {
                            String state = "";
                            String neoxy = neoPosInState[0] + "," + neoPosInState[1];
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = Integer.toString(Integer.parseInt(noCarriedHostagesglobal) + 1);
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if ((hostagesXYandHealthArrayList.get(j).equals(neoPosInState[0]) && hostagesXYandHealthArrayList.get(j + 1).equals(neoPosInState[1]))) {
                                    hostageCarriedDamage += "," + hostagesXYandHealthArrayList.get(j + 2);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    break;

                                }

                            }
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98) {
                                    System.out.println("carry Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;

                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }

                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }

                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);
//                                System.out.println(hostages);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "carry", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "carry", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "carry", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "carry", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "carry", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "carry", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("drop")) {
                            String state = "";
                            String neoxy = neoPosInState[0] + "," + neoPosInState[1];
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            ArrayList<String> hostageCarriedDamageArrayList = new ArrayList<>(Arrays.asList(hostageCarriedDamageArray));
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98) {
                                    System.out.println("drop Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;
                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            if (Integer.parseInt(noCarriedHostages) != 0)
                            {
                                for (int x = 1; x < hostageCarriedDamageArrayList.size(); x++) {
                                    if (Integer.parseInt(hostageCarriedDamageArrayList.get(x))  >= 100)
                                    {
                                        deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    }
                                    hostageCarriedDamageArrayList.remove(x);
                                    x--;
                                }
                            }

                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);


                            }
                            noCarriedHostages = "0";
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArrayList);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "drop", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "drop", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "drop", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "drop", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "drop", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "drop", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("takePill")) {
                            String state = "";
                            String neoxy = neoPosInState[0] + "," + neoPosInState[1];
                            String neoHealth = neoHealthglobal;
                            if (Integer.parseInt(neoHealth) <= 20) {
                                neoHealth = "0";
                            } else {
                                neoHealth = Integer.toString(Integer.parseInt(neoHealth) - 20);
                            }
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] pillsArray = pills.split(",");
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            ArrayList<String> pillsArrayList = new ArrayList<String>(Arrays.asList(pillsArray));
                            for (int j = 2; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j)) <= 20) {
                                    hostagesXYandHealthArrayList.set(j, "0");
                                } else {
                                    hostagesXYandHealthArrayList.set(j, Integer.toString((Integer.parseInt(hostagesXYandHealthArrayList.get(j)) - 20)));
                                }
                            }
                            for (int x = 0; x < hostageCarriedDamageArray.length; x++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[x]) >= 100)) {
                                    hostageCarriedDamageArray[x] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[x]) - 20);

                                }

                            }
                            for (int k = 0; k < pillsArrayList.size(); k += 2) {
                                if ((pillsArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1]))))) && (pillsArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                    pillsArrayList.remove(k + 1);
                                    pillsArrayList.remove(k);
                                    break;
                                }
                            }
                            if (pillsArrayList.size() == 0) {
                                pills = "-1,-1";
                            } else {
                                pills = String.join(",", pillsArrayList);
                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "takePill", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "takePill", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "takePill", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "takePill", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "takePill", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "takePill", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
                        } else if (ops.get(i).equals("kill")) {
                            String state = "";
                            String neoxy = neoPosInState[0] + "," + neoPosInState[1];
                            String neoHealth = neoHealthglobal;
                            if (Integer.parseInt(neoHealth) >= 80) {
                                System.out.println(getPlan(nodeToCheckGoal));
                                neoHealth = Integer.toString(Integer.parseInt(neoHealth) + 20);
                                System.out.println("Neo Died");
//                                return "gameover";

                            } else {
                                neoHealth = Integer.toString(Integer.parseInt(neoHealth) + 20);
                            }
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            String agents = agentsglobal;
                            String[] agentsArray = agents.split(",");

                            ArrayList<String> agentsArrayList = new ArrayList<String>(Arrays.asList(agentsArray));

                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98) {
//                                    System.out.println("kill Died" + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));

                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) - 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;
                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            boolean killAgent = true;
                            String[] hostTurnToAgentXYlocalArray = hostTurnToAgentXYlocal.split(",");
                            ArrayList<String> hostagesTurnToAgentXYlocalArrayList = new ArrayList<String>(Arrays.asList(hostTurnToAgentXYlocalArray));
                            for (int k = 0; k < hostagesTurnToAgentXYlocalArrayList.size(); k += 2) {
                                 if ((hostagesTurnToAgentXYlocalArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])) - 1))) && (hostagesTurnToAgentXYlocalArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                    System.out.println("Kill Hostage up");
                                    killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) - 1);
                                    hostagesTurnToAgentXYlocalArrayList.remove(k + 1);
                                    hostagesTurnToAgentXYlocalArrayList.remove(k);
                                    k -=2;
//                                    killAgent = false;
//                                    break;
                                }
                                 else if ((hostagesTurnToAgentXYlocalArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])) + 1))) && (hostagesTurnToAgentXYlocalArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                     killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                     noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) - 1);
                                     hostagesTurnToAgentXYlocalArrayList.remove(k + 1);
                                     hostagesTurnToAgentXYlocalArrayList.remove(k);
                                     k-=2;
//                                     killAgent = false;
//                                     break;
                                 }
                                 else if ((hostagesTurnToAgentXYlocalArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])) - 1))) && (hostagesTurnToAgentXYlocalArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                     killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                     noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) - 1);
                                     hostagesTurnToAgentXYlocalArrayList.remove(k + 1);
                                     hostagesTurnToAgentXYlocalArrayList.remove(k);
                                     k-=2;
//                                     killAgent = false;
//                                     break;
                                 }
                                 else if ((hostagesTurnToAgentXYlocalArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])) + 1))) && (hostagesTurnToAgentXYlocalArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                    killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) - 1);
                                    hostagesTurnToAgentXYlocalArrayList.remove(k + 1);
                                    hostagesTurnToAgentXYlocalArrayList.remove(k);
                                    k-=2;
//                                    killAgent = false;
//                                    break;
                                }

                            }
                            if (killAgent)
                            {
                                for (int k = 0; k < agentsArrayList.size(); k += 2) {
                                    if ((agentsArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])) - 1))) && (agentsArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                        killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                        agentsArrayList.remove(k + 1);
                                        agentsArrayList.remove(k);
                                        k-=2;
//                                        break;
                                    }
                                    else if ((agentsArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])) + 1))) && (agentsArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])))))) {
                                        killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                        agentsArrayList.remove(k + 1);
                                        agentsArrayList.remove(k);
                                        k-=2;
//                                        break;
                                    }
                                    else if ((agentsArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])) - 1))) && (agentsArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                        killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                        agentsArrayList.remove(k + 1);
                                        agentsArrayList.remove(k);
                                        k-=2;
//                                        break;
                                    }
                                    else if ((agentsArrayList.get(k + 1).equals(Integer.toString((Integer.parseInt(neoPosInState[1])) + 1))) && (agentsArrayList.get(k).equals(Integer.toString((Integer.parseInt(neoPosInState[0])))))) {
                                        killCount = Integer.toString(Integer.parseInt(killCount) + 1);
                                        agentsArrayList.remove(k + 1);
                                        agentsArrayList.remove(k);
                                        k-=2;
//                                        break;
                                    }

                                }
                            }

                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }

                            }
                            if (agentsArrayList.size() == 0) {
                                agents = "-1,-1";
                            } else {
                                agents = String.join(",", agentsArrayList);
                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            System.out.println("Size before join:" + hostagesTurnToAgentXYlocalArrayList.size());
                            hostTurnToAgentXYlocal = String.join(",", hostagesTurnToAgentXYlocalArrayList);
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "kill", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "kill", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "kill", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "kill", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "kill", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "kill", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }

                        } else if (ops.get(i).equals("fly")) {

                            String state = "";
                            String neoxy = (Integer.parseInt(neoPosInState[0]) - 1) + "," + neoPosInState[1];
                            for (int j = 0; j < pads.length; j += 4) {
                                if (neoPosInState[1].equals(pads[j + 1]) && neoPosInState[0].equals(pads[j])) {
                                    neoxy = pads[j + 2] + "," + pads[j + 3];
                                    break;
                                }
                            }
                            String neoHealth = neoHealthglobal;
                            String noCarriedHostages = noCarriedHostagesglobal;
                            String noOfHostagesLeft = noOfHostagesLeftglobal;
                            String noOfHostagesTurnAgents = noOfHostagesTurnAgentsglobal;
                            String hostages = hostagesglobal;
                            String[] hostagesArray = hostages.split(",");
                            List<String> hostagesXYandHealthArrayList = new ArrayList<String>(Arrays.asList(hostagesArray));
                            String hostTurnToAgentXYlocal = hostTurnToAgentXY;
                            String deathCount = deathCountglobal;
                            String killCount = killCountglobal;
                            String hostageCarriedDamage = hostageCarriedDamageglobal;
                            String pills = pillsglobal;
                            String agents = agentsglobal;
                            String[] hostageCarriedDamageArray = hostageCarriedDamage.split(",");
                            for (int j = 0; j < hostagesXYandHealthArrayList.size(); j += 3) {
                                if (Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) >= 98) {
                                    System.out.println(hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1) + "," + hostagesXYandHealthArrayList.get(j + 2));
                                    hostTurnToAgentXYlocal += "," + hostagesXYandHealthArrayList.get(j) + "," + hostagesXYandHealthArrayList.get(j + 1);
                                    noOfHostagesTurnAgents = Integer.toString(Integer.parseInt(noOfHostagesTurnAgents) + 1);
                                    deathCount = Integer.toString(Integer.parseInt(deathCount) + 1);
                                    noOfHostagesLeft = Integer.toString(Integer.parseInt(noOfHostagesLeft) + 1);
                                    hostagesXYandHealthArrayList.remove(j + 2);
                                    hostagesXYandHealthArrayList.remove(j + 1);
                                    hostagesXYandHealthArrayList.remove(j);
                                    j -= 3;
                                } else {
                                    hostagesXYandHealthArrayList.set(j + 2, Integer.toString(Integer.parseInt(hostagesXYandHealthArrayList.get(j + 2)) + 2));
                                }
                            }
                            for (int k = 0; k < hostageCarriedDamageArray.length; k++) {
                                if (!(Integer.parseInt(hostageCarriedDamageArray[k]) >= 100)) {
                                    hostageCarriedDamageArray[k] = Integer.toString(Integer.parseInt(hostageCarriedDamageArray[k]) + 2);
                                }

                            }
                            if (hostagesXYandHealthArrayList.size() == 0) {
                                hostages = "-1,-1,-1000000";
                            } else {
                                hostages = String.join(",", hostagesXYandHealthArrayList);

                            }
                            hostageCarriedDamage = String.join(",", hostageCarriedDamageArray);
                            state += neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + hostageCarriedDamage + ";" + pills + ";" + agents + ";" + hostages + ";";
                            String stateWithout = neoxy + ";" + neoHealth + ";" + noCarriedHostages + ";" + noOfHostagesLeft + ";" + noOfHostagesTurnAgents + ";" + hostTurnToAgentXYlocal + ";" + deathCount + ";" + killCount + ";" + pills + ";" + agents + ";";
                            Node node = new Node(state, nodeToCheckGoal, "fly", nodeToCheckGoal.depth++, 0);
                            if (strategy.equals("UC")) {
                                node = new Node(state, nodeToCheckGoal, "fly", nodeToCheckGoal.depth++, getAccumalatedPathCost(nodeToCheckGoal));

                            }
                            else if (strategy.equals("GR1")) {
                                node = new Node(state, nodeToCheckGoal, "fly", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB));

                            }
                            else if (strategy.equals("AS1")) {
                                node = new Node(state, nodeToCheckGoal, "fly", nodeToCheckGoal.depth++, getHeuristic1(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            else if (strategy.equals("GR2")) {
                                node = new Node(state, nodeToCheckGoal, "fly", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB));
                            }
                            else if (strategy.equals("AS2")) {
                                node = new Node(state, nodeToCheckGoal, "fly", nodeToCheckGoal.depth++, getHeuristic2(nodeToCheckGoal, tB) + getAccumalatedPathCost(nodeToCheckGoal));
                            }
                            if (!repeatedStates.contains(stateWithout)) {
                                if (Integer.parseInt(neoHealth) < 100) {
                                    repeatedStates.add(stateWithout);
                                    nodes = Strategy(strategy, nodes, node);
                                }
                            }
//                        System.out.println();
//                        System.out.println(nodes.size());
//                        //LinkedList<Node> copyNodes = new LinkedList<>(nodes);
//                        for(int y = 0; y< nodes.size(); y++){
//                            System.out.print(nodes.get(y).operator + ", ");
//                        }

                        }

                    }
                }
            }
        }
        //Then we add those new children to the queue, using certain queuing function (bfs, dfs, etc...)

    }


    public static String solve(String grid, String strategy, boolean visualize){
        String[] ops = { "up", "down", "left", "right", "carry", "drop", "takePill", "kill", "fly"};
        ArrayList<String> operators = new ArrayList<>();
        for(int i = 0; i<ops.length; i++){
            operators.add(ops[i]);
        }

        String solution = GeneralSearch(grid, operators, strategy);
//        "3,3;1;1,2;2,2;1,0;0,0;2,0,0,1,0,1,2,0;0,2,6,2,1,10;"
        return solution;
    }




}