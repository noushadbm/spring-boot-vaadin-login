package com.example.vaadinapp.service;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.server.ServiceInitEvent;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ThemeService implements VaadinServiceInitListener {

    public static final String LIGHT_THEME = "light";
    public static final String DARK_THEME = "dark";

    @Override
    public void serviceInit(ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiInitEvent -> {
            UI ui = uiInitEvent.getUI();
            ui.addBeforeEnterListener(e -> {
                String theme = getTheme();
                if (LIGHT_THEME.equals(theme)) {
                    ui.getElement().setAttribute("theme", LIGHT_THEME);
                } else if (DARK_THEME.equals(theme)) {
                    ui.getElement().setAttribute("theme", DARK_THEME);
                }
            });
        });
    }

    public void setTheme(UI ui, String theme) {
        VaadinSession session = ui.getSession();
        if (session != null) {
            session.setAttribute(String.class, theme);
        }
        ui.getElement().setAttribute("theme", theme);
    }

    public String getTheme() {
        return LIGHT_THEME;
    }
}
