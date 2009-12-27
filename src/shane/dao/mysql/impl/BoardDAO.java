package shane.dao.mysql.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ArrayList;

import shane.dao.IBoardDAO;
import shane.dao.mysql.AbsMysqlDAO;
import shane.vo.subcommunity;

public class BoardDAO extends AbsMysqlDAO implements IBoardDAO {

	Connection con = null;
	Statement st = null;
	ResultSet rs = null;
	PreparedStatement pst = null;
	
	public List<subcommunity> getBoard() {

		List<subcommunity> boardList = new ArrayList<subcommunity>();
		try {
			con = getConnection();
			String sql = "select * from subcommunity";
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			while (rs.next()) {
				subcommunity board = new subcommunity();
				board.setSid(rs.getInt("S_ID"));
				board.setName(rs.getString("S_Name"));
				board.setDescribe(rs.getString("S_Desc"));
				board.setCreTime(rs.getDate("S_CreTime"));
				board.setStatus(rs.getBoolean("S_Status"));
				boardList.add(board);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return boardList;
	}

	public subcommunity getBoard(int sid) {

		subcommunity board = new subcommunity();
		try {
			con = getConnection();
			String sql = "select * from subcommunity where S_ID=" + sid;
			pst = con.prepareStatement(sql);
			
			rs = pst.executeQuery();
			if (rs.next()) {
				board.setSid(rs.getInt("S_ID"));
				board.setName(rs.getString("S_Name"));
				board.setDescribe(rs.getString("S_Desc"));
				board.setCreTime(rs.getDate("S_CreTime"));
				board.setStatus(rs.getBoolean("S_Status"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return board;
	}

	public int getIDByName(String name) {
		
		int sid = 0;
		try {
			con = getConnection();
			String sql = "select S_ID from subcommunity where S_Name='" + name + "'";
			pst = con.prepareStatement(sql);
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

	public boolean newBoard(String name, String desc, int status) {

		boolean flag = false;
		try {
			con = getConnection();
			String sql = "insert into subcommunity(S_Name,S_Desc,S_CreTime,S_Status) values (?,?,?,?)";
			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, desc);
			pst.setDate(3, new java.sql.Date(new java.util.Date().getTime()));
			pst.setInt(4, status);
			
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

	public boolean updateArtNum(int sid) {

		boolean flag = false;
		int number = 0;
		try {
			con = getConnection();
			number = new ArticleDAO().getArtNum(sid);
			String sql = "update subcommunity set S_ArtNum=? where S_ID='" + sid + "'";
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

	public boolean updateInfo(int sid, String name, String desc, int status) {

		boolean flag = false;
		try {
			con = getConnection();
			String sql = "update subcommunity set S_Name=?,S_Desc=?,S_Status=? where S_ID=" + sid;

			pst = con.prepareStatement(sql);
			pst.setString(1, name);
			pst.setString(2, desc);
			pst.setInt(3, status);
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

	public boolean delBoard(int sid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "select A_ID from article where S_ID='" + sid + "'";
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			RevertDAO revdao = new RevertDAO();
			while (rs.next()) {
				revdao.delRevByAID(rs.getInt(1));
				new ArticleDAO().delArt(rs.getInt(1));
			}
			this.delBoardLast(sid);
			flag = true;
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return flag;
	}
	
	public boolean delBoardLast(int sid) {
		
		boolean flag = false;
		try {
			con = getConnection();
			String sql = "delete from subcommunity where S_ID='" + sid + "'";
			PreparedStatement pst = con.prepareStatement(sql);
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
	
	public String getBoardName(int sid) {
		
		String name = null;
		try {
			con = getConnection();
			String sql = "select S_Name from subcommunity where S_ID=" + sid;
			PreparedStatement pst = con.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				name = rs.getString("S_Name");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
	    } finally {
	    	this.close(null, pst, con);
	    }
		return name;
	}

}
