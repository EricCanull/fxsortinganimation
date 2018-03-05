package sortingalgoritms.sorts;

import sortingalgoritms.util.IComparable;

/**
 * Implementation of the Pancake Sort algorithm.
 *
 * @author Eric Canull
 * @version 1.0
 */
public final class CPancakeSorter extends ASortOperator {

    public static final CPancakeSorter SINGLETON = new CPancakeSorter();

    /**
     * Implementation of the Pancake Sort algorithm.
     */
    private CPancakeSorter() {
        //Private Constructor will prevent the instantiation of this class
        //directly
    }

    /**
     * Starts the Pancake Sort algorithm.
     *
     * @param numbers an array of numbers used for the sorting
     * @param lowIndex a integer representing the lowest index position in the
     * array
     * @param highIndex a integer representing the highest index position in the
     * array
     */
    @Override
    protected void startSort(IComparable[] numbers, int lowIndex, int highIndex) {
        resetCount();
        IComparable max;

        for (int index = 0; index < numbers.length; index++) {
            max = numbers[0];
            lowIndex = 0;
            count();
            for (int j = 0; j < numbers.length - index; j++) {
                count();
                if (numbers[j].compare(max) == IComparable.GREATER) {
                    max = numbers[j];
                    lowIndex = j;
                    count();
                }
            }

            flip(numbers, lowIndex, numbers.length - 1 - index);
            count();
        }
    }

    /**
     * Flips the array like a pancake.
     *
     * @param numbers an array of numbers used for the sorting
     * @param left a integer representing the left index position in the array
     * @param right a integer representing the right index position in the array
     */
    public void flip(IComparable[] numbers, int left, int right) {

        while (left <= right) {
            IComparable temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
            count();
        }
    }
}
