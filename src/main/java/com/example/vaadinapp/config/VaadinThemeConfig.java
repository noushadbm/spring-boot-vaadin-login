package com.example.vaadinapp.config;

import com.vaadin.flow.server.VaadinService;
import com.vaadin.flow.server.VaadinServiceInitListener;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Component;

@Component
public class VaadinThemeConfig implements VaadinServiceInitListener {

    private static final String DARK_THEME_CSS = "vaadin-themes/dark-theme.css";

    @Override
    public void serviceInit(com.vaadin.flow.server.ServiceInitEvent event) {
        event.getSource().addUIInitListener(uiInitEvent -> {
            uiInitEvent.getUI().addBeforeEnterListener(e -> {
                VaadinService service = VaadinService.getCurrent();
                if (service != null) {
                    service.getResourceAsStream(DARK_THEME_CSS);
                }
            });
        });
    }
}
