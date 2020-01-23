package be.ucll;


import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.data.util.ObjectProperty;
import com.vaadin.v7.data.validator.StringLengthValidator;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

public class Ex7 implements View {

    @Override
    public Component getViewComponent() {
        GridLayout gridLayout = new GridLayout();
        gridLayout.setSpacing(true);
        gridLayout.setColumns(1);
        gridLayout.setRows(2);

        ObjectProperty<String> property = new ObjectProperty<>(null, String.class);
        HorizontalLayout textFields = new HorizontalLayout();

        final TextField text1 = new TextField(property);
        text1.setNullRepresentation("");
        text1.addValidator(new StringLengthValidator("Entry should be 1 character", 1, 1, true));

        final TextField text2 = new TextField();
        text2.setNullRepresentation("");
        final TextField text3 = new TextField();
        text3.setNullRepresentation("");
        final Label label = new Label();
        textFields.addComponents(text1, text2, text3, label);

        HorizontalLayout buttons = new HorizontalLayout();

        final Button submit = new Button("Submit");
        submit.addClickListener((Button.ClickListener) event -> label.setValue(text1.getValue() + " " + text2.getValue() + " " + text3.getValue()));
        final Button clear = new Button("Clear");
        clear.addClickListener((Button.ClickListener) event -> {
            text1.setValue(null);
            text2.setValue(null);
            text3.setValue(null);
            label.setValue(null);
        });

        buttons.addComponents(submit, clear);

        gridLayout.addComponents(textFields, buttons);

        return gridLayout;
    }
}
