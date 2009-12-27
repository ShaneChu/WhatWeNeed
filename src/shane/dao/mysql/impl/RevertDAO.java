package shane.dao.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import shane.dao.IRevertDAO;
import shane.dao.mysql.AbsMysqlDAO;
import shane.vo.revert;

public class RevertDAO extends AbsMysqlDAO implements IRevertDAO {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public List<revert> getRevertByID(int aid) {
		
		List<revert> revertList = new ArrayList<revert>();
		try {
			con = getConnection();
			String sql = "select * from revert where A_ID=?";
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, aid);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				revert rev = new revert();
				rev.setRid(rs.getInt("R_ID"));
				rev.setContent(rs.getString("R_Content"));
				rev.setCreTime(rs.getTimestamp("R_CreTime"));
				rev.setUserid(rs.getInt("U_ID"));
				rev.setCommunityid(rs.getInt("S_ID"));
				rev.setArticleid(rs.getInt("A_ID"));
				revertList.add(rev);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    }
		return revertList;
	}

	public revert getRevert(int rid) {
		
		revert rev = new revert();
		try {
			con = getConnection();
			String sql = "select * from revert where R_ID=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, rid);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				rev.setRid(rs.getInt("R_ID"));
				rev.setContent(rs.getString("R_Content"));
				rev.setCreTime(rs.getTimestamp("R_CreTime"));
				rev.setUserid(rs.getInt("U_ID"));
				rev.setCommunityid(rs.getInt("S_ID"));
				rev.setArticleid(rs.getInt("A_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rev;
		
	}

	public String getUserName(int uid) {
		
		String userName = null;
		try {
			con = getConnection();
			String sql = "select U_Name from user where U_ID=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, uid);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				userName = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userName;
	}

	public boolean doSave(String content, int aid, int uid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "insert into revert (R_Content, R_CreTime, A_ID, S_ID, U_ID) values (?,?,?,?,?)";
			pst = con.prepareStatement(sql);
			
			Timestamp time = new Timestamp(new Date().getTime());
			pst.setString(1, content);
			pst.setTimestamp(2, time);
			pst.setInt(3, aid);
			pst.setInt(4, getBoardID(aid));
			pst.setInt(5, uid);
			
			int i = pst.executeUpdate();
			if (i > 0) {
				boolean isUpdate = new ArticleDAO().updateInfo(aid, uid, time);
				if (isUpdate) {
					flag = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getBoardID(int aid) {
		int sid = 0;
		try {
			Connection conn = getConnection();
			String sql = "select S_ID from article where A_ID=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, aid);
			
			ResultSet res = stmt.executeQuery();
			if (res.next()) {
				sid = res.getInt("S_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return sid;
	}
	
	public int getRevNum(int aid) {

		int revNum = 0; 
		try {
			con = getConnection();
			String sql = "select count(*) from revert where A_ID='" + aid + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				revNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pst, con);
		}
		return revNum;
	}
	
	public boolean delRevByUID(int uid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "delete from revert where U_ID='" + uid +"'";
			pst = con.prepareStatement(sql);
			int i = pst.executeUpdate();
			
			if (i >= 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public boolean delRevByAID(int aid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "delete from revert where A_ID='" + aid +"'";
			pst = con.prepareStatement(sql);
			int i = pst.executeUpdate();
			
			if (i >= 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public int getAID(int rid) {
		
		int aid = 0;
		try {
			Connection con = getConnection();
			String sql = "select A_ID from revert where R_ID='" + rid + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			ResultSet rs = pst.executeQuery();
			
			if (rs.next()) {
				aid =rs.getInt("A_ID");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return aid;
	}

	public boolean doUpdate(String content, int rid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "update revert set R_Content=? where R_ID=" + rid;
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, content);
			
			int i = pst.executeUpdate();
			if (i >= 0) {
				flag = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}
	
	public boolean delSingleRev(int rid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "delete from revert where R_ID=" + rid;
			pst = con.prepareStatement(sql);
			int i = pst.executeUpdate();
			
			if (i >= 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return flag;
	}

}
