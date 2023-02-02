import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumberSecondary;

/**
 * {@code NaturalNumber} represented as a {@code String} with implementations of
 * primary methods.
 *
 * @convention <pre>
 * [all characters of $this.rep are '0' through '9']  and
 * [$this.rep does not start with '0']
 * </pre>
 * @correspondence <pre>
 * this = [if $this.rep = "" then 0
 *         else the decimal number whose ordinary depiction is $this.rep]
 * </pre>
 *
 * @author Karl Chavez and Hafsa Gureye
 *
 */
public class NaturalNumber3 extends NaturalNumberSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Representation of {@code this}.
     */
    private String rep;

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {

        // TODO - fill in body

        this.rep = ""; //Starts with an empty string

    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public NaturalNumber3() {

        // TODO - fill in body

        this.createNewRep(); //Calls createNewRep for an empty string

    }

    /**
     * Constructor from {@code int}.
     *
     * @param i
     *            {@code int} to initialize from
     */
    public NaturalNumber3(int i) {
        assert i >= 0 : "Violation of: i >= 0";

        // TODO - fill in body

        // we'll call createNewRep for empty string
        this.createNewRep();

        //if int is not zero, we'll convert it into string and set to rep
        if (i != 0) {
            this.rep = Integer.toString(i);
        }

    }

    /**
     * Constructor from {@code String}.
     *
     * @param s
     *            {@code String} to initialize from
     */
    public NaturalNumber3(String s) {
        assert s != null : "Violation of: s is not null";
        assert s.matches("0|[1-9]\\d*") : ""
                + "Violation of: there exists n: NATURAL (s = TO_STRING(n))";

        // TODO - fill in body

        //we'll call createNewRep for empty string
        this.createNewRep();

        //if rep is not zero, will be equal to the given string
        if (!s.equals("0")) {
            this.rep = s;
        }

    }

    /**
     * Constructor from {@code NaturalNumber}.
     *
     * @param n
     *            {@code NaturalNumber} to initialize from
     */
    public NaturalNumber3(NaturalNumber n) {
        assert n != null : "Violation of: n is not null";

        // TODO - fill in body

        //we'll call createNewRep for empty string
        this.createNewRep();

        //if NN is not zero, will be converted into string and set to rep
        if (!n.isZero()) {
            this.rep = n.toString();
        }

    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final NaturalNumber newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(NaturalNumber source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof NaturalNumber3 : ""
                + "Violation of: source is of dynamic type NaturalNumberExample";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        NaturalNumber3 localSource = (NaturalNumber3) source;
        this.rep = localSource.rep;
        localSource.createNewRep();
    }

    /*
     * Kernel methods ---------------------------------------------------------
     */

    @Override
    public final void multiplyBy10(int k) {
        assert 0 <= k : "Violation of: 0 <= k";
        assert k < RADIX : "Violation of: k < 10";

        // TODO - fill in body

        // make sure both rep and int added are nonzero
        if (this.rep.length() > 0 || k > 0) {

            //converting given integer to string and adding it at the end of the string
            this.rep += Integer.toString(k);
        }

    }

    @Override
    public final int divideBy10() {

        // TODO - fill in body

        int returnNum = 0; //The number that is returned.

        /*
         * It's initialized to zero because if the if statement fails, we return
         * a zero. No need for a 0 case
         */
        if (!(this.rep.length() == 0)) //If it isn't an empty string
        {
            //Cuts the last digit off and converts string to integer
            returnNum = Integer
                    .parseInt(this.rep.substring(this.rep.length() - 1));

            //Cuts the last digit off from the string
            this.rep = this.rep.substring(0, this.rep.length() - 1);
        }
        return returnNum;
    }

    @Override
    public final boolean isZero() {

        // TODO - fill in body

        //Returns a boolean value whether the length of the string is 0 or not
        return this.rep.length() == 0;
    }

}
