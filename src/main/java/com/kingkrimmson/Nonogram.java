package com.kingkrimmson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chris on 1/28/2016.
 */
public class Nonogram {

    private final int EMPTY=0, YCELL=1, NCELL=2;
    private int width, height;
    public ArrayList<ArrayList<String>> x, y;
    public ArrayList<ArrayList<Integer>> board;

    public Nonogram(File boardFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(boardFile);

        x = new ArrayList<ArrayList<String>>();
        y = new ArrayList<ArrayList<String>>();

        width = scanner.nextInt();
        height = scanner.nextInt();

        //Dump blank line
        scanner.nextLine();

        for (int w = 0; w < width; w++) {
            x.add(new ArrayList(Arrays.asList(scanner.nextLine().split(" "))));
        }
        for (int h = 0; h < height; h++) {
            y.add(new ArrayList(Arrays.asList(scanner.nextLine().split(" "))));
        }

        board = new ArrayList<ArrayList<Integer>>();
        for (int w = 0; w < width; w++) {
            ArrayList<Integer> row = new ArrayList<Integer>();
            for (int h = 0; h < height; h++) {
                row.add(0);
            }
            board.add(row);
        }
    }

    public void printBoard() {
        int maxheight=0,maxwidth=0;
        for (ArrayList<String> row : x) {
            if (row.size()>maxheight) {
                maxheight = row.size();
            }
        }
        for (ArrayList<String> row : y) {
            if (row.size()>maxwidth) {
                maxwidth = row.size();
            }
        }
        for (int h = maxheight; h >= 0; h--) {
            for (int w = maxwidth; w >= 0; w-- ){
                System.out.print("   ");
            }
            for (int w = 0; w < width; w++) {
                String cell = "  ";
                try {
                    cell = x.get(w).get(h);
                    if (cell.length() == 1) {
                        cell = " " + cell;
                    }
                } catch (Exception e) {}
                System.out.print(" " + cell);
            }
            System.out.println();
        }
        for (int w = maxwidth; w >= 0; w-- ){
            System.out.print("   ");
        }
        for (int w = 0; w < width; w++) {
            System.out.print(" __");
        }
        System.out.println();

        for (int h = 0; h < height; h++) {
            for (int w = maxwidth; w >= 0; w--) {
                String cell = "  ";
                try {
                    cell = y.get(h).get(w);
                    if (cell.length() == 1) {
                        cell = " " + cell;
                    }
                } catch (Exception e) {}
                System.out.print(cell + " ");
            }
            System.out.print("|");
            for (int w = 0; w < width; w++) {
                if (board.get(h).get(w)==YCELL) {
                    System.out.print("XX|");
                } else if (board.get(h).get(w)==NCELL) {
                    System.out.print("--|");
                } else {
                    if (h == width - 1) {
                        System.out.print("__|");
                    } else {
                        System.out.print("  |");
                    }
                }
            }
            System.out.println();
        }
    }

    public boolean isSolved() {
        // For each row
        for (int h = 0; h < height; h++) {
            ArrayList<String> line = new ArrayList<String>();
            int curr = 0;
            // For each cell
            for (int w = 0; w < width; w++ ){
                // If the cell is filled
                if (board.get(h).get(w)==YCELL) {
                    // Increment the count
                    curr +=1;
                    // If it's the last cell, log
                    if (w == width - 1) {
                        line.add(String.valueOf(curr));
                    }
                } else { // If the cell is not filled
                    // And curr is set OR it's the last cell with an empty row
                    if (curr > 0 || (w == width - 1 && line.size()==0)) {
                        line.add(String.valueOf(curr));
                        curr = 0;
                    }
                }
            }
            if (!compareArrays(line, y.get(h))) {
                return false;
            }
        }
        // For each column
        for (int w = 0; w < width; w++) {
            ArrayList<String> line = new ArrayList<String>();
            int curr = 0;
            // For each cell
            for (int h = 0; h < height; h++ ){
                // If the cell is filled
                if (board.get(h).get(w)==YCELL) {
                    // Increment the count
                    curr +=1;
                    // If it's the last cell, log
                    if (h == height - 1) {
                        line.add(String.valueOf(curr));
                    }
                } else { // If the cell is not filled
                    // And curr is set OR it's the last cell with an empty row
                    if (curr > 0 || (h == height - 1 && line.size()==0)) {
                        line.add(String.valueOf(curr));
                        curr = 0;
                    }
                }
            }
            if (!compareArrays(line, x.get(w))) {
                return false;
            }
        }
        return true;
    }

    public boolean compareArrays(ArrayList<String> array1, ArrayList<String> array2) {
        if (array1 != null && array2 != null){
            if (array1.size() != array2.size())
                return false;
            else
                for (int i = 0; i < array2.size(); i++) {
                    if (!array2.get(i).equals(array1.get(i))) {
                        return false;
                    }
                }
        }else{
            return false;
        }
        return true;
    }

    public boolean rowHasEmpty(int row) {
        for (int cell : board.get(row)) {
            if (cell == EMPTY )
                return true;
        }
        return false;
    }

    public boolean colHasEmpty(int col) {
        for (int c = 0; c < height; c++) {
            if (board.get(c).get(col) == EMPTY)
                return true;
        }
        return false;
    }

    public void fillRow(int row, int fill) {
        for (int c = 0; c < width; c++) {
            board.get(row).set(c, fill);
        }
    }

    public void fillCol(int col, int fill) {
        for (int c = 0; c < height; c++) {
            board.get(c).set(col, fill);
        }
    }

    public ArrayList<Integer> getRowRule(int row) {
        ArrayList<Integer> rowArray = new ArrayList<Integer>();
        for (String cell : y.get(row)) {
            //rowArray.add
        }
        return null;
    }

    public boolean solve() {
        // Until solved
        while (!isSolved()) {
            // For each row
            for (int h = 0; h < height; h++) {
                //ArrayList<ArrayList<Integer>> possibilities = new ArrayList<ArrayList<Integer>>();
                //ArrayList<Integer> rule = y.get(h);
                // Generate all possibilities that fit this row's rule
                // Reduce set to possibilities that fit row's current state
                // Implement any cells which are constant among all sets.



                // If the row still has cells to fill
                if (rowHasEmpty(h)) {
                    // If the hint has only 1 number
                    if (y.get(h).size() == 1) {
                        // That number is the full line or empty line
                        if (Integer.parseInt(y.get(h).get(0)) == height) {
                            fillRow(h, YCELL);
                            continue;
                        } else if (Integer.parseInt(y.get(h).get(0)) == 0) {
                            fillRow(h, NCELL);
                            continue;
                        }
                    }
                }
            }
            // For each column
            for (int w = 0; w < width; w++) {
                // If the col still has cells to fill
                if (colHasEmpty(w)) {
                    if (x.get(w).size() == 1) {
                        if (Integer.parseInt(x.get(w).get(0)) == width) {
                            fillCol(w, YCELL);
                            continue;
                        } else if (Integer.parseInt(x.get(w).get(0)) == 0) {
                            fillCol(w, NCELL);
                            continue;
                        }
                    }
                }
            }
            if (!isSolved())
                return false;
        }
        return true;
    }

    public static void main(String args[]){
        try {
            Nonogram game = new Nonogram(new File("Puzzles/basic_test_3.txt"));
            game.printBoard();
            System.out.println("Solvable? " + game.solve());
            game.printBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
