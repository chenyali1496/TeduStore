package cn.tedu.store.service.ex;
/**
 * 文本格式异常
 * @author Administrator
 *@see UsernameFormatException
 *@see PasswordFormatException
 */
public class TextFormatException extends ServiceException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3027205226159738868L;

	public TextFormatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TextFormatException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public TextFormatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public TextFormatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public TextFormatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
