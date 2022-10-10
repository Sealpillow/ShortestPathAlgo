package ShortestPath;

import java.util.Arrays;

public class PracticePrim
{
    // Prim 's Algorithm in Java

    // Overview:
    // 1. start from source, initialise selected[source] = true
    // 2. find destination with connection with the source
    // 3. find the lowest weighted edge from the source to the destination
    // 4. if found -> min = g[v][e] -> selected[e] = true -> edge++
    // 5. iterate through vertex that has selected[] = true
    // 6. find the next lowest weighted edge from the vertex that has selected[] = true
    // 7. if found -> min = g[v][e] -> selected[e] = true -> edge++
    // 8. continue till edge = V-1;
    public static void Prim(int[][] G, int vertex)
    {

        int INF = Integer.MAX_VALUE;

        int edge; // number of edge

        // create an array to track selected vertex
        // selected will become true otherwise false
        boolean[] selected = new boolean[vertex];

        // set selected false initially
        Arrays.fill(selected, false);

        // set number of edge to 0
        edge = 0;

        // the number of edge in minimum spanning tree will be
        // always less than (V -1), where V is number of vertices in
        // graph

        // choose 0th vertex and make it true
        selected[0] = true;

        // print for edge and weight
        System.out.println("Edge : Weight");

        while (edge < vertex - 1)
        {
            // For every vertex in the set S, find the all adjacent vertices,
            // calculate the distance from the vertex selected at step 1.
            // if the vertex is already in the set S, discard it otherwise
            // choose another vertex nearest to selected vertex at step 1.

            int min = INF;
            int s = 0; // row number
            int d = 0; // col number

            for (int source = 0; source < vertex; source++)
            {
                if (selected[source])
                {
                    for (int destination = 0; destination < vertex; destination++)
                    {
                        // not in selected and there is an edge
                        if (!selected[destination] && G[source][destination] != 0)
                        {
                            //System.out.println(i+" "+j+" " +G[i][j]);
                            if (min > G[source][destination])
                            {
                                min = G[source][destination];
                                s = source;
                                d = destination;
                            }
                        }
                    }
                }
            }
            System.out.println(s + " - " + d + " :  " + G[s][d]);
            selected[d] = true;
            edge++;
        }
    }

    public static void main(String[] args)
    {

        // number of vertices in graph
        int vertex = 5;

        // create a 2d array of size 5x5
        // for adjacency matrix to represent graph
        int[][] G = { { 0, 9, 75, 0, 0 },
                      { 9, 0, 95, 19, 42 },
                      { 75, 95, 0, 51, 66 },
                      { 0, 19, 51, 0, 31 },
                      { 0, 42, 66, 31, 0 } };

        Prim(G, vertex);
    }

}
