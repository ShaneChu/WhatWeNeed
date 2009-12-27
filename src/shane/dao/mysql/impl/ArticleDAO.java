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

import shane.dao.IArticleDAO;
import shane.dao.mysql.AbsMysqlDAO;
import shane.vo.article;

public class ArticleDAO extends AbsMysqlDAO implements IArticleDAO {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public List<article> getArticleByID(int sid) {
		
		List<article> articleList = new ArrayList<article>();
		try {
			con = getConnection();
			String sql = "select * from article where S_ID=? order by A_IsTop desc";
			pst = con.prepareStatement(sql);
			
			pst.setInt(1, sid);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				article art = new article();
				art.setAid(rs.getInt("A_ID"));
				art.setTopic(rs.getString("A_Topic"));
				art.setContent(rs.getString("A_Content"));
				art.setRevNum(rs.getInt("A_RevNum"));
				art.setCreTime(rs.getTimestamp("A_CreTime"));
				art.setLastReUser(rs.getInt("A_LastReUser"));
				art.setLastReTime(rs.getTimestamp("A_LastReTime"));
				art.setTop(rs.getBoolean("A_IsTop"));
				art.setOpen(rs.getBoolean("A_IsOpen"));
				art.setUserid(rs.getInt("U_ID"));
				art.setCommunityid(rs.getInt("S_ID"));
				articleList.add(art);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return articleList;
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
		} finally {
			this.close(null, pst, con);
		}
		return userName;
	}

	public article getArticle(int aid) {

		article art = new article();
		try {
			con = getConnection();
			String sql = "select * from article where A_ID=?";
			pst = con.prepareStatement(sql);
			pst.setInt(1, aid);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				art.setAid(rs.getInt("A_ID"));
				art.setTopic(rs.getString("A_Topic"));
				art.setContent(rs.getString("A_Content"));
				art.setCreTime(rs.getTimestamp("A_CreTime"));
				art.setLastReUser(rs.getInt("A_LastReUser"));
				art.setLastReTime(rs.getTimestamp("A_LastReTime"));
				art.setTop(rs.getBoolean("A_IsTop"));
				art.setOpen(rs.getBoolean("A_IsOpen"));
				art.setUserid(rs.getInt("U_ID"));
				art.setCommunityid(rs.getInt("S_ID"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pst, con);
		}
		return art;
	}
	
	public boolean updateInfo(int aid, int uid, Timestamp time) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "update article set A_LastReUser=?,A_LastReTime=? where A_ID='" + aid + "'";

			pst = con.prepareStatement(sql);
			pst.setInt(1, uid);
			pst.setTimestamp(2, time);
			int i = pst.executeUpdate(); 
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pst, con);
		}
		return flag;
	}

	public boolean donew(String headline, String content, int uid, int sid) {

		boolean flag = false;
		try {
			con = getConnection();
			String sql = "insert into article(A_Topic, A_Content, A_CreTime, A_LastReUser, A_LastReTime, A_IsTop, U_ID, S_ID) values(?,?,?,?,?,?,?,?)";

			Timestamp time = new Timestamp(new Date().getTime());
			pst = con.prepareStatement(sql);
			pst.setString(1, headline);
			pst.setString(2, content);
			pst.setTimestamp(3, time);
			pst.setInt(4, uid);
			pst.setTimestamp(5, time);
			pst.setBoolean(6, false);
			pst.setInt(7, uid);
			pst.setInt(8, sid);
			
			int i = pst.executeUpdate(); 
			if (i > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pst, con);
		}
		return flag;
	}
	
	public int getArtNum(int sid) {

		int artNum = 0; 
		try {
			con = getConnection();
			String sql = "select count(*) from article where S_ID='" + sid + "'";
			pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				artNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close(null, pst, con);
		}
		return artNum;
	}

	public boolean updateRevNum(int aid) {
		
		boolean flag = false;
		int number = 0;
		try {
			con = getConnection();
			number = new RevertDAO().getRevNum(aid);
			String sql = "update article set A_RevNum=? where A_ID='" + aid + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setInt(1, number);
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
	
	public boolean delArtByAuthor(int uid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "select A_ID from article where U_ID='" + uid + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			RevertDAO revdao = new RevertDAO();
			while (rs.next()) {
				System.out.println(rs.getInt(1));
				revdao.delRevByAID(rs.getInt(1));
				this.delArt(rs.getInt(1));
				flag = true;
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}
	
	public boolean delArt(int aid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "delete from article where a_ID='" + aid + "'";
			PreparedStatement pst = con.prepareStatement(sql);
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
	
	public int getSID(int aid) {
		
		int sid = 0;
		try {
			con = getConnection();
			String sql = "select S_ID from article where A_ID=" + aid;
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				sid = rs.getInt("S_ID");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return sid;
	}

	public boolean doUpdate(int aid, String headline, String content, int isTop, int isOpen, int sid) {

		boolean flag = false;
		try {
			con = getConnection();
			String sql = "update article set A_Topic=?, A_Content=?, A_IsTop=?, A_IsOpen=?, S_ID=? where A_ID=" + aid;
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, headline);
			pst.setString(2, content);
			pst.setInt(3, isTop);
			pst.setInt(4, isOpen);
			pst.setInt(5, sid);
			
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

}
