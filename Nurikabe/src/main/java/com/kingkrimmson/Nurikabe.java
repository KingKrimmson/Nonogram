package com.kingkrimmson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Chris on 1/28/2016.
 */
public class Nurikabe {

    private int width, height;
    public ArrayList<ArrayList<String>> x, y;
    private ArrayList<ArrayList<Boolean>> board;

    public Nurikabe(File boardFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(boardFile);

        x = new ArrayList<ArrayList<String>>();
        y = new ArrayList<ArrayList<String>>();

        x.add(new ArrayList<String>());

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
            for (int w = maxwidth; w > 0; w-- ){
                System.out.print("   ");
            }
            for (int w = 0; w < width; w++) {
                String cell = " ";
                try {
                    cell = x.get(w).get(h);
                } catch (Exception e) {}
                System.out.print(" " + cell + " ");
            }
            System.out.println();
        }
        for (int h = 0; h < height; h++) {
            for (int w = maxwidth; w >= 0; w--) {
                String cell = " ";
                try {
                    cell = y.get(h).get(w);
                } catch (Exception e) {}
                System.out.print(" " + cell + " ");
            }
            
            System.out.println();
        }
    }

    public static void main(String args[]){
        try {
            Nurikabe game = new Nurikabe(new File(args[0] + "/Puzzles/basic_test_2.txt"));
            game.printBoard();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
