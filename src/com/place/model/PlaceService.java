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

	public PlaceVO getOne(String P_place) {
		return dao.findByPrimaryKey(P_place);
		}

	public List<PlaceVO> getAll() {
		return dao.getAll();
	}

	public String insertplace(String mem_no, String p_place, String picnic_no,Integer picnic_pl) {
		System.out.println("hello");
		JSONObject lonlat = null;
		try {
			lonlat = com.util.encoding.Coordinate.getCoordinate(p_place);
		} catch (Exception e) {
			e.printStackTrace();
		}

		String lat = ((JSONObject) lonlat.getJSONArray("results").get(0)).getJSONObject("geometry")
				.getJSONObject("location").get("lat").toString();
		String lon = ((JSONObject) lonlat.getJSONArray("results").get(0)).getJSONObject("geometry")
				.getJSONObject("location").get("lng").toString();
		System.out.println(lat);
		PlaceVO placeVO = new PlaceVO();
		placeVO.setMem_no(mem_no);
		placeVO.setP_place(p_place);
		placeVO.setPicnic_no(picnic_no);
		placeVO.setP_pop(picnic_pl);
		placeVO.setP_lat(Double.valueOf(lat));
		placeVO.setP_lon(Double.valueOf(lon));
		
		String p_no= dao.insertplace(placeVO);
		
		return p_no;
	}
	public String insertMFplace(String mem_no, String picnic_no,PlaceVO placeVO2,Integer picnic_pl) {
		PlaceVO placeVO = new PlaceVO();
		placeVO.setMem_no(mem_no);
		placeVO.setP_place(placeVO2.getP_place());
		placeVO.setPicnic_no(picnic_no);
		placeVO.setP_lat(placeVO2.getP_lat());
		placeVO.setP_lon(placeVO2.getP_lon());
		placeVO.setMf_no(placeVO2.getMf_no());
		System.out.println(placeVO.getMf_no());
		placeVO.setP_name(placeVO2.getP_name());
		placeVO.setP_sta("A");
		placeVO.setP_price(placeVO2.getP_price());
		placeVO.setP_pop(picnic_pl);
		String p_no = dao.insertone(placeVO);
		return p_no;
	}
	//--------------------------------------------------------------------------------------
	public PlaceVO k_getOne(String p_no) {
			return dao.k_findByPrimaryKey(p_no);
		}

}
