package be.mobyus.vaadin.model;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;

@Widgetset("com.vaadin.v7.Vaadin7WidgetSet")
@Theme("mytheme")
public class MyUi extends UI {

	private MenuBar menuBar = new MenuBar();
	private Navigator navigator;

	{
		menuBar.setWidth(100.0f, Unit.PERCENTAGE);
	}

	@Override
	protected void init(VaadinRequest request) {
		Panel panel = new Panel();
		panel.setSizeFull();

		this.navigator = new Navigator(this, panel);

		GridLayout gridLayout = new GridLayout(1, 2);
		gridLayout.setSizeFull();
		gridLayout.setRowExpandRatio(1, 1F);

		installTemplate();

		navigator.navigateTo("LoginPage");

		gridLayout.addComponents(menuBar, panel);
		setContent(gridLayout);
	}

	private void installTemplate() {
		navigator.addView("SearchPage", SearchPage.class);
		navigator.addView("DetailsPage", DetailsPage.class);
		navigator.addView("LoginPage", LoginPage.class);
		MenuBar.MenuItem item = menuBar.addItem("Navigation");
		item.addItem("Search Page", (selectedItem) -> navigator.navigateTo("SearchPage"));
		item.addItem("Details Page", (selectedItem) -> navigator.navigateTo("DetailsPage"));
		item.addItem("Login Page", (selectedItem) -> navigator.navigateTo("LoginPage"));
	}
}

