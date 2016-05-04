import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.Test;

import io.kontakt.Presenter;
import io.kontakt.View;
import io.kontakt.framework.DragDropEvent;
import io.kontakt.subcomponent.ListObject;
import io.kontakt.subcomponent.ListPresenter;
import io.kontakt.subcomponent.ListView;

public class MVPTest {

	public ListObject randomObject(final List<ListObject> list) {
		final Random random = new Random();
		final int index = random.nextInt(list.size());
		return list.get(index);
	}

	@Test
	public void testRenderLists() throws Exception {

		final View mainView = new View();
		final ListPresenter leftListPresenter = new ListPresenter();
		final ListView leftListView = new ListView();
		final ArrayList<ListObject> leftListElements = new ArrayList<>();
		leftListView.setListElements(leftListElements);

		final ListPresenter rightListPresenter = new ListPresenter();
		final ListView rightListView = new ListView();
		final ArrayList<ListObject> rightListElements = new ArrayList<>();
		rightListView.setListElements(rightListElements);

		final Presenter presenter = new Presenter.Builder().view(mainView).leftListPresenter(leftListPresenter)
				.rightListPresenter(rightListPresenter).leftListView(leftListView).rightListView(rightListView).build();
		presenter.initializePresenter();
		presenter.start();

		assertTrue(rightListElements.size() > 0);
		assertTrue(leftListElements.size() > 0);
	}

	@Test
	public void testMoveFromLeftToRight() throws Exception {

		final View mainView = new View();
		final ListPresenter leftListPresenter = new ListPresenter();
		final ListView leftListView = new ListView();
		final ArrayList<ListObject> leftListElements = new ArrayList<>();
		leftListView.setListElements(leftListElements);

		final ListPresenter rightListPresenter = new ListPresenter();
		final ListView rightListView = new ListView();
		final ArrayList<ListObject> rightListElements = new ArrayList<>();
		rightListView.setListElements(rightListElements);

		final Presenter presenter = new Presenter.Builder().view(mainView).leftListPresenter(leftListPresenter)
				.rightListPresenter(rightListPresenter).leftListView(leftListView).rightListView(rightListView).build();
		presenter.initializePresenter();
		presenter.start();

		int leftElementsBefore = leftListElements.size();
		int rightElementsBefore = rightListElements.size();

		final DragDropEvent event = new DragDropEvent();
		final ListObject draggedObject = new ListObject(randomObject(leftListElements).object());
		draggedObject.onDragStart(event);
		rightListPresenter.onDrop(event);

		assertEquals(leftListElements.size(), leftElementsBefore - 1);
		assertEquals(rightListElements.size(), rightElementsBefore + 1);

		assertFalse(leftListElements.stream().anyMatch(element -> element.equals(draggedObject)));
		assertTrue(rightListElements.stream().anyMatch(element -> element.equals(draggedObject)));
	}
}
