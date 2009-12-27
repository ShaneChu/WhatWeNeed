package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shane.biz.IUserBiz;
import shane.biz.impl.UserBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.vo.User;


public class LoginCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String account = request.getParameter("username");
		String password = request.getParameter("password");
		String url = "/pages/loginFalse.jsp";
			User user = new User();
			user.setAccount(account);
			user.setPassword(password);

			IUserBiz iuser = new UserBiz();
			String name = iuser.dologin(user);
			
			if(name != ""){
				if (name.equals("<locked>")) {
					url = "/pages/loginLocked.jsp";
				} else {
					HttpSession session = request.getSession();
					session.setAttribute("account", account);
					session.setAttribute("username", name); 
					url = "/pages/loginOK.jsp";
				}
			}
		return url;
	}
}
