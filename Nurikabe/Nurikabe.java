import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Chris on 1/28/2016.
 */
public class Nurikabe {

    private int width, height;
    private ArrayList<ArrayList<Integer>> x, y;
    private ArrayList<ArrayList<Boolean>> board;

    public Nurikabe(File boardFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(boardFile);

        x = new ArrayList<ArrayList<Integer>>();
        y = new ArrayList<ArrayList<Integer>>();

        width = scanner.nextInt();
        height = scanner.nextInt();

        for (int i = 0; i < width; i++) {
            x.add(new ArrayList(scanner.next().split(" ")));
        }
    }


}
