package be.ucll;

import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.Panel;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;

public class Ex4 implements View {

	@Override
	public Component getViewComponent() {

		//Enable this to see visible borders
		/*Page.getCurrent().getStyles().add(".v-gridlayout {border-style: solid;border-width: 5px;border-color: blue; } "
				+ ".v-gridlayout-slot {border-style: solid;border-width: 1px;border-color: red; }");*/

		GridLayout gridLayout = new GridLayout();
		gridLayout.setSpacing(true);
		gridLayout.setMargin(true);

		gridLayout.setColumns(6);
		gridLayout.setRows(8);

		gridLayout.setColumnExpandRatio(5, 1f);
		gridLayout.setRowExpandRatio(6, 1f);

		gridLayout.setSizeFull();

		final Label voornaamLabel = new Label("Voornaam:");
		voornaamLabel.setStyleName("redLabel");
		voornaamLabel.setSizeUndefined();
		final Label achternaamLabel = new Label("Achternaam:");
		achternaamLabel.setSizeUndefined();

		final Label landLabel = new Label("Land:");
		landLabel.setSizeUndefined();
		final Label gemeenteLabel = new Label("Gemeente:");
		gemeenteLabel.setSizeUndefined();
		final Label postcodeLabel = new Label("Postcode:");
		postcodeLabel.setSizeUndefined();
		final Label straatLabel = new Label("Straat:");
		straatLabel.setSizeUndefined();
		final Label huistnummerLabel = new Label("Huisnummer/index:");
		huistnummerLabel.setSizeUndefined();

		final TextField voornaamTextField = new TextField();
		voornaamTextField.setInputPrompt("voornaam");
		final TextField achternaamTextField = new TextField();
		achternaamTextField.setInputPrompt("achternaam");

		final TextField landTextField = new TextField();
		landTextField.setInputPrompt("land");
		final TextField gemeenteTextField = new TextField();
		gemeenteTextField.setInputPrompt("gemeente");
		final TextField postcodeTextField = new TextField();
		postcodeTextField.setInputPrompt("postcode");
		final TextField straatTextField = new TextField();
		straatTextField.setInputPrompt("straat");
		final TextField huisnummerTextField = new TextField();
		huisnummerTextField.setInputPrompt("huisnummer/index");

		final Button button = new Button("Verzend");

		Panel ident = new Panel("Identificatie gegevens");

		Panel contact = new Panel("Contact gegevens");

		gridLayout.addComponent(ident, 0, 0, 1, 0);
		gridLayout.addComponent(contact, 3, 0, 4, 0);

		gridLayout.addComponent(voornaamLabel, 0, 1);
		gridLayout.addComponent(voornaamTextField, 1, 1);
		gridLayout.addComponent(achternaamLabel, 0, 2);
		gridLayout.addComponent(achternaamTextField, 1, 2);

		gridLayout.addComponent(landLabel, 3, 1);
		gridLayout.addComponent(landTextField, 4, 1);

		gridLayout.addComponent(gemeenteLabel, 3, 2);
		gridLayout.addComponent(gemeenteTextField, 4, 2);

		gridLayout.addComponent(postcodeLabel, 3, 3);
		gridLayout.addComponent(postcodeTextField, 4, 3);

		gridLayout.addComponent(straatLabel, 3, 4);
		gridLayout.addComponent(straatTextField, 4, 4);

		gridLayout.addComponent(huistnummerLabel, 3, 5);

		return gridLayout;
	}
}
