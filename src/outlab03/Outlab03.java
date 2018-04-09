package outlab03;

/*
Authors:
Date:
Overview:
 */

import java.util.Scanner;

public class Outlab03 {
        public static void main(String args[]) {            
            floydWarshall();
        }
    
    public static void floydWarshall() {
            /*
            steps: 
            1. get number of vertices -- make new array for table; set k = # vertices
            2. store weighted graph in 2d array; 0 = inf, - = [1][1], [2][2], etc.
            3. make 2 arrays: da for k-1 values, db for dk values
            4. loop through and fill d1/ to dk values in table
            5. print each completed table set for the current k value
            6. print the final version of the dk table, clearly labeled
            */    
            
            int k, j, i;            // k = # of vertices, i/j = row/column for table        
            int matrix[][];          // 2d array for matrix values in table

            // get # of vertices for k value
            Scanner scan = new Scanner(System.in);
            System.out.println("Enter the number of vertices");
            k = scan.nextInt();

            // set up the # of rows and columns from k value
            i = k + 1;
            j = k + 1;

            // set up the 2d array to hold the initial graph values
            matrix = new int[i][j];

            // get and store initial graph values in array
            System.out.println("Enter the weighted matrix for the graph:");
            for (int s = 1; s <= k; s++) {
                for (int d = 1; d <= k; d++) {
                    // store each new int in the 2d array for each row
                    matrix[s][d] = scan.nextInt();

                    if (s == d) {
                        matrix[s][d] = 0;
                        continue;
                    }
                }
            }
            
            System.out.println("\n");
            
            // print out the initial matrix
            System.out.println("Initital graph matrix (k = 0): ");
            printFM(matrix, k);
            System.out.println("\n");
        
            /*
            psuedocode:

            Let d be a |V| by |V| array of minimum weights initialized to infinity
            BEGIN
                for each vertex v
                    d[v][v] ← 0
                        for each edge (u,v)
                            d[u][v] ← w(u,v)
                                for k from 1 to |V|
                                    for i from 1 to |V|
                                        for j from 1 to |V|
                                            if d[i][j] > d[i][k] + d[k][j]
                                                d[i][j] ← d[i][k] + d[k][j]
                                            end if
            END
            */

            // print out each subsequent matrix table from n = 1 to n = k
            for (int n = 1; n <= k; n++) {
                System.out.println("Updated weighted graph for k = " + n);
                // adjust the matrice table in temp matrix
                    for (int r = 1; r <= k; r++) {
                        // row loop
                        for (int c = 1; c <= k; c++) {
                            // col loop

                            // check to see which value is smaller: current [r][c] cell or
                            // the value sum for [r][k] + [k][c]
                            if (matrix[r][c] > ( matrix[r][n] + matrix[n][c] )) {
                                // current [r][c] cell is bigger, so assign smaller value
                                matrix[r][c] = matrix[r][n] + matrix[n][c];
                            }
                            /*else {
                                // current [r][c] cell is smaller, so assign that value
                                graph[r][c] = matrix[r][c];
                            }*/
                        }
                    }
                printFM(matrix, k);
                System.out.println();
            } 
            
            System.out.println("Final matrix for graph (k = " + k + "):");
            printFM(matrix, k);
            System.out.println();
            
            scan.close(); 
        
    }
    
    public static void printFM(int matrix[][], int k) {
            for (int a = 1; a <= k; a++) {
                System.out.print("\t"+ a);
            }
            System.out.println("\n");
            for (int r = 1; r <= k; r++) {
                // row loop
                System.out.print(r + "\t");
                
                for (int c = 1; c <= k; c++) {
                    // col loop
                    System.out.print(matrix[r][c] + "\t");
                }
                System.out.println();
            }
    }
}
