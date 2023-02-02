import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.map.Map;
import components.map.Map.Pair;

/**
 * JUnit test fixture for {@code Map<String, String>}'s constructor and kernel
 * methods.
 *
 * @author Karl Chavez and Hafsa Gureye
 *
 */
public abstract class MapTest {

    /**
     * Invokes the appropriate {@code Map} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new map
     * @ensures constructorTest = {}
     */
    protected abstract Map<String, String> constructorTest();

    /**
     * Invokes the appropriate {@code Map} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new map
     * @ensures constructorRef = {}
     */
    protected abstract Map<String, String> constructorRef();

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the implementation
     * under test type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsTest = [pairs in args]
     */
    private Map<String, String> createFromArgsTest(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorTest();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    /**
     *
     * Creates and returns a {@code Map<String, String>} of the reference
     * implementation type with the given entries.
     *
     * @param args
     *            the (key, value) pairs for the map
     * @return the constructed map
     * @requires <pre>
     * [args.length is even]  and
     * [the 'key' entries in args are unique]
     * </pre>
     * @ensures createFromArgsRef = [pairs in args]
     */
    private Map<String, String> createFromArgsRef(String... args) {
        assert args.length % 2 == 0 : "Violation of: args.length is even";
        Map<String, String> map = this.constructorRef();
        for (int i = 0; i < args.length; i += 2) {
            assert !map.hasKey(args[i]) : ""
                    + "Violation of: the 'key' entries in args are unique";
            map.add(args[i], args[i + 1]);
        }
        return map;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, value,
    // hasKey, and size

    /*
     * test cases for constructors
     */

    //Test when creating an empty constructor
    @Test
    public final void testConstructorEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when creating a non-empty constructor of one element
    @Test
    public final void testConstructorNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("june", "july");
        Map<String, String> mExpected = this.createFromArgsRef("june", "july");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when creating a non-empty constructor with more than 1 element
    @Test
    public final void testConstructorNonEmpty2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("june", "july",
                "august", "september");
        Map<String, String> mExpected = this.createFromArgsRef("june", "july",
                "august", "september");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when creating a non-empty constructor with many elements
    @Test
    public final void testConstructorNonEmpty3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("june", "july",
                "august", "september", "october", "november", "december",
                "january");
        Map<String, String> mExpected = this.createFromArgsRef("june", "july",
                "august", "september", "october", "november", "december",
                "january");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * test cases for add
     */

    //Test when adding an element to an empty map
    @Test
    public final void testAddToEmptyMap() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("june", "july");
        /*
         * Call method under test
         */
        m.add("june", "july");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when adding elements to an empty map more than once
    @Test
    public final void testAddToEmptyMap2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsRef("june", "july",
                "august", "september");
        /*
         * Call method under test
         */
        m.add("june", "july");
        m.add("august", "september");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when adding one element to a non-empty map
    @Test
    public final void testAddToNonEmptyMap() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("june", "july");
        Map<String, String> mExpected = this.createFromArgsRef("june", "july",
                "august", "september");
        /*
         * Call method under test
         */
        m.add("august", "september");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when adding elements to a non-empty map more than once
    @Test
    public final void testAddToNonEmptyMap2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("june", "july");
        Map<String, String> mExpected = this.createFromArgsRef("june", "july",
                "august", "september", "november", "december");
        /*
         * Call method under test
         */
        m.add("august", "september");
        m.add("november", "december");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * test cases for remove
     */

    //Test when removing one element till empty
    @Test
    public final void testRemoveUntilEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        m.remove("panda");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when removing more than one element till empty
    @Test
    public final void testRemoveUntilEmpty2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana");
        Map<String, String> mExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        m.remove("panda");
        m.remove("monkey");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when removing one element
    @Test
    public final void testRemoveNonEmpty1() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana");
        Map<String, String> mExpected = this.createFromArgsRef("monkey",
                "banana");
        /*
         * Call method under test
         */
        m.remove("panda");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    //Test when removing more than one element
    @Test
    public final void testRemoveNonEmpty2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana", "bunny", "carrot");
        Map<String, String> mExpected = this.createFromArgsRef("monkey",
                "banana");
        /*
         * Call method under test
         */
        m.remove("panda");
        m.remove("bunny");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
    }

    /*
     * test cases for removeAny
     */

    //Test when removing any element until empty for one element
    @Test
    public final void testRemoveAnyUntilEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo");
        /*
         * Call method under test
         */
        Pair<String, String> element = m.removeAny();
        boolean hasElement = mExpected.hasKey(element.key());
        Pair<String, String> sameElement = mExpected.remove(element.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasElement);
        assertEquals(sameElement, element);
    }

