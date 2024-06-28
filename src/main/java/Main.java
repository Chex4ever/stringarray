import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) {
        int testSize=100_000;
        IntegerList bubbleArray = new ArrayIntegerList(testSize);
        IntegerList selectionArray = new ArrayIntegerList(testSize);
        IntegerList insertionArray = new ArrayIntegerList(testSize);
        for (int i = 0; i < testSize; i++) {
            bubbleArray.add(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
            selectionArray.add(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
            insertionArray.add(ThreadLocalRandom.current().nextInt(Integer.MIN_VALUE, Integer.MAX_VALUE));
        }
        long sortBubbleStart = System.currentTimeMillis();
        bubbleArray.sortBubble();
        System.out.println("sortBubble time " + (System.currentTimeMillis() - sortBubbleStart));
        long sortSelectionStart = System.currentTimeMillis();
        selectionArray.sortSelection();
        System.out.println("sortSelection time " + (System.currentTimeMillis() - sortSelectionStart));
        long sortInsertionStart = System.currentTimeMillis();
        insertionArray.sortInsertion();
        System.out.println("sortInsertion time " + (System.currentTimeMillis() - sortInsertionStart));
    }
}