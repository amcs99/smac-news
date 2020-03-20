package com.smac.news.business;

import java.util.List;

import com.smac.news.service.dto.TintucDTO;

public interface TintucBusiness {

	public Long getCount();
	
	public List<TintucDTO> getListTinTuc(int start, int length);
	
	public TintucDTO getTinTucById(int id);
	
}
