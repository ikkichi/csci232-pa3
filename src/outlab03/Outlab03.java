package outlab03;

/*
* Authors: Elizabeth Hamaoka
* Date: 10 April 2018
* Overview:Implement one of the three graphing methods -- Prim's Algorithm
* Needs to use user input -- the graph.csv file has the adjacency matrix i used
*/

import java.io.*;
import java.util.*;

public class Outlab03 {
    static Scanner scan;


    public static void main (String[] args) throws FileNotFoundException {

        scan = new Scanner(System.in);

        int [][] matrix = new int [6][6];
        int [] visited = new int[6];
        int min;
        int u = 0;
        int v = 0;
        int total = 0;

        for (int i = 0; i < 6; i++) {
            visited[i] = 0;

            for (int j = 0; j < 6; j++) {
                matrix[i][j] = scanner.nextInt();

                if (matrix[i][j] == 0) {
                    matrix[i][j] = 999;
                }
            }
        }
        visited[0] = 1;

        for(int counter = 0; counter < 5; counter++)
        {
            min = 999;

            for(int i = 0; i < 6; i++)
            {
                if (visited[i] == 1)
                {
                    for (int j = 0; j < 6; j++)
                    {
                        if (visited[j] != 1)
                        {
                            if(min > matrix[i][j])
                            {
                                min = matrix[i][j];
                                u = i;
                                v = j;
                            }
                        }
                    }
                }
            }
            visited[v] = 1;
            total += min;
            System.out.println("Edge found: " + u + "->" + v);
        }
        System.out.println("the weight of the mst is: " + total);
    }
}
