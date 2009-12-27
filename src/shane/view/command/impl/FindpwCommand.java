package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shane.biz.IUserBiz;
import shane.biz.impl.UserBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.vo.User;


public class FindpwCommand implements ICommand {
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String account = request.getParameter("account");
		String url = "/pages/findFalse.jsp";

		IUserBiz imodify = new UserBiz();
		User user = imodify.dofind(account);
		
		if(user != null){
			
			HttpSession session = request.getSession();
			session.setAttribute("findUser", user); 
			url="/pages/answer.jsp";
			
		}
		return url;
	}
}
