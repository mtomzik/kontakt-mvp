package io.kontakt.subcomponent;

import io.kontakt.framework.CategoryService;

import java.util.List;

public class ListModel {

	private String category;

	private List<Object> list;

	public void model(final String category) {
		this.category = category;
		this.list = CategoryService.INSTANCE.getCategoryObjects(category);
	}

	public String category() {
		return this.category;
	}

	public List<Object> list() {
		return this.list;
	}

}
