package com.place.model;
import java.util.List;

public interface PlaceDAO_interface {
		public void insert(PlaceVO placeVO);
		public void update(PlaceVO placeVO);
		public void delete(String p_no);
		public PlaceVO findByPrimaryKey(String P_Place);
		public List<PlaceVO> getAll();
		public String insertplace(PlaceVO placeVO);
		//---------------------------------------------------
		public void k_insert(PlaceVO placeVO);
		public void k_update(PlaceVO placeVO);
		public void k_delete(String p_no);
		public PlaceVO k_findByPrimaryKey(String p_no);
		public List<PlaceVO> k_getAll();
		public void k_insertplace(PlaceVO placeVO);

}
