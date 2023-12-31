public class Vertex {
    public int id;
    public char color;
    public Vertex pred;
    public int dist;
    public VertexList adj;

    public Vertex(int id, char color, Vertex pred, int dist, VertexList adj) {
        this.id = id;
        this.color = color;
        this.pred = pred;
        this.dist = dist;
        this.adj = adj;
    }

    public Vertex(int id) {
        this(id, 'w', null, 0, new VertexList());
    }

    @Override
    public String toString() {
        String s = "ID: " + id + "\nColor: " + color + "\nPredecessor ID: ";
        if (pred == null) {
            s += "null";
        } else {
            s += pred.id;
        }
        s += "\nDistance: " + dist
                + "\nAdjacency List: " + adj.toString();
        return s;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vertex) {
            Vertex vertex = (Vertex) obj;
            return this.id == vertex.id;
        }
        return false;
    }


}