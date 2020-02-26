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
	//当前方法的返回值中的泛型表示需要给客户端的结果中，除了操作状态和提示信息意外，还给什么数据
	@ResponseBody
	public ResponseResult<Void> handleReg(User user){
		//验证数据格式，如果不符合，则直接响应，提示错误信息

		if(!TextValidator.checkUsername(user.getUsername())) {
			return new ResponseResult<Void>(301,"用户名格式不正确");
		}
		if(!TextValidator.checkPassword(user.getPassword())) {
			return new ResponseResult<Void>(302,"密码格式不正确");
		}
		//调用业务层对象实现注册
		userService.reg(user);
		//执行返回new ResponseResult<Void>()
		return new ResponseResult<Void>();
	}

	@RequestMapping(value="/handle_login.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleLogin(
			@RequestParam("username")String username,
			@RequestParam("password")String password,HttpSession session){
		//验证数据格式，如果不符合，则直接响应，提示错误信息
		if(!TextValidator.checkUsername(username)) {
			return new ResponseResult<Void>(301,"用户名格式不正确");
		}
		if(!TextValidator.checkPassword(password)) {
			return new ResponseResult<Void>(302,"密码格式不正确");
		}
		//调用业务层对象实现登录
		User result=userService.login(username, password);
		session.setAttribute("id", result.getId());
		session.setAttribute("username", result.getUsername());
		//执行返回new ResponseResult<Void>()
		return new ResponseResult<Void>();
	}

	@RequestMapping(value="/change_password.do",
			method=RequestMethod.POST)
	@ResponseBody
	public ResponseResult<Void> handleChangePassword(
			@RequestParam("old_password") String oldPassword,
			@RequestParam("new_password") String newPassword,
			HttpSession session){
		//验证数据格式，如果不符合，则直接响应，提示错误信息
		if(!TextValidator.checkPassword(oldPassword)) {
			return new ResponseResult<Void>(302,"原始密码格式不正确");
		}
		if(!TextValidator.checkPassword(newPassword)) {
			return new ResponseResult<Void>(302,"新密码格式不正确");
		}

		//从Session中获取当前用户的id
		Integer id=Integer.valueOf(session.getAttribute("id").toString());
		//通过业务层执行修改密码
		userService.changePassword(id, oldPassword, newPassword);


		return new ResponseResult<Void>();
	}

	@RequestMapping(value="/change_info.do",
			method=RequestMethod.GET)
	@ResponseBody
	public ResponseResult<Void> handleChangeInfo(
			User user,HttpSession session){
		//获取id
		//从Session中获取当前用户的id
		Integer id=Integer.valueOf(session.getAttribute("id").toString());
		//执行
		user.setId(id);
		userService.changeInfo(user);
		//返回
		return new ResponseResult<Void>();

	}

	@RequestMapping(value="/info.do")
	@ResponseBody
	public ResponseResult<User> getInfo(HttpSession session){
		//获取id
		//从Session中获取当前用户的id
		Integer id=getUidFromSession(session);
		//查询
		User user=	userService.findUserById(id);
		//创建返回值对象
		ResponseResult<User> rr=new ResponseResult<User>();
		//把查询结果封装到返回值对象的data属性中
		rr.setData(user);

		//返回
		return rr;

	}

	public static final long MAX_UPLOAD_SIZE=1*1024*1024;
	//头像类型白名单
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
		//检查是否上传了文件
		if(file.isEmpty()) {
			throw new RequestArgumentException("没有选择上传的文件，或上传的文件的内容为空！");
		}
		//检查上传的文件大小
		long fileSize=file.getSize();
		if(fileSize>MAX_UPLOAD_SIZE) {
			throw new UploadFileSizeLimitException("上传的文件大小超出了文件限制。限制值为"+(MAX_UPLOAD_SIZE)/1024+"KByte");
		}
		//检查上传的文件类型
		String contentType=file.getContentType();
		if(!CONTENT_TYPE_WHITE_LIST.contains(contentType)) {
			throw new UploadFileContentTypeException("上传的文件类型错误！允许的文件类型："+CONTENT_TYPE_WHITE_LIST);
		}

		//确定保存上传文件的文件夹名称
		String uploadDirName="upload";

		//获取id
		Integer id=getUidFromSession(session);

		//确定文件夹对象
		String parentDirPath=request.getServletContext().getRealPath(uploadDirName);
		File parentDir=new File(parentDirPath);
		if(!parentDir.exists()) {
			parentDir.mkdirs();
		}
		//确定文件名
		String originalFileName=file.getOriginalFilename();
		int beginIndex=originalFileName.lastIndexOf(".");
		String suffix=originalFileName.substring(beginIndex);
		String fileName=getFileName(id)+suffix;
		String avatar=uploadDirName+"/"+fileName;
		//创建dest对象，是File类型
		File dest=new File(parentDir,fileName);

		//执行保存
		try{
			file.transferTo(dest);
		}catch(IllegalStateException e) {
			//e.printStackTrace();
			throw new UploadStateException("读取文件中断，文件路径可能已经发生变化");
		}catch(IOException e) {
			//e.printStackTrace();
			throw new UploadIOException("读取数据出错，文件可能已被移动、删除，或网络中断！");
		}
		//更新数据表
		userService.changeAvatar(id, avatar);

		//返回
		ResponseResult<String> rr=new ResponseResult<String>();
		rr.setData(avatar);
		return rr;
	}



	/**
	 * 获取上传文件的文件名，文件名的命名规则：uuid-yyyyMMddHHmmss
	 * @param id
	 * @return
	 */
	private String getFileName(Integer id) {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		return id+"-"+sdf.format(date);
	}



}

