/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
