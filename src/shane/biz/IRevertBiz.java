package shane.biz;

import java.util.List;

import shane.vo.revert;

public interface IRevertBiz {

	public List<revert> getRevertByID(int aid);
	public String getUserName(int uid) ;
	public revert getRevert(int rid);
	public boolean doSave(String content, int aid, int uid);
	public int getRevNum(int aid);
	public boolean doUpdate(String content, int rid);
	
}
