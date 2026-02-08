package com.example.vaadinapp.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "settings", layout = MainLayout.class)
@PageTitle("Settings | Vaadin App")
@PermitAll
public class SettingsView extends VerticalLayout {

    public SettingsView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Settings");
        Paragraph description = new Paragraph("Configure your application settings and preferences.");

        add(title, description);
    }
}