package com.smac.news.common.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import ma.glasnost.orika.DefaultFieldMapper;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.Builder;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BaseMapper<Model, DTO> {
	
	private static final MapperFactory mapperFactory = (new Builder()).build();
	private MapperFacade mapper;
	private Class<Model> model;
	private Class<DTO> dto;

	public BaseMapper(Class<Model> model, Class<DTO> dto) {
		mapperFactory.classMap(model, dto).constructorA(new String[0]).constructorB(new String[0])
				.byDefault(new DefaultFieldMapper[0]).register();
		this.mapper = mapperFactory.getMapperFacade();
		this.dto = dto;
		this.model = model;
	}

	public BaseMapper() {
		this.mapper = mapperFactory.getMapperFacade();
	}

	public DTO toDtoBean(Model model) {
		return this.mapper.map(model, this.dto);
	}

	public Model toPersistenceBean(DTO dtoBean) {
		return this.mapper.map(dtoBean, this.model);
	}

	public List<DTO> toDtoBean(Iterable<Model> models) {
		List<DTO> dtoBeans = new ArrayList<>();
		if (models == null) {
			return dtoBeans;
		} else {
			Iterator var3 = models.iterator();

			while (var3.hasNext()) {
				Model model = (Model) var3.next();
				dtoBeans.add(this.toDtoBean(model));
			}

			return dtoBeans;
		}
	}

	public List<DTO> toDtoBean(List<Model> models) {
		List<DTO> dtoBeans = new ArrayList<>();
		if (models == null) {
			return dtoBeans;
		} else {
			Iterator var3 = models.iterator();

			while (var3.hasNext()) {
				Model model = (Model) var3.next();
				dtoBeans.add(this.toDtoBean(model));
			}

			return dtoBeans;
		}
	}

	public List<Model> toPersistenceBean(List<DTO> dtoBeans) {
		List<Model> models = new ArrayList<>();
		if (dtoBeans != null && !dtoBeans.isEmpty()) {
			Iterator var3 = dtoBeans.iterator();

			while (var3.hasNext()) {
				DTO dtoBean = (DTO) var3.next();
				models.add(this.toPersistenceBean(dtoBean));
			}

			return models;
		} else {
			return models;
		}
	}
}