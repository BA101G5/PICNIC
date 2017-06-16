package com.announcement.model;
import java.util.List;

public interface AnnouncementDAO_interface {
	public void insert (AnnouncementVO announcementVO);
 	public void update (AnnouncementVO announcementVO);
 	public void delete (String ann_no);
 	public AnnouncementVO findByPrimaryKey(String ann_no);
 	public List<AnnouncementVO> getAll();
}
