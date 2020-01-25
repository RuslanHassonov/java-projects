package be.mobyus.vaadin.model;

import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Sizeable;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.v7.data.util.ObjectProperty;
import com.vaadin.v7.ui.VerticalLayout;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class DetailsPage extends Template {

    private String orderId;
    private Order order;
    private Label separator;
    private OrderServiceImpl orderService = new OrderServiceImpl();
    private VerticalLayout verticalLayout = new VerticalLayout();
    private GridLayout gridLayout = new GridLayout();
    private Grid<Product> grid = new Grid<>(Product.class);
    private List<Product> productList;

    @Override
    protected Component getBody() {
        return verticalLayout;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        this.orderId = event.getParameters();
        this.productList = getProductList(this.orderId);

        gridLayout.setSpacing(true);
        gridLayout.setMargin(true);

        gridLayout.setColumns(5);
        gridLayout.setRows(7);

        gridLayout.setColumnExpandRatio(5, 1f);
        gridLayout.setRowExpandRatio(6, 1f);

        gridLayout.setSizeFull();

        final com.vaadin.v7.ui.Label orderId = new com.vaadin.v7.ui.Label("Order ID:");
        orderId.setSizeUndefined();
        final com.vaadin.v7.ui.Label customerId = new com.vaadin.v7.ui.Label("Customer ID:");
        customerId.setSizeUndefined();
        final com.vaadin.v7.ui.Label nrOfProducts = new com.vaadin.v7.ui.Label("Number of Products:");
        nrOfProducts.setSizeUndefined();
        final com.vaadin.v7.ui.Label deliveryDays = new com.vaadin.v7.ui.Label("Delivery Days:");
        deliveryDays.setSizeUndefined();
        final com.vaadin.v7.ui.Label deliveredStatus = new com.vaadin.v7.ui.Label("Delivered:");
        deliveredStatus.setSizeUndefined();
        final com.vaadin.v7.ui.Label totalPrice = new com.vaadin.v7.ui.Label("Total Price:");
        totalPrice.setSizeUndefined();

        final com.vaadin.v7.ui.Label orderIdTextField = new com.vaadin.v7.ui.Label();
        orderIdTextField.setCaption(this.orderId);
        final com.vaadin.v7.ui.Label customerIdTextField = new com.vaadin.v7.ui.Label();
        customerIdTextField.setCaption(this.order.getCustomerId());
        final com.vaadin.v7.ui.Label nrOfProductsTextField = new com.vaadin.v7.ui.Label();
        nrOfProductsTextField.setCaption(String.valueOf(this.order.getProducts().size()));
        final com.vaadin.v7.ui.Label deliveryDaysTextField = new com.vaadin.v7.ui.Label();
        deliveryDaysTextField.setCaption(String.valueOf(this.order.getDeliveryDays()));

        final CheckBox deliveredStatusChkBox = new CheckBox();
        deliveredStatusChkBox.setEnabled(false);
        if (this.order.isDelivered()){
            deliveredStatusChkBox.setValue(true);
        }

        final com.vaadin.v7.ui.Label totalPriceTextField = new com.vaadin.v7.ui.Label();
        totalPriceTextField.setCaption(String.valueOf(this.order.getTotalOrderPrice()));

        gridLayout.addComponent(orderId, 0, 0);
        gridLayout.addComponent(orderIdTextField, 1, 0);
        gridLayout.addComponent(nrOfProducts, 0, 1);
        gridLayout.addComponent(nrOfProductsTextField, 1, 1);
        gridLayout.addComponent(customerId, 0, 2);
        gridLayout.addComponent(customerIdTextField, 1, 2);

        gridLayout.addComponent(deliveryDays, 2, 0);
        gridLayout.addComponent(deliveryDaysTextField, 3, 0);
        gridLayout.addComponent(deliveredStatus, 2, 1);
        gridLayout.addComponent(deliveredStatusChkBox, 3, 1);
        gridLayout.addComponent(totalPrice, 2, 2);
        gridLayout.addComponent(totalPriceTextField, 3, 2);

        verticalLayout.addComponent(new Label("Order Details"));
        separator = new Label("<hr />", ContentMode.HTML);
        separator.setWidth(100f, Sizeable.Unit.PERCENTAGE);
        verticalLayout.addComponent(separator);
        verticalLayout.addComponent(gridLayout);

        grid.setWidth(100f, Sizeable.Unit.PERCENTAGE);
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setItems(productList);

        grid.setColumns("productId", "productName","productDescription", "productPrice");

        verticalLayout.addComponent(new Label("Product List"));
        separator = new Label("<hr />", ContentMode.HTML);
        separator.setWidth(100f, Sizeable.Unit.PERCENTAGE);
        verticalLayout.addComponent(separator);
        verticalLayout.addComponent(grid);
    }

    private List<Product> getProductList(String orderId){
        order = orderService.getOrderById(orderId);
        return order.getProducts();
    }
}
