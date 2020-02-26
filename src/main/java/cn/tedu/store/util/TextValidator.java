package cn.tedu.store.util;
/**
 * �ı���֤������
 * @author Administrator
 *
 */
public final class TextValidator {
	
	public TextValidator() {
		super();
	}
	/**
 * ��֤�û�����������ʽ
	 */
	private static final	String REGEX_USERNAME
	="[a-zA-Z]{1}[a-zA-Z0-9_]{3,15}";
	
	/**
	 * ��֤�ֻ��ŵ�������ʽ
	 */
	private static final	String REGEX_PHONE="[0-9_]{11}";
	
	/**
	 * ��֤�����������ʽ
	 */
	private static final	String REGEX_EMAIL="[@]{1}[a-zA-Z0-9]+[.]+[a-z]+";
	
	/**
	 * ��֤�û�����ʽ
	 * @param username �û���
	 * @return ����falseʱ��ʾ�����ϸ�ʽҪ�󣬷���true��ʾ����
	 */
	public static boolean checkUsername(String username) {
		if(username==null) {
			return false;
		}
		return username.matches(REGEX_USERNAME);
	}
	/**
	 * ��֤�����ʽ
	 * @param password
	 * @return ����falseʱ��ʾ�����ϸ�ʽҪ�󣬷���true��ʾ����
	 */
	public static boolean checkPassword(String password) {
		if(password==null) {
			return false;
		}
		return password.length()>=4&&password.length()<=16;
	}
	/**
	 * ����ֻ���ʽ
	 * @param phone
	 * @return
	 */
	public static boolean checkPhone(String phone) {
		if(phone==null) {
			return false;
		}
		return phone.matches(REGEX_PHONE);
	}
	/**
	 * ��������ʽ
	 * @param email
	 * @return
	 */
	public static boolean checkEmail(String email) {
		if(email==null) {
			return false;
		}
		return email.matches(REGEX_EMAIL);
	}
}
