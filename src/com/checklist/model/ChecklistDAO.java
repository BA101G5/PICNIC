package com.checklist.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ChecklistDAO implements Checklist_interface {

	private static DataSource ds = null;
	static{
		try{
			Context ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/ba101_5");
		}catch (NamingException e){
			e.printStackTrace();
		}
	}
	private static final String INSERT_STMT =
			"INSERT INTO checklist (chli_no, chli_cate, chli_be_num, chli_memno, chli_reason, chli_date) VALUES ('CH' || LPAD(CHLI_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?)";
	private static final String GET_ALL_UNDONE_STMT =
			"SELECT * FROM checklist where chli_sta is NULL order by chli_no";
	private static final String GET_ALL_DONE_STMT =
			"SELECT * FROM checklist where chli_sta LIKE 'F' order by chli_no";
	private static final String GET_ONE_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist where chli_no=?";
	private static final String DELETE =
			"DELETE FROM checklist where chli_no = ?";
	private static final String UPDATE =
			"UPDATE checklist set  chli_start=?,chli_day=?, chli_end=?, chli_pun=?, chli_sta=? where chli_no=?";
	private static final String UPDATE_MEM =
			"UPDATE GENERAL_MEMBER set  mem_sta = 'D' where mem_no=?";
	private static final String UPDATE_MF = 
			"UPDATE MANUFACTURERS set mf_sta = 'D'  where mf_no=?";
	private static final String UPDATE_GOODS= 
			"UPDATE GOODS_SELL set GS_STA = 'D' where mf_no=?";
	//下架單向商品
	private static final String UPDATE_GOODS_ONE= 
			"UPDATE GOODS_SELL set GS_STA = 'D' where gs_no=?";
	
	//找團主
	private static final String FIND_IDEM = 
			"select mem_no from picmem where picmem_iden='團主' and picnic_no=?";
	//封鎖野餐團
	private static final String BLOCKADE_PICNIC = 
			"update picnic set picnic_sta = 'B' where picnic_no = ?";
	
	//找發文章人
	private static final String FIND_AF = 
			"select author_no from forum_article where article_no = ?";
	//刪文章
	private static final String SELETE_AF = 
			"delete from forum_article where article_no = ?";
	
	//找留言人
	private static final String FIND_AB = 
				"select author_no from pboard_article where article_no = ?";
	//刪留言
	private static final String SELETE_AB = 
				"delete from pboard_article where article_no = ?";
	
	@Override
	public void insert(ChecklistVO checklistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(INSERT_STMT);
			
			pstmt.setInt(1,checklistVO.getChli_cate());
			pstmt.setString(2, checklistVO.getChli_be_num());
			pstmt.setString(3, checklistVO.getChli_memno());
			//pstmt.setTimestamp(4, checklistVO.getChli_start());
			//pstmt.setTimestamp(5, checklistVO.getChli_end());
			//pstmt.setInt(6, checklistVO.getChli_day());
			//pstmt.setInt(7, checklistVO.getChli_pun());
			pstmt.setString(4, checklistVO.getChli_reason());
			pstmt.setTimestamp(5,checklistVO.getChli_date());
			//pstmt.setString(10, checklistVO.getChli_sta());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update(ChecklistVO checklistVO) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE);
			
			pstmt.setTimestamp(1,checklistVO.getChli_start());
			pstmt.setInt(2, checklistVO.getChli_day());
			pstmt.setTimestamp(3, checklistVO.getChli_end());
			pstmt.setInt(4, checklistVO.getChli_pun());
			pstmt.setString(5, checklistVO.getChli_sta());
			pstmt.setString(6, checklistVO.getChli_no());
			
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void delete(String chli_no) {

		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(DELETE);
			
			pstmt.setString(1, chli_no);
		
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public ChecklistVO findByPrimaryKey(String chli_no) {
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ONE_STMT);
			
			pstmt.setString(1, chli_no);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
			}
			
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return checklistVO;
	}

	@Override
	public List<ChecklistVO> getAllUndone() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNDONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
				list.add(checklistVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	
	@Override
	public List<ChecklistVO> getAllDone() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
				list.add(checklistVO);
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllDone_0() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==0){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllDone_1() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==1){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllDone_2() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==2){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllDone_3() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==3){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllDone_4() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_DONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==4){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllUndone_0() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNDONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==0){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllUndone_1() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNDONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==1){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllUndone_2() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNDONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==2){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllUndone_3() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNDONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==3){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}
	@Override
	public List<ChecklistVO> getAllUndone_4() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(GET_ALL_UNDONE_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				if(rs.getInt("chli_cate")==4){
					checklistVO.setChli_no(rs.getString("chli_no"));
					checklistVO.setChli_cate(rs.getInt("chli_cate"));
					checklistVO.setChli_be_num(rs.getString("chli_be_num"));
					checklistVO.setChli_memno(rs.getString("chli_memno"));
					checklistVO.setChli_start(rs.getTimestamp("chli_start"));
					checklistVO.setChli_end(rs.getTimestamp("chli_end"));
					checklistVO.setChli_day(rs.getInt("chli_day"));
					checklistVO.setChli_pun(rs.getInt("chli_pun"));
					checklistVO.setChli_reason(rs.getString("chli_reason"));
					checklistVO.setChli_date(rs.getTimestamp("chli_date"));
					checklistVO.setChli_sta(rs.getString("chli_sta"));
					list.add(checklistVO);
				}
				
			}
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
		return list;
	}

	@Override
	public void update_mf(String mf_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MF);
			pstmt.setString(1, mf_no);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update_mem(String mem_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_MEM);
			pstmt.setString(1, mem_no);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void update_goods(String mf_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(UPDATE_GOODS);
			pstmt.setString(1, mf_no);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void update_goods_one(String gs_no) {
		Connection con = null;
	PreparedStatement pstmt = null;
	
	try{
		con = ds.getConnection();
		pstmt = con.prepareStatement(UPDATE_GOODS_ONE);
		pstmt.setString(1, gs_no);
		pstmt.executeUpdate();
	}catch (SQLException se) {
		throw new RuntimeException("A database error occured. "
				+ se.getMessage());
	} finally {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
		if (con != null) {
			try {
				con.close();
			} catch (Exception e) {
				e.printStackTrace(System.err);
			}
		}
	}
		
	}

	@Override
	public void blockadePicnic(String picnic_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(BLOCKADE_PICNIC);
			pstmt.setString(1, picnic_no);
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
			
		}

	@Override
	public String findPicnicA(String picnic_no) {
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mem_no = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_IDEM);
			
			pstmt.setString(1, picnic_no);
			
			rs = pstmt.executeQuery();
			
			rs.next();
				checklistVO = new ChecklistVO();
				
				checklistVO.setMem_no(rs.getString("mem_no"));
				mem_no = rs.getString("mem_no");
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mem_no;
	}

	@Override
	public String findAFMem(String article_no) {
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mem_no = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AF);
			
			pstmt.setString(1, article_no);
			
			rs = pstmt.executeQuery();
			
			rs.next();
				checklistVO = new ChecklistVO();
				
				checklistVO.setMem_no(rs.getString("author_no"));
				mem_no = rs.getString("author_no");
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mem_no;
	}

	@Override
	public void deleteArticle(String article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELETE_AF);
			
			pstmt.setString(1, article_no);
		
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public String findABMem(String article_no) {
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String mem_no = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(FIND_AB);
			
			pstmt.setString(1, article_no);
			
			rs = pstmt.executeQuery();
			
			rs.next();
				checklistVO = new ChecklistVO();
				
				checklistVO.setMem_no(rs.getString("author_no"));
				mem_no = rs.getString("author_no");
			
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return mem_no;
	}

	@Override
	public void deletePBArticle(String article_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(SELETE_AB);
			
			pstmt.setString(1, article_no);
		
			pstmt.executeUpdate();
		}catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

  //--------------------------------------------------------------
  
	private static final String K_INSERT_STMT =
			"INSERT INTO checklist (chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta) VALUES ('CH' || LPAD(CHLI_NO_SQ.NEXTVAL, 8, '0'),?,?,?,?,?,?,?,?,?,?)";
	private static final String K_GET_ALL_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist order by chli_no";
	private static final String K_GET_ONE_STMT =
			"SELECT chli_no, chli_cate, chli_be_num, chli_memno, chli_start, chli_end, chli_day, chli_pun, chli_reason, chli_date, chli_sta FROM checklist where chli_no=?";
	private static final String K_DELETE =
			"DELETE FROM checklist where chli_no = ?";
	private static final String K_UPDATE =
			"UPDATE CHECKLIST set  CHLI_CATE=?, CHLI_BE_NUM=?, CHLI_MEMNO=?, CHLI_START=?, CHLI_END=? ,CHLI_DAY=?,CHLI_PUN=?,CHLI_REASON=?,CHLI_DATE=?,CHLI_STA=? "
			+ " where CHLI_NO=? ";
	
	
	@Override
	public void k_insert(ChecklistVO checklistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{

			con = ds.getConnection();
			pstmt = con.prepareStatement(K_INSERT_STMT);
			
			pstmt.setInt(1,checklistVO.getChli_cate());
			pstmt.setString(2, checklistVO.getChli_be_num());
			pstmt.setString(3, checklistVO.getChli_memno());
			pstmt.setTimestamp(4, checklistVO.getChli_start());
			pstmt.setTimestamp(5, checklistVO.getChli_end());
			pstmt.setInt(6, checklistVO.getChli_day());
			pstmt.setInt(7, checklistVO.getChli_pun());
			pstmt.setString(8, checklistVO.getChli_reason());
			pstmt.setTimestamp(9,checklistVO.getChli_date());
			pstmt.setString(10, checklistVO.getChli_sta());
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
	}

	@Override
	public void k_update(ChecklistVO checklistVO) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try{

			con = ds.getConnection();
			pstmt = con.prepareStatement(K_UPDATE);
			
			pstmt.setInt(1, checklistVO.getChli_cate());
			pstmt.setString(2, checklistVO.getChli_be_num());
			pstmt.setString(3,checklistVO.getChli_memno());
			pstmt.setTimestamp(4, checklistVO.getChli_start());
			pstmt.setTimestamp(5,checklistVO.getChli_end());
			pstmt.setInt(6, checklistVO.getChli_day());
			pstmt.setInt(7, checklistVO.getChli_pun());
			pstmt.setString(8, checklistVO.getChli_reason());
			pstmt.setTimestamp(9, checklistVO.getChli_date());
			pstmt.setString(10, checklistVO.getChli_sta());
			pstmt.setString(11, checklistVO.getChli_no());
	
			pstmt.executeUpdate();
			
			
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public void k_delete(String chli_no) {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_DELETE);
			
			pstmt.setString(1, chli_no);
			
			pstmt.executeUpdate();
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		
	}

	@Override
	public ChecklistVO k_findByPrimaryKey(String chli_no) {
		
		ChecklistVO checklistVO = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ONE_STMT);
			
			pstmt.setString(1, chli_no);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return checklistVO;
	}

	@Override
	public List<ChecklistVO> k_getAll() {
		List<ChecklistVO> list = new ArrayList<ChecklistVO>();
		ChecklistVO checklistVO = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = ds.getConnection();
			pstmt = con.prepareStatement(K_GET_ALL_STMT);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				checklistVO = new ChecklistVO();
				
				checklistVO.setChli_no(rs.getString("chli_no"));
				checklistVO.setChli_cate(rs.getInt("chli_cate"));
				checklistVO.setChli_be_num(rs.getString("chli_be_num"));
				checklistVO.setChli_memno(rs.getString("chli_memno"));
				checklistVO.setChli_start(rs.getTimestamp("chli_start"));
				checklistVO.setChli_end(rs.getTimestamp("chli_end"));
				checklistVO.setChli_day(rs.getInt("chli_day"));
				checklistVO.setChli_pun(rs.getInt("chli_pun"));
				checklistVO.setChli_reason(rs.getString("chli_reason"));
				checklistVO.setChli_date(rs.getTimestamp("chli_date"));
				checklistVO.setChli_sta(rs.getString("chli_sta"));
				list.add(checklistVO);
				
			}
		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. "
					+ se.getMessage());
			// Clean up JDBC resources
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException se) {
					se.printStackTrace(System.err);
				}
			}
			if (con != null) {
				try {
					con.close();
				} catch (Exception e) {
					e.printStackTrace(System.err);
				}
			}
		}
		return list;
	}
}
