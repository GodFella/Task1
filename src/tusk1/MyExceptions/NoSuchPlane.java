package tusk1.MyExceptions;

/**
 *
 * @author serg
 */
public class NoSuchPlane extends RuntimeException {

    private String msg;

    public NoSuchPlane() {
        msg = "There is no plane you ask to find";
    }

    @Override
    public String toString() {
        return msg;
    }
}
