package cn.tedu.store.service.ex;
/**
 * 用户名冲突异常，在尝试注册已存在的用户名时抛出
 * @author Administrator
 *
 */
public class UsernameConflictException extends ServiceException{

	private static final long serialVersionUID = -4106401894279090959L;

	public UsernameConflictException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UsernameConflictException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}


	
}
