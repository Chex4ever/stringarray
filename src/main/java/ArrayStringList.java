import java.util.Arrays;

public class ArrayStringList implements StringList {
    private final static int DEFAULT_CAPACITY = 16;
    private int size;
    private String[] array;

    public ArrayStringList() {
        this.size = 0;
        this.array = new String[DEFAULT_CAPACITY];
    }

    private void checkSizeAndGrow() {
        if (size >= array.length)
            array = Arrays.copyOf(array, (int) (array.length * 1.5));
    }

    private void outOfBoundAddCheck(int index) {
        if (index > size) {
            throw new IllegalArgumentException("Индекс больше размера списка. Размер списка %d. Запрошенный индекс %d".formatted(size, index));
        }
    }
    private void outOfBoundCheck(int index) {
        if (index >= size) {
            throw new IllegalArgumentException("Индекс больше размера списка. Размер списка %d. Запрошенный индекс %d".formatted(size, index));
        }
    }
    private void checkIsNull(String item) {
        if (item.isEmpty()) {
            throw new NullPointerException();
        }
    }

    private void popItem(int index) {
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    @Override
    public String add(String item) {
        checkIsNull(item);
        checkSizeAndGrow();
        array[size++] = item;
        return item;
    }

    @Override
    public String add(int index, String item) {
        checkIsNull(item);
        outOfBoundAddCheck(index);
        checkSizeAndGrow();
        size++;
        for (int i = size - 1; i > index; i--) {
            array[i] = array[i - 1];
        }
        array[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        outOfBoundCheck(index);
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return remove(i);
            }
        }
        throw new IllegalArgumentException("Элемент %s отсутствует в списке".formatted(item));
    }

    @Override
    public String remove(int index) {
        outOfBoundCheck(index);
        String item = array[index];
        popItem(index);
        return item;
    }

    @Override
    public boolean contains(String item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(String item) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(String item) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public String get(int index) {
        outOfBoundCheck(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList.size() != size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!array[i].equals(otherList.get(i))) {
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
        array = new String[DEFAULT_CAPACITY];
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, size);
    }
}
