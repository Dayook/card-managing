package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/calling_card?allowPublicKeyRetrieval=true&useSSL=false";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
    
	// DB의 명함을 검색하는 메소드
	public List<BusinessCard> searchBusinessCard(String keyword){
		
		// 검색 결과를 담을 리스트 생성
		List<BusinessCard> list = new ArrayList<BusinessCard>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String sql = "SELECT name, phone, company, time FROM person WHERE name LIKE ? ORDER by time ASC";
			String sqlStr = "%" + keyword + "%";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			ps = conn.prepareStatement(sql);
			ps.setString(1, sqlStr);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				// DB에 저장된 이름, 연락처, 회사, 시간을 불러옴
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String company = rs.getString("company");
				Date createDate = rs.getDate("time");
				// 불러온 값들을 통해 명함 생성
				BusinessCard businesscard = new BusinessCard(name, phone, company, createDate);
				// 반환할 리스트에 넣어줌
				list.add(businesscard);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			ps.close();
			conn.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	
    	return list;
    }
	

	// DB에 명함을 추가하는 메소드
    public int addBusinessCard(BusinessCard businessCard){
    	int result = 0;
   
    	Connection conn = null;
    	PreparedStatement ps = null;
    			
    	try {
    		String sql = "INSERT INTO person VALUES(?,?,?,sysdate())";

    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    		ps = conn.prepareStatement(sql);
    		
    		// sql문의 ?, ?, ?에 들어갈 값들을 차례로 설정해주기
    		ps.setString(1, businessCard.getName());
    		ps.setString(2, businessCard.getPhone());
    		ps.setString(3, businessCard.getCompanyName());
    		
    		result = ps.executeUpdate();
    		
    	}catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
    	
    }
}
