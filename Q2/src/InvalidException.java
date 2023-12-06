/*
 * A class that represents an error specific to this project
 */

public class InvalidException extends Exception {

	private static final long serialVersionUID = 1L;

	public InvalidException() {
        super();
    }

    public InvalidException(String msg) {
        super(msg);
    }
    
    
}

