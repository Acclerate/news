package cc.momas.news.exception;

import javax.servlet.ServletException;

/**
 * 核心异常,当核心代码出异常时抛出此异常,非核心请使用业务异常BizException
 * 
 * @author sothe
 *
 */
public class CoreException extends ServletException{

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
