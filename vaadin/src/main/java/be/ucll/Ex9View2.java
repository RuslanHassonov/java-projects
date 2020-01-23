package be.ucll;


import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;

public class Ex9View2 implements View {

    @Override
    public Component getViewComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setSizeFull();
        verticalLayout.setMargin(true);
        verticalLayout.setSpacing(true);
        Label l = new Label("View2");
        verticalLayout.addComponent(l);

        return verticalLayout;
    }

}
