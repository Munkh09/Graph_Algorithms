import java.util.ArrayList;
import java.util.List;

public class VertexList {
    public List<Vertex> list;
    public VertexList(){
        list = new ArrayList<Vertex>();
    }
    //Finds or creates a vertex
    public Vertex findOrMake(int i) {
        for(Vertex vertex : list){
            if(vertex.id == i){
                return vertex;
            }
        }
        Vertex vertex = new Vertex(i);
        list.add(vertex);
        return vertex;
    }

    @Override
    public String toString() {
        String s = "";
        for (Vertex vertex : list) {
            s += vertex.id;
            if(list.indexOf(vertex) != list.size()-1){
                s += ",";
            }
        }
        return s;
    }

}