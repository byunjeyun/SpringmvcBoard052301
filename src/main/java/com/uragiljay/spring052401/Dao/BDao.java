package com.uragiljay.spring052401.Dao;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.uragiljay.spring052401.Dto.BDto;



public class BDao {

	
	DataSource dataSource;

	public BDao() {
		super();
		// TODO Auto-generated constructor stub
		
		try {
			Context context = new InitialContext();
			dataSource = (DataSource) context.lookup("java:comp/env/jdbc/Oracle11g");
		} catch (NamingException e) {
			e.printStackTrace();
			
		}
		
		
	}
	
		
public ArrayList<BDto> list() {
		
		ArrayList<BDto> dtos = new ArrayList<BDto>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from mvc_board order by bgroup desc, bstep asc";
			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				 int bId = rs.getInt("bid");
				 String bName = rs.getString("bname");
				 String bTitle = rs.getString("btitle");
				 String bContent = rs.getString("bcontent");
				 Timestamp bDate = rs.getTimestamp("bdate");
				 int bHit = rs.getInt("bhit");
				 int bGroup = rs.getInt("bgroup");
				 int bStep = rs.getInt("bstep");
				 int bIndent = rs.getInt("bindent");
				 
				 BDto dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				 dtos.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return dtos;
	}
	
	public void write(String bname, String btitle, String bcontent) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, MVC_BOARD_SEQ.currval, 0, 0)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	}	
		
	
	public BDto content_view(String cid) {
		
		upHit(cid);
		
		BDto dto = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from mvc_board where bid =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(cid));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				 int bId = rs.getInt("bid");
				 String bName = rs.getString("bname");
				 String bTitle = rs.getString("btitle");
				 String bContent = rs.getString("bcontent");
				 Timestamp bDate = rs.getTimestamp("bdate");
				 int bHit = rs.getInt("bhit");
				 int bGroup = rs.getInt("bgroup");
				 int bStep = rs.getInt("bstep");
				 int bIndent = rs.getInt("bindent");
				 
				 dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		return dto; 
	}
	
	public void modify(String bid, String bname, String btitle, String bcontent) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
			conn = dataSource.getConnection();
			String query = "UPDATE mvc_board SET bname=?, btitle=?, bcontent=? WHERE bid=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setString(4, bid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	}	
	
	public void delete(String bid) {
		// TODO Auto-generated method stub
		
		Connection conn = null;
		PreparedStatement pstmt = null;		
		
		try {
			conn = dataSource.getConnection();
			String query = "delete from mvc_board where bid=?";
			pstmt = conn.prepareStatement(query);			
			
			pstmt.setString(1, bid);
			
			pstmt.executeUpdate();//데이터 삽입에 성공하면 1이 반환
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	}
	public void upHit(String bid) {
	
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
			conn = dataSource.getConnection();
			String query = "UPDATE mvc_board SET bhit=bhit+1 WHERE bid=?";
			pstmt = conn.prepareStatement(query);
						pstmt.setString(1, bid);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	}	
		
public BDto reply_view(String bid) {
		
			
		BDto dto = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = dataSource.getConnection();
			String query = "select * from mvc_board where bid =?";
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, Integer.parseInt(bid));
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				
				 int bId = rs.getInt("bid");
				 String bName = rs.getString("bname");
				 String bTitle = rs.getString("btitle");
				 String bContent = rs.getString("bcontent");
				 Timestamp bDate = rs.getTimestamp("bdate");
				 int bHit = rs.getInt("bhit");
				 int bGroup = rs.getInt("bgroup");
				 int bStep = rs.getInt("bstep");
				 int bIndent = rs.getInt("bindent");
				 
				 dto = new BDto(bId, bName, bTitle, bContent, bDate, bHit, bGroup, bStep, bIndent);
				 
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rs != null) {
					rs.close();
				}
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
					
		return dto; 
	}


	public void reply(String bid, String bname, String btitle, String bcontent, String bgroup, String bstep,
			String bindent) {
		// TODO Auto-generated method stub
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		
		try {
			conn = dataSource.getConnection();
			String query = "INSERT INTO mvc_board(bid, bname, btitle, bcontent, bhit, bgroup, bstep, bindent) VALUES (MVC_BOARD_SEQ.nextval, ?, ?, ?, 0, ?, ?, ?)";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1, bname);
			pstmt.setString(2, btitle);
			pstmt.setString(3, bcontent);
			pstmt.setInt(4, Integer.parseInt(bgroup));
			pstmt.setInt(5, Integer.parseInt(bstep)+1);
			pstmt.setInt(6, Integer.parseInt(bindent)+1);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	
	}

	public void reply_sort(String cgroup, String cstep) {
		
		Connection conn = null;
		PreparedStatement pstmt = null;

		
		try {
			conn = dataSource.getConnection();
			String query = "UPDATE mvc_board SET bstep=bstep+1 WHERE bgroup=? and bstep=?";
			pstmt = conn.prepareStatement(query);
			
			pstmt.setString(1,  cgroup);
			pstmt.setString(1,  cstep);
			
			pstmt.executeUpdate();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

}





	
	
	

