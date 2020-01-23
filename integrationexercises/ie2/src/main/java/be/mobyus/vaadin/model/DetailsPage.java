package be.mobyus.vaadin.model;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.Image;
import com.vaadin.v7.ui.VerticalLayout;

import java.util.concurrent.atomic.AtomicInteger;

public class DetailsPage extends Template {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        int orderId = Integer.parseInt(event.getParameters());
    }

    @Override
    protected Component getBody() {
        VerticalLayout verticalLayout = new VerticalLayout();
        Image image = new Image("Router CatZ", new ThemeResource("routercat.jpg"));
        verticalLayout.addComponent(image);
        verticalLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
        verticalLayout.setSizeFull();
        return verticalLayout;
    }
}
