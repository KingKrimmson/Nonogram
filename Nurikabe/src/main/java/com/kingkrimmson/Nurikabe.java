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
        for (int h = 0; h < height; h++) {
            ArrayList<Integer> line = new ArrayList<Integer>();
            int curr = 0;
            for (int w = 0; w < width; w++ ){
                if (board.get(h).get(w)) {
                    curr +=1;
                } else {
                    if (curr > 0 || w == width - 1) {
                        line.add(curr);
                        curr = 0;
                    }
                }
            }
            Collections.reverse(line);
            if (!line.equals(y.get(h))) {
                return false;
            }
        }
        for (int w = 0; w < width; w++) {

        }
        return true;
    }

    public static void main(String args[]){
        try {
            Nurikabe game = new Nurikabe(new File("../Puzzles/basic_test_1.txt"));
            game.board.get(0).set(2, true);
            game.board.get(1).set(2, true);
            game.board.get(2).set(0, true);
            game.board.get(2).set(1, true);
            game.board.get(2).set(2, true);
            game.board.get(2).set(3, true);
            game.board.get(2).set(4, true);
            game.board.get(3).set(2, true);
            game.board.get(4).set(2, true);
            game.printBoard();
            System.out.println(game.isSolved());
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
