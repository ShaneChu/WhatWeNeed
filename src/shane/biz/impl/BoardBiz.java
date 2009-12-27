package shane.biz.impl;

import java.util.List;

import shane.biz.IBoardBiz;
import shane.dao.IBoardDAO;
import shane.dao.mysql.impl.BoardDAO;
import shane.vo.subcommunity;

public class BoardBiz implements IBoardBiz {

	public List<subcommunity> getBoard() {
		   IBoardDAO boarddao = new BoardDAO();
		   List<subcommunity> boardList = boarddao.getBoard();
		   return boardList;
	}

	public int getIDByName(String name) {
		IBoardDAO boarddao = new BoardDAO();
		int sid = boarddao.getIDByName(name);
		return sid;
	}

	public boolean updateArtNum(int sid) {
		IBoardDAO boarddao = new BoardDAO();
		boolean flag = boarddao.updateArtNum(sid);
		return flag;
	}

	public subcommunity getBoard(int sid) {
		IBoardDAO boarddao = new BoardDAO();
		subcommunity board = boarddao.getBoard(sid);
		return board;
	}

	public boolean updateInfo(int sid, String name, String desc, int status) {
		IBoardDAO boarddao = new BoardDAO();
		boolean flag = boarddao.updateInfo(sid,name,desc,status);
		return flag;
	}

	public boolean newBoard(String name, String desc, int status) {
		IBoardDAO boarddao = new BoardDAO();
		boolean flag = boarddao.newBoard(name, desc, status);
		return flag;
	}

	public boolean delBoard(int sid) {
		IBoardDAO boarddao = new BoardDAO();
		boolean flag = boarddao.delBoard(sid);
		return flag;
	}

}
