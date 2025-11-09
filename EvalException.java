// Author - https://github.com/JimBuffenbarger/dws
public class EvalException extends Exception {

	private int pos;
	private String msg;
	/**
	 * Constructs an EvalException, used to signal runtime errors 
	 * encountered during program evaluation (interpretation).
	 * * @param pos The position in the source program where the error occurred.
	 * @param msg A descriptive message of the error (e.g., "undefined variable").
	 */
	public EvalException(int pos, String msg) {
		this.pos=pos;
		this.msg=msg;
	}
	/**
	 * Returns a string representation of the exception for error reporting.
	 * * @return A string containing "eval error", position, and message.
	 */
	public String toString() {
		return "eval error"
			+", pos="+pos
			+", "+msg;
	}

}
