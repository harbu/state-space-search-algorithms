package idastar.problems.constraint;

import java.util.List;
import java.util.Set;


public interface Constraint {

    public void apply(int varIndex, Integer valueChosen, List<Set<Integer>> domains);
}
