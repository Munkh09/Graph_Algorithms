import java.util.*;

public class Graph {
    public VertexList vertices;
    public Graph(){
        vertices = new VertexList();
    }
    //creates a new graph based on the input (which is a set of edges separated by semicolons)
    //returns the new graph
    //The input can only have single digit integers followed by either a comma or a semicolon
    public Graph readFromString(String input) {
        Graph newGraph = new Graph();
        for(int i=0; i < input.length(); i=i+4){
            newGraph.addEdge(Integer.parseInt(String.valueOf(input.charAt(i))), Integer.parseInt(String.valueOf(input.charAt(i + 2))));
        }
        return newGraph;
    }
    //creates a directed edge between to vertices
    public void addEdge(int i, int j) {
        Vertex vertex1 = vertices.findOrMake(i);
        Vertex vertex2 = vertices.findOrMake(j);
        vertex2.pred = vertex1;
        vertex2.dist = vertex1.dist + 1;
        vertex1.adj.list.add(vertex2);
    }

    //Returns directed edges
    //e.g: 1:2; means there exists an edge from vertex 1 to 2
    public String writeToString() {
        String s = "";
        for (Vertex vertex : vertices.list){
            if(!vertex.adj.list.isEmpty()){
                s += vertex.id + ":" + vertex.adj + ";";
            }
        }
        return s;
    }
    //Iterative Breadth First Search that returns a graph with updated predecessors and distances
    public Graph BFS(Vertex s){
        Graph newGraph = new Graph();
        for (Vertex vertex : vertices.list){
            if(!vertex.equals(s)) {
                vertex.color = 'w';
                vertex.dist = 100000;  //dummy value
                vertex.pred = null;
            }
        }
        s.color = 'g';
        s.dist = 0;
        s.pred = null;
        Queue<Vertex> queue = new LinkedList<>();
        queue.add(s);
        while(!queue.isEmpty()){
            Vertex u = queue.remove();
            for (Vertex vertex : u.adj.list){
                if(vertex.color == 'w'){
                    vertex.color = 'g';
                    vertex.dist = u.dist + 1;
                    vertex.pred = u;
                    queue.add(vertex);
                }
            }
            u.color = 'b';
            newGraph.vertices.list.add(u);
        }
        return newGraph;
    }

    //Recursive Depth First Search - traverses the whole graph while updating predecessors and colors
    // and returns the graph
    //Since we don't specify a specific source vertex, the returned graph will be the same as the original graph
    //However, the traversal order is Depth First Search
    public Graph recDFS(){
        Graph newGraph = new Graph();
        for (Vertex vertex : vertices.list){
            vertex.color = 'w';
            vertex.pred = null;
        }
        for (Vertex vertex : vertices.list){
            if (vertex.color == 'w'){
                visitDFS(vertex, newGraph);
            }
        }
        return newGraph;
    }
    //visit part of recursive DFS
    private void visitDFS(Vertex currentVertex, Graph newGraph){
        currentVertex.color = 'g';
        for (Vertex vertex : currentVertex.adj.list){
            if(vertex.color == 'w'){
                vertex.pred = currentVertex;
                visitDFS(vertex, newGraph);
            }
        }
        currentVertex.color = 'b';
        newGraph.vertices.list.add(currentVertex);
    }

    //iterative Depth First Search - traverses the whole graph while updating predecessors
    // and returns the graph
    //Since we don't specify a specific source vertex, the returned graph will be the same as the original graph
    //However, the traversal order is Depth First Search
    public Graph itDFS() {
        Graph newGraph = new Graph();
        for (Vertex v : vertices.list) {
            v.color = 'w';
            v.pred = null;
        }
        Stack<Vertex> stack = new Stack<>();
        for(Vertex v : vertices.list) {
            if(v.color =='w') {
                stack.push(v);
                while (!stack.isEmpty()) {
                    Vertex u = stack.pop();
                    for (Vertex vertex : u.adj.list) {
                        if (vertex.color == 'w') {
                            stack.push(vertex);
                            vertex.pred = u;
                        }
                    }
                    u.color = 'b';
                    newGraph.vertices.list.add(u);
                }
            }
        }
        return newGraph;
    }

    //Topological Sort - returns topologically ordered linked list of vertix ids
    //or null if the graph is cyclic
    public List<Integer> topSort(){
            List<Integer> linkedList = new LinkedList<Integer>();
            for (Vertex v : vertices.list) {
                v.color = 'w';
                v.pred = null;
            }
            Stack<Vertex> stack = new Stack<>();
            for(Vertex v : vertices.list) {
                if(v.color =='w') {
                    stack.push(v);
                    while (!stack.isEmpty()) {
                        Vertex u = stack.pop();
                        for (Vertex vertex : u.adj.list) {
                            if(vertex.adj.list.contains(u)){
                                return null;
                            }
                            if (vertex.color == 'w') {
                                stack.push(vertex);
                                vertex.pred = u;
                            }
                        }
                        u.color = 'b';
                        linkedList.add(u.id);
                    }
                }
            }
            String edges = this.writeToString();
            for(int i=2; i<edges.length(); i=i+4){
                for(int k=4; k<edges.length(); k=k+4){
                    if(edges.charAt(i) == edges.charAt(k)){
                        if(edges.charAt(i-2) == edges.charAt(k+2)) {
                            return null;
                        }
                    }
                }
            }
            return linkedList;
        }

    //toString method to see the graph content
    @Override
    public String toString(){
        String s = "";
        for (Vertex vertex : vertices.list){
            s += vertex.toString() + " \n\n";
        }
        return s;
    }


}
