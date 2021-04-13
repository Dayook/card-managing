package org.edwith.webbe.cardmanager.dto;

import java.util.Date;

public class BusinessCard {
    private String name;
    private String phone;
    private String companyName;
    private Date createDate;
    
    // 생성자1. name, phone, companyName
    // 명함 입력 메소드에서 사용
    // 생성시간은 DB에서 입력되므로 사용자가 입력할 필요 없음
    public BusinessCard(String name, String phone, String companyName) {
        this.name = name;
        this.phone = phone;
        this.companyName = companyName;
    }
    
    // 생성자2. name, phone, companyName, createDate
    // 명함 검색 메소드에서 사용
    // DB에서 명함 불러올 때, 과거에 입력됐던 생성시간까지 함께 불러옴
    public BusinessCard(String name, String phone, String companyName, Date createDate) {
        this.name = name;
        this.phone = phone;
        this.companyName = companyName;
        this.createDate = createDate;
    }




	public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "BusinessCard{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", companyName='" + companyName + '\'' +
                ", createDate=" + createDate +
                '}';
    }
}
