import java.util.LinkedList;

public class Keret {
    public Character name;
    private LinkedList<Integer> pages = new LinkedList<>();
    int used;
    boolean empty;

    int freezeTime = 0;
    boolean dirty = false;

    Keret(Character name){
        this.name = name;
        empty = true;
        used = 0;
    }


    public  LinkedList<Integer> getPages() {
        return pages;
    }


}
