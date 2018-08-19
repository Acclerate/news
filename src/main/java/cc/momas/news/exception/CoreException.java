package cc.momas.news.exception;

public class CoreException extends Exception{

	private static final long serialVersionUID = 7237244079305390840L;

	public CoreException(String msg) {
		super(msg);
	}

	public CoreException(String message, Throwable cause) {
		super(message, cause);
	}

	public CoreException(Throwable cause) {
		super(cause);
	}
}
