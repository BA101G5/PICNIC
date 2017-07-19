package com.general_member.model;

import java.io.Serializable;
import java.sql.Date;

public class GeneralMemberVO implements Serializable{
	private String MEM_NO;
	private String MEM_NAME;
	private Character MEM_GEN;
	private Date MEM_BIRTH;
	private String MEM_ADDR;
	private String MEM_MAIL;
	private String MEM_PSW;
	private String MEM_SELF;
	private byte[] MEM_PIC;
	private Integer MEM_COIN;
	private Character MEM_STA;
	private String MEM_PHONE;
	private Character MEM_PBOARD;
	
	
	public String getMEM_NO() {
		return MEM_NO;
	}
	public void setMEM_NO(String MEM_NO) {
		this.MEM_NO = MEM_NO;
	}
	public String getMEM_NAME() {
		return MEM_NAME;
	}
	public void setMEM_NAME(String MEM_NAME) {
		this.MEM_NAME = MEM_NAME;
	}
	public Character getMEM_GEN() {
		return MEM_GEN;
	}
	public void setMEM_GEN(Character MEM_GEN) {
		this.MEM_GEN = MEM_GEN;
	}
	public Date getMEM_BIRTH() {
		return MEM_BIRTH;
	}
	public void setMEM_BIRTH(Date MEM_BIRTH) {
		this.MEM_BIRTH = MEM_BIRTH;
	}
	public String getMEM_ADDR() {
		return MEM_ADDR;
	}
	public void setMEM_ADDR(String MEM_ADDR) {
		this.MEM_ADDR = MEM_ADDR;
	}
	public String getMEM_MAIL() {
		return MEM_MAIL;
	}
	public void setMEM_MAIL(String MEM_MAIL) {
		this.MEM_MAIL = MEM_MAIL;
	}
	public String getMEM_PSW() {
		return MEM_PSW;
	}
	public void setMEM_PSW(String MEM_PSW) {
		this.MEM_PSW = MEM_PSW;
	}
	public String getMEM_SELF() {
		return MEM_SELF;
	}
	public void setMEM_SELF(String MEM_SELF) {
		this.MEM_SELF = MEM_SELF;
	}
	public byte[] getMEM_PIC() {
		return MEM_PIC;
	}
	public void setMEM_PIC(byte[] MEM_PIC) {
		this.MEM_PIC = MEM_PIC;
	}
	public Integer getMEM_COIN() {
		return MEM_COIN;
	}
	public void setMEM_COIN(Integer MEM_COIN) {
		this.MEM_COIN = MEM_COIN;
	}
	public Character getMEM_STA() {
		return MEM_STA;
	}
	public void setMEM_STA(Character MEM_STA) {
		this.MEM_STA = MEM_STA;
	}
	public String getMEM_PHONE() {
		return MEM_PHONE;
	}
	public void setMEM_PHONE(String MEM_PHONE) {
		this.MEM_PHONE = MEM_PHONE;
	}
	public Character getMEM_PBOARD() {
		return MEM_PBOARD;
	}
	public void setMEM_PBOARD(Character MEM_PBOARD) {
		this.MEM_PBOARD = MEM_PBOARD;
	}
	
	
}
