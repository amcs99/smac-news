package com.smac.news.common.base;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;


@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseRepoBusiness<Model, DTO> {

	protected BaseMapper<Model, DTO> mapper;
	protected String modelPackage = "com.smac.news.domain.";
	protected String dtoPackage = "com.smac.news.service.dto.";
	
	
	
	@PostConstruct
	private void init() throws ClassNotFoundException {
		String modelName = StringUtils.substringBeforeLast(this.getClass().getSimpleName(), "BusinessImpl");
		Class modelClass = Class.forName(this.modelPackage + modelName);
		Class dtoClass = Class.forName(this.dtoPackage + modelName + "DTO");
		this.mapper = new BaseMapper(modelClass, dtoClass);
	}
	
}
