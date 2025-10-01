package dmit2015.faces;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import org.omnifaces.util.Messages;

import java.util.logging.Logger;

/**
 * Request-scoped backing bean: new instance per HTTP request.
 * Use for simple actions/data that don't need to persist after the response.
 */
@Named("buttonDemoBeanRequest")
@RequestScoped // New instance per HTTP request; no Serializable required
public class ButtonDemoBeanRequest {

    private static final Logger LOG = Logger.getLogger(ButtonDemoBeanRequest.class.getName());

    public void handleClick() {
        // Add a FacesMessage message to FacesContext
        Messages.addGlobalInfo("Button clicked using actionListener!");
    }

    // The return value is url to navigate after action.
    public String submit() {
        Messages.addGlobalInfo("Button submitted using action!");
        return null;
    }
}
