package com.checklist.model;

import java.util.List;

public interface ChecklistDAO_interface {
	public void k_insert(ChecklistVO checklistVO);
	public void k_update(ChecklistVO checklistVO);
	public void k_delete(String chli_no);
	public ChecklistVO k_findByPrimaryKey(String chli_no);
	public List<ChecklistVO> k_getAll();
}
