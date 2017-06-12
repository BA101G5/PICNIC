package com.checklist.model;
import java.util.List;

public interface Checklist_interface {
		public void insert(ChecklistVO checklistVO);
		public void update(ChecklistVO checklistVO);
		public void delete(String chli_no);
		public ChecklistVO findByPrimaryKey(String chli_no);
		public List<ChecklistVO> getAll();

}
