import java.util.ArrayList;

import org.testng.annotations.Test;

import io.kontakt.Presenter;
import io.kontakt.View;
import io.kontakt.framework.DragDropEvent;
import io.kontakt.subcomponent.ListObject;
import io.kontakt.subcomponent.ListPresenter;
import io.kontakt.subcomponent.ListView;

import static org.testng.Assert.*;

public class MVPTest {
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
		final ListObject listElement = leftListElements.get(0);
		final ListObject draggedObject = new ListObject(listElement.object());
		listElement.onDragStart(event);
		rightListPresenter.onDrop(event);

		System.out.println(leftListElements.size() + " -> " + leftElementsBefore);
		System.out.println(rightListElements.size() + " -> " + rightElementsBefore);

		assertEquals(leftListElements.size(), leftElementsBefore + 1);
		// assertEquals(rightListElements.size(), rightElementsBefore);

		assertFalse(leftListElements.stream().anyMatch(element -> element.equals(draggedObject)));
		// assertTrue(rightListElements.stream().anyMatch(element ->
		// element.equals(draggedObject)));
	}
}
