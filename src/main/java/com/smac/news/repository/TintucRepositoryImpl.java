package com.smac.news.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.smac.news.domain.Tintuc;

public class TintucRepositoryImpl implements TinTucRepositoryCustom{

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public Long getCount() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT count(*) FROM tintuc tt WHERE tt.image_thumbnail like 'http%'");

		Query query = em.createNativeQuery(sql.toString());

		Long result = Long.parseLong(String.valueOf(query.getSingleResult()));
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tintuc> getListTinTuc(int start, int length) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tt FROM Tintuc tt WHERE tt.imageThumbnail like 'http%'");

		Query query = em.createQuery(sql.toString());
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tintuc> getTinTucById(Long id) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tt FROM Tintuc tt WHERE tt.id=:id");

		Query query = em.createQuery(sql.toString());
		query.setParameter("id", id);
		return query.getResultList();
	}
	
	

	
}
