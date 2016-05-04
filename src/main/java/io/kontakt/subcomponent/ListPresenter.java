package io.kontakt.subcomponent;

import io.kontakt.framework.DragDropEvent;
import io.kontakt.framework.DropTarget;
import io.kontakt.framework.Presenter;
import io.kontakt.framework.View;

public class ListPresenter implements Presenter, DropTarget {

	private ListView listView;
	private ListModel listModel;
	private io.kontakt.Presenter presenter;

	public void initalizeModel() {
		this.listModel = new ListModel();
	}

	public void addElement(final Object element) {
		listView.addElement(element);
	}

	@Override
	public View view() {
		return this.listView;
	}

	public void setView(final ListView listView) {
		this.listView = listView;
	}

	public void setPresenter(final io.kontakt.Presenter presenter) {
		this.presenter = presenter;
	}

	public void removeElement(final Object element) {
		listView.removeElement(element);
	}

	public void show(final String category) {
		listModel.model(category);
		listModel.list().stream().forEach(element -> {
			final ListObject object = new ListObject(element);
			listView.addElement(object);
		});
	}

	@Override
	public void onDrop(final DragDropEvent event) {
		final Object object = event.getData();
		presenter.move(listModel.category(), object);
	}
}
