
import java.util.*;


public class Graph {
    static LinkedList<Node> nodes = new LinkedList<>();
    static String[][] commands = new String[10][20];
    Graph(){}
    public void graphBuilding(){
        int x = 0;
        int y = 1;
        Node task = findNode(commands[x][0]);
        while(commands[x][y] != null && !task.finished) {
            while (commands[x][y] != null) {
                String str = commands[x][y];
                Node t = findNode(commands[x][0]);

                if(str.contains("+")){
                    if(!t.empty &&  !t.finished){
                        t.out.add(findNode(makeRes(str)));
                        Node r = findNode(makeRes(str));
                        r.in.add(t);
                        t.waitingFor.add(r);
                    }else if(t.empty && !findNode(makeRes(str)).taken && t.waitingFor.isEmpty() && !t.finished ){
                        t.in.add(findNode(makeRes(str)));
                        Node r = findNode(makeRes(str));
                        r.out.add(t);
                        t.empty = false;
                        r.taken = true;
                        t.waitingFor.remove(r);
                    }else if(t.empty && findNode(makeRes(str)).taken && !t.finished){
                        t.out.add(findNode(makeRes(str)));
                        Node r = findNode(makeRes(str));
                        r.in.add(t);
                        t.waitingFor.add(r);
                    }
                }
                if(str.contains("-")){
                    if(/*!t.empty && !t.waitingFor.contains(findNode(makeRes(str))) &&*/ !t.finished){
                        t.in.remove(findNode(makeRes(str)));
                        Node r = findNode(makeRes(str));
                        r.out.remove(t);
                        t.empty = true;
                        r.taken = false;
                        Node temp = whosWaitingFor(r);
                        if(temp != null && temp != t ){
                            temp.in.add(r);
                            temp.empty = false;
                            r.out.add(temp);
                            r.taken = true;
                            temp.out.remove(r);
                            r.in.remove(temp);
                            temp.waitingFor.remove(r);
                        }
                    }
                    /*
                    else if(t.empty && !t.finished){
                        t.in.remove(findNode(makeRes(str)));
                        Node r = findNode(makeRes(str));
                        r.out.remove(t);
                    }
                    */
                }
                if(!str.equals("0") && holtPont()) {
                    System.out.println(t.name + "," + y + "," + makeRes(str));
                    for (Node n : t.out) {
                        if (n.name.equals(makeRes(str))) {
                            t.out.remove(n);
                            n.in.remove(t);
                        }
                    }
                    for (Node n : t.in) {
                        if (n.name.equals(makeRes(str))) {
                                t.in.remove(n);
                                n.out.remove(t);
                            }
                        }
                }
                if(str.equals(doubleAsk())){
                    int i = y + 1;
                    System.out.println(t.name + "," + i + "," + makeRes(str));
                }
                if(commands[x][y + 1] == null){
                    t.finished = true;
                }


                x++;
            }
            y++;
            x= 0;
        }
    }

    public Node findNode(String name){
        for(Node t : nodes){
            if(t.name.equals(name)){
                return  t;
            }
        }
        return null;
    }
    public Node whosWaitingFor(Node n){
        for(Node t : nodes){
            if(t.waitingFor.contains(n) && t.empty){
                return t;
            }
        }
        return null;
    }
    public String makeRes(String command){
        char[] c = command.toCharArray();
        String str = String.valueOf(c[1]);
        str+=c[2];
        return str;

    }
    public boolean holtPont(){
        Queue<Node> noIn = new LinkedList<>();
        LinkedList<Node> list = new LinkedList<>(nodes);
        int[] inDegree = new int[nodes.size()];
        for(Node n : list){
            for(Node neighbour : n.in){

            }
        }
        for(Node n : list){
            if(n.in.isEmpty()){
                noIn.add(n);
            }
        }
        if(noIn.isEmpty()){
            return true;
        }


        while(!noIn.isEmpty()){
            Node node =  noIn.poll();
            list.remove(node);
            /*
            if(list.isEmpty()){
                return false;
            }
           */
            LinkedList<Node> outToRemove = new LinkedList<>(node.out);
            LinkedList<Node> inToRemove = new LinkedList<>();
            for (Node n : outToRemove) {
                if(n.name.contains("T")) {
                    Task temp = new Task();
                    temp = (Task) n;
                    inToRemove.add(n);
                }
                if(n.name.contains("R")) {
                    Resource temp = new Resource();
                    temp = (Resource) n;
                    inToRemove.add(n);
                }

            }

            for(Node n : inToRemove) {
                //outToRemove.remove();
                inToRemove.remove(n);
                if (inToRemove.isEmpty()) {
                    noIn.add(n);
                }
            }
        }
        return !list.isEmpty();
    }

    public String doubleAsk(){
        String str;
        int x = 0;
        int y = 0;
        while(commands[x][y] != null) {
            while (commands[x][y] != null) {
                str = commands[x][y];
                if(!str.equals("0") && str.equals(commands[x][y + 1])){
                    return str;
                }
                y++;
            }
            x++;
            y = 0;
        }
        return null;
    }
}