    //Test when removing any element until empty for more than one element
    @Test
    public final void testRemoveAnyUntilEmpty2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo", "monkey", "banana");
        /*
         * Call method under test
         */
        Pair<String, String> element = m.removeAny();
        Pair<String, String> element2 = m.removeAny();
        boolean hasElement = mExpected.hasKey(element.key());
        boolean hasElement2 = mExpected.hasKey(element.key());
        Pair<String, String> sameElement = mExpected.remove(element.key());
        Pair<String, String> sameElement2 = mExpected.remove(element2.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasElement);
        assertEquals(true, hasElement2);
        assertEquals(sameElement, element);
        assertEquals(sameElement2, element2);
    }

    //Test when removing any element for more than one element
    @Test
    public final void testRemoveAnyNonEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo", "monkey", "banana");
        /*
         * Call method under test
         */
        Pair<String, String> element = m.removeAny();
        boolean hasElement = mExpected.hasKey(element.key());
        Pair<String, String> sameElement = mExpected.remove(element.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasElement);
        assertEquals(sameElement, element);
    }

    //Test when removing any element for more than one element
    @Test
    public final void testRemoveAnyNonEmpty2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana", "bunny", "carrot");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo", "monkey", "banana", "bunny", "carrot");
        /*
         * Call method under test
         */
        Pair<String, String> element = m.removeAny();
        Pair<String, String> element2 = m.removeAny();
        boolean hasElement = mExpected.hasKey(element.key());
        boolean hasElement2 = mExpected.hasKey(element2.key());
        Pair<String, String> sameElement = mExpected.remove(element.key());
        Pair<String, String> sameElement2 = mExpected.remove(element2.key());
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, hasElement);
        assertEquals(true, hasElement2);
        assertEquals(sameElement, element);
        assertEquals(sameElement2, element2);
    }

    /*
     * test cases for value
     */

    //Test value when there is one element
    @Test
    public final void testValueOnePair() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo");
        Map<String, String> mExpected = this.createFromArgsTest("panda",
                "bamboo");
        /*
         * Call method under test
         */
        String val = m.value("panda");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals("bamboo", val);
    }

    //Test value when there is more than one element
    @Test
    public final void testValueMorePair() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "monkey", "banana");
        Map<String, String> mExpected = this.createFromArgsTest("panda",
                "bamboo", "monkey", "banana");
        /*
         * Call method under test
         */
        String val = m.value("panda");
        String val2 = m.value("monkey");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(m, mExpected);
        assertEquals("bamboo", val);
        assertEquals("banana", val2);
    }

    /*
     * test cases for hasKey
     */

    //Test HasKey when it's false for one element
    @Test
    public final void testHasKeyFalse() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo");
        /*
         * Call method under test
         */
        boolean actual = m.hasKey("tiger");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, actual);
    }

    //Test HasKey when it's false for more than one element
    @Test
    public final void testHasKeyFalse2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "lion", "gazelle");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo", "lion", "gazelle");
        /*
         * Call method under test
         */
        boolean actual = m.hasKey("tiger");
        boolean actual2 = m.hasKey("zebra");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(false, actual);
        assertEquals(false, actual2);
    }

    //Test HasKey when it's true for one element
    @Test
    public final void testHasKeyTrue() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo");
        /*
         * Call method under test
         */
        boolean actual = m.hasKey("panda");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, actual);
    }

    //Test HasKey when it's true for more than one element
    @Test
    public final void testHasKeyTrue2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "lion", "gazelle");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo", "lion", "gazelle");
        /*
         * Call method under test
         */
        boolean actual = m.hasKey("panda");
        boolean actual2 = m.hasKey("lion");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(mExpected, m);
        assertEquals(true, actual);
        assertEquals(true, actual2);
    }

    /*
     * test cases for size
     */

    //Test when map is empty
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest();
        Map<String, String> mExpected = this.createFromArgsTest();
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, size);
        assertEquals(mExpected, m);
    }

    //Test when map has one element
    @Test
    public final void testSizeNonEmpty2() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo");
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(1, size);
        assertEquals(mExpected, m);
    }

    //Test when map has more than one element
    @Test
    public final void testSizeNonEmpty3() {
        /*
         * Set up variables
         */
        Map<String, String> m = this.createFromArgsTest("panda", "bamboo",
                "lion", "gazelle", "elephant", "tiger");
        Map<String, String> mExpected = this.createFromArgsRef("panda",
                "bamboo", "lion", "gazelle", "elephant", "tiger");
        /*
         * Call method under test
         */
        int size = m.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, size);
        assertEquals(mExpected, m);
    }

}
