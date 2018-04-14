/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package sortingalgoritms.sorts;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Eric Canull
 */
public class SortOperatorList {
    
    private final List<AbstractSort> operators;
    
    public SortOperatorList() {
        
        // Add the Sorting classes into the arrayList
        operators = new ArrayList<>();
        operators.add(CBubbleSort.SINGLETON);
        operators.add(CSelectionSort.SINGLETON);
        operators.add(CInsertionSort.SINGLETON);
        operators.add(CMergeSort.SINGLETON);
        operators.add(CQuickSort.SINGLETON);
        operators.add(CShellSort.SINGLETON);
        operators.add(CPancakeSort.SINGLETON);
        operators.add(CCocktailSort.SINGLETON);
        operators.add(CHeapSort.SINGLETON);
        operators.add(CExchangeSort.SINGLETON);
    }
    
    public List<AbstractSort> getList() {
        return operators;
    } 
    
    /**
     * create a lambda method that accepts a boolean condition statement and
     * uses the test interface to search for specific algorithm
     * @param condition
     * @return 
     */
    public List<AbstractSort> getOperator(SortOperatorEquals condition) {
        List<AbstractSort> getSortOperator = new ArrayList<>();
        operators.stream().filter((b) -> (condition.test(b))).forEachOrdered((b) -> {
            getSortOperator.add(b);
        });
        return getSortOperator;
    }

    public interface SortOperatorEquals {
        boolean test(AbstractSort a);
    }
}
