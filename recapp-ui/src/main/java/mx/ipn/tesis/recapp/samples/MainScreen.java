package mx.ipn.tesis.recapp.samples;

import mx.ipn.tesis.recapp.MyUI;
import mx.ipn.tesis.recapp.samples.about.AboutView;
import mx.ipn.tesis.recapp.samples.crud.SampleCrudView;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import mx.ipn.tesis.recapp.manifiestos.crud.ManifiestosCrudView;

/**
 * Content of the UI when the user is logged in.
 * 
 * 
 */
public class MainScreen extends HorizontalLayout {
    private Menu menu;

    public MainScreen(MyUI ui) {

        setSpacing(false);
        setStyleName("main-screen");

        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();

        final Navigator navigator = new Navigator(ui, viewContainer);
        navigator.setErrorView(ErrorView.class);
        menu = new Menu(navigator);
        menu.addView(new SampleCrudView(), SampleCrudView.VIEW_NAME,
                SampleCrudView.VIEW_NAME, VaadinIcons.EDIT);
        // HSP       
        ManifiestosCrudView vista = new ManifiestosCrudView();
        menu.addView(vista, ManifiestosCrudView.VIEW_NAME,
                ManifiestosCrudView.VIEW_NAME, VaadinIcons.EDIT);
        //HSP
        menu.addView(new AboutView(), AboutView.VIEW_NAME, AboutView.VIEW_NAME,
                VaadinIcons.INFO_CIRCLE);

        navigator.addViewChangeListener(viewChangeListener);

        addComponent(menu);
        addComponent(viewContainer);
        setExpandRatio(viewContainer, 1);
        setSizeFull();
    }

    // notify the view menu about view changes so that it can display which view
    // is currently active
    ViewChangeListener viewChangeListener = new ViewChangeListener() {

        @Override
        public boolean beforeViewChange(ViewChangeEvent event) {
            return true;
        }

        @Override
        public void afterViewChange(ViewChangeEvent event) {
            menu.setActiveView(event.getViewName());
        }

    };
}