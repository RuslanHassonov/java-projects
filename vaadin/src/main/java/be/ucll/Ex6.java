package be.ucll;

import com.vaadin.navigator.View;
import com.vaadin.ui.Component;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.Property;
import com.vaadin.v7.shared.ui.combobox.FilteringMode;
import com.vaadin.v7.ui.ComboBox;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class Ex6 implements View {

    private final List<String> options = new ArrayList<>();

    @Override
    public Component getViewComponent() {

        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setSpacing(true);

        options.add("Antwerpen");
        options.add("Aalst");

        final Label label = new Label();
        final ComboBox combo = new ComboBox("Select your municipality", options) {

            private String filterString;

            @Override
            protected List<?> getFilteredOptions() {
                if (isNotBlank(filterString) && filterString.length() >= 2) {
                    return super.getFilteredOptions();
                }
                return new ArrayList<>();
            }

            @Override
            public void changeVariables(Object source, Map<String, Object> variables) {
                super.changeVariables(source, variables);
                filterString = (String) variables.get("filter");
            }
        };
        combo.setInputPrompt("No country selected");
        combo.setFilteringMode(FilteringMode.CONTAINS);
        combo.setImmediate(true);
        combo.setNullSelectionAllowed(false);

        combo.addValueChangeListener((Property.ValueChangeListener) event -> {
            final String valueString = String.valueOf(event.getProperty().getValue());
            label.setValue(valueString);
        });

        horizontalLayout.addComponents(combo, label);

        return horizontalLayout;
    }
}
