import components.naturalnumber.NaturalNumber;
import components.naturalnumber.NaturalNumber1L;

/**
 * Customized JUnit test fixture for {@code NaturalNumber3}.
 */
public class NaturalNumber3Test extends NaturalNumberTest {

    @Override
    protected final NaturalNumber constructorTest() {

        // TODO - fill in body

        return new NaturalNumber3(); //Our own implementation
    }

    @Override
    protected final NaturalNumber constructorTest(int i) {

        // TODO - fill in body

        return new NaturalNumber3(i);
    }

    @Override
    protected final NaturalNumber constructorTest(String s) {

        // TODO - fill in body

        return new NaturalNumber3(s);
    }

    @Override
    protected final NaturalNumber constructorTest(NaturalNumber n) {

        // TODO - fill in body

        return new NaturalNumber3(n);
    }

    @Override
    protected final NaturalNumber constructorRef() {

        // TODO - fill in body

        return new NaturalNumber1L(); //The reference that we'll be using
    }

    @Override
    protected final NaturalNumber constructorRef(int i) {

        // TODO - fill in body

        return new NaturalNumber1L(i);
    }

    @Override
    protected final NaturalNumber constructorRef(String s) {

        // TODO - fill in body

        return new NaturalNumber1L(s);
    }

    @Override
    protected final NaturalNumber constructorRef(NaturalNumber n) {

        // TODO - fill in body

        return new NaturalNumber1L(n);
    }

}
