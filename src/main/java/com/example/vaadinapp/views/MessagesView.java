package com.example.vaadinapp.views;

import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;

@Route(value = "messages", layout = MainLayout.class)
@PageTitle("Messages | Vaadin App")
@PermitAll
public class MessagesView extends VerticalLayout {

    public MessagesView() {
        setSizeFull();
        setPadding(true);
        setSpacing(true);

        H2 title = new H2("Messages");
        Paragraph description = new Paragraph("View and manage your messages and notifications.");

        add(title, description);
    }
}