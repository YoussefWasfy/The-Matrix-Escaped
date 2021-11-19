import java.util.Random;
public class Matrix {
    static Random rand = new Random();
    //Generates Hostage coordinates and Hostage initial damage
    public int [] HostageCordAndDamage(int gridSize, boolean onGrid [][])
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
                        if(!onGrid[i][j])
                        {
                            x = i;
                            y = j;
                            notOngrid = false;
                        }
                    }
            }
            else if(onGrid[x][y])
            {
                System.out.println(cellCount);
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
    public int[] Agents(int gridSize, boolean onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[2];
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
        //        Checks whether this cell is empty and if not generates new coordinates
        while (notOngrid)
        {
            if(onGrid[x][y])
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
    public int[] Pills(int gridSize, boolean onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[2];
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
//        Checks whether this cell is empty and if not generates new coordinates
        while (notOngrid)
        {
            if(onGrid[x][y])
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
    public int[] Pads(int gridSize, boolean onGrid [][])
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
            if(onGrid[startpadx][startpady])
            {
                startpadx = rand.nextInt(gridSize);
                startpady = rand.nextInt(gridSize);
            }
            if (onGrid[finishpadx][finishpady])
            {
                finishpadx = rand.nextInt(gridSize);
                finishpady = rand.nextInt(gridSize);
            }
            if (!(onGrid[startpadx][startpady] && onGrid[finishpadx][finishpady]))
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

    public int[] TelephoneBooth(int gridSize, boolean onGrid [][])
    {
        boolean notOngrid = true;
        int coordinates[] = new int[2];
        int x = rand.nextInt(gridSize);
        int y = rand.nextInt(gridSize);
        while (notOngrid)
        {
            if(onGrid[x][y])
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

    public String GenGrid()
    {
        String grid = "";
        int gridSize = rand.nextInt(11)+5;
        int numOfCells = (gridSize * gridSize) - (2*gridSize);
        boolean onGrid[][]  = new boolean[gridSize][gridSize];
        int M = gridSize;
        int N = gridSize;
        grid += M + "," + N +"; ";
        int C = rand.nextInt(4)+1;
        grid += C + "; ";
        int numbOfHostages = rand.nextInt(8)+3;
        numOfCells -= numbOfHostages;
        int neox = rand.nextInt(gridSize);
        int neoy = rand.nextInt(gridSize);
        onGrid[neox][neoy] = true;
        numOfCells -= 1;
        grid += neox + "," + neoy + "; ";
        int telephoneCoord [] = TelephoneBooth(gridSize, onGrid);
        int telephonex = telephoneCoord[0];
        int telephoney = telephoneCoord[1];
        onGrid[telephonex][telephoney] = true;
        grid += telephonex + "," + telephoney + "; ";
        numOfCells -= 1;
        int maxAgents = rand.nextInt(2*gridSize);
        int maxPads = rand.nextInt(2*gridSize);
        int maxPills = rand.nextInt(numbOfHostages) + 1;

//        This loop generates the agents on the grid
        for (int i = 0; i < maxAgents; i++)
        {
            int agentCoord[] = Agents(gridSize, onGrid);
            int agentx = agentCoord[0];
            int agenty = agentCoord[1];
            onGrid[agentx][agenty] = true;
            if(i == (maxAgents-1))
            {
                grid += agentx + "," + agenty + "; ";
            }
            else
            {
                grid += agentx + "," + agenty + ",";
            }
        }
        System.out.println("Agents Generated");
//        This loop generates the pills on the grid
        for (int i = 0; i < maxPills; i++)
        {
            int pillCoord[] = Pills(gridSize, onGrid);
            int pillx = pillCoord[0];
            int pilly = pillCoord[1];
            onGrid[pillx][pilly] = true;
            if (i == (maxPills-1))
            {
                grid += pillx + "," + pilly + "; ";
            }
            else
            {
                grid += pillx + "," + pilly + ",";
            }
        }
        System.out.println("Pills Generated");
//        This loop generates the start and finish pads on the grid
        for (int i = 0; i < maxPads; i++)
        {
            int padCoord[] = Pads(gridSize, onGrid);
            int startpadx = padCoord[0];
            int startpady = padCoord[1];
            int finishpadx = padCoord[2];
            int finishpady = padCoord[3];
            onGrid[startpadx][startpady] = true;
            onGrid[finishpadx][finishpady] = true;
            if (i == (maxPads-1))
            {
                grid += startpadx + "," + startpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += startpadx + "," + startpady + "; ";
            }
            else
            {
                grid += startpadx + "," + startpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += finishpadx + "," + finishpady + ",";
                grid += startpadx + "," + startpady + ",";
            }
        }
        System.out.println("Pads Generated");
//        This loop generate the hostages on the grid with the initial damage
        for (int i = 0; i < numbOfHostages; i++)
        {
            int hostageCoord[] = HostageCordAndDamage(gridSize, onGrid);
            int hostagex = hostageCoord[0];
            int hostagey = hostageCoord[1];
            int damage = hostageCoord[2];
            onGrid[hostagex][hostagey] = true;
            if (i == (numbOfHostages-1))
            {
                grid += hostagex + "," + hostagey + ","+ damage + "; ";
            }
            else
            {
                grid += hostagex + "," + hostagey + ","+ damage + ",";
            }
        }
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

        return grid;
    }
}
