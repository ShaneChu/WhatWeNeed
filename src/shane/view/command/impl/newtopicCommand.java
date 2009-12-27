package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.biz.IArticleBiz;
import shane.biz.IBoardBiz;
import shane.biz.impl.ArticleBiz;
import shane.biz.impl.BoardBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;

public class newtopicCommand implements ICommand {
	
	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String headline = request.getParameter("headline");
		String content = request.getParameter("content");
		int uid = Integer.parseInt(request.getParameter("uid"));
		String boardName = request.getParameter("boardName");
		IBoardBiz boardbiz = new BoardBiz();
		int sid = boardbiz.getIDByName(boardName);
		
		String url = "/pages/ArticleFalse.jsp";
		IArticleBiz iart = new ArticleBiz();
		boolean flag = iart.donew(headline, content, uid, sid);
		
		if(flag){
			boardbiz.updateArtNum(sid);
			url = "/topic.jsp?id=" + sid;
		}
	return url;
	}
}
