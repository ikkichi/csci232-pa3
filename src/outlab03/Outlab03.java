/* * Author: Marielle Korringa
 * Date: 10 April 2018 
 * Overview: This program implement different algorithms to find the MST of a graph. 
 *  There are no special instructions for running this program. */

package outlab03;

import java.util.*;
import java.lang.*;
import java.io.*;

class Edge implements Comparable<Edge> {
    char u;
    char v;
    int weight;
    public int compareTo(Edge compareEdge) {
        return this.weight-compareEdge.weight;
    }
}

class Cluster{
    char parent;
    int rank;
}

class Graph{
	static int V;
	int E; 
	static Edge edges[]; 
	
    public Graph(int n, int m) {
        V=n;
        E=m;
        edges = new Edge[E]; 
        for(int x = 0; x < E; ++x){
        	edges[x] = new Edge(); 
        }
    }
	public int V(){
		return V;
	}
	public int E(){
		return E;
	}
	static char find(Cluster clusters[], char x){
	    if (clusters[x].parent != x){
	        clusters[x].parent = find(clusters, clusters[x].parent);
	    }
	    return clusters[x].parent;
	}
	
	//Merge two clusters together
	 static void Merge(Cluster cluster[], char x, char y){
	        char u = find(cluster, x); //C(u)
	        char v = find(cluster, y); //C(v)
	        
	        if (cluster[u].rank < cluster[v].rank){ //if C(u) < C(v)
	            cluster[u].parent = v;
	        }
	        else if (cluster[u].rank > cluster[v].rank){ //if C(u) > C(v)
	            cluster[v].parent = u;
	        }
	        else{ //if C(u) = C(v)
	            cluster[v].parent = u;
	            cluster[u].rank++;
	        }
	    }
}

public class Program3 {
	public static void main(String args[]){ 
		 int V = 4;  // Number of vertices in graph
	     int E = 5;  // Number of edges in graph
	     Graph graph = new Graph(V, E);
	 
	        // add edge 0-1
	        graph.edges[0].u = 'A';
	        graph.edges[0].v = 'B';
	        graph.edges[0].weight = 10;
	 
	        // add edge 0-2
	        graph.edges[1].u = 'A';
	        graph.edges[1].v = 'C';
	        graph.edges[1].weight = 6;
	 
	        // add edge 0-3
	        graph.edges[2].u = 'A';
	        graph.edges[2].v = 'D';
	        graph.edges[2].weight = 5;
	 
	        // add edge 1-3
	        graph.edges[3].u = 'B';
	        graph.edges[3].v = 'D';
	        graph.edges[3].weight = 15;
	 
	        // add edge 2-3
	        graph.edges[4].u = 'C';
	        graph.edges[4].v = 'D';
	        graph.edges[4].weight = 4;
		
		KruskalMST(graph);
	}
	 //Kruskal’s Algorithm to find the minimum spanning tree for a weighted graph
	 public static void KruskalMST(Graph G){
	        Edge T[] = new Edge[G.V];  //Contain edges of MST
	        for (int x=0; x<G.V; ++x){
	            T[x] = new Edge();
	        }
	        Arrays.sort(G.edges);
	        Cluster cluster[] = new Cluster[G.V];
	        for(int x=0; x<G.V; ++x){
	            cluster[x]=new Cluster();
	        }
	        //Define an elementary cluster C(v)={v} 
	        for (char v = 0; v < G.V; ++v){
	            cluster[v].parent = v;
	            cluster[v].rank = 0;
	        }
	        PriorityQueue<Integer> Q = new PriorityQueue<Integer>(); 
	        for (int i = 0; i < G.V; ++i){
	        	Q.add(G.edges[i].weight); 
	        }
	        int x = 0;
	        int edge = 0; 
	        while (edge < G.V - 1){
	            Edge next_edge = new Edge();
	            next_edge = G.edges[x++];
	            char y = G.find(cluster, next_edge.u); //C(u)
	            char z = G.find(cluster, next_edge.v); //C(v)
	            if (y != z){ //if C(u) != C(v)
	            	//add edge to Tree
	                T[edge++] = next_edge;
	                //Merge C(u) and C(v) into one cluster
	                G.Merge(cluster, y, z);
	            }
	        }
	        //print out the results 
	        System.out.println("Kruskal Algorithm: ");
	        for (x = 0; x < edge; ++x){
	            System.out.print(T[x].u+""+T[x].v+" ");
	        }
	        System.out.println();
		}
}
