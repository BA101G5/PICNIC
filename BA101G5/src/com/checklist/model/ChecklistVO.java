package com.checklist.model;
import java.sql.Timestamp;
public class ChecklistVO implements java.io.Serializable {
	private String chli_no;
	private Integer chli_cate;
	private String chli_be_num;
	private String chli_memno;
	private Timestamp chli_start;
	private Timestamp chli_end;
	private Integer chli_day;
	private Integer chli_pun;
	private String chli_reason;
	private Timestamp chli_date;
	private String chli_sta;
	public String getChli_no() {
		return chli_no;
	}
	public void setChli_no(String chli_no) {
		this.chli_no = chli_no;
	}
	public Integer getChli_cate() {
		return chli_cate;
	}
	public void setChli_cate(Integer chli_cate) {
		this.chli_cate = chli_cate;
	}
	public String getChli_be_num() {
		return chli_be_num;
	}
	public void setChli_be_num(String chli_be_num) {
		this.chli_be_num = chli_be_num;
	}
	public String getChli_memno() {
		return chli_memno;
	}
	public void setChli_memno(String chli_memno) {
		this.chli_memno = chli_memno;
	}
	public Timestamp getChli_start() {
		return chli_start;
	}
	public void setChli_start(Timestamp chli_start) {
		this.chli_start = chli_start;
	}
	public Timestamp getChli_end() {
		return chli_end;
	}
	public void setChli_end(Timestamp chli_end) {
		this.chli_end = chli_end;
	}
	public Integer getChli_day() {
		return chli_day;
	}
	public void setChli_day(Integer chli_day) {
		this.chli_day = chli_day;
	}
	public Integer getChli_pun() {
		return chli_pun;
	}
	public void setChli_pun(Integer chli_pun) {
		this.chli_pun = chli_pun;
	}
	public String getChli_reason() {
		return chli_reason;
	}
	public void setChli_reason(String chli_reason) {
		this.chli_reason = chli_reason;
	}
	public Timestamp getChli_date() {
		return chli_date;
	}
	public void setChli_date(Timestamp chli_date) {
		this.chli_date = chli_date;
	}
	public String getChli_sta() {
		return chli_sta;
	}
	public void setChli_sta(String chli_sta) {
		this.chli_sta = chli_sta;
	}
}
