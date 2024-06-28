import java.util.Arrays;

public class ArrayIntegerList implements IntegerList {
    private final static int DEFAULT_CAPACITY = 16;
    private int size;
    private int[] array;
    private boolean isSorted = false;
    public ArrayIntegerList() {
        this.size = 0;
        this.array = new int[DEFAULT_CAPACITY];
    }

    public ArrayIntegerList(int capacity) {
        this.size = 0;
        this.array = new int[capacity];
    }

    private void checkSizeAndGrow() {
        if (size >= array.length)
            array = Arrays.copyOf(array, array.length * 2);
    }

    private void outOfBoundCheck(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Индекс больше размера списка. Размер списка %d. Запрошенный индекс %d".formatted(size, index));
        }
    }

    private void popItem(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public Integer add(Integer item) {
        checkSizeAndGrow();
        array[size++] = item;
        isSorted = false;
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        outOfBoundCheck(index);
        checkSizeAndGrow();
        size++;
        for (int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        isSorted = false;
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        outOfBoundCheck(index);
        array[index] = item;
        isSorted = false;
        return item;
    }

    @Override
    public Integer removeInteger(Integer item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return remove(i);
            }
        }
        throw new IllegalArgumentException("Элемент %s отсутствует в списке".formatted(item));
    }

    @Override
    public Integer remove(int index) {
        outOfBoundCheck(index);
        Integer item = array[index];
        popItem(index);
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        if (!isSorted){
            sortInsertion();
        }
        return binarySearch(item);
    }

    private boolean binarySearch(int item) {
        int min = 0;
        int max = size - 1;
        while (min <= max) {
            int mid = (min + max) / 2;
            if (item == array[mid]) {
                return true;
            }
            if (item < array[mid]) {
                max = mid - 1;
            } else {
                min = mid + 1;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Integer item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Integer item) {
        for (int i = size - 1; i >= 0; i--) {
            if (item.equals(array[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public Integer get(int index) {
        outOfBoundCheck(index);
        return array[index];
    }

    @Override
    public boolean equals(IntegerList otherList) {
        if (otherList.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!otherList.get(i).equals(array[i])) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        array = new int[DEFAULT_CAPACITY];
    }

    @Override
    public int[] toArray() {
        return Arrays.copyOf(array, size);
    }

    @Override
    public Integer[] toIntegerArray() {

        return Arrays.stream(Arrays.copyOf(array, size)).boxed().toArray(Integer[]::new);
    }

    @Override
    public void sort() {
        sortInsertion();
    }

    @Override
    public void sortBubble() {
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(j, j + 1);
                }
            }
        }
        isSorted = true;
    }

    @Override
    public void sortSelection() {

        for (int i = 0; i < size - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < size; j++) {
                if (array[j] < array[minElementIndex]) {
                    minElementIndex = j;

                }
            }
            swapElements(i, minElementIndex);
        }
        isSorted = true;
    }

    @Override
    public void sortInsertion() {
        for (int i = 1; i < size; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
        isSorted = true;
    }

    private void swapElements(int indexA, int indexB) {
        int tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }
}
