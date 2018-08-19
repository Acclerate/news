package cc.momas.news.exception;

public class BizException extends RuntimeException {

	private static final long serialVersionUID = 8228076443631941701L;

	public BizException(String msg) {
		super(msg);
	}

	public BizException(String message, Throwable cause) {
		super(message, cause);
	}

	public BizException(Throwable cause) {
		super(cause);
	}
}
