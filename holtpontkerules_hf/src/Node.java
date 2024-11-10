import java.util.LinkedList;

public abstract class Node {
    LinkedList<Node> in = new LinkedList<>();
    LinkedList<Node> out = new LinkedList<>();
    LinkedList<Node> waitingFor = new LinkedList<>();
    String name;
    boolean finished;
    boolean empty;
    boolean taken;


}
