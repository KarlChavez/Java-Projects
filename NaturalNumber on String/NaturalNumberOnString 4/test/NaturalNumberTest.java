import static org.junit.Assert.assertEquals;

import org.junit.Test;

import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * JUnit test fixture for {@code NaturalNumber}'s constructors and kernel
 * methods.
 *
 * @author Karl Chavez and Hafsa Gureye
 *
 */
public abstract class NaturalNumberTest {

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @return the new number
     * @ensures constructorTest = 0
     */
    protected abstract NaturalNumber constructorTest();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorTest = i
     */
    protected abstract NaturalNumber constructorTest(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorTest)
     */
    protected abstract NaturalNumber constructorTest(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * implementation under test and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorTest = n
     */
    protected abstract NaturalNumber constructorTest(NaturalNumber n);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @return the new number
     * @ensures constructorRef = 0
     */
    protected abstract NaturalNumber constructorRef();

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param i
     *            {@code int} to initialize from
     * @return the new number
     * @requires i >= 0
     * @ensures constructorRef = i
     */
    protected abstract NaturalNumber constructorRef(int i);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param s
     *            {@code String} to initialize from
     * @return the new number
     * @requires there exists n: NATURAL (s = TO_STRING(n))
     * @ensures s = TO_STRING(constructorRef)
     */
    protected abstract NaturalNumber constructorRef(String s);

    /**
     * Invokes the appropriate {@code NaturalNumber} constructor for the
     * reference implementation and returns the result.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     * @return the new number
     * @ensures constructorRef = n
     */
    protected abstract NaturalNumber constructorRef(NaturalNumber n);

    // TODO - add test cases for four constructors, multiplyBy10, divideBy10, isZero

    /**
     * Testing constructors
     */

    /*
     * Testing no arguments in a constructor
     */

    @Test
    public final void noArgumentConstructorTest() {

        //Creating the variables
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef();

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero integer argument in a constructor
     */

    @Test
    public final void intZeroConstructorTest() {

        //Creating the variables
        int num = 0;
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing non-zero integer argument in a constructor
     */

    @Test
    public final void intNonZeroConstructorTest() {

        //Creating the variables
        int num = 16;
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing maximum integer argument in a constructor
     */

    @Test
    public final void intMaxConstructorTest() {

        //Creating the variables
        int num = 2147483647;
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero string argument in a constructor
     */

    @Test
    public final void stringZeroConstructorTest() {

        //Creating the variables
        String num = "0";
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing non-zero integer argument in a constructor
     */

    @Test
    public final void stringNonZeroConstructorTest() {

        //Creating the variables
        String num = "16";
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing maximum string argument in a constructor
     */

    @Test
    public final void stringMaxConstructorTest() {

        //Creating the variables
        String num = "2147483647";
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero Natural Number argument in a constructor
     */

    @Test
    public final void nnZeroConstructorTest() {

        //Creating the variables
        NaturalNumber num = new NaturalNumber1L(0);
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing non-zero Natural Number argument in a constructor
     */

    @Test
    public final void nnNonZeroConstructorTest() {

        //Creating the variables
        NaturalNumber num = new NaturalNumber1L(16);
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing maximum Natural Number argument in a constructor
     */

    @Test
    public final void nnMaxConstructorTest() {

        //Creating the variables
        NaturalNumber num = new NaturalNumber1L(2147483647);
        NaturalNumber nn = this.constructorTest(num);
        NaturalNumber nnExpected = this.constructorRef(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /**
     * Testing kernel method MultiplyBy10
     */

    /*
     * Testing zero in method MultiplyBy10
     */

    @Test
    public final void zeroMultiplyBy10Test() {

        //Creating the variables
        int num = 0;
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero in method MultiplyBy10 when Natural Number has 0
     */

    @Test
    public final void zeroMultiplyBy10Test2() {

        //Creating the variables
        int num = 0;
        NaturalNumber nn = this.constructorTest(0);
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero in method MultiplyBy10 when Natural Number isn't empty and
     * non-zero
     */

    @Test
    public final void zeroMultiplyBy10Test3() {

        //Creating the variables
        int num = 0;
        NaturalNumber nn = this.constructorTest(6);
        NaturalNumber nnExpected = this.constructorRef(60);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing non-zero in method MultiplyBy10
     */

    @Test
    public final void nonzeroMultiplyBy10Test() {

        //Creating the variables
        int num = 6;
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef(6);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing non-zero in method MultiplyBy10 when Natural Number has 0
     */

    @Test
    public final void nonzeroMultiplyBy10Test2() {

        //Creating the variables
        int num = 6;
        NaturalNumber nn = this.constructorTest(0);
        NaturalNumber nnExpected = this.constructorRef(6);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing non-zero in method MultiplyBy10 when Natural Number isn't empty
     * and non-zero
     */

    @Test
    public final void nonzeroMultiplyBy10Test3() {

        //Creating the variables
        int num = 7;
        NaturalNumber nn = this.constructorTest(6);
        NaturalNumber nnExpected = this.constructorRef(67);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing maximum integer in method MultiplyBy10
     */

    @Test
    public final void maxMultiplyBy10Test() {

        //Creating the variables
        int num = 7;
        NaturalNumber nn = this.constructorTest(214748364);
        NaturalNumber nnExpected = this.constructorRef(2147483647);

        //Call method
        nn.multiplyBy10(num);

        //Comparing the variables
        assertEquals(nn, nnExpected);
    }

    /**
     * Testing kernel method DivideBy10
     */

    /*
     * Testing zero in method DivideBy10
     */

    @Test
    public final void zeroDivideBy10Test() {

        //Creating the variables
        int nnRemainderExpected = 0;
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero in method DivideBy10 when Natural Number has 0
     */

    @Test
    public final void zeroDivideBy10Test2() {

        //Creating the variables
        int nnRemainderExpected = 0;
        NaturalNumber nn = this.constructorTest(0);
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing zero in method DivideBy10 when Natural Number isn't empty or
     * non-zero
     */

    @Test
    public final void zeroDivideBy10Test3() {

        //Creating the variables
        int nnRemainderExpected = 0;
        NaturalNumber nn = this.constructorTest(60);
        NaturalNumber nnExpected = this.constructorRef(6);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing nonzero in method DivideBy10 (one's digit)
     */

    @Test
    public final void nonzeroDivideBy10Test() {

        //Creating the variables
        int nnRemainderExpected = 6;
        NaturalNumber nn = this.constructorTest(6);
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing nonzero in method DivideBy10 (ten's digit)
     */

    @Test
    public final void nonzeroDivideBy10Test2() {

        //Creating the variables
        int nnRemainderExpected = 7;
        NaturalNumber nn = this.constructorTest(67);
        NaturalNumber nnExpected = this.constructorRef(6);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing nonzero in method DivideBy10 (hundred's digit)
     */

    @Test
    public final void nonzeroDivideBy10Test3() {

        //Creating the variables
        int nnRemainderExpected = 9;
        NaturalNumber nn = this.constructorTest(679);
        NaturalNumber nnExpected = this.constructorRef(67);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing nonzero in method DivideBy10 with maximum digit
     */

    @Test
    public final void maxDivideBy10Test() {

        //Creating the variables
        int nnRemainderExpected = 7;
        NaturalNumber nn = this.constructorTest(2147483647);
        NaturalNumber nnExpected = this.constructorRef(214748364);

        //Call method
        int nnRemainder = nn.divideBy10();

        //Comparing the variables
        assertEquals(nnRemainder, nnRemainderExpected);
        assertEquals(nn, nnExpected);
    }

    /**
     * Testing kernel method isZero
     */

    /*
     * Testing isZero when the Natural Number is empty
     */

    @Test
    public final void isZeroDivideBy10Test() {

        //Creating the variables
        boolean nnBooleanExpected = true;
        NaturalNumber nn = this.constructorTest();
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        boolean nnBoolean = nn.isZero();

        //Comparing the variables
        assertEquals(nnBoolean, nnBooleanExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing isZero when the Natural Number has the number 0
     */

    @Test
    public final void isZeroDivideBy10Test2() {

        //Creating the variables
        boolean nnBooleanExpected = true;
        NaturalNumber nn = this.constructorTest(0);
        NaturalNumber nnExpected = this.constructorRef(0);

        //Call method
        boolean nnBoolean = nn.isZero();

        //Comparing the variables
        assertEquals(nnBoolean, nnBooleanExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing isZero when the Natural Number isn't 0 (one's digit)
     */

    @Test
    public final void isNotZeroDivideBy10Test() {

        //Creating the variables
        boolean nnBooleanExpected = false;
        NaturalNumber nn = this.constructorTest(6);
        NaturalNumber nnExpected = this.constructorRef(6);

        //Call method
        boolean nnBoolean = nn.isZero();

        //Comparing the variables
        assertEquals(nnBoolean, nnBooleanExpected);
        assertEquals(nn, nnExpected);
    }

    /*
     * Testing isZero when the Natural Number is maximum digit
     */

    @Test
    public final void maxIsNotZeroDivideBy10Test() {

        //Creating the variables
        boolean nnBooleanExpected = false;
        NaturalNumber nn = this.constructorTest(2147483647);
        NaturalNumber nnExpected = this.constructorRef(2147483647);

        //Call method
        boolean nnBoolean = nn.isZero();

        //Comparing the variables
        assertEquals(nnBoolean, nnBooleanExpected);
        assertEquals(nn, nnExpected);
    }

}
