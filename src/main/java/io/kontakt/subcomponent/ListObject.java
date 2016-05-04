package io.kontakt.subcomponent;

import io.kontakt.framework.DragDropEvent;
import io.kontakt.framework.Draggable;

public class ListObject implements Draggable {

	private final Object object;

	public ListObject(final Object object) {
		this.object = object;
	}

	public Object object() {
		return object;
	}

	@Override
	public void onDragStart(final DragDropEvent event) {
		event.setData(object);
	}
}
