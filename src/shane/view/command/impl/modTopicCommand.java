package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.biz.IArticleBiz;
import shane.biz.IBoardBiz;
import shane.biz.impl.ArticleBiz;
import shane.biz.impl.BoardBiz;
import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;

public class modTopicCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String headline = request.getParameter("headline");
		String content = request.getParameter("content");
		String boardName = request.getParameter("boardName");
		int isTop = Integer.parseInt(request.getParameter("isTop"));
		int isOpen = Integer.parseInt(request.getParameter("isOpen"));
		int aid = Integer.parseInt(request.getParameter("aid"));
		
		IBoardBiz boardbiz = new BoardBiz();
		int sid = boardbiz.getIDByName(boardName);
		
		String url = "/pages/ArticleFalse.jsp";
		IArticleBiz iart = new ArticleBiz();
		boolean flag = iart.doUpdate(aid, headline, content, isTop, isOpen, sid);
		
		if(flag){
			boardbiz.updateArtNum(sid);
			url = "/article.jsp?id=" + aid;
		}
	return url;
	}
}
