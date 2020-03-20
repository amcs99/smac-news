package com.smac.news.repository;

import java.util.List;

import com.smac.news.domain.Tintuc;

public interface TinTucRepositoryCustom {

	/**
	 * get so luong tin tuc
	 * @return
	 */
	public Long getCount();
	
	/**
	 * lay danh sach tin tuc theo 
	 * @param start
	 * @param length
	 * @return
	 */
	public List<Tintuc> getListTinTuc(int start, int length);
	
	public List<Tintuc> getTinTucById(Long id);
}
