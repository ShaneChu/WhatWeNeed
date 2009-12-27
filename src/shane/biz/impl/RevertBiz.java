package shane.biz.impl;

import java.util.List;

import shane.biz.IRevertBiz;
import shane.dao.IRevertDAO;
import shane.dao.mysql.impl.RevertDAO;
import shane.vo.revert;

public class RevertBiz implements IRevertBiz {

	public List<revert> getRevertByID(int aid) {
		IRevertDAO revertdao = new RevertDAO();
		List<revert> revertList = revertdao.getRevertByID(aid);
		return revertList;
	}

	public revert getRevert(int rid) {
		IRevertDAO revertdao = new RevertDAO();
		revert rev = revertdao.getRevert(rid);
		return rev;
	}

	public String getUserName(int uid) {
		IRevertDAO revertdao = new RevertDAO();
		String userName = revertdao.getUserName(uid);
		return userName;
	}

	public boolean doSave(String content, int aid, int uid) {
		IRevertDAO revertdao = new RevertDAO();
		boolean flag = revertdao.doSave(content, aid, uid);
		return flag;
	}
	
	public int getRevNum(int aid) {
		IRevertDAO revertdao = new RevertDAO();
		int num = revertdao.getRevNum(aid);
		return num;
	}

	public boolean doUpdate(String content, int rid) {
		IRevertDAO revertdao = new RevertDAO();
		boolean flag = revertdao.doUpdate(content, rid);
		return flag;
	}

}
