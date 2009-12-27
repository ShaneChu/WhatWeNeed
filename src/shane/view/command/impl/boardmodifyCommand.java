package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.biz.IBoardBiz;
import shane.biz.impl.BoardBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;

public class boardmodifyCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		int sid = Integer.parseInt(request.getParameter("sid"));
		String name = request.getParameter("boardName");
		String desc = request.getParameter("Desc");
		int status = Integer.parseInt(request.getParameter("status"));
		String url = "/pages/modifyFalse.jsp";

		IBoardBiz iboard = new BoardBiz();
		boolean flag = iboard.updateInfo(sid, name, desc, status);
		if(flag){ 
			url = "/adminBoard.jsp";
		}
		return url;
	}
	
}
