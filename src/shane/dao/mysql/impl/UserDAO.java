package shane.dao.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import shane.dao.IUserDAO;
import shane.dao.mysql.AbsMysqlDAO;
import shane.vo.User;


public class UserDAO extends AbsMysqlDAO implements IUserDAO {
	
	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	
	public boolean save(User user) {
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "insert into user (U_Account,U_Passwd,U_Name,U_Gender,U_Age,U_Email,U_Question,U_Answer,U_Image,U_Identify) values (?,?,?,?,?,?,?,?,?,?)";

			pst = con.prepareStatement(sql);
			pst.setString(1, user.getAccount());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getName());
			pst.setBoolean(4, user.getGender());
			pst.setInt(5, user.getAge());
			pst.setString(6, user.getEmail());
			pst.setString(7, user.getQuestion());
			pst.setString(8, user.getAnswer());
			pst.setString(9, user.getImage());
			pst.setInt(10, 2);
			
			
			int i = pst.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}


	public String findUserByName(User user) {
	
		String nickname = "";
		try {
			con = getConnection();
			String sql = "select * from user where U_Account=? and U_Passwd=?";
			pst = con.prepareStatement(sql);
			pst.setString(1, user.getAccount());
			pst.setString(2, user.getPassword());
			
			ResultSet i = pst.executeQuery();
			if (i.next()) {
				if (!i.getBoolean("U_IsLocked")) {
					nickname = i.getString("U_Name");
				} else {
					nickname = "<locked>";
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			this.close(null, pst, con);
		}
		return nickname;
	}
	
	
	public User getInfo(String account) {
		
		con = getConnection();
		String sql = "select * from user where U_Account=?";
		
		try {
			pst = con.prepareStatement(sql);
			pst.setString(1, account);
		
			rs = pst.executeQuery();
			if (rs.next()) {
				User user = new User();
				user.setAccount(rs.getString("U_Account"));
				user.setPassword(rs.getString("U_Passwd"));
				user.setName(rs.getString("U_Name"));
				user.setGender(rs.getBoolean("U_Gender"));
				user.setAge(rs.getInt("U_Age"));
				user.setEmail(rs.getString("U_Email"));
				user.setQuestion(rs.getString("U_Question"));
				user.setAnswer(rs.getString("U_Answer"));
				user.setImage(rs.getString("U_Image"));
				user.setLocked(rs.getBoolean("U_IsLocked"));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public boolean doUpdate(User user) {
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "update user set U_Passwd=?,U_Name=?,U_Gender=?,U_Age=?,U_Email=?,U_Question=?,U_Answer=?,U_Image=? where U_Account='" + user.getAccount() + "'";

			pst = con.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getName());
			pst.setBoolean(3, user.getGender());
			pst.setInt(4, user.getAge());
			pst.setString(5, user.getEmail());
			pst.setString(6, user.getQuestion());
			pst.setString(7, user.getAnswer());
			if (user.getImage() != null) {
				pst.setString(8, user.getImage());
			} else {
				pst.setString(8, this.getImage(user.getAccount()));
			}
			
			int i = pst.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}
	
	
	public User doFind(String account) {

		try {
			con = getConnection();
			String sql = "select * from user where U_Account=?";

			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			
			rs = pst.executeQuery();
			if (rs.next()) {		
				User user = new User();
				user.setAccount(account);
				user.setPassword(rs.getString("U_Passwd"));
				user.setName(rs.getString("U_Name"));
				user.setGender(rs.getBoolean("U_Gender"));
				user.setAge(rs.getInt("U_Age"));
				user.setEmail(rs.getString("U_Email"));
				user.setQuestion(rs.getString("U_Question"));
				user.setAnswer(rs.getString("U_Answer"));
				user.setImage(rs.getString("U_Image"));
				user.setIdentify(rs.getInt("U_Identify"));
				user.setLocked(rs.getBoolean("U_IsLocked"));
				return user;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return null;
	}
	
	
	public int getUID(String account) {
		
		int uid = 0;
		try {
			con = getConnection();
			String sql = "select U_ID from user where U_Account=?";

			pst = con.prepareStatement(sql);
			pst.setString(1, account);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				uid = rs.getInt("U_ID");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return uid;
	}
	
	
	public static ResultSet checkUser(String sql) {
		
		UserDAO userdao = new UserDAO();
		ResultSet rs = null;
		try {
			Connection con = userdao.getConnection();
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			userdao.close(null, userdao.pst, userdao.con);
		}
		return rs;
	}


	public boolean isAdmin(String account) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "select * from user where U_Account='" + account + "'";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				if (rs.getInt("U_Identify") == 1) {
					flag = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}

	
	public List<User> getAllUser() {
		
		List<User> userList = new ArrayList<User>();
		try {
			con = getConnection();
			String sql = "select * from user";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setAccount(rs.getString("U_Account"));
				user.setPassword(rs.getString("U_Passwd"));
				user.setName(rs.getString("U_Name"));
				user.setGender(rs.getBoolean("U_Gender"));
				user.setAge(rs.getInt("U_Age"));
				user.setEmail(rs.getString("U_Email"));
				user.setQuestion(rs.getString("U_Question"));
				user.setAnswer(rs.getString("U_Answer"));
				user.setIdentify(rs.getInt("U_Identify"));
				user.setLocked(rs.getBoolean("U_IsLocked"));
				userList.add(user);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return userList;
	}


	public boolean delUser(String account) {

		boolean flag = false;
		int uid = this.getUID(account);
		boolean isRevDel = new RevertDAO().delRevByUID(uid);
		boolean isArtDel = new ArticleDAO().delArtByAuthor(uid);
		System.out.println("isRevDel:" + isRevDel);
		System.out.println("isArtDel:" + isArtDel);
		if (isRevDel && isArtDel) {
			try {
				con = getConnection();
				String sql = "delete from user where U_Account='" + account + "'";
				pst = con.prepareStatement(sql);
				
				int i = pst.executeUpdate();
				if (i > 0) {
					flag = true;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
		    } finally {
		    	this.close(null, pst, con);
		    }
		}
		return flag;
	}
	
	public String getHeadImage(int uid) {
		
		String image = null;
		try {
			con = getConnection();
			String sql = "select U_Image from user where U_ID=" + uid;
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				image = rs.getString(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return image;
	}
	
	public String getImage(String account) {
		
		UserDAO userdao = new UserDAO();
		String image = null;
		try {
			Connection con = getConnection();
			String sql = "select U_Image from user where U_Account=?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, account);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				image = rs.getString(1);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	userdao.close(null, userdao.pst, userdao.con);
	    }
		return image;
	}
	
	public boolean userMod(User user) {
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "update user set U_Passwd=?,U_Name=?,U_Gender=?,U_Age=?,U_Email=?,U_Question=?,U_Answer=?,U_Image=?,U_IsLocked=?,U_Identify=? where U_Account='" + user.getAccount() + "'";

			pst = con.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			pst.setString(2, user.getName());
			pst.setBoolean(3, user.getGender());
			pst.setInt(4, user.getAge());
			pst.setString(5, user.getEmail());
			pst.setString(6, user.getQuestion());
			pst.setString(7, user.getAnswer());
			if (user.getImage() != null) {
				pst.setString(8, user.getImage());
			} else {
				pst.setString(8, this.getImage(user.getAccount()));
			}
			pst.setBoolean(9, user.isLocked());
			pst.setInt(10, user.getIdentify());
			
			int i = pst.executeUpdate();
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}
	
}




