package com.smac.news.business.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.smac.news.business.TintucBusiness;
import com.smac.news.common.base.BaseRepoBusiness;
import com.smac.news.common.utils.DataUtil;
import com.smac.news.domain.Tintuc;
import com.smac.news.repository.TintucRepository;
import com.smac.news.service.dto.TintucDTO;

@Component
public class TintucBusinessImpl extends BaseRepoBusiness<Tintuc, TintucDTO> implements TintucBusiness {

	@Autowired
	private TintucRepository tintucRepo;

	@Override
	public Long getCount() {
		return tintucRepo.getCount();
	}

	@Override
	public List<TintucDTO> getListTinTuc(int start, int length) {
		if (DataUtil.isNullObject(start)) {
			start = 0;
		}
		if (DataUtil.isNullObject(length)) {
			length = 10;
		}
		return mapper.toDtoBean(tintucRepo.getListTinTuc(start, length));
	}

	@Override
	public TintucDTO getTinTucById(int id) {
		if(DataUtil.isNullObject(id)) {
			id = 1;
		}
		List<Tintuc> list = tintucRepo.getTinTucById(Long.parseLong(String.valueOf(id)));
		if(!DataUtil.isNullOrEmpty(list)) {
			return mapper.toDtoBean(list.get(0));
		}
		return null;
	}
	
}
