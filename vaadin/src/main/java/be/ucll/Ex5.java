package be.ucll;


import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.Label;

public class Ex5 implements View {

    private int counter = 0;

    @Override
    public Component getViewComponent() {

        GridLayout gridLayout = new GridLayout();

        gridLayout.setColumns(2);
        gridLayout.setSpacing(true);

        final Button b1 = new Button("Click me");
        final Label l1 = new Label();
        gridLayout.addComponents(b1, l1);

        b1.addClickListener((Button.ClickListener) event -> l1.setValue("" + ++counter));

        final Button b2 = new Button("Click me");
        final Label l2 = new Label();
        gridLayout.addComponents(b2, l2);

        b2.addClickListener((Button.ClickListener) event -> l2.setValue("" + ++counter));

        final Button b3 = new Button("Click me");
        final Label l3 = new Label();
        gridLayout.addComponents(b3, l3);

        b3.addClickListener((Button.ClickListener) event -> l3.setValue("" + ++counter));

        gridLayout.addComponents(new Label(""), new Label(""));

        final Button b4 = new Button("Update all");
        gridLayout.addComponent(b4);

        b4.addClickListener((Button.ClickListener) event -> {
            l1.setValue("" + counter);
            l2.setValue("" + counter);
            l3.setValue("" + counter);
        });

        return gridLayout;
    }
}
