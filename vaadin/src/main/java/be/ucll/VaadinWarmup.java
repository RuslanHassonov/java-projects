package be.ucll;

import java.util.Arrays;
import java.util.stream.Collectors;

import com.vaadin.navigator.View;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Component;
import com.vaadin.v7.shared.ui.label.ContentMode;
import com.vaadin.v7.ui.HorizontalLayout;
import com.vaadin.v7.ui.Label;
import com.vaadin.v7.ui.TextField;
import com.vaadin.v7.ui.VerticalLayout;

public class VaadinWarmup implements View {

	private String[] municipalities = new String[] { "Aalst", "Aalter[1]", "Aarschot", "Aartselaar", "Affligem", "Alken", "Alveringem", "Antwerpen", "Anzegem",
			"Ardooie",
			"Arendonk", "As", "Asse", "Assenede", "Avelgem", "Baarle-Hertog", "Balen", "Beernem", "Beerse", "Beersel", "Begijnendijk", "Bekkevoort", "Beringen",
			"Berlaar", "Berlare", "Bertem", "Bever", "Beveren", "Bierbeek", "Bilzen", "Blankenberge", "Bocholt", "Boechout", "Bonheiden", "Boom", "Boortmeerbeek",
			"Borgloon", "Bornem", "Borsbeek", "Boutersem", "Brakel", "Brasschaat", "Brecht", "Bredene", "Bree", "Brugge", "Buggenhout", "Damme", "De Haan", "De Panne",
			"De Pinte", "Deerlijk", "Deinze[1]", "Denderleeuw", "Dendermonde", "Dentergem", "Dessel", "Destelbergen", "Diepenbeek", "Diest", "Diksmuide", "Dilbeek",
			"Dilsen-Stokkem", "Drogenbos", "Duffel", "Edegem", "Eeklo", "Erpe-Mere", "Essen", "Evergem", "Galmaarden", "Gavere", "Geel", "Geetbets", "Genk", "Gent",
			"Geraardsbergen", "Gingelom", "Gistel", "Glabbeek", "Gooik", "Grimbergen", "Grobbendonk", "Haacht", "Haaltert", "Halen", "Halle", "Ham", "Hamme",
			"Hamont-Achel", "Harelbeke", "Hasselt", "Hechtel-Eksel", "Heers", "Heist-op-den-Berg", "Hemiksem", "Herent", "Herentals", "Herenthout", "Herk-de-Stad",
			"Herne", "Herselt", "Herstappe", "Herzele", "Heusden-Zolder", "Heuvelland", "Hoegaarden", "Hoeilaart", "Hoeselt", "Holsbeek", "Hooglede", "Hoogstraten",
			"Horebeke", "Houthalen-Helchteren", "Houthulst", "Hove", "Huldenberg", "Hulshout", "Ichtegem", "Ieper", "Ingelmunster", "Izegem", "Jabbeke", "Kalmthout",
			"Kampenhout", "Kapellen", "Kapelle-op-den-Bos", "Kaprijke", "Kasterlee", "Keerbergen", "Kinrooi", "Kluisbergen", "Knokke-Heist", "Koekelare", "Koksijde",
			"Kontich", "Kortemark", "Kortenaken", "Kortenberg", "Kortessem", "Kortrijk", "Kraainem", "Kruibeke", "Kruisem[1]", "Kuurne", "Laakdal", "Laarne", "Lanaken",
			"Landen", "Langemark-Poelkapelle", "Lebbeke", "Lede", "Ledegem", "Lendelede", "Lennik", "Leopoldsburg", "Leuven", "Lichtervelde", "Liedekerke", "Lier",
			"Lierde", "Lievegem[1]", "Lille", "Linkebeek", "Lint", "Linter", "Lochristi", "Lokeren", "Lommel", "Londerzeel", "Lo-Reninge", "Lubbeek", "Lummen",
			"Maarkedal", "Maaseik", "Maasmechelen", "Machelen", "Maldegem", "Malle", "Mechelen", "Meerhout", "Meise", "Melle", "Menen", "Merchtem", "Merelbeke",
			"Merksplas", "Mesen", "Meulebeke", "Middelkerke", "Moerbeke", "Mol", "Moorslede", "Mortsel", "Nazareth", "Niel", "Nieuwerkerken", "Nieuwpoort", "Nijlen",
			"Ninove", "Olen", "Oostende", "Oosterzele", "Oostkamp", "Oostrozebeke", "Opwijk", "Oudenaarde", "Oudenburg", "Oud-Heverlee", "Oudsbergen[1]", "Oud-Turnhout",
			"Overijse", "Peer", "Pelt[1]", "Pepingen", "Pittem", "Poperinge", "Putte", "Puurs-Sint-Amands[1]", "Ranst", "Ravels", "Retie", "Riemst", "Rijkevorsel",
			"Roeselare", "Ronse", "Roosdaal", "Rotselaar", "Ruiselede", "Rumst", "Schelle", "Scherpenheuvel-Zichem", "Schilde", "Schoten", "Sint-Genesius-Rode",
			"Sint-Gillis-Waas", "Sint-Katelijne-Waver", "Sint-Laureins", "Sint-Lievens-Houtem", "Sint-Martens-Latem", "Sint-Niklaas", "Sint-Pieters-Leeuw",
			"Sint-Truiden", "Spiere-Helkijn", "Stabroek", "Staden", "Steenokkerzeel", "Stekene", "Temse", "Ternat", "Tervuren", "Tessenderlo", "Tielt", "Tielt-Winge",
			"Tienen", "Tongeren", "Torhout", "Tremelo", "Turnhout", "Veurne", "Vilvoorde", "Vleteren", "Voeren", "Vorselaar", "Vosselaar", "Waasmunster", "Wachtebeke",
			"Waregem", "Wellen", "Wemmel", "Wervik", "Westerlo", "Wetteren", "Wevelgem", "Wezembeek-Oppem", "Wichelen", "Wielsbeke", "Wijnegem", "Willebroek", "Wingene",
			"Wommelgem", "Wortegem-Petegem", "Wuustwezel", "Zandhoven", "Zaventem", "Zedelgem", "Zele", "Zelzate", "Zemst", "Zoersel", "Zonhoven", "Zonnebeke",
			"Zottegem", "Zoutleeuw", "Zuienkerke", "Zulte", "Zutendaal", "Zwalm", "Zwevegem", "Zwijndrecht" };

	@Override
	public Component getViewComponent() {

		VerticalLayout verticalLayout = new VerticalLayout();
		HorizontalLayout horizontalLayout = new HorizontalLayout();

		TextField textField = new TextField();
		Button button = new Button("Search!");
		Label label = new Label();
		label.setContentMode(ContentMode.HTML);

		button.addClickListener((ClickListener) event -> {
			label.setValue(Arrays.stream(municipalities).filter(value -> value.toLowerCase().startsWith(textField.getValue().toLowerCase()))
					.collect(Collectors.joining("<br>")));
		});

		horizontalLayout.addComponents(textField, button);
		verticalLayout.addComponent(horizontalLayout);

		verticalLayout.addComponent(label);

		return verticalLayout;
	}
}
