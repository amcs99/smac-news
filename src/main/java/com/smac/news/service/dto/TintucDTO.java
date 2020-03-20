package com.smac.news.service.dto;

import java.io.Serializable;

public class TintucDTO implements Serializable {

	private Long id;

	private String tieuDe;

	private String imageThumbnail;

	private String thoiGianDang;

	private String noiDungTomTat;

	private String noiDungDayDu;

	private String tacGia;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getImageThumbnail() {
		return imageThumbnail;
	}

	public void setImageThumbnail(String imageThumbnail) {
		this.imageThumbnail = imageThumbnail;
	}

	public String getThoiGianDang() {
		return thoiGianDang;
	}

	public void setThoiGianDang(String thoiGianDang) {
		this.thoiGianDang = thoiGianDang;
	}

	public String getNoiDungTomTat() {
		return noiDungTomTat;
	}

	public void setNoiDungTomTat(String noiDungTomTat) {
		this.noiDungTomTat = noiDungTomTat;
	}

	public String getNoiDungDayDu() {
		return noiDungDayDu;
	}

	public void setNoiDungDayDu(String noiDungDayDu) {
		this.noiDungDayDu = noiDungDayDu;
	}

	public String getTacGia() {
		return tacGia;
	}

	public void setTacGia(String tacGia) {
		this.tacGia = tacGia;
	}

}
