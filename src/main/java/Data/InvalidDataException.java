package Data;

/**
 * Can be thrown if a game file is not correct
 */
public class InvalidDataException extends Exception {

    public InvalidDataException() {
        super();
    }

    public InvalidDataException(String s) {
        super(s);
    }
}
