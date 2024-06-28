import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ArrayIntegerListTest {
    IntegerList integerList = new ArrayIntegerList();

    @Test
    void emptyConstructorTest() {
        IntegerList emptyIntegerList = new ArrayIntegerList();
        assertThat(emptyIntegerList.size()).isEqualTo(0);

    }

    @Test
    void addTest() {
        Integer actual = integerList.add(1);
        assertThat(actual).isEqualTo(1);
        assertThat(integerList.size()).isEqualTo(1);
        assertThat(integerList.get(0)).isEqualTo(1);
        actual = integerList.add(2);
        assertThat(actual).isEqualTo(2);
        assertThat(integerList.size()).isEqualTo(2);
        assertThat(integerList.get(1)).isEqualTo(2);
    }

    @Test
    void insertTest() {
        integerList.add(1);
        integerList.add(3);
        Integer actual = integerList.add(1, 2);
        assertThat(actual).isEqualTo(2);
        assertThat(integerList.size()).isEqualTo(3);
        assertThat(integerList.get(1)).isEqualTo(2);
        assertThat(integerList.get(2)).isEqualTo(3);

    }

    @Test
    void addBruteTest() {
        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            integerList.add(i);
        }
        assertThat(integerList.size()).isEqualTo(testCount);
        assertThat(integerList.get(testCount - 1)).isEqualTo(testCount - 1);
    }

    @Test
    void setTest() {
        int testCount = 1000;
        int setIndex = 111;
        for (int i = 0; i < testCount; i++) {
            integerList.add(i);
        }
        Integer actual = integerList.set(setIndex, 1);
        assertThat(actual).isEqualTo(1);
        assertThat(integerList.get(setIndex)).isEqualTo(1);
    }

    @Test
    void removeIntegerTest() {
        int testSize = 1222;
        int removeFrom = 111;
        int removeTo = 1000;
        for (int i = 0; i < testSize; i++) {
            integerList.add(i);
        }
        for (int i = removeFrom; i < removeTo; i++) {
            integerList.removeInteger(i);
        }

        Integer actual = integerList.removeInteger(removeTo);
        assertThat(actual).isEqualTo(removeTo);
        assertThat(integerList.get(removeFrom)).isEqualTo(removeTo + 1);
        assertThat(integerList.size()).isEqualTo(testSize - (removeTo - removeFrom) - 1);
    }

    @Test
    void removeByIndexTest() {
        int testSize = 1222;
        int removeIndex = 111;
        int removeCount = 1000;
        for (int i = 0; i < testSize; i++) {
            integerList.add(i);
        }
        for (int i = 0; i < removeCount; i++) {
            integerList.remove(removeIndex);
        }

        Integer actual = integerList.remove(removeIndex);
        assertThat(actual).isEqualTo(removeCount + removeIndex);
        assertThat(integerList.get(removeIndex)).isEqualTo(removeCount + removeIndex + 1);
        assertThat(integerList.size()).isEqualTo(testSize - removeCount - 1);
    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> integerList.remove(0));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> integerList.remove(1));
    }

    @Test
    void containsTest() {
        integerList.add(5);
        integerList.add(1);
        integerList.add(4);
        integerList.add(3);
        integerList.add(2);
        assertThat(integerList.contains(1)).isTrue();
        assertThat(integerList.contains(2)).isTrue();
        assertThat(integerList.contains(3)).isTrue();
        assertThat(integerList.contains(4)).isTrue();
        assertThat(integerList.contains(5)).isTrue();
        assertThat(integerList.contains(6)).isFalse();
    }

    @Test
    void indexOfTest() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(1);
        integerList.add(2);
        assertThat(integerList.indexOf(1)).isEqualTo(0);
        assertThat(integerList.indexOf(2)).isEqualTo(1);
        assertThat(integerList.indexOf(3)).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(1);
        integerList.add(2);
        assertThat(integerList.lastIndexOf(1)).isEqualTo(2);
        assertThat(integerList.lastIndexOf(2)).isEqualTo(3);
        assertThat(integerList.lastIndexOf(3)).isEqualTo(-1);
    }

    @Test
    void getTest() {
        integerList.add(1);
        assertThat(integerList.get(0)).isEqualTo(1);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> integerList.get(1));
    }

    @Test
    void EqualsTest() {
        IntegerList sameList = new ArrayIntegerList();
        IntegerList notSameList = new ArrayIntegerList();
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        sameList.add(1);
        sameList.add(2);
        sameList.add(3);
        notSameList.add(1);
        notSameList.add(3);
        notSameList.add(2);

        assertThat(integerList.equals(sameList)).isTrue();
        assertThat(integerList.equals(notSameList)).isFalse();
    }

    @Test
    void size() {
        int testSize = 1000;
        for (int i = 0; i < testSize; i++) {
            integerList.add(i);
        }
        assertThat(integerList.size()).isEqualTo(testSize);
    }

    @Test
    void isEmpty() {
        integerList.add(1);
        IntegerList emptyList = new ArrayIntegerList();
        assertThat(integerList.isEmpty()).isFalse();
        assertThat(emptyList.isEmpty()).isTrue();
    }

    @Test
    void clear() {
        integerList.add(1);
        integerList.add(2);
        integerList.clear();
        assertThat(integerList.isEmpty()).isTrue();
    }

    @Test
    void toArray() {
        integerList.add(1);
        integerList.add(2);
        integerList.add(3);
        Integer[] actual = integerList.toIntegerArray();
        Integer[] expected = new Integer[]{1, 2, 3};
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void sortBubbleTest() {
        int[] expected = new int[]{1, 2, 3, 4, 5};
        integerList.add(5);
        integerList.add(1);
        integerList.add(4);
        integerList.add(2);
        integerList.add(3);
        integerList.sortBubble();
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void sortSelectionTest() {
        int[] expected = new int[]{1, 2, 3, 4, 5};
        integerList.add(5);
        integerList.add(1);
        integerList.add(4);
        integerList.add(2);
        integerList.add(3);
        integerList.sortSelection();
        assertThat(integerList.toArray()).isEqualTo(expected);
    }

    @Test
    void sortInsertionTest() {
        int[] expected = new int[]{1, 2, 3, 4, 5};
        integerList.add(5);
        integerList.add(1);
        integerList.add(4);
        integerList.add(2);
        integerList.add(3);
        integerList.sortInsertion();
        assertThat(integerList.toArray()).isEqualTo(expected);
    }
}