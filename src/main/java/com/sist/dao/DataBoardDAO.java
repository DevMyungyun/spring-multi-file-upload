package com.sist.dao;

import java.sql.*;
import java.util.*;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import oracle.jdbc.driver.OracleTypes;
import oracle.jdbc.oracore.OracleType;

@Repository
public class DataBoardDAO {
	private Connection conn;
	private CallableStatement cs; //���ν��� ȣ��
	@Autowired
	private BasicDataSource ds;
	public DataBoardDAO(){
		try{
			Class.forName(ds.getDriverClassName());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	public void getConnection(){
		try{
			conn=DriverManager.getConnection(ds.getUrl(),ds.getUsername(),ds.getPassword());
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	
	public void disConnection(){
		try{
			if(cs!=null) cs.close();
			if(conn!=null) conn.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
	}
	//���
	public List<DataBoardVO> boarListData(int page){
		List<DataBoardVO> list=new ArrayList<DataBoardVO>();
		try{
			getConnection();
			int rowSize=10;
			int start=(page*rowSize)-(rowSize-1);
			int end=page*rowSize;
			String sql="{CALL boardListData(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, start);
			cs.setInt(2, end);
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs=(ResultSet)cs.getObject(3);
			while(rs.next()){
				DataBoardVO vo=new DataBoardVO();
		
				vo.setNo(rs.getInt(1));
				vo.setSubject(rs.getString(2));
				vo.setName(rs.getString(3));
				vo.setRegdate(rs.getDate(4));
				vo.setHit(rs.getInt(5));
				list.add(vo);
			}
			rs.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		
		return list;
	}
	
	//�߰�
	public void boardInsert(DataBoardVO vo) {
		try{
			getConnection();
			String sql="{CALL boardInsert(?,?,?,?,?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setString(1, vo.getName());
			cs.setString(2, vo.getSubject());
			cs.setString(3, vo.getContent());
			cs.setString(4, vo.getPwd());
			cs.setString(5, vo.getFilename());
			cs.setString(6, vo.getFilesize());
			cs.setInt(7, vo.getFilecount());
			cs.executeQuery();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally{
			disConnection();
		}
	}
	//���뺸��
	public DataBoardVO boardContentData(int no){
		DataBoardVO vo=new DataBoardVO();
		try{
			getConnection();
			String sql="{CALL boardContentData(?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();
			ResultSet rs=(ResultSet)cs.getObject(2);
			rs.next();
			vo.setNo(rs.getInt(1));
			vo.setName(rs.getString(2));
			vo.setSubject(rs.getString(3));
			vo.setContent(rs.getString(4));
			vo.setRegdate(rs.getDate(5));
			vo.setHit(rs.getInt(6));
			vo.setFilename(rs.getString(7));
			vo.setFilesize(rs.getString(8));
			vo.setFilecount(rs.getInt(9));
			rs.close();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		return vo;
	}
	/*
	 * 	PL/SQL
	 * 		���ǹ�
	 * 		= IF(����) THEN
	 * 			ó��
	 * 		  END IF;	==>if(����) () *���� '='
	 * 		= IF (����) THEN
	 * 			����
	 * 		  ELSE
	 * 			����
	 * 		  END IF;	if~else
	 * 		= IF (����) THEN
	 * 			ó��
	 * 		  ELSIF (����) THEN
	 * 			ó��
	 * 		  ELSIF (����) THEN
	 * 			ó��
	 * 		  ELSE
	 * 			ó��
	 * 		  END IF;
	 * 
	 * 		FOR
	 * 			FOR ���� IN 1..9 LOOP => '1..9' 1���� 9����
	 * 				ó��
	 * 			END LOOP;
	 * 			(EXIT %NOT FOUND;) =>
	 */
	public DataBoardVO boardFileInfo(int no) {
		DataBoardVO vo=new DataBoardVO();
		try{
			getConnection();
			String sql="{CALL boardFileInfo(?,?,?}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.registerOutParameter(2, OracleTypes.INTEGER);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeQuery();
			vo.setFilecount(cs.getInt(2));
			vo.setFilename(cs.getString(3));
			
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		return vo;
	}
	
	public boolean boardDelete(int no,String pwd){
		boolean bCheck=false;
		try{
			getConnection();
			String sql="{CALL boardDelete(?,?,?)}";
			cs=conn.prepareCall(sql);
			cs.setInt(1, no);
			cs.setString(2, pwd);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.executeQuery();
			bCheck=Boolean.parseBoolean(cs.getString(3));
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}finally {
			disConnection();
		}
		return bCheck;
	}
	
	
	
	
	
	
}
