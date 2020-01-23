package be.ucll;

import com.vaadin.navigator.View;
import com.vaadin.server.Sizeable;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.VerticalLayout;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Ex10Template implements View {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public Component getViewComponent() {

        GridLayout gridLayout = new GridLayout();
        // Color to white
        gridLayout.setRows(3);
        gridLayout.setColumns(1);
        gridLayout.setRowExpandRatio(1, 1F);
        gridLayout.setSizeFull();

        Label welcome = new Label("Welcome to my website!");
        welcome.setSizeUndefined();
        Label curdatetime = new Label("Date and time currently are:" + simpleDateFormat.format(new Date()));
        curdatetime.setSizeUndefined();

        VerticalLayout headerLines = new VerticalLayout(welcome, curdatetime);
        HorizontalLayout header = new HorizontalLayout();
        header.addComponent(headerLines);
        headerLines.setComponentAlignment(welcome, Alignment.TOP_CENTER);
        headerLines.setComponentAlignment(curdatetime, Alignment.TOP_CENTER);
        header.setWidth(100, Sizeable.Unit.PERCENTAGE);

        Label label = new Label("(c) Koen Serneels");
        label.setSizeUndefined();
        HorizontalLayout footer = new HorizontalLayout(label);
        footer.setComponentAlignment(label, Alignment.TOP_CENTER);
        footer.setWidth(100, Sizeable.Unit.PERCENTAGE);

        gridLayout.addComponent(header, 0, 0);
        gridLayout.addComponent(getBody(), 0, 1);
        gridLayout.addComponent(footer, 0, 2);

        return gridLayout;
    }

    protected abstract Component getBody();
}
