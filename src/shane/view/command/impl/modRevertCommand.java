package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.biz.IRevertBiz;
import shane.biz.impl.RevertBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;

public class modRevertCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String content = request.getParameter("content");
		int rid = Integer.parseInt(request.getParameter("rid"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		IRevertBiz revbiz = new RevertBiz();
		
		String url = "/pages/RevertFalse.jsp";
		boolean flag = revbiz.doUpdate(content, rid);
		
		if(flag){
			url = "/article.jsp?id=" + aid;
		}
	return url;
	}
}
