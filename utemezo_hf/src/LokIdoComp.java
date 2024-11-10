import java.util.Comparator;

public class LokIdoComp implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        return Integer.compare(a.li, b.li);
    }
}
