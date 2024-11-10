import java.util.Scanner;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Graph g = new Graph();

        Scanner sc = new Scanner(System.in);  //System.in
        boolean meg_mehet = true;
        int x = 0;
        while (meg_mehet && sc.hasNextLine()) {
            int y = 0;
            String line = sc.nextLine();
            if (line.contains(",")) {
               String[] str = line.split(",");
               for (String s : str) {
                   Graph.commands[x][y] = s;
                   y++;
               }

               for(String temp : str){
                    if(temp.contains("T")){
                        Task t = new Task();
                        t.name = temp;
                        int i = 0;
                        for(Node n : Graph.nodes) {
                            if (!n.name.equals(t.name)) {
                                i++;
                            }
                        }
                        if(i == Graph.nodes.size()){
                            Graph.nodes.add(t);
                        }
                    }else if(temp.contains("R")){
                        Resource r = new Resource();
                        r.name = g.makeRes(temp);
                        int i = 0;
                        for(Node n : Graph.nodes) {
                            if (!n.name.equals(r.name)) {
                                i++;
                            }
                        }
                        if(i == Graph.nodes.size()){
                            Graph.nodes.add(r);
                        }
                    }
               }
                x++;
            } else meg_mehet = false;
        }
        sc.close();
        g.graphBuilding();
    }

}