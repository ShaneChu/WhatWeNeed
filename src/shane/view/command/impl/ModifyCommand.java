package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shane.biz.IUserBiz;
import shane.biz.impl.UserBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.vo.User;


public class ModifyCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String account = request.getParameter("account");
		String url = "/index.jsp";

			IUserBiz imodify = new UserBiz();
			User user = imodify.domodify(account);
			
			if(user != null){
				
				HttpSession session = request.getSession();
				session.setAttribute("currentUser", user); 
				url="/info.jsp";
				
			}
		return url;
	}
}
