import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.set.Set;

/**
 * JUnit test fixture for {@code Set<String>}'s constructor and kernel methods.
 *
 * @author Karl Chavez and Hafsa Gureye
 *
 */
public abstract class SetTest {

    /**
     * Invokes the appropriate {@code Set} constructor for the implementation
     * under test and returns the result.
     *
     * @return the new set
     * @ensures constructorTest = {}
     */
    protected abstract Set<String> constructorTest();

    /**
     * Invokes the appropriate {@code Set} constructor for the reference
     * implementation and returns the result.
     *
     * @return the new set
     * @ensures constructorRef = {}
     */
    protected abstract Set<String> constructorRef();

    /**
     * Creates and returns a {@code Set<String>} of the implementation under
     * test type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsTest = [entries in args]
     */
    private Set<String> createFromArgsTest(String... args) {
        Set<String> set = this.constructorTest();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    /**
     * Creates and returns a {@code Set<String>} of the reference implementation
     * type with the given entries.
     *
     * @param args
     *            the entries for the set
     * @return the constructed set
     * @requires [every entry in args is unique]
     * @ensures createFromArgsRef = [entries in args]
     */
    private Set<String> createFromArgsRef(String... args) {
        Set<String> set = this.constructorRef();
        for (String s : args) {
            assert !set.contains(
                    s) : "Violation of: every entry in args is unique";
            set.add(s);
        }
        return set;
    }

    // TODO - add test cases for constructor, add, remove, removeAny, contains, and size

    /*
     * Test cases for constructor
     */

    //Testing constructor when it's empty
    @Test
    public final void testConstructorEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Testing constructor when it's non-empty and has one element
    @Test
    public final void testConstructorNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Testing constructor when it's non-empty and has more than one element
    @Test
    public final void testConstructorNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle", "Square");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle",
                "Square");
        /*
         * Call method under test
         */
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for add
     */

    //Test when adding one item to an empty set
    @Test
    public final void testAddEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        s.add("Star");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when adding more than one item to an empty set
    @Test
    public final void testAddEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle",
                "Square");
        /*
         * Call method under test
         */
        s.add("Star");
        s.add("Circle");
        s.add("Square");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when adding one item to a non-empty set
    @Test
    public final void testAddNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle");
        /*
         * Call method under test
         */
        s.add("Circle");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when adding more than one item to a non-empty set
    @Test
    public final void testAddNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle",
                "Square");
        /*
         * Call method under test
         */
        s.add("Circle");
        s.add("Square");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for remove
     */

    //Test when removing one item till empty
    @Test
    public final void testRemoveUntilEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        s.remove("Star");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when removing more than one item till empty
    @Test
    public final void testRemoveUntilEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle", "Square");
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        s.remove("Star");
        s.remove("Circle");
        s.remove("Square");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when removing one item
    @Test
    public final void testRemoveNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        s.remove("Circle");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    //Test when removing more than one item
    @Test
    public final void testRemoveNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle", "Square");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        s.remove("Circle");
        s.remove("Square");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
    }

    /*
     * Test cases for removeAny
     */

    //Test when removing any item until empty for one item
    @Test
    public final void testRemoveAnyUntilEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        String item = s.removeAny();
        boolean contain = sExpected.contains(item);
        String sameItem = sExpected.remove(item);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
        assertEquals(item, sameItem);
    }

    //Test when removing any item until empty for more than one item
    @Test
    public final void testRemoveAnyUntilEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle");
        /*
         * Call method under test
         */
        String item = s.removeAny();
        boolean contain = sExpected.contains(item);
        String sameItem = sExpected.remove(item);
        String item2 = s.removeAny();
        boolean contain2 = sExpected.contains(item2);
        String sameItem2 = sExpected.remove(item2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
        assertEquals(item, sameItem);
        assertEquals(true, contain2);
        assertEquals(item2, sameItem2);
    }

    //Test when removing any item for one item
    @Test
    public final void testRemoveAnyNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle");
        /*
         * Call method under test
         */
        String item = s.removeAny();
        boolean contain = sExpected.contains(item);
        String sameItem = sExpected.remove(item);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
        assertEquals(item, sameItem);
    }

    //Test when removing any item for more than one item
    @Test
    public final void testRemoveAnyNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle", "Square");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle",
                "Square");
        /*
         * Call method under test
         */
        String item = s.removeAny();
        boolean contain = sExpected.contains(item);
        String sameItem = sExpected.remove(item);
        String item2 = s.removeAny();
        boolean contain2 = sExpected.contains(item2);
        String sameItem2 = sExpected.remove(item2);
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
        assertEquals(item, sameItem);
        assertEquals(true, contain2);
        assertEquals(item2, sameItem2);
    }

    /*
     * Test cases for contains
     */

    //Test contain when it's false for one item
    @Test
    public final void testContainsFalse() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        boolean contain = s.contains("Diamond");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, contain);
    }

    //Test contain when it's false for more than one item
    @Test
    public final void testContainsFalse2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle");
        /*
         * Call method under test
         */
        boolean contain = s.contains("Diamond");
        boolean contain2 = s.contains("Triangle");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(false, contain);
        assertEquals(false, contain2);
    }

    //Test contain when it's true for one item
    @Test
    public final void testContainsTrue() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        boolean contain = s.contains("Star");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
    }

    //Test contain when it's true for more than one item
    @Test
    public final void testContainsTrue2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle");
        /*
         * Call method under test
         */
        boolean contain = s.contains("Star");
        boolean contain2 = s.contains("Circle");
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(true, contain);
        assertEquals(true, contain2);
    }

    /*
     * Test cases for size
     */

    //Test size when set is empty
    @Test
    public final void testSizeEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest();
        Set<String> sExpected = this.createFromArgsRef();
        /*
         * Call method under test
         */
        int sizeOfSet = s.size();
        int trueSizeOfSet = sExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sizeOfSet, trueSizeOfSet);
        assertEquals(sizeOfSet, 0);
    }

    ///Test size when set is non-empty for one item
    @Test
    public final void testSizeNonEmpty() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star");
        Set<String> sExpected = this.createFromArgsRef("Star");
        /*
         * Call method under test
         */
        int sizeOfSet = s.size();
        int trueSizeOfSet = sExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sizeOfSet, trueSizeOfSet);
        assertEquals(sizeOfSet, 1);
    }

    ///Test size when set is non-empty for more than one item
    @Test
    public final void testSizeNonEmpty2() {
        /*
         * Set up variables
         */
        Set<String> s = this.createFromArgsTest("Star", "Circle", "Square");
        Set<String> sExpected = this.createFromArgsRef("Star", "Circle",
                "Square");
        /*
         * Call method under test
         */
        int sizeOfSet = s.size();
        int trueSizeOfSet = sExpected.size();
        /*
         * Assert that values of variables match expectations
         */
        assertEquals(sExpected, s);
        assertEquals(sizeOfSet, trueSizeOfSet);
        assertEquals(sizeOfSet, 3);
    }
}
