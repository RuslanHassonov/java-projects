package be.ucll;

import java.util.Collection;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;

public class Ex9 implements View {

	@Override
	public Component getViewComponent() {

		GridLayout gridLayout = new GridLayout();
		gridLayout.setSizeFull();
		gridLayout.setMargin(true);
		gridLayout.setSpacing(true);
		gridLayout.setRows(2);
		gridLayout.setColumns(1);

		MenuBar menuBar = new MenuBar();
		gridLayout.addComponent(menuBar);

		menuBar.addItem("View1", c -> {
			UI.getCurrent().getNavigator().navigateTo("ex9view1");
		});
		menuBar.addItem("View2", c -> {
			UI.getCurrent().getNavigator().navigateTo("ex9view2");
		});

		MenuBar.MenuItem misc = menuBar.addItem("Misc", null);

		misc.addItem("Notification", c -> {
			Notification.show("Action " + c.getText(), Notification.Type.ERROR_MESSAGE);
		});

		misc.addItem("Toggle", toggle -> {
			toggle(toggle, misc, menuBar.getItems());
		});

		gridLayout.setRowExpandRatio(1, 1F);
		return gridLayout;
	}

	private void toggle(MenuBar.MenuItem toggle, MenuBar.MenuItem misc, Collection<MenuBar.MenuItem> menuItems) {
		for (MenuBar.MenuItem item : menuItems) {

			//We do not want to toggle 'Misc' and 'Toggle' menu items
			if (item.getId() != toggle.getId() && item.getId() != misc.getId()) {
				item.setEnabled(!item.isEnabled());
			}

			//Recurse for potential children
			if (item.getChildren() != null) {
				toggle(toggle, misc, item.getChildren());
			}
		}
	}
}
