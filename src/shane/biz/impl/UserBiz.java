package shane.biz.impl;

import java.util.List;

import shane.biz.IUserBiz;
import shane.dao.IUserDAO;
import shane.dao.mysql.impl.UserDAO;
import shane.vo.User;


public class UserBiz implements IUserBiz {
	public UserBiz() {}
   
	public boolean doreg(User user) {
		IUserDAO userdao = new UserDAO();
		boolean flag = userdao.save(user);
		return flag;
	}
   
	public String dologin(User user) {
		IUserDAO userdao = new UserDAO();
		String name = userdao.findUserByName(user);
		return name;
	}
   
   
	public User domodify(String account) {
	   IUserDAO userdao = new UserDAO();
	   User currentUser = userdao.getInfo(account);
	   return currentUser;
	}
	
	public boolean doupdate(User user) {
		IUserDAO userdao = new UserDAO();
		boolean flag = userdao.doUpdate(user);
		return flag;
	}
   
	public User dofind(String account) {
		IUserDAO userdao = new UserDAO();
		User user = userdao.doFind(account);
		return user;
	}

	public int getUID(String account) {
		IUserDAO userdao = new UserDAO();
		int uid = userdao.getUID(account);
		return uid;
	}
	
	public boolean isadmin(String account) {
		IUserDAO userdao = new UserDAO();
		boolean flag = userdao.isAdmin(account);
		return flag;
	}

	public List<User> getAllUser() {
		IUserDAO userdao = new UserDAO();
		List<User> userList = userdao.getAllUser();
		return userList;
	}

	public boolean delUser(String account) {
		IUserDAO userdao = new UserDAO();
		boolean flag = userdao.delUser(account);
		return flag;
	}
	
	public boolean userMod(User user) {
		IUserDAO userdao = new UserDAO();
		boolean flag = userdao.userMod(user);
		return flag;
	}
	
   public static void main(String[] args) {
	   
 		IUserBiz userBiz=new UserBiz();
 		User user =new User();
 		user.setAccount("single");
 		user.setPassword("anor");
 		user.setName("swall");
 		user.setGender(true);
 		user.setAge(34);
 		user.setEmail("single@hot.com");
 		user.setQuestion("�ҵ�ְҵ��ʲô��");
 		user.setAnswer("Һ̬����������");
 		
 		if(userBiz.doreg(user)){
 			
 			System.out.println(" ����һ�£�ע���Ƿ�ɹ����ɹ�����");
 		}
	}



}





 
