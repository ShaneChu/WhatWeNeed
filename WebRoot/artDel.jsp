<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="shane.biz.impl.UserBiz" %>
<%@ page import="shane.biz.impl.ArticleBiz,shane.vo.article" %>
<%@ page import="shane.dao.mysql.impl.ArticleDAO" %>
<%@ page import="shane.dao.mysql.impl.RevertDAO" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>删除主题</title>
</head>
<body>

<%
	String _aid = request.getParameter("id");
	if (_aid == null) {
		_aid = "0";
	}
	int aid = Integer.parseInt(_aid);
	
	ArticleDAO artdao = new ArticleDAO();
	int sid = artdao.getSID(aid);
	article art = new ArticleBiz().getArticle(aid);
	String acco = (String)session.getAttribute("account");
	boolean isAdmin = new UserBiz().isadmin(acco);
	if (isAdmin) {
		boolean flag = new RevertDAO().delRevByAID(aid);
		boolean isArtDel = artdao.delArt(aid);
		String url = "topic.jsp?id="+sid;
		response.sendRedirect(url);
%>
<% } else { 
	response.sendRedirect("index.jsp");
} %>
</body>
</html>