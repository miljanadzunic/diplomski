package rs.diplomski.sys.exception;

public class BaseException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BaseException(String message) {
		super(message);
	}
	
	public BaseException(String message, boolean enableSuppression, boolean writableStackTrace) {
		super(message, null, enableSuppression, writableStackTrace);
	}
	
}
