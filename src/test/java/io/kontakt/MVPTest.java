package io.kontakt;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.kontakt.Presenter;
import io.kontakt.View;
import io.kontakt.framework.DragDropEvent;
import io.kontakt.subcomponent.ListObject;
import io.kontakt.subcomponent.ListPresenter;
import io.kontakt.subcomponent.ListView;

public class MVPTest {

	private static final View mainView = new View();
	private static final ListPresenter leftListPresenter = new ListPresenter();
	private static final ListView leftListView = new ListView();
	private static final ArrayList<ListObject> leftListElements = new ArrayList<>();

	private static final ListPresenter rightListPresenter = new ListPresenter();
	private static final ListView rightListView = new ListView();
	private static final ArrayList<ListObject> rightListElements = new ArrayList<>();

	private static final Presenter presenter = new Presenter.Builder().view(mainView)
			.leftListPresenter(leftListPresenter).rightListPresenter(rightListPresenter).leftListView(leftListView)
			.rightListView(rightListView).build();

	private ListObject randomObject(final List<ListObject> list) {
		final Random random = new Random();
		final int index = random.nextInt(list.size());
		return list.get(index);
	}

	@BeforeTest
	public void setUp() {
		rightListView.setListElements(rightListElements);
		leftListView.setListElements(leftListElements);
		presenter.initializePresenter();
		presenter.start();

	}

	@Test
	public void testRenderLists() throws Exception {
		assertTrue(rightListElements.size() > 0);
		assertTrue(leftListElements.size() > 0);
	}

	@Test
	public void testMoveFromLeftToRight() throws Exception {
		final int leftElementsBefore = leftListElements.size();
		final int rightElementsBefore = rightListElements.size();

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
