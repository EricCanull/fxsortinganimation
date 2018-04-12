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
package sortingalgoritms.util;

/**
 * Implements the functionality of the collection lists java.lang.Comparable.
 */
public interface IComparable {

    final int LESS = -1;
    final int EQUAL = 0;
    final int GREATER = 1;

    /**
     * Similar to Comparable.compareTo
     *
     * @param number a value to compare
     * @return the result of the comparison
     */
    int compare(IComparable number);
}
