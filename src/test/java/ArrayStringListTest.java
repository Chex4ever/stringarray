import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class ArrayStringListTest {
    private static final String string1 = "Первый";
    private static final String string2 = "Второй";
    private static final String string3 = "Третий";
    StringList stringList = new ArrayStringList();

    @Test
    void emptyConstructorTest() {
        StringList emptyStringList = new ArrayStringList();
        assertThat(emptyStringList.size()).isEqualTo(0);

    }

    @Test
    void addTest() {
        String actual = stringList.add(string1);
        assertThat(actual).isEqualTo(string1);
        assertThat(stringList.size()).isEqualTo(1);
        assertThat(stringList.get(0)).isEqualTo(string1);
        actual = stringList.add(string2);
        assertThat(actual).isEqualTo(string2);
        assertThat(stringList.size()).isEqualTo(2);
        assertThat(stringList.get(1)).isEqualTo(string2);
    }
    @Test
    void addNullTest() {
        assertThatExceptionOfType(NullPointerException.class).isThrownBy(() -> stringList.add(null));
    }
    @Test
    void insertTest() {
        stringList.add(string1);
        stringList.add(string3);
        String actual = stringList.add(1, string2);
        assertThat(actual).isEqualTo(string2);
        assertThat(stringList.size()).isEqualTo(3);
        assertThat(stringList.get(1)).isEqualTo(string2);
        assertThat(stringList.get(2)).isEqualTo(string3);

    }

    @Test
    void addBruteTest() {
        int testCount = 1000;
        for (int i = 0; i < testCount; i++) {
            stringList.add(Integer.toString(i));
        }
        assertThat(stringList.size()).isEqualTo(testCount);
        assertThat(Integer.parseInt(stringList.get(testCount - 1))).isEqualTo(testCount - 1);
    }

    @Test
    void setTest() {
        int testCount = 1000;
        int setIndex = 111;
        for (int i = 0; i < testCount; i++) {
            stringList.add(Integer.toString(i));
        }
        String actual = stringList.set(setIndex, string1);
        assertThat(actual).isEqualTo(string1);
        assertThat(stringList.get(setIndex)).isEqualTo(string1);
    }

    @Test
    void removeByItemTest() {
        int testSize = 1222;
        int removeFrom = 111;
        int removeTo = 1000;
        for (int i = 0; i < testSize; i++) {
            stringList.add(Integer.toString(i));
        }
        for (int i = removeFrom; i < removeTo; i++) {
            stringList.remove(Integer.toString(i));
        }

        String actual = stringList.remove(Integer.toString(removeTo));
        assertThat(actual).isEqualTo(Integer.toString(removeTo));
        assertThat(stringList.get(removeFrom)).isEqualTo(Integer.toString(removeTo + 1));
        assertThat(stringList.size()).isEqualTo(testSize - (removeTo - removeFrom) - 1);
    }

    @Test
    void removeByIndexTest() {
        int testSize = 1222;
        int removeIndex = 111;
        int removeCount = 1000;
        for (int i = 0; i < testSize; i++) {
            stringList.add(Integer.toString(i));
        }
        for (int i = 0; i < removeCount; i++) {
            stringList.remove(removeIndex);
        }

        String actual = stringList.remove(removeIndex);
        assertThat(actual).isEqualTo(Integer.toString(removeCount + removeIndex));
        assertThat(stringList.get(removeIndex)).isEqualTo(Integer.toString(removeCount + removeIndex + 1));
        assertThat(stringList.size()).isEqualTo(testSize - removeCount - 1);
    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stringList.remove(0));
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stringList.remove(string1));
    }

    @Test
    void containsTest() {
        stringList.add(string1);
        stringList.add(string2);
        assertThat(stringList.contains(string1)).isTrue();
        assertThat(stringList.contains(string2)).isTrue();
        assertThat(stringList.contains(string3)).isFalse();
    }

    @Test
    void indexOfTest() {
        stringList.add(string1);
        stringList.add(string2);
        stringList.add(string1);
        stringList.add(string2);
        assertThat(stringList.indexOf(string1)).isEqualTo(0);
        assertThat(stringList.indexOf(string2)).isEqualTo(1);
        assertThat(stringList.indexOf(string3)).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        stringList.add(string1);
        stringList.add(string2);
        stringList.add(string1);
        stringList.add(string2);
        assertThat(stringList.lastIndexOf(string1)).isEqualTo(2);
        assertThat(stringList.lastIndexOf(string2)).isEqualTo(3);
        assertThat(stringList.lastIndexOf(string3)).isEqualTo(-1);
    }

    @Test
    void getTest() {
        stringList.add(string1);
        assertThat(stringList.get(0)).isEqualTo(string1);
        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> stringList.get(1));
    }

    @Test
    void EqualsTest() {
        StringList sameList = new ArrayStringList();
        StringList notSameList = new ArrayStringList();
        stringList.add(string1);
        stringList.add(string2);
        stringList.add(string3);
        sameList.add(string1);
        sameList.add(string2);
        sameList.add(string3);
        notSameList.add(string1);
        notSameList.add(string3);
        notSameList.add(string2);

        assertThat(stringList.equals(sameList)).isTrue();
        assertThat(stringList.equals(notSameList)).isFalse();
    }

    @Test
    void size() {
        int testSize = 1000;
        for (int i = 0; i < testSize; i++) {
            stringList.add(Integer.toString(i));
        }
        assertThat(stringList.size()).isEqualTo(testSize);
    }

    @Test
    void isEmpty() {
        stringList.add(string1);
        StringList emptyList = new ArrayStringList();
        assertThat(stringList.isEmpty()).isFalse();
        assertThat(emptyList.isEmpty()).isTrue();
    }

    @Test
    void clear() {
        stringList.add(string1);
        stringList.add(string2);
        stringList.clear();
        assertThat(stringList.isEmpty()).isTrue();
    }

    @Test
    void toArray() {
        stringList.add(string1);
        stringList.add(string2);
        stringList.add(string3);
        String[] actual = stringList.toArray();
        String[] expected = new String[]{string1, string2, string3};
        assertThat(actual).isEqualTo(expected);
    }
}