package io.kontakt;

import io.kontakt.framework.Activity;
import io.kontakt.framework.CategoryService;
import io.kontakt.subcomponent.ListPresenter;
import io.kontakt.subcomponent.ListView;
import io.kontakt.utils.DragNDropElement;

public class Presenter implements io.kontakt.framework.Presenter, Activity {

	private static final CategoryService CATEGORY_SERVICE = CategoryService.INSTANCE;

	private View view;
	private Model model;

	private ListView leftListView;
	private ListPresenter leftListPresenter;

	private ListView rightListView;
	private ListPresenter rightListPresenter;

	public Presenter(final View view, final ListView leftView, final ListView rightView,
			final ListPresenter rightPresenter, final ListPresenter leftPresenter) {
		this.view = view;
		this.leftListView = leftView;
		this.rightListView = rightView;
		this.leftListPresenter = leftPresenter;
		this.rightListPresenter = rightPresenter;
		this.model = new Model(CATEGORY_SERVICE.createCategory("left"), CATEGORY_SERVICE.createCategory("right"));
		leftListPresenter.setView(leftListView);
		leftPresenter.setPresenter(this);
		leftListPresenter.initalizeModel();
		rightListPresenter.setView(rightListView);
		rightPresenter.setPresenter(this);
		rightListPresenter.initalizeModel();
	}

	public Model model() {
		return this.model;
	}

	public ListView leftListView() {
		return this.leftListView;
	}

	public ListView rightListView() {
		return this.rightListView;
	}

	public ListPresenter leftListPresenter() {
		return this.leftListPresenter;
	}

	public ListPresenter rightListPresenter() {
		return this.rightListPresenter;
	}

	@Override
	public void start() {
		leftListPresenter.show(model.left());
		rightListPresenter.show(model.right());

	}

	@Override
	public io.kontakt.framework.View view() {
		return this.view;
	}

	public void move(final String category, final Object object) {
		DragNDropElement.moveElement(category, object, model, CATEGORY_SERVICE, rightListPresenter, leftListPresenter);
	}

}
