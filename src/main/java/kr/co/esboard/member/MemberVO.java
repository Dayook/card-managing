package kr.co.esboard.member;

import java.sql.Date;

public class MemberVO {
	
	private String id;
	private String pwd;
	private String nickName;
	private String email;
	private Date joinDate;
	
	public MemberVO(String id, String pwd, String nickName, String email, Date joinDate) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.nickName = nickName;
		this.email = email;
		this.joinDate = joinDate;
	}
	
	

	public MemberVO(String id, String pwd, String nickName, String email) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.nickName = nickName;
		this.email = email;
	}



	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickname) {
		this.nickName = nickname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	
	
	

}
