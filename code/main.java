import java.util.ArrayList;

public class main {


    public static void main(String[] args) {
        Matrix matrix = new Matrix();
        String grid = matrix.GenGrid();
        String solution = matrix.solve(grid, "DF", false);
        System.out.println(grid);





    }
}
