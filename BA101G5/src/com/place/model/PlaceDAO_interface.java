package com.place.model;
import java.util.List;

public interface PlaceDAO_interface {
		public void insert(PlaceVO placeVO);
		public void update(PlaceVO placeVO);
		public void delete(String p_no);
		public PlaceVO findByPrimaryKey(String P_Place);
		public List<PlaceVO> getAll();
		public String insertplace(PlaceVO placeVO);

}
