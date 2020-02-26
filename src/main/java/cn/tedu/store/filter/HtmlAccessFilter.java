package cn.tedu.store.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/*
 * HTML���ʹ�����
 */
public class HtmlAccessFilter implements Filter {
	/**
	 * ������������ֱ�ӷ��ʵ�ҳ���б�
	 */
	List<String> whiteList=new ArrayList<String>();
	public void init(FilterConfig arg0) throws ServletException {
		//ȷ��������
		//**ע�⣺���htmlҳ��Ķ����͵ײ�����iframe���룬��Ҳ��Ҫ���뵽��������
		whiteList.add("register.html");
		whiteList.add("login.html");
		whiteList.add("index.html");
		whiteList.add("product_details.html");
	
		//���
		System.out.println("�����¼��ҳ���б�");
		for(String page:whiteList) {
			System.out.println(page);
		}
	}

	public void doFilter(
			ServletRequest arg0, ServletResponse arg1, FilterChain filterChain)
					throws IOException, ServletException {
		//��ȡ��ǰҳ��
		HttpServletRequest request=(HttpServletRequest) arg0;
		String uri=request.getRequestURI();
		//System.out.println("��ǰ����"+uri);
		int beginIndex=uri.lastIndexOf("/")+1;
		String fileName=uri.substring(beginIndex);
		System.out.println("��ǰ����ҳ�棺"+fileName);
		
		//�жϵ�ǰ���ʵ����Ǹ�ҳ��
		if(whiteList.contains(fileName)) {
			//����������¼��ҳ�棬ֱ�ӷ��У����磺login.html
			System.out.println("�����¼,ֱ�ӷ���");
			filterChain.doFilter(arg0, arg1);
			return;
		}
		//�������Ҫ��¼��ҳ�棬�ж�session,��������
		HttpSession session=request.getSession();
		if(session.getAttribute("id")!=null) {
			//Session����id����ʾ�Ѿ���¼��ֱ�ӷ���
			System.out.println("�Ѿ���¼,ֱ�ӷ���");
			//����ִ�й�����
			filterChain.doFilter(arg0, arg1);
			return;
		}
		//ִ�е��˴�����ʾ��ǰҳ�治�ڰ������У���δ��¼
		//���صı����ǣ��ض��򵽵�¼ҳ
		HttpServletResponse response=(HttpServletResponse) arg1;
		response.sendRedirect("login.html");
		//����ִ�й�������
		filterChain.doFilter(arg0, arg1);
	}
	public void destroy() {

	}


}
