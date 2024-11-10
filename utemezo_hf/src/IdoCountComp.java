import java.util.Comparator;

public class IdoCountComp implements Comparator<Task> {
    @Override
    public int compare(Task a, Task b) {
        {
            return Integer.compare(-(a.ii), -(b.ii));
        }
    }
}
