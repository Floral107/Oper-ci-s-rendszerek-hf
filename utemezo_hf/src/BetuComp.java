import java.util.Comparator;

public class BetuComp implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        return a.betu.compareTo(b.betu);
    }
}
