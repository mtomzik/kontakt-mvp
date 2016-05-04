package io.kontakt;

import io.kontakt.framework.Activity;
import io.kontakt.framework.CategoryService;
import io.kontakt.subcomponent.ListPresenter;
import io.kontakt.subcomponent.ListView;
import io.kontakt.utils.DragNDropElement;

public class Presenter implements io.kontakt.framework.Presenter, Activity {

	public static class Builder {

		protected View view;

		protected ListView leftListView;
		protected ListPresenter leftListPresenter;

		protected ListView rightListView;
		protected ListPresenter rightListPresenter;

		public Builder() {
			// empty
		}

		public Builder view(final View view) {
			this.view = view;
			return this;
		}

		public Builder rightListView(final ListView rightListView) {
			this.rightListView = rightListView;
			return this;
		}

		public Builder leftListView(final ListView leftListView) {
			this.leftListView = leftListView;
			return this;
		}

		public Builder leftListPresenter(final ListPresenter leftListPresenter) {
			this.leftListPresenter = leftListPresenter;
			return this;
		}

		public Builder rightListPresenter(final ListPresenter rightListPresenter) {
			this.rightListPresenter = rightListPresenter;
			return this;
		}

		public Presenter build() {
			return new Presenter(this);
		}
	}

	private static final CategoryService CATEGORY_SERVICE = CategoryService.INSTANCE;

	private View view;
	private Model model;

	private ListView leftListView;
	private ListPresenter leftListPresenter;

	private ListView rightListView;
	private ListPresenter rightListPresenter;

	private Presenter(final Builder builder) {
		this.view = builder.view;
		this.leftListView = builder.leftListView;
		this.rightListView = builder.rightListView;
		this.leftListPresenter = builder.leftListPresenter;
		this.rightListPresenter = builder.rightListPresenter;
		this.model = new Model(CATEGORY_SERVICE.createCategory("left"), CATEGORY_SERVICE.createCategory("left"));
	}

	public void initializePresenter() {
		leftListPresenter.setView(leftListView);
		leftListPresenter.setPresenter(this);
		leftListPresenter.initalizeModel();
		rightListPresenter.setView(rightListView);
		rightListPresenter.setPresenter(this);
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
