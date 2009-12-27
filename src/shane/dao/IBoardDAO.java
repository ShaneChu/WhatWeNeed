package shane.dao;

import java.util.List;

import shane.vo.subcommunity;

public interface IBoardDAO {

	public List<subcommunity> getBoard();
	public int getIDByName(String name);
	public boolean updateArtNum(int sid);
	public subcommunity getBoard(int sid);
	public boolean updateInfo(int sid, String name, String desc, int status);
	public boolean newBoard(String name, String desc, int status);
	public boolean delBoard(int sid);
	
}
