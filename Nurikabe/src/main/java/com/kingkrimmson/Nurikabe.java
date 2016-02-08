package com.kingkrimmson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.jar.Pack200;

/**
 * Created by Chris on 1/28/2016.
 */
public class Nurikabe {

    private int width, height;
    public ArrayList<ArrayList<String>> x, y;
    public ArrayList<ArrayList<Boolean>> board;

    public Nurikabe(File boardFile) throws FileNotFoundException {
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

        board = new ArrayList<ArrayList<Boolean>>();
        for (int w = 0; w < width; w++) {
            ArrayList<Boolean> row = new ArrayList<Boolean>();
            for (int h = 0; h < height; h++) {
                row.add(false);
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
                if (board.get(h).get(w)) {
                    System.out.print("XX|");
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
                if (board.get(h).get(w)) {
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
            /* For testing
            Collections.reverse(line);
            System.out.print("My Line " + h + ": ");
            for (int l = 0; l < line.size(); l++) {
                System.out.print(line.get(l) + " ");
            }
            System.out.println();
            System.out.print("RL Line " + h + ": ");
            for (int l = 0; l < y.get(h).size(); l++) {
                System.out.print(y.get(h).get(l) + " ");
            }
            System.out.println();
            System.out.println(compareArrays(line, y.get(h)));*/

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
                if (board.get(h).get(w)) {
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
            /* For testing
            Collections.reverse(line);
            System.out.print("My Line " + h + ": ");
            for (int l = 0; l < line.size(); l++) {
                System.out.print(line.get(l) + " ");
            }
            System.out.println();
            System.out.print("RL Line " + h + ": ");
            for (int l = 0; l < y.get(h).size(); l++) {
                System.out.print(y.get(h).get(l) + " ");
            }
            System.out.println();
            System.out.println(compareArrays(line, y.get(h)));*/

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



    public static void main(String args[]){
        try {
            Nurikabe game = new Nurikabe(new File("../Puzzles/basic_test_1.txt"));
            game.printBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
