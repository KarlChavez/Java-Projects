import components.map.Map;
import components.map.Map.Pair;
import components.program.Program;
import components.program.Program1;
import components.queue.Queue;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.statement.Statement;
import components.utilities.Reporter;
import components.utilities.Tokenizer;

/**
 * Layered implementation of secondary method {@code parse} for {@code Program}.
 *
 * @author Karl Chavez and Hafsa Gureye
 *
 */
public final class Program1Parse1 extends Program1 {

    /*
     * Private members --------------------------------------------------------
     */

    /**
     * Parses a single BL instruction from {@code tokens} returning the
     * instruction name as the value of the function and the body of the
     * instruction in {@code body}.
     *
     * @param tokens
     *            the input tokens
     * @param body
     *            the instruction body
     * @return the instruction name
     * @replaces body
     * @updates tokens
     * @requires <pre>
     * [<"INSTRUCTION"> is a prefix of tokens]  and
     *  [<Tokenizer.END_OF_INPUT> is a suffix of tokens]
     * </pre>
     * @ensures <pre>
     * if [an instruction string is a proper prefix of #tokens]  and
     *    [the beginning name of this instruction equals its ending name]  and
     *    [the name of this instruction does not equal the name of a primitive
     *     instruction in the BL language] then
     *  parseInstruction = [name of instruction at start of #tokens]  and
     *  body = [Statement corresponding to the block string that is the body of
     *          the instruction string at start of #tokens]  and
     *  #tokens = [instruction string at start of #tokens] * tokens
     * else
     *  [report an appropriate error message to the console and terminate client]
     * </pre>
     */
    private static String parseInstruction(Queue<String> tokens,
            Statement body) {
        assert tokens != null : "Violation of: tokens is not null";
        assert body != null : "Violation of: body is not null";
        assert tokens.length() > 0 && tokens.front().equals("INSTRUCTION") : ""
                + "Violation of: <\"INSTRUCTION\"> is proper prefix of tokens";

        // TODO - fill in body

        //Check instruction
        String instruction = tokens.dequeue();
        Reporter.assertElseFatalError(instruction.equals("INSTRUCTION"),
                "ERROR: INSTRUCTION not located at: " + instruction);

        //A set of primitive instructions for easy verification
        Set<String> primitiveInstruction = new Set1L<String>();
        primitiveInstruction.add("turnleft");
        primitiveInstruction.add("turnright");
        primitiveInstruction.add("move");
        primitiveInstruction.add("infect");
        primitiveInstruction.add("skip");

        //Check if instruction name is primitive
        String primName = tokens.dequeue();
        if (primitiveInstruction.contains(primName)) {
            Reporter.assertElseFatalError(false,
                    "ERROR: Primitive instruction name at: " + primName);
        }

        //Check if name is an identifier
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(primName),
                "ERROR: Not an identifier at: " + primName);

        //Check IS
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "ERROR: Not an IS at: " + is);

        //Parse Block
        body.parseBlock(tokens);

        //Check END
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "ERROR: Not an END at: " + end);

        //Check similarity of identifier at the end
        String endIdentifier = tokens.dequeue();
        Reporter.assertElseFatalError(endIdentifier.equals(primName),
                "ERROR: Not same identifier at: " + endIdentifier);

        return primName;
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Program1Parse1() {
        super();
    }

    /*
     * Public methods ---------------------------------------------------------
     */

    @Override
    public void parse(SimpleReader in) {
        assert in != null : "Violation of: in is not null";
        assert in.isOpen() : "Violation of: in.is_open";
        Queue<String> tokens = Tokenizer.tokens(in);
        this.parse(tokens);
    }

    @Override
    public void parse(Queue<String> tokens) {
        assert tokens != null : "Violation of: tokens is not null";
        assert tokens.length() > 0 : ""
                + "Violation of: Tokenizer.END_OF_INPUT is a suffix of tokens";

        // TODO - fill in body

        //Check Program
        String program = tokens.dequeue();
        Reporter.assertElseFatalError(program.equals("PROGRAM"),
                "ERROR: Not a PROGRAM at: " + program);

        //Check Identifier
        String identifier = tokens.dequeue();
        Reporter.assertElseFatalError(Tokenizer.isIdentifier(identifier),
                "ERROR: Not an identifier at: " + identifier);

        //Set program name
        Program p = this.newInstance();
        p.setName(identifier);

        //Check IS
        String is = tokens.dequeue();
        Reporter.assertElseFatalError(is.equals("IS"),
                "ERROR: Not an IS at: " + is);

        //Parse {instruction}
        Map<String, Statement> ctxt = p.newContext();
        while (tokens.front().equals("INSTRUCTION")) {
            Statement pBody = p.newBody();
            String instr = parseInstruction(tokens, pBody);
            for (Pair<String, Statement> item : ctxt) {
                Reporter.assertElseFatalError(!item.key().equals(instr),
                        "ERROR: Duplicate instruction names at " + item);
            }
            ctxt.add(instr, pBody);
        }
        p.swapContext(ctxt);

        //Check BEGIN
        String begin = tokens.dequeue();
        Reporter.assertElseFatalError(begin.equals("BEGIN"),
                "ERROR: Not a BEGIN at: " + begin);

        //Parse block
        Statement bodyP = this.newBody();
        bodyP.parseBlock(tokens);
        p.swapBody(bodyP);

        //Check End
        String end = tokens.dequeue();
        Reporter.assertElseFatalError(end.equals("END"),
                "ERROR: Not an END at: " + end);

        //Check Identifier end for similarity
        String identifierEnd = tokens.dequeue();
        Reporter.assertElseFatalError(identifierEnd.equals(identifier),
                "ERROR: Not the same identifier at: " + identifierEnd);

        //Check EOI
        Reporter.assertElseFatalError(
                tokens.front().equals("### END OF INPUT ###"),
                "ERROR: Not EOI at: " + tokens.front());
        this.transferFrom(p);
    }

    /*
     * Main test method -------------------------------------------------------
     */

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        SimpleReader in = new SimpleReader1L();
        SimpleWriter out = new SimpleWriter1L();
        /*
         * Get input file name
         */
        out.print("Enter valid BL program file name: ");
        String fileName = in.nextLine();
        /*
         * Parse input file
         */
        out.println("*** Parsing input file ***");
        Program p = new Program1Parse1();
        SimpleReader file = new SimpleReader1L(fileName);
        Queue<String> tokens = Tokenizer.tokens(file);
        file.close();
        p.parse(tokens);
        /*
         * Pretty print the program
         */
        out.println("*** Pretty print of parsed program ***");
        p.prettyPrint(out);

        in.close();
        out.close();
    }

}
