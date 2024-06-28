import java.util.Arrays;

public class ArrayIntegerList implements IntegerList {
    private final static int DEFAULT_CAPACITY = 16;
    private int size;
    private int[] array;
    private int[] sortedArray;
    private boolean isSorted = false;
    public ArrayIntegerList() {
        this.size = 0;
        this.array = new int[DEFAULT_CAPACITY];
    }

    public ArrayIntegerList(int capacity) {
        this.size = 0;
        this.array = new int[capacity];
    }

    private void grow() {
        if (size >= array.length)
            array = Arrays.copyOf(array, (int) (array.length * 1.5));
    }

    private void outOfBoundCheck(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Индекс больше размера списка. Размер списка %d. Запрошенный индекс %d".formatted(size, index));
        }
    }

    @Override
    public Integer add(Integer item) {
        grow();
        array[size++] = item;
        isSorted = false;
        sortedArray = new int[0];
        return item;
    }

    @Override
    public Integer add(int index, Integer item) {
        outOfBoundCheck(index);
        grow();
        size++;
        for (int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        isSorted = false;
        sortedArray = new int[0];
        return item;
    }

    @Override
    public Integer set(int index, Integer item) {
        outOfBoundCheck(index);
        array[index] = item;
        isSorted = false;
        sortedArray = new int[0];
        return item;
    }

    @Override
    public Integer removeInteger(Integer item) {
        for (int i = 0; i < size; i++) {
            if (item.equals(array[i])) {
                isSorted = false;
                sortedArray = new int[0];
                return remove(i);
            }
        }
        throw new IllegalArgumentException("Элемент %s отсутствует в списке".formatted(item));
    }

    @Override
    public Integer remove(int index) {
        outOfBoundCheck(index);
        Integer item = array[index];
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        isSorted = false;
        sortedArray = new int[0];
        return item;
    }

    @Override
    public boolean contains(Integer item) {
        if (!isSorted){
            sortedArray=toArray();
            sort(sortedArray);
        }
        return binarySearch(sortedArray, item);
    }

    private static boolean binarySearch(int[] array, int item) {
        int min = 0;
        int max = array.length - 1;
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
    public void sort(int[] array) {
        sortMerge(array);
        isSorted=true;
    }

    public static void sortBubble(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    swapElements(array, j, j + 1);
                }
            }
        }
    }

    public static void sortSelection(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            int minElementIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minElementIndex]) {
                    minElementIndex = j;

                }
            }
            swapElements(array, i, minElementIndex);
        }
    }


    public static void sortInsertion(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i;
            while (j > 0 && array[j - 1] >= temp) {
                array[j] = array[j - 1];
                j--;
            }
            array[j] = temp;
        }
    }

    private static void swapElements(int[] array, int indexA, int indexB) {
        int tmp = array[indexA];
        array[indexA] = array[indexB];
        array[indexB] = tmp;
    }

    public static void sortMerge(int[] arr) {
        if (arr.length < 2) {
            return;
        }
        int mid = arr.length / 2;
        int[] left = new int[mid];
        int[] right = new int[arr.length - mid];

        for (int i = 0; i < left.length; i++) {
            left[i] = arr[i];
        }

        for (int i = 0; i < right.length; i++) {
            right[i] = arr[mid + i];
        }

        sortMerge(left);
        sortMerge(right);

        merge(arr, left, right);
    }

    public static void merge(int[] arr, int[] left, int[] right) {
        int mainP = 0;
        int leftP = 0;
        int rightP = 0;
        while (leftP < left.length && rightP < right.length) {
            if (left[leftP] <= right[rightP]) {
                arr[mainP++] = left[leftP++];
            } else {
                arr[mainP++] = right[rightP++];
            }
        }
        while (leftP < left.length) {
            arr[mainP++] = left[leftP++];
        }
        while (rightP < right.length) {
            arr[mainP++] = right[rightP++];
        }
    }
}
