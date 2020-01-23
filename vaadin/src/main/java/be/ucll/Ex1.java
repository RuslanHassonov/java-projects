package be.ucll;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

public class Ex1 implements View {

	@Override
	public Component getViewComponent() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		Button button = new Button("Click me");
		Label label = new Label("Hello world!");
		TextField textField = new TextField("");
		label.setVisible(false);

		button.addClickListener((ClickListener) event -> label.setVisible(!label.isVisible()));

		horizontalLayout.addComponent(button);
		horizontalLayout.addComponent(label);

		horizontalLayout.setComponentAlignment(button, Alignment.BOTTOM_CENTER);

		return horizontalLayout;
	}
}
