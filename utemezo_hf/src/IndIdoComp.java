import java.util.Comparator;

public class IndIdoComp implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        return Integer.compare(a.ii, b.ii);
    }
}
