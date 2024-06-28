import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int testSize = 100_000;
        int[] randomArray = new int[testSize];
        for (int i = 0; i < testSize; i++) {
            randomArray[i]=ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
        int[] bubbleArray = randomArray.clone();
        int[] selectionArray = randomArray.clone();
        int[] insertionArray = randomArray.clone();
        int[] mergeArray = randomArray.clone();
        long sortBubbleStart = System.currentTimeMillis();
        ArrayIntegerList.sortBubble(bubbleArray);
        System.out.println("sortBubble time " + (System.currentTimeMillis() - sortBubbleStart));
        long sortSelectionStart = System.currentTimeMillis();
        ArrayIntegerList.sortSelection(selectionArray);
        System.out.println("sortSelection time " + (System.currentTimeMillis() - sortSelectionStart));
        long sortInsertionStart = System.currentTimeMillis();
        ArrayIntegerList.sortInsertion(insertionArray);
        System.out.println("sortInsertion time " + (System.currentTimeMillis() - sortInsertionStart));
        long sortMergeStart = System.currentTimeMillis();
        ArrayIntegerList.sortInsertion(mergeArray);
        System.out.println("sortMerge time " + (System.currentTimeMillis() - sortMergeStart));
    }
}