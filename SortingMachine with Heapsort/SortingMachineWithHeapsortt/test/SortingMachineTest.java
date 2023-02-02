import static org.junit.Assert.assertEquals;

import java.util.Comparator;

import org.junit.Test;

import components.sortingmachine.SortingMachine;

/**
 * JUnit test fixture for {@code SortingMachine<String>}'s constructor and
 * kernel methods.
 *
 * @author Karl Chavez and Hafsa Gureye
 *
 */
public abstract class SortingMachineTest {

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * implementation under test and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorTest = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorTest(
            Comparator<String> order);

    /**
     * Invokes the appropriate {@code SortingMachine} constructor for the
     * reference implementation and returns the result.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @return the new {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures constructorRef = (true, order, {})
     */
    protected abstract SortingMachine<String> constructorRef(
            Comparator<String> order);

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the
     * implementation under test type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsTest = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsTest(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorTest(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     *
     * Creates and returns a {@code SortingMachine<String>} of the reference
     * implementation type with the given entries and mode.
     *
     * @param order
     *            the {@code Comparator} defining the order for {@code String}
     * @param insertionMode
     *            flag indicating the machine mode
     * @param args
     *            the entries for the {@code SortingMachine}
     * @return the constructed {@code SortingMachine}
     * @requires IS_TOTAL_PREORDER([relation computed by order.compare method])
     * @ensures <pre>
     * createFromArgsRef = (insertionMode, order, [multiset of entries in args])
     * </pre>
     */
    private SortingMachine<String> createFromArgsRef(Comparator<String> order,
            boolean insertionMode, String... args) {
        SortingMachine<String> sm = this.constructorRef(order);
        for (int i = 0; i < args.length; i++) {
            sm.add(args[i]);
        }
        if (!insertionMode) {
            sm.changeToExtractionMode();
        }
        return sm;
    }

    /**
     * Comparator<String> implementation to be used in all test cases. Compare
     * {@code String}s in lexicographic order.
     */
    private static class StringLT implements Comparator<String> {

        @Override
        public int compare(String s1, String s2) {
            return s1.compareToIgnoreCase(s2);
        }

    }

    /**
     * Comparator instance to be used in all test cases.
     */
    private static final StringLT ORDER = new StringLT();

    /*
     * Sample test cases.
     */

    @Test
    public final void testConstructor() {
        SortingMachine<String> m = this.constructorTest(ORDER);
        SortingMachine<String> mExpected = this.constructorRef(ORDER);
        assertEquals(mExpected, m);
    }

    @Test
    public final void testAddEmpty() {
        SortingMachine<String> m = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> mExpected = this.createFromArgsRef(ORDER, true,
                "green");
        m.add("green");
        assertEquals(mExpected, m);
    }

    // TODO - add test cases for add, changeToExtractionMode, removeFirst,
    // isInInsertionMode, order, and size

    /*
     * Testing add
     */

    //Test when adding an element with one entry
    @Test
    public final void testAddOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true,
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "white");
        /*
         * Call method under test
         */
        s.add("white");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when adding an element with many entries
    @Test
    public final void testAddMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "white", "grey", "brown");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "white", "grey", "brown", "blue");
        /*
         * Call method under test
         */
        s.add("blue");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when adding an element with identical entries
    @Test
    public final void testAddIdentical() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true,
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "black");
        /*
         * Call method under test
         */
        s.add("black");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Testing changeToExtractionMode
     */

    //Test when changing extraction mode when empty
    @Test
    public final void testChangeToExtractionModeEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, extraction);
        assertEquals(sExpected, s);
    }

    //Test when changing extraction mode with one entry
    @Test
    public final void testChangeToExtractionModeOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true,
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black");
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, extraction);
        assertEquals(sExpected, s);
    }

    //Test when changing extraction mode with many entries
    @Test
    public final void testChangeToExtractionModeMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "white", "grey", "brown");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black", "white", "grey", "brown");
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, extraction);
        assertEquals(sExpected, s);
    }

    //Test when changing extraction mode with identical entries
    @Test
    public final void testChangeToExtractionModeIdentical() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black", "black");
        /*
         * Call method under test
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, extraction);
        assertEquals(sExpected, s);
    }

    /*
     * Testing removeFirst
     */

    //Test removingFirst with one entry
    @Test
    public final void testRemoveFirstOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true,
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Check preconditions
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        assertEquals(false, extraction);
        /*
         * Call method under test
         */
        String item = s.removeFirst();
        int changeSize = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("black", item);
        assertEquals(0, changeSize);
        assertEquals(sExpected, s);
    }

    //Test removingFirst with many entries
    @Test
    public final void testRemoveFirstMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "white", "grey", "brown");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "white", "grey", "brown");
        /*
         * Check preconditions
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        assertEquals(false, extraction);
        /*
         * Call method under test
         */
        String item = s.removeFirst();
        int changeSize = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("black", item);
        assertEquals(3, changeSize);
        assertEquals(sExpected, s);
    }

    //Test removingFirst with identical entries
    @Test
    public final void testRemoveFirstIdentical() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black");
        /*
         * Check preconditions
         */
        s.changeToExtractionMode();
        boolean extraction = s.isInInsertionMode();
        assertEquals(false, extraction);
        /*
         * Call method under test
         */
        String item = s.removeFirst();
        int changeSize = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals("black", item);
        assertEquals(1, changeSize);
        assertEquals(sExpected, s);
    }

    /*
     * Testing isInsertionMode
     */

    //Test isInsertionMode when empty
    @Test
    public final void testIsInsertionModeTrueEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode with one entry
    @Test
    public final void testIsInsertionModeTrueOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true,
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black");
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode with many entries
    @Test
    public final void testIsInsertionModeTrueMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "white", "grey");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "white", "grey");
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode with identical entries
    @Test
    public final void testIsInsertionModeTrueIdentical() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "black");
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(true, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode when empty and false
    @Test
    public final void testIsInsertionModeFalseEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode with one and false
    @Test
    public final void testIsInsertionModeFalseOne() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false,
                "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black");
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode with many entries and false
    @Test
    public final void testIsInsertionModeFalseMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false,
                "black", "white", "grey");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black", "white", "grey");
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, insertion);
        assertEquals(sExpected, s);
    }

    //Test isInsertionMode with identical entries and false
    @Test
    public final void testIsInsertionModeFalseIdentical() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false,
                "black", "black");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black", "black");
        /*
         * Call method under test
         */
        boolean insertion = s.isInInsertionMode();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(false, insertion);
        assertEquals(sExpected, s);
    }

    /*
     * Test order
     */

    //Test order when empty, insertionMode is true and order is true
    @Test
    public final void testOrderTrueEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, s.order());
        assertEquals(sExpected, s);
    }

    //Test order when non-empty, insertionMode is true and order is true
    @Test
    public final void testOrderTrueNonEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "white");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "white");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, s.order());
        assertEquals(sExpected, s);
    }

    //Test order when empty, insertionMode is false and order is true
    @Test
    public final void testOrderFalseEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, s.order());
        assertEquals(sExpected, s);
    }

    //Test order when non-empty, insertionMode is false and order is true
    @Test
    public final void testOrderFalseNonEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false,
                "black", "white");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black", "white");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(ORDER, s.order());
        assertEquals(sExpected, s);
    }

    /*
     * Test size
     */

    //Test size when empty and isInsertionMode true
    @Test
    public final void testSizeTrueEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true);
        /*
         * Call method under test
         */
        int size = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, size);
        assertEquals(sExpected, s);
    }

    //Test size when non-empty and isInsertionMode true
    @Test
    public final void testSizeTrueMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, true, "black",
                "white", "grey");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, true,
                "black", "white", "grey");
        /*
         * Call method under test
         */
        int size = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, size);
        assertEquals(sExpected, s);
    }

    //Test size when empty and isInsertionMode false
    @Test
    public final void testSizeFalseEmpty() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false);
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false);
        /*
         * Call method under test
         */
        int size = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(0, size);
        assertEquals(sExpected, s);
    }

    //Test size when non-empty and isInsertionMode false
    @Test
    public final void testSizeFalseMany() {
        /*
         * Set up variables
         */
        SortingMachine<String> s = this.createFromArgsTest(ORDER, false,
                "black", "white", "grey");
        SortingMachine<String> sExpected = this.createFromArgsRef(ORDER, false,
                "black", "white", "grey");
        /*
         * Call method under test
         */
        int size = s.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(3, size);
        assertEquals(sExpected, s);
    }

}
