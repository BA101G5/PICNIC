package com.announcement.model;

import java.util.List;

public class AnnouncementService {

	private AnnouncementDAO_interface dao;

	public AnnouncementService() {
		dao = new AnnouncementDAO();
	}

	public AnnouncementVO addAnnouncement(String ann_text) {

		AnnouncementVO announcementVO = new AnnouncementVO();

		announcementVO.setAnn_text(ann_text);
		dao.insert(announcementVO);

		return announcementVO;
	}

	public AnnouncementVO updateAnnouncement(String ann_no, String ann_text) {

		AnnouncementVO announcementVO = new AnnouncementVO();

		announcementVO.setAnn_no(ann_no);
		announcementVO.setAnn_text(ann_text);
		dao.update(announcementVO);

		return announcementVO;
	}

	public void deleteAnnouncement(String ann_no) {
		dao.delete(ann_no);
	}

	public AnnouncementVO getOneAnnouncement(String ann_no) {
		return dao.findByPrimaryKey(ann_no);
	}

	public List<AnnouncementVO> getAll() {
		return dao.getAll();
	}
}

