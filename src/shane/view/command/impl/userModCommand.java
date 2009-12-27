package shane.view.command.impl;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

import shane.biz.IUserBiz;
import shane.biz.impl.UserBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.vo.User;

public class userModCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}
	
	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String _gender = request.getParameter("gender");
		int age = Integer.parseInt(request.getParameter("age"));
		String email = request.getParameter("email");
		String question = request.getParameter("question");
		String answer = request.getParameter("answer");
		String image = request.getParameter("file");
		String _status = request.getParameter("status");
		int identify = Integer.parseInt(request.getParameter("identify"));
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		String fileName = sdf.format(date);
		
		User currentuser = new User();
		
		try {
			String path = request.getSession().getServletContext().getRealPath("/");
			path = path + "upload/";
			
			FileOutputStream ot = new FileOutputStream(path + fileName + ".jpg");
			FileInputStream in = new FileInputStream(image);
			JPEGImageDecoder jpgCodec = JPEGCodec.createJPEGDecoder(in);
			BufferedImage bufImage = jpgCodec.decodeAsBufferedImage();
			JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(ot);
			encoder.encode(bufImage);
			currentuser.setImage(fileName);
			in.close();
			ot.close();
			System.out.print("JSP上传图片成功！");
		} catch(Exception e) {
			System.out.print(e.toString());
		}
		
		boolean gender = true;
		if("0".equals(_gender)){
			gender=false;
		}
		boolean status = false;
		if("1".equals(_status)){
			status=true;
		}
		
		currentuser.setAccount(account);
		currentuser.setPassword(password);
		currentuser.setName(name);
		currentuser.setGender(gender);
		currentuser.setAge(age);
		currentuser.setEmail(email);
		currentuser.setQuestion(question);
		currentuser.setAnswer(answer);
		currentuser.setLocked(status);
		currentuser.setIdentify(identify);
		
		String url = "/index.jsp";

			IUserBiz imodify = new UserBiz();
			boolean flag = imodify.userMod(currentuser);
			
			if(flag){
				url="/adminUser.jsp";
			}
		return url;
	}	
	
}
