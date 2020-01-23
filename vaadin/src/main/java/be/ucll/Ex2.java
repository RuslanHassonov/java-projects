package be.ucll;

import com.vaadin.annotations.Widgetset;
import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.v7.data.Validator;
import com.vaadin.v7.data.Validator.InvalidValueException;
import com.vaadin.v7.data.util.converter.StringToIntegerConverter;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

public class Ex2 implements View {

	@Override
	public Component getViewComponent() {
		HorizontalLayout horizontalLayout = new HorizontalLayout();
		horizontalLayout.setSpacing(true);

		final Button button = new Button("Click me");
		final TextField textField = new TextField();
		textField.setNullRepresentation("");
		textField.setValidationVisible(true);
		textField.setConverter(new StringToIntegerConverter());
		textField.addValidator((Validator) value -> {
			if (textField.getConvertedValue() != null) {
				if ((Integer) textField.getConvertedValue() > 3)
					throw new InvalidValueException("invalid");
			}
		});
		textField.setImmediate(true);

		final Label label = new Label();

		button.addClickListener((Button.ClickListener) event -> {
			if (textField.isValid()) {
				label.setValue(textField.getValue());
			} else {
				label.setValue("");
			}
		});

		horizontalLayout.addComponent(button);
		horizontalLayout.addComponent(textField);
		horizontalLayout.addComponent(label);

		return horizontalLayout;
	}
}
