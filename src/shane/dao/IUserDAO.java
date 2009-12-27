package shane.dao;

import shane.vo.User;
import java.util.List;

public interface IUserDAO {
	
	public boolean save(User user);
	public String findUserByName(User user);
	public User getInfo(String account);
	public boolean doUpdate(User user);
	public User doFind(String account);
	public int getUID(String account);
	public boolean isAdmin(String account);
	public List<User> getAllUser();
	public boolean delUser(String account);
	public boolean userMod(User user);
	
}
