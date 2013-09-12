package org.harbu.search.problem.constraint;

import org.harbu.search.problem.NoHeuristic;
import org.harbu.search.problem.Problem;

/**
 * Represents a general constraint satisfaction problem. The problem is as
 * follows: we have a set of variables we wish to set. For each variable, we
 * have a domain of valid values. The challenge comes in the form of
 * constraints: each time we assign a variable, depending upon the problem
 * description, the domains for the remaining variables get reduced.
 *
 * @author Eric Andrews
 */
public class ConstraintSatisfaction extends Problem<ConstraintSatisfactionState> {

    public ConstraintSatisfaction(ConstraintSatisfactionState startState) {
        setStartState(startState);
        setGoal(new ConstraintSatisfactionGoal());
        setHeuristic(new NoHeuristic<ConstraintSatisfactionState>());
    }
}
