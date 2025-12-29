package com.londonriskmap.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.map.Map;
import com.vaadin.flow.component.map.configuration.Coordinate;
import com.vaadin.flow.component.map.configuration.View;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./themes/userlayout.css")
@Route("")
public class MapView extends VerticalLayout {

    public MapView() {
        setSizeFull();
        setPadding(false);
        setSpacing(false);

        // Vaadin Map
        Map map = new Map();
        map.setSizeFull();
        View view = map.getView();
        view.setZoom(12);
        map.setCenter(new Coordinate(-0.12, 51.5074));

        // Title pill
        Div title = new Div ("LondonRiskMap");
        title.getStyle()
                .set("position", "absolute")
                .set("top", "16px")
                .set("left", "50%")
                .set("transform", "translateX(-50%)")
                .set("background", "white")
                .set("padding", "18px 28px")
                .set("border-radius", "12px")
                .set("box-shadow", "0 4px 12px rgba(0,0,0,0.2)")
                .set("font-size", "28px")
                .set("font-weight", "400")
                .set("line-height", "1")
                .set("z-index", "10");

        // Bottom search button
        Button searchButton = new Button("Enter address or coordinates",
                new Icon(VaadinIcon.SEARCH));
        searchButton.getStyle()
                .set("position", "absolute")
                .set("bottom", "20px")
                .set("left", "50%")
                .set("transform", "translateX(-50%)")
                .set("width", "min(90%, 420px)")
                .set("padding", "14px")
                .set("border-radius", "9999px")
                .set("box-shadow", "0 8px 20px rgba(0,0,0,0.25)")
                .set("z-index", "10");

        // interactive layout (vaadin documentation style)
        Dialog routeDialog = new Dialog();
        routeDialog.getElement().getStyle().set("inset", "auto 0 0 0");
        routeDialog.setModal(true);
        routeDialog.setCloseOnEsc(true);
        routeDialog.setCloseOnOutsideClick(false);
        routeDialog.addClassName("route-dialog");


        VerticalLayout sheet = new VerticalLayout();
        sheet.addClassName("route-sheet");
        sheet.setPadding(false);
        sheet.setSpacing(false);

        // header
        Div header = new Div();
        header.addClassName("route-header");

        Div headerTitle = new Div();
        headerTitle.setText("Plan your route");
        headerTitle.addClassName("route-title");

        Button close = new Button(new Icon(VaadinIcon.CLOSE_SMALL));
        close.addClassName("route-close");
        close.addClickListener(e -> routeDialog.close());

        header.add(headerTitle, close);

        // start
        Div startLabel = new Div("Where are you starting from?");
        startLabel.addClassName("route-label");

        TextField startField = new TextField();
        startField.setPlaceholder("Enter starting location");
        startField.addClassName("route-field");

        Button locate = new Button(new Icon(VaadinIcon.LOCATION_ARROW));
        locate.addClassName("locate-button");

        Div startWrapper = new Div();
        startWrapper.addClassName("start-wrapper");
        startWrapper.add(startField, locate);
        // destination
        Div destLabel = new Div("Where are you headed to?");
        destLabel.addClassName("route-label");

        TextField destField = new TextField();
        destField.setPlaceholder("Enter destination");
        destField.addClassName("route-field");

        // search
        Button searchRoute = new Button("Search Route");
        searchRoute.addClassName("search-route-button");

        sheet.add(
                header,
                startLabel,
                startWrapper,
                destLabel,
                destField,
                searchRoute
        );
        sheet.setHeight("30vh");


        routeDialog.add(sheet);

        searchButton.addClickListener(e -> routeDialog.open());

        add(map, title, searchButton, routeDialog);
    }
}
