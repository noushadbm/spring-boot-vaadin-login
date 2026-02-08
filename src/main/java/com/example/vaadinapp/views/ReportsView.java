package com.example.vaadinapp.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "reports", layout = MainLayout.class)
@PageTitle("Reports | Vaadin App")
@PermitAll
public class ReportsView extends VerticalLayout {

    public ReportsView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Reports");
        Paragraph description = new Paragraph("Generate and view reports for your business data.");

        add(title, description);
    }
}