package cn.tedu.store.service.ex;
/**
 * ҵ���쳣���ǵ�ǰ��Ŀ�������쳣��Ļ��࣬ͨ������ֱ���׳������쳣
 * @author Administrator
 *
 */
public class ServiceException extends RuntimeException{

	private static final long serialVersionUID = 980104530291206274L;

	public ServiceException() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public ServiceException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
