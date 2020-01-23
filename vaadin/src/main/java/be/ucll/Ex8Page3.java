package be.ucll;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.v7.data.util.ObjectProperty;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class Ex8Page3 implements View {

    private ObjectProperty<Integer> counterOp = new ObjectProperty<Integer>(null, Integer.class);

    @Override
    public Component getViewComponent() {
        VerticalLayout verticalLayout = new VerticalLayout();

        verticalLayout.setSpacing(true);

        final Label counter = new Label(counterOp);
        final Button prev = new Button("<< Previous");
        final Label page = new Label("page3");

        prev.addClickListener((Button.ClickListener) event -> UI.getCurrent().getNavigator().navigateTo("ex8page2"));
        verticalLayout.addComponent(counter);
        verticalLayout.addComponent(new HorizontalLayout(prev));
        verticalLayout.addComponent(page);

        return verticalLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        counterOp.setValue(VaadinSession.getCurrent().getAttribute(AtomicInteger.class).getAndIncrement());
    }
}