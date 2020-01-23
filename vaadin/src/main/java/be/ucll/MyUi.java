package be.ucll;

import java.util.concurrent.atomic.AtomicInteger;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.Navigator;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinSession;
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

		installWarmup();
		installEx1TillEx7();
		installEx8();
		installEx9();
		installEx10();
		//installRpi();

		navigator.navigateTo("ex1");

		gridLayout.addComponents(menuBar, panel);
		setContent(gridLayout);
	}

	private void installWarmup() {
		navigator.addView("vaadinwarmup", VaadinWarmup.class);
		menuBar.addItem("Vaadin warmup", (selectedItem) -> navigator.navigateTo("vaadinwarmup"));
	}

	private void installEx1TillEx7() {
		navigator.addView("ex1", Ex1.class);
		menuBar.addItem("Ex1", (selectedItem) -> navigator.navigateTo("ex1"));
		navigator.addView("ex2", Ex2.class);
		menuBar.addItem("Ex2", (selectedItem) -> navigator.navigateTo("ex2"));
		navigator.addView("ex3", Ex3.class);
		menuBar.addItem("Ex3", (selectedItem) -> navigator.navigateTo("ex3"));
		navigator.addView("ex4", Ex4.class);
		menuBar.addItem("Ex4", (selectedItem) -> navigator.navigateTo("ex4"));
		navigator.addView("ex5", Ex5.class);
		menuBar.addItem("Ex5", (selectedItem) -> navigator.navigateTo("ex5"));
		navigator.addView("ex6", Ex6.class);
		menuBar.addItem("Ex6", (selectedItem) -> navigator.navigateTo("ex6"));
		navigator.addView("ex7", Ex7.class);
		menuBar.addItem("Ex7", (selectedItem) -> navigator.navigateTo("ex7"));
	}

	private void installEx8() {
		VaadinSession.getCurrent().setAttribute(AtomicInteger.class, new AtomicInteger());

		navigator.addView("ex8page1", new Ex8Page1());
		menuBar.addItem("Ex8", (selectedItem) -> navigator.navigateTo("ex8page1"));
		navigator.addView("ex8page2", new Ex8Page2());
		navigator.addView("ex8page3", new Ex8Page3());
	}

	private void installEx9() {
		navigator.addView("ex9", Ex9.class);
		menuBar.addItem("Ex9", (selectedItem) -> navigator.navigateTo("ex9"));

		navigator.addView("ex9view1", Ex9View1.class);
		navigator.addView("ex9view2", Ex9View2.class);
	}

	private void installEx10() {
		navigator.addView("ex10view1", Ex10View1.class);
		navigator.addView("ex10view2", Ex10View2.class);
		MenuBar.MenuItem item = menuBar.addItem("Ex10");
		item.addItem("View1", (selectedItem) -> navigator.navigateTo("ex10view1"));
		item.addItem("View2", (selectedItem) -> navigator.navigateTo("ex10view2"));
	}

	private void installRpi() {
		//TODO enable this once solution in place
		//navigator.addView("rpi", RpiClientView.class);
		menuBar.addItem("Raspberry", (selectedItem) -> navigator.navigateTo("rpi"));
	}
}

