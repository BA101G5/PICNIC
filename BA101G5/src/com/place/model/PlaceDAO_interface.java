package com.place.model;
import java.util.List;

public interface PlaceDAO_interface {
		public void insert(PlaceVO placeVO);
		public void update(PlaceVO placeVO);
		public void delete(String p_no);
		public PlaceVO findByPrimaryKey(String p_no);
		public List<PlaceVO> getAll();
		public void insertplace(PlaceVO placeVO);

}
