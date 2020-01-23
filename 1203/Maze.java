import java.util.*;

public interface Maze<Pos> {

    Pos getStart();

    List<Pos> getNeighbors(Pos p);

    boolean isGoal(Pos p);

    void printAnswer(List<Pos> ps);
}