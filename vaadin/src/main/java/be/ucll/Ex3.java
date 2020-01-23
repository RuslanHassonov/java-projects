package be.ucll;


import com.vaadin.data.Binder;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.converter.StringToIntegerConverter;
import com.vaadin.navigator.View;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickListener;

public class Ex3 implements View {

	private Integer value;

	@Override
	public Component getViewComponent() {
	final Button button = new Button("Click me");
		final TextField textField = new TextField();

		Binder<Ex3> binder= new Binder();
		binder.forField(textField).withNullRepresentation("")
				.withConverter(new StringToIntegerConverter("Moet numerisch zijn"))
				.withValidator((value, context) -> {
					return ValidationResult.ok();
				}).bind(Ex3::getValue, Ex3::setValue);
		binder.setBean(this);

		final Label label = new Label();

		button.addClickListener((ClickListener) event -> {
			if (binder.isValid()) {
				label.setValue(""+getValue());
			} else {
				label.setValue("");
			}
		});

		HorizontalLayout horizontalLayout= new HorizontalLayout();
		horizontalLayout.addComponents(textField,label, button);
		return horizontalLayout;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
}