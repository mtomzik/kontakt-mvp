package io.kontakt.utils;

import io.kontakt.Model;
import io.kontakt.framework.CategoryService;
import io.kontakt.subcomponent.ListPresenter;

public class DragNDropElement {

	private static void move(final ListPresenter addTo, final ListPresenter removeFrom, final Object object) {
		removeFrom.removeElement(object);
		addTo.addElement(object);
	}

	private static void update(final boolean isLeft, final CategoryService service, final Model model,
			final Object object) {
		if (isLeft) {
			service.addToCategory(model.left(), object);
			service.removeFromCategory(model.right(), object);
		} else {
			service.addToCategory(model.right(), object);
			service.removeFromCategory(model.left(), object);
		}
	}

	public static void moveElement(final String category, final Object element, final Model model,
			final CategoryService service, final ListPresenter rightPresenter, final ListPresenter leftPresenter) {
		if (category.equals(model.left())) {
			move(leftPresenter, rightPresenter, element);
			update(true, service, model, element);
		} else if (category.equals(model.right())) {
			move(rightPresenter, leftPresenter, element);
			update(false, service, model, element);
		} else {
			throw new IllegalStateException("Wrong category name: " + category);
		}
	}

}
