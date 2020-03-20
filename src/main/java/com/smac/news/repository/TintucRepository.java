package com.smac.news.repository;

import com.smac.news.domain.Tintuc;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Tintuc entity.
 */
@Repository
public interface TintucRepository extends JpaRepository<Tintuc, Long>, TinTucRepositoryCustom {

}
