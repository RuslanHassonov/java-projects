package be.ucll;


import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.v7.ui.VerticalLayout;

public class Ex10View1 extends Ex10Template {

	@Override
	protected Component getBody() {
		VerticalLayout verticalLayout = new VerticalLayout();
		Image image = new Image("IE CatZ", new ThemeResource("haxorcat.jpg"));
		verticalLayout.addComponents(image);
		verticalLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		verticalLayout.setSizeFull();
		return verticalLayout;
	}
}
