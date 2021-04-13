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
    
	// DB�� ������ �˻��ϴ� �޼ҵ�
	public List<BusinessCard> searchBusinessCard(String keyword){
		
		// �˻� ����� ���� ����Ʈ ����
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
				// DB�� ����� �̸�, ����ó, ȸ��, �ð��� �ҷ���
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String company = rs.getString("company");
				Date createDate = rs.getDate("time");
				// �ҷ��� ������ ���� ���� ����
				BusinessCard businesscard = new BusinessCard(name, phone, company, createDate);
				// ��ȯ�� ����Ʈ�� �־���
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
	

	// DB�� ������ �߰��ϴ� �޼ҵ�
    public int addBusinessCard(BusinessCard businessCard){
    	int result = 0;
   
    	Connection conn = null;
    	PreparedStatement ps = null;
    			
    	try {
    		String sql = "INSERT INTO person VALUES(?,?,?,sysdate())";

    		Class.forName("com.mysql.cj.jdbc.Driver");
    		conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
    		ps = conn.prepareStatement(sql);
    		
    		// sql���� ?, ?, ?�� �� ������ ���ʷ� �������ֱ�
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
