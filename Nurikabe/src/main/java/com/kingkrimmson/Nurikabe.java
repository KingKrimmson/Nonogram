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
    public ArrayList<ArrayList<Integer>> x, y;
    private ArrayList<ArrayList<Boolean>> board;

    public Nurikabe(File boardFile) throws FileNotFoundException {
        Scanner scanner = new Scanner(boardFile);

        x = new ArrayList<ArrayList<Integer>>();
        y = new ArrayList<ArrayList<Integer>>();

        width = scanner.nextInt();
        height = scanner.nextInt();

        for (int w = 0; w < width; w++) {
            x.add(new ArrayList(Arrays.asList(scanner.next().split(" "))));
        }
        for (int h = 0; h < height; h++) {
            y.add(new ArrayList(Arrays.asList(scanner.next().split(" "))));
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

    public static void main(String args[]){
        try {
            Nurikabe game = new Nurikabe(new File(args[0]));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
