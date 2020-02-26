package cn.tedu.store.service.ex;
/**
 * 插入数据异常，通常在活得持久层insert操作后返回值
 * @author Administrator
 *
 */
public class InsertDataException extends ServiceException {


	private static final long serialVersionUID = -1445454936015495198L;

	public InsertDataException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public InsertDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public InsertDataException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public InsertDataException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public InsertDataException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

}
