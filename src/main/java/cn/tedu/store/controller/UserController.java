package cn.tedu.store.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import cn.tedu.store.entity.ResponseResult;
import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.service.ex.RequestArgumentException;
import cn.tedu.store.service.ex.UploadFileContentTypeException;
import cn.tedu.store.service.ex.UploadFileSizeLimitException;
import cn.tedu.store.service.ex.UploadIOException;
import cn.tedu.store.service.ex.UploadStateException;
import cn.tedu.store.util.TextValidator;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	@Autowired
	private IUserService userService;
	@RequestMapping(value="/handle_reg.do",
			method=RequestMethod.POST)
	//��ǰ�����ķ���ֵ�еķ��ͱ�ʾ��Ҫ���ͻ��˵Ľ���У����˲���״̬����ʾ��Ϣ���⣬����ʲô����
	@ResponseBody
	public ResponseResult<Void> handleReg(User user){
		//��֤���ݸ�ʽ����������ϣ���ֱ����Ӧ����ʾ������Ϣ

		if(!TextValidator.checkUsername(user.getUsername())) {
			return new ResponseResult<Void>(301,"�û�����ʽ����ȷ");
		}
		if(!TextValidator.checkPassword(user.getPassword())) {
			return new ResponseResult<Void>(302,"�����ʽ����ȷ");
		}
		//����ҵ������ʵ��ע��
		userService.reg(user);
		//ִ�з���new ResponseResult<Void>()
		return new ResponseResult<Void>();
	}

	@RequestMapping(value="/handle_login.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(
			@RequestParam("username")String username,
			@RequestParam("password")String password,HttpSession session){
		//��֤���ݸ�ʽ����������ϣ���ֱ����Ӧ����ʾ������Ϣ
		if(!TextValidator.checkUsername(username)) {
			return new ResponseResult<Void>(301,"�û�����ʽ����ȷ");
		}
		if(!TextValidator.checkPassword(password)) {
			return new ResponseResult<Void>(302,"�����ʽ����ȷ");
		}
		//����ҵ������ʵ�ֵ�¼
		User result=userService.login(username, password);
		session.setAttribute("id", result.getId());
		session.setAttribute("username", result.getUsername());
		//ִ�з���new ResponseResult<Void>()
		return new ResponseResult<Void>();
	}

	@RequestMapping(value="/change_password.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(
			@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session){
		//��֤���ݸ�ʽ����������ϣ���ֱ����Ӧ����ʾ������Ϣ
		if(!TextValidator.checkPassword(oldPassword)) {
			return new ResponseResult<Void>(302,"ԭʼ�����ʽ����ȷ");
		}
		if(!TextValidator.checkPassword(newPassword)) {
			return new ResponseResult<Void>(302,"�������ʽ����ȷ");
		}

		//��Session�л�ȡ��ǰ�û���id
		Integer id=Integer.valueOf(session.getAttribute("id").toString());
		//ͨ��ҵ���ִ���޸�����
		userService.changePassword(id, oldPassword, newPassword);


		return new ResponseResult<Void>();
	}

	@RequestMapping(value="/change_info.do",
			method=RequestMethod.GET)
	@ResponseBody
	public ResponseResult<Void> handleChangeInfo(
			User user,HttpSession session){
		//��ȡid
		//��Session�л�ȡ��ǰ�û���id
		Integer id=Integer.valueOf(session.getAttribute("id").toString());
		//ִ��
		user.setId(id);
		userService.changeInfo(user);
		//����
		return new ResponseResult<Void>();

	}

	@RequestMapping(value="/info.do")
	@ResponseBody
	public ResponseResult<User> getInfo(HttpSession session){
		//��ȡid
		//��Session�л�ȡ��ǰ�û���id
		Integer id=getUidFromSession(session);
		//��ѯ
		User user=	userService.findUserById(id);
		//��������ֵ����
		ResponseResult<User> rr=new ResponseResult<User>();
		//�Ѳ�ѯ�����װ������ֵ�����data������
		rr.setData(user);

		//����
		return rr;

	}

	public static final long MAX_UPLOAD_SIZE=1*1024*1024;
	//ͷ�����Ͱ�����
	public static final List<String> CONTENT_TYPE_WHITE_LIST=new ArrayList<String>();
	@PostConstruct
	public void init() {
		CONTENT_TYPE_WHITE_LIST.add("image/jpeg");
		CONTENT_TYPE_WHITE_LIST.add("image/png");
	}

	@RequestMapping(value="/upload.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<String> handleUpload(
			HttpServletRequest request,
			HttpSession session,
			@RequestParam("file") CommonsMultipartFile file){
		//����Ƿ��ϴ����ļ�
		if(file.isEmpty()) {
			throw new RequestArgumentException("û��ѡ���ϴ����ļ������ϴ����ļ�������Ϊ�գ�");
		}
		//����ϴ����ļ���С
		long fileSize=file.getSize();
		if(fileSize>MAX_UPLOAD_SIZE) {
			throw new UploadFileSizeLimitException("�ϴ����ļ���С�������ļ����ơ�����ֵΪ"+(MAX_UPLOAD_SIZE)/1024+"KByte");
		}
		//����ϴ����ļ�����
		String contentType=file.getContentType();
		if(!CONTENT_TYPE_WHITE_LIST.contains(contentType)) {
			throw new UploadFileContentTypeException("�ϴ����ļ����ʹ���������ļ����ͣ�"+CONTENT_TYPE_WHITE_LIST);
		}

		//ȷ�������ϴ��ļ����ļ�������
		String uploadDirName="upload";

		//��ȡid
		Integer id=getUidFromSession(session);

		//ȷ���ļ��ж���
		String parentDirPath=request.getServletContext().getRealPath(uploadDirName);
		File parentDir=new File(parentDirPath);
		if(!parentDir.exists()) {
			parentDir.mkdirs();
		}
		//ȷ���ļ���
		String originalFileName=file.getOriginalFilename();
		int beginIndex=originalFileName.lastIndexOf(".");
		String suffix=originalFileName.substring(beginIndex);
		String fileName=getFileName(id)+suffix;
		String avatar=uploadDirName+"/"+fileName;
		//����dest������File����
		File dest=new File(parentDir,fileName);

		//ִ�б���
		try{
			file.transferTo(dest);
		}catch(IllegalStateException e) {
			//e.printStackTrace();
			throw new UploadStateException("��ȡ�ļ��жϣ��ļ�·�������Ѿ������仯");
		}catch(IOException e) {
			//e.printStackTrace();
			throw new UploadIOException("��ȡ���ݳ����ļ������ѱ��ƶ���ɾ�����������жϣ�");
		}
		//�������ݱ�
		userService.changeAvatar(id, avatar);

		//����
		ResponseResult<String> rr=new ResponseResult<String>();
		rr.setData(avatar);
		return rr;
	}



	/**
	 * ��ȡ�ϴ��ļ����ļ������ļ�������������uuid-yyyyMMddHHmmss
	 * @param id
	 * @return
	 */
	private String getFileName(Integer id) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return id+"-"+sdf.format(date);
	}



}

