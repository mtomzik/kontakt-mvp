import java.util.ArrayList;

import org.testng.annotations.Test;

import io.kontakt.subcomponent.ListObject;
import io.kontakt.subcomponent.ListPresenter;
import io.kontakt.subcomponent.ListView;

import static org.testng.Assert.*;

public class ListMVPTest {

	@Test
	public void test() {
		ListView view = new ListView();
		ListPresenter presenter = new ListPresenter();
		ArrayList<ListObject> list = new ArrayList<>();
		view.setListElements(list);
		presenter.setView(view);
	}

}
