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
 * Represents the abstract lambda expression whose sole purpose in life is to
 * evaluate itself on an input and return the result of the evaluation.
 */
public interface ISortOperator {

    /**
     * @param arg input object for the lambda expression.
     * @return an output object resulting from evaluating the lambda expression
     * on the input arg.
     */
    public Object apply(Object arg);
}
