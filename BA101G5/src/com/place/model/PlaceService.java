package com.place.model;

import java.util.List;

import net.sf.json.JSONObject;

public class PlaceService {
	private PlaceDAO dao = null;

	public PlaceService() {
		dao = new PlaceDAO();
	}

	public PlaceVO addPlace() {
		return null;
	}

	public PlaceVO updatePlace() {
		return null;
	}

	public void deletePlace() {
	}

	public PlaceVO getOne() {
		return null;
	}

	public List<PlaceVO> getAll() {
		return null;
	}

	public void insertplace(String mem_no, String p_place, String picnic_no) {
		JSONObject lonlat = null;
		System.out.println(p_place);
		System.out.println("Hello");
		try {
			lonlat = com.util.encoding.Coordinate.getCoordinate(p_place);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String lat = ((JSONObject) lonlat.getJSONArray("results").get(0)).getJSONObject("geometry")
				.getJSONObject("location").get("lat").toString();
		String lon = ((JSONObject) lonlat.getJSONArray("results").get(0)).getJSONObject("geometry")
				.getJSONObject("location").get("lng").toString();

		PlaceVO placeVO = new PlaceVO();
		placeVO.setMem_no(mem_no);
		placeVO.setP_place(p_place);
		placeVO.setPicnic_no(picnic_no);
		placeVO.setP_lat(Double.valueOf(lat));
		placeVO.setP_lon(Double.valueOf(lon));
		
		dao.insertplace(placeVO);
	}
}
