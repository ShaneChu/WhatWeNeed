package shane.view.command.impl;

import java.util.Date;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.awt.image.BufferedImage;
import com.sun.image.codec.jpeg.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shane.biz.IUserBiz;
import shane.biz.impl.UserBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.vo.User;


public class RegisterCommand implements ICommand {

	public String execute(HttpServletRequest request,HttpServletResponse response) {
		return null;
	}
	
	public String execute(IRequestHelper helper) {
			
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String url =  new String();
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String _gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String image = request.getParameter("file");
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = sdf.format(date);
		
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "upload/";
			
			FileOutputStream ot = new FileOutputStream(path + fileName + ".jpg");
			FileInputStream in = new FileInputStream(image);
			JPEGImageDecoder jpgCodec = JPEGCodec.createJPEGDecoder(in);
			BufferedImage bufImage = jpgCodec.decodeAsBufferedImage();
			JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(ot);
			encoder.encode(bufImage);
			in.close();
			ot.close();
			System.out.print("JSP上传图片成功！<BR>");
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		
		boolean gender = true;
		if("0".equals(_gender)){
			gender=false;
		}
		
		User user = new User();
		user.setAccount(account);
		user.setPassword(password);
		user.setName(name);
		user.setGender(gender);
		user.setAge(age);
		user.setEmail(email);
		user.setQuestion(question);
		user.setAnswer(answer);
		user.setImage(fileName);
		
		IUserBiz userbiz = new UserBiz();
		boolean flag = userbiz.doreg(user);
				
		if(flag==true){
			
			HttpSession session = request.getSession();
			session.setAttribute("username", user.getName());
			session.setAttribute("account", user.getAccount());
			url="/pages/regOK.jsp";
			
		}
		else {
			url="/pages/regFalse.jsp";
		}	
		return url;
	}

}
