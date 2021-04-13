package org.edwith.webbe.cardmanager.dao;

import org.edwith.webbe.cardmanager.dto.BusinessCard;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class BusinessCardManagerDao {
	private static String dburl = "jdbc:mysql://localhost:3306/";
	private static String dbUser = "connectuser";
	private static String dbpasswd = "connect123!@#";
    
	public List<BusinessCard> searchBusinessCard(String keyword){
		
		List<BusinessCard> list = new ArrayList<>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dburl, dbUser, dbpasswd);
			String sql = "SELECT name, phone, company, time FROM person WHERE name LIKE ? ORDER by time ASC";
			ps = conn.prepareStatement(sql);
			String sqlStr = "%" + str+ "%";
			ps.setString(1, sqlStr);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String company = rs.getString("company");
				String time = rs.getString("time");
				BusinessCard businesscard = new BusinessCard(name, phone, company, time);
				
				
			}
			
		}
    	
    	
    }

    public BusinessCard addBusinessCard(BusinessCard businessCard){
    	
    }
}
