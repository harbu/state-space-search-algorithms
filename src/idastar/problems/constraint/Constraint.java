package idastar.problems.constraint;

import java.util.List;
import java.util.Set;


/**
 * A constraint to be used in <code>ConstraintSatisfactionProblem</code>.
 * @author Eric Andrews
 */
public interface Constraint {

    /**
     * Apply this constraint to variables by reducing their domains.
     * @param varIndex      the (index of) variable set.
     * @param valueChosen   the value that was placed into <code>varIndex</code>.
     * @param domains       one domain per variable (in order of variables),
     *                       use <code>remove</code> on sets to reduce domains.
     */
    public void apply(int varIndex, Integer valueChosen, List<Set<Integer>> domains);
}
