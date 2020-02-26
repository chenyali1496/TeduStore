package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.service.ex.AddressAccessException;
import cn.tedu.store.service.ex.AddressNotFoundException;
import cn.tedu.store.service.ex.CartNotFoundException;
import cn.tedu.store.service.ex.DeleteDataException;
import cn.tedu.store.service.ex.InsertDataException;
import cn.tedu.store.service.ex.PasswordFormatException;
import cn.tedu.store.service.ex.PasswordNotMatchException;
import cn.tedu.store.service.ex.ProductNumLimitException;
import cn.tedu.store.service.ex.RequestArgumentException;
import cn.tedu.store.service.ex.ServiceException;
import cn.tedu.store.service.ex.UpdateDataException;
import cn.tedu.store.service.ex.UploadFileContentTypeException;
import cn.tedu.store.service.ex.UploadFileSizeLimitException;
import cn.tedu.store.service.ex.UploadIOException;
import cn.tedu.store.service.ex.UploadStateException;
import cn.tedu.store.service.ex.UserNotFoundException;
import cn.tedu.store.service.ex.UsernameConflictException;
import cn.tedu.store.service.ex.UsernameFormatException;

/**
 * ��������Ļ���
 * @author Administrator
 *
 */
public abstract class BaseController {
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void>handleException(Exception e){
		//�ж��쳣���Ͳ�����
		if(e instanceof RequestArgumentException) {
			//��������쳣
			return new ResponseResult<Void>(300,e);
		}else if(e instanceof UsernameFormatException) {
			//�û�����ʽ����
			return new ResponseResult<Void>(301,e);
		}else if(e instanceof  PasswordFormatException) {
			//�����ʽ����
			return new ResponseResult<Void>(302,e);
		}else if(e instanceof UploadFileSizeLimitException) {
			//�ϴ��ļ���С��������
			return new ResponseResult<Void>(303,e);
		}else if(e instanceof UploadFileContentTypeException) {
			//�ϴ��ļ������쳣
			return new ResponseResult<Void>(304,e);
		}else if(e instanceof UploadStateException) {
			//�ϴ��ļ�״̬�쳣
			return new ResponseResult<Void>(305,e);
		}else if(e instanceof UploadIOException) {
			//�ϴ��ļ���д�쳣
			return new ResponseResult<Void>(306,e);
		}else if(e instanceof CartNotFoundException) {
			//���Է��ʹ��ﳵ�����쳣
			return new ResponseResult<Void>(307,e);
		}else if(e instanceof ProductNumLimitException) {
			//�޸Ĺ��ﳵ�����쳣
			return new ResponseResult<Void>(308,e);
		}else if(e instanceof UsernameConflictException) {
			//�û�����ͻ�쳣
			return new ResponseResult<Void>(401,e);
		}else if(e instanceof UserNotFoundException) {
			//�û��񲻴���
			return new ResponseResult<Void>(402,e);
		}else if(e instanceof PasswordNotMatchException) {
			//�������
			return new ResponseResult<Void>(403,e);
		}else if(e instanceof AddressNotFoundException) {
			//�ջ���ַ���ݲ�����
			return new ResponseResult<Void>(404,e);
		}else if(e instanceof AddressAccessException) {
			//�ջ���ַ���ݷ����쳣�������ǷǷ�����
			return new ResponseResult<Void>(405,e);
		}else if(e instanceof InsertDataException) {
			//���������쳣
			return new ResponseResult<Void>(501,e);
		}else if(e instanceof UpdateDataException) {
			//���������쳣
			return new ResponseResult<Void>(502,e);
		}else if(e instanceof DeleteDataException) {
			//ɾ�������쳣
			return new ResponseResult<Void>(503,e);
		}
		return null;
	}
	/**
	 * ��session�л�ȡ��ǰ��¼���û�id
	 * @param session
	 * @return ��ǰ��¼���û�id
	 */
	protected Integer getUidFromSession(HttpSession session) {
		return 	Integer.valueOf(session.getAttribute("id").toString());
	}



}
