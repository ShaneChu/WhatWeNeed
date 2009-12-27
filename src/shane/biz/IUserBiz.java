package shane.biz;

import shane.vo.User;
import java.util.List;

public interface IUserBiz 
{
   
	public boolean doreg(User user);
	public String dologin(User user);
	public User domodify(String account);
	public boolean doupdate(User user);
	public User dofind(String account);
	public int getUID(String account);
	public List<User> getAllUser();
	public boolean delUser(String account);
	public boolean userMod(User user);
	
}
