public class Main {
    public static void main(String[] args) {
        //Testing readFromString() and writeToString() methods
        Graph graph = new Graph();
        Graph newGraph = graph.readFromString("0,3;0,1;3,4;4,7;3,2;1,2;2,7;");
        System.out.println("A new graph is created: \n" + newGraph);
        System.out.println("Above graph's edges: ");
        System.out.println(newGraph.writeToString());

        /*//BFS test
        System.out.println("Created new graph from BFS method with source vertex 1: ");
        Graph newGraph2 = newGraph.BFS(newGraph.vertices.findOrMake(1));
        System.out.println(newGraph2.writeToString());*/

        //DFS recursive test
        System.out.println("The new DFS tree created by recursive DFS:");
        Graph newGraph3 = newGraph.recDFS();
        System.out.println(newGraph3);

        //DFS iterative test
        System.out.println("The new DFS tree created by iterative DFS:");
        Graph newGraph4 = newGraph.itDFS();
        System.out.println(newGraph4);

        //Topological sort test
        System.out.println("Topological sort/order of the original graph:");
        System.out.println(newGraph.topSort());

        //Creating a Cyclic graph
        System.out.println("Topological sort/order of the cyclic graph:");
        Graph cyclicGraph = new Graph();
        Graph newCyclicGraph = cyclicGraph.readFromString("0,3;3,0;");
        System.out.println(newCyclicGraph.topSort());
        System.out.println("Topological sort/order of the cyclic graph:");
        Graph newCyclicGraph2 = cyclicGraph.readFromString("3,3;");
        System.out.println(newCyclicGraph2.topSort());
    }

}
