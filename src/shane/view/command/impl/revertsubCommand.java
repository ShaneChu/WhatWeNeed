package shane.view.command.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import shane.view.command.ICommand;
import shane.view.helper.IRequestHelper;
import shane.biz.IRevertBiz;
import shane.biz.IArticleBiz;
import shane.biz.impl.RevertBiz;
import shane.biz.impl.ArticleBiz;

public class revertsubCommand implements ICommand {

	public String execute(HttpServletRequest request,
			HttpServletResponse response) {
		return null;
	}

	public String execute(IRequestHelper helper) {
		
		HttpServletRequest request = helper.getRequest();
		HttpServletResponse response = helper.getResponse();
		
		String content = request.getParameter("content");
		int aid = Integer.parseInt(request.getParameter("aid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		String url = "/pages/subFalse.jsp";
		
		IRevertBiz irevert = new RevertBiz();
		boolean flag = irevert.doSave(content, aid, uid);
		IArticleBiz artbiz = new ArticleBiz();
		
		if(flag){
			artbiz.updateRevNum(aid);
			url="/article.jsp?aid=" + aid;
		}
		return url;
	}

}
