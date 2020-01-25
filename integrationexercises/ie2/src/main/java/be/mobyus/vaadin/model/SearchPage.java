package be.mobyus.vaadin.model;

import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.v7.ui.TextField;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SearchPage extends Template {

    private List<Order> orderList;
	Grid<Order> grid;
	private OrderSearchCriteria orderSearchCriteria;

	@Override
    protected Component getBody() {
        VerticalLayout verticalLayout = new VerticalLayout();
        Label separator;

        verticalLayout.addComponent(new Label("Search Criteria"));
        separator = new Label("<hr />", ContentMode.HTML);
        separator.setWidth(100f, Sizeable.Unit.PERCENTAGE);
        verticalLayout.addComponent(separator);

        verticalLayout.addComponent(getSearchCriteriaLayout());

        verticalLayout.addComponent(new Label("Search Results"));
        separator = new Label("<hr />", ContentMode.HTML);
        separator.setWidth(100f, Sizeable.Unit.PERCENTAGE);
        verticalLayout.addComponent(separator);

        verticalLayout.addComponent(getSearchResultsLayout());

        return verticalLayout;
    }

    private GridLayout getSearchCriteriaLayout() {

        GridLayout gridLayout = new GridLayout();
        gridLayout.setSpacing(true);
        gridLayout.setMargin(true);

        gridLayout.setColumns(6);
        gridLayout.setRows(8);

        gridLayout.setColumnExpandRatio(5, 1f);
        gridLayout.setRowExpandRatio(6, 1f);

        gridLayout.setSizeFull();

        final com.vaadin.v7.ui.Label minAmount = new com.vaadin.v7.ui.Label("Minimum Amount:");
        minAmount.setSizeUndefined();
        final com.vaadin.v7.ui.Label maxAmount = new com.vaadin.v7.ui.Label("Maximum Amount:");
        maxAmount.setSizeUndefined();
        final com.vaadin.v7.ui.Label nrOfProducts = new com.vaadin.v7.ui.Label("Number of Products:");
        nrOfProducts.setSizeUndefined();

        final com.vaadin.v7.ui.Label productName = new com.vaadin.v7.ui.Label("Product Name:");
        productName.setSizeUndefined();
        final com.vaadin.v7.ui.Label deliveredStatus = new com.vaadin.v7.ui.Label("Delivered:");
        deliveredStatus.setSizeUndefined();
        final com.vaadin.v7.ui.Label emailAddress = new com.vaadin.v7.ui.Label("Email Address:");
        emailAddress.setSizeUndefined();

        final TextField minAmountTextField = new TextField();
        minAmountTextField.setInputPrompt("Minimum Amount");
        minAmountTextField.setNullRepresentation("");
        minAmountTextField.setValidationVisible(true);

        final TextField maxAMountTextField = new TextField();
        maxAMountTextField.setInputPrompt("Maximum Amount");
        maxAMountTextField.setNullRepresentation("");
        maxAMountTextField.setValidationVisible(true);

        final TextField nrOfProductsTextField = new TextField();
        nrOfProductsTextField.setInputPrompt("Number of Products");
        nrOfProductsTextField.setNullRepresentation("");
        nrOfProductsTextField.setValidationVisible(true);

        final TextField productNameTextField = new TextField();
        productNameTextField.setInputPrompt("Product Name");
        productNameTextField.setNullRepresentation("");
        productNameTextField.setValidationVisible(true);

        final CheckBox deliveredStatusChkBox = new CheckBox();
        final TextField emailAddressTextField = new TextField();
        emailAddressTextField.setInputPrompt("Email Address");
        emailAddressTextField.setNullRepresentation("");
        emailAddressTextField.setValidationVisible(true);

        final Button search = new Button("Search");
		search.addClickListener(event ->{
			orderSearchCriteria = getOrderSearchCriteria(
					minAmountTextField.getValue(),
					maxAMountTextField.getValue(),
					nrOfProductsTextField.getValue(),
					productNameTextField.getValue(),
					emailAddressTextField.getValue());

			orderList = searchOrdersWithCriteria(orderSearchCriteria);
			grid.setItems(orderList);

		});

        final Button clear = new Button("Clear");
        clear.addClickListener(event -> {
            minAmountTextField.clear();
            maxAMountTextField.clear();
            nrOfProductsTextField.clear();
            productNameTextField.clear();
            emailAddressTextField.clear();
        });

        gridLayout.addComponent(minAmount, 0, 0);
        gridLayout.addComponent(minAmountTextField, 1, 0);
        gridLayout.addComponent(nrOfProducts, 0, 1);
        gridLayout.addComponent(nrOfProductsTextField, 1, 1);
        gridLayout.addComponent(productName, 0, 2);
        gridLayout.addComponent(productNameTextField, 1, 2);

        gridLayout.addComponent(maxAmount, 2, 0);
        gridLayout.addComponent(maxAMountTextField, 3, 0);
        gridLayout.addComponent(deliveredStatus, 2, 1);
        gridLayout.addComponent(deliveredStatusChkBox, 3, 1);
        gridLayout.addComponent(emailAddress, 2, 2);
        gridLayout.addComponent(emailAddressTextField, 3, 2);

        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.setSizeFull();
        buttonLayout.addComponent(search);
        buttonLayout.addComponent(clear);
        gridLayout.addComponent(buttonLayout, 3, 3);

        return gridLayout;
    }

    private Grid<Order> getSearchResultsLayout() {
        grid = new Grid<>(Order.class);

        grid.setWidth(100f, Sizeable.Unit.PERCENTAGE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);

        grid.addColumn(
                Order -> Order.getProducts().size())
                .setCaption("Number of Products")
                .setId("nrOfProducts");

        grid.addComponentColumn(item -> createDetailsButton(item)).setId("details");

        grid.setColumns("orderId", "customerId", "nrOfProducts", "delivered", "deliveryDays", "totalOrderPrice", "details");

        return grid;
    }

    private Button createDetailsButton(Order item) {
        Button button = new Button("Details", clickEvent -> {
            UI.getCurrent().getNavigator().navigateTo("DetailsPage/" + item.getOrderId());
        });
        return button;
    }

    private OrderSearchCriteria getOrderSearchCriteria(String minAmount, String maxAmount, String nrOfProducts, String productName, String emailAddress) {
        OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria();
        if (!minAmount.equals("")) {orderSearchCriteria.setMinAmount(BigDecimal.valueOf(Long.parseLong(minAmount)));}
        if (!maxAmount.equals("")) {orderSearchCriteria.setMaxAmount(BigDecimal.valueOf(Long.parseLong(maxAmount)));}
        if (!nrOfProducts.equals("")) {orderSearchCriteria.setNumberOfProducts(Integer.valueOf(nrOfProducts));}

        orderSearchCriteria.setProductName(minAmount);
        orderSearchCriteria.setEmail(emailAddress);
        orderSearchCriteria.setProductName(productName);

        return orderSearchCriteria;
    }

    private List<Order> searchOrdersWithCriteria(OrderSearchCriteria orderSearchCriteria) {
        OrderService orderService = new OrderServiceImpl();
        return orderService.searchOrders(orderSearchCriteria);

    }


}
