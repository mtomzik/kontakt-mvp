package io.kontakt.subcomponent;

import java.util.List;

import io.kontakt.framework.View;

public class ListView implements View {

	private List<ListObject> listElements;

	public void addElement(final Object element) {
		final ListObject listElement = new ListObject(element);
		listElements.add(listElement);
	}

	public void removeElement(final Object element) {
		listElements.removeIf(object -> equals(element));
	}

	public void setListElements(final List<ListObject> listElements) {
		this.listElements = listElements;
	}
	
	public List<ListObject> listElements() {
		return this.listElements;
	}
}
