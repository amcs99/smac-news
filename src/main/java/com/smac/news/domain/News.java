package com.smac.news.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A News.
 */
@Entity
@Table(name = "news")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class News implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tieu_de")
    private String tieuDe;

    @Column(name = "image_thumbnail")
    private String imageThumbnail;

    @Column(name = "thoi_gian_dang")
    private String thoiGianDang;

    @Size(max = 1000)
    @Column(name = "noi_dung_tom_tat", length = 1000)
    private String noiDungTomTat;

    @Size(max = 10000)
    @Column(name = "noi_dung_day_du", length = 10000)
    private String noiDungDayDu;

    @Column(name = "tac_gia")
    private String tacGia;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public News tieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
        return this;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public News imageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
        return this;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public String getThoiGianDang() {
        return thoiGianDang;
    }

    public News thoiGianDang(String thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
        return this;
    }

    public void setThoiGianDang(String thoiGianDang) {
        this.thoiGianDang = thoiGianDang;
    }

    public String getNoiDungTomTat() {
        return noiDungTomTat;
    }

    public News noiDungTomTat(String noiDungTomTat) {
        this.noiDungTomTat = noiDungTomTat;
        return this;
    }

    public void setNoiDungTomTat(String noiDungTomTat) {
        this.noiDungTomTat = noiDungTomTat;
    }

    public String getNoiDungDayDu() {
        return noiDungDayDu;
    }

    public News noiDungDayDu(String noiDungDayDu) {
        this.noiDungDayDu = noiDungDayDu;
        return this;
    }

    public void setNoiDungDayDu(String noiDungDayDu) {
        this.noiDungDayDu = noiDungDayDu;
    }

    public String getTacGia() {
        return tacGia;
    }

    public News tacGia(String tacGia) {
        this.tacGia = tacGia;
        return this;
    }

    public void setTacGia(String tacGia) {
        this.tacGia = tacGia;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof News)) {
            return false;
        }
        return id != null && id.equals(((News) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "News{" +
            "id=" + getId() +
            ", tieuDe='" + getTieuDe() + "'" +
            ", imageThumbnail='" + getImageThumbnail() + "'" +
            ", thoiGianDang='" + getThoiGianDang() + "'" +
            ", noiDungTomTat='" + getNoiDungTomTat() + "'" +
            ", noiDungDayDu='" + getNoiDungDayDu() + "'" +
            ", tacGia='" + getTacGia() + "'" +
            "}";
    }
}
