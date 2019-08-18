import com.sun.istack.internal.NotNull;

import java.util.Arrays;

// https://habr.com/ru/post/440436/#11  - вопросы & ответы
// https://habr.com/ru/post/422085/     - сортировка выбором
// https://habr.com/ru/post/204600/     - сортировка пузырьком
// https://habr.com/ru/post/415935/     - сортировка вставкой
public class Main {

    public static void main(String[] args) {
//        minMax();
//        selectionSort();
//        bubbleSort();
//        shakenNotStirred();
        removeAllEntriesOf(9);
    }


    // =================================================================================================================
    // Learning code lab
    // =================================================================================================================

    /**
     * Removing a defined value from an Array and trim size of the Array.
     * @param entry a value to remove from the Array.
     */
    private static void removeAllEntriesOf(final int entry) {
        int length = 10000;
        int[] numbers = getIntArrayOfLength(length);
        printArrayWithText("array before: ", numbers);

        int offset = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            if (entry == numbers[i]) {
                offset++;

            } else {
                numbers[i - offset] = numbers[i];
            }
        }
        long delta = System.currentTimeMillis() - startTime;

        int[] result = Arrays.copyOf(numbers, length - offset);
        printArrayWithText("array _after: ", result);
        System.out.println("swaps: " + offset
                + ", entryToRemove: " + entry
                + ", len before: " + length
                + ", after: " + result.length
        );
        System.out.println("time, ms: " + delta);
    }

    /**
     * Sorting by "simple selection" algorithm
     */
    private static void selectionSort() {
        int length = 10000;
        int[] numbers = getIntArrayOfLength(length);
        printArrayWithText("-sorted array: ", numbers);

        int nonSortedLength;
        int max;
        int indexToSwapWith;
        long swaps = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            nonSortedLength = length - i;
            indexToSwapWith = nonSortedLength - 1;
            max = numbers[indexToSwapWith];
            for (int j = 0; j < nonSortedLength; j++) {
                if (max < numbers[j]) {
                    max = numbers[j];
                    indexToSwapWith = j;
                }
            }

            if (indexToSwapWith == nonSortedLength - 1) {
                continue;
            }

            swaps++;
            numbers[indexToSwapWith] = numbers[nonSortedLength - 1];
            numbers[nonSortedLength - 1] = max;
        }
        long delta = System.currentTimeMillis() - startTime;

        printArrayWithText("+sorted array: ", numbers);
        System.out.println("swaps: " + swaps);
        System.out.println("time, ms: " + delta);
    }

    /**
     * Sorting by "bubble" algorithm
     */
    private static void bubbleSort() {
        int length = 10000;
        int[] numbers = getIntArrayOfLength(length);
        printArrayWithText("-sorted array: ", numbers);

        int buff;
        long swaps = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            // Без последнего элемента, т.к. будет свайп j+1
            for (int j = 0; j < length - i - 1; j++) {
                if (numbers[j] > numbers[j+1]) {
                    buff = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = buff;
                    swaps++;
                }
            }
        }
        long delta = System.currentTimeMillis() - startTime;

        printArrayWithText("+sorted array: ", numbers);
        System.out.println("swaps: " + swaps);
        System.out.println("time, ms: " + delta);
    }

    /**
     * Sorting by "shake" algorithm (2-way bubbles)
     */
    private static void shakenNotStirred() {
        int length = 10000;
        int[] numbers = getIntArrayOfLength(length);
        printArrayWithText("-sorted array: ", numbers);

        int buff;
        long positiveSwaps = 0;
        long negativeSwaps = 0;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < length; i++) {
            // Без последнего элемента, т.к. будет свайп j+1
            for (int j = 0; j < length - i - 1; j++) {
                if (numbers[j] > numbers[j+1]) {
                    buff = numbers[j];
                    numbers[j] = numbers[j+1];
                    numbers[j+1] = buff;
                    positiveSwaps++;
                }
            }

            for (int k = length - i - 1; k > i; k--) {
                if (numbers[k] < numbers[k-1]) {
                    buff = numbers[k];
                    numbers[k] = numbers[k-1];
                    numbers[k-1] = buff;
                    negativeSwaps++;
                }
            }
        }
        long delta = System.currentTimeMillis() - startTime;

        printArrayWithText("+sorted array: ", numbers);
        System.out.println("swaps positive: " + positiveSwaps + ", negative: " + negativeSwaps);
        System.out.println("time, ms: " + delta);
    }

    /**
     * Min-max-average in a int[100]
     */
    private static void minMax() {
        int length = 100;
        int[] numbers = getIntArrayOfLength(length);

        int max = numbers[0];
        int min = max;
        int summ = 0;
        int current = 0;
        for (int i = 0; i < length; i++) {
            current = numbers[i];
            if (max < current) {
                max = current;
            }

            if (min > current) {
                min = current;
            }

            summ += current;
        }

        System.out.println("min: " + min + ", max: " + max + ", average: " + summ / length);
    }


    // =================================================================================================================
    // Utility
    // =================================================================================================================

    private static int[] getIntArrayOfLength(final int length) {
        int[] array = new int[length];
        for (int i = 0; i < length; i++) {
            array[i] = getRandom1To100();
        }
        return array;
    }

    private static int getRandom1To100() {
        return (int) (Math.random() * 100) + 1;
    }

    private static void printArrayWithText(@NotNull final String text, @NotNull final int[] array) {
        System.out.println(text + Arrays.toString(array));
    }
}
