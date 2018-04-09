/*
 * Permissions of this free software license are conditioned on making available
 * complete source code of licensed works and modifications under the same 
 * license or the GNU GPLv3. Copyright and license notices must be preserved.
 * Contributors provide an express grant of patent rights. However, a larger 
 * work using the licensed work through interfaces provided by the licensed 
 * work may be distributed under different terms and without source code 
 * for the larger work.
 */
package sortingalgoritms.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author andje22
 */
public class SortOperatorList {
    
    private final List<ASortOperator> operators;
    
    public SortOperatorList() {
        
        // Add the Sorting classes into the arrayList
        operators = new ArrayList<>();
        operators.add(CBubbleSorter.SINGLETON);
        operators.add(CSelectionSorter.SINGLETON);
        operators.add(CInsertionSorter.SINGLETON);
        operators.add(CMergeSorter.SINGLETON);
        operators.add(CQuickSorter.SINGLETON);
        operators.add(CShellSorter.SINGLETON);
        operators.add(CPancakeSorter.SINGLETON);
        operators.add(CCocktailSorter.SINGLETON);
        operators.add(CHeapSorter.SINGLETON);
        operators.add(CExchangeSorter.SINGLETON);
    }
    
    public List<ASortOperator> getList() {
        return operators;
    } 
    
    /**
     * create a lambda method that accepts a boolean condition statement and
     * uses the test interface to search for specific algorithm
     * @param condition
     * @return 
     */
    public List<ASortOperator> getOperator(SortOperatorEquals condition) {
        List<ASortOperator> getSortOperator = new ArrayList<>();
        operators.stream().filter((b) -> (condition.test(b))).forEachOrdered((b) -> {
            getSortOperator.add(b);
        });
        return getSortOperator;
    }

    public interface SortOperatorEquals {
        boolean test(ASortOperator a);
    }
}
