package cn.tedu.store.service.ex;
/**
 * 用户名格式异常
 * @author Administrator
 *
 */
public class UsernameFormatException extends  TextFormatException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8661983413228444819L;

	public UsernameFormatException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameFormatException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameFormatException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameFormatException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
