/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.favoritePlaces.business;

import code.google.com.favoritePlaces.persistence.Place;
import code.google.com.favoritePlaces.persistence.PlaceDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Stateless
@Named
public class FavoritePlacesEJB {
    public static final int MAXIMUM_VISITS = 5;
    public static final String YOU_CANNOT_ADD_MORE_THAN_FIVE_PLACES = "You cannot add more than five places in a single session ...";
    private String address;
    private List<Place> savedPlaces;
    private String      errorMessage;

    @Inject
    PlaceDAO placeDAO;

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the savedAddresses
     */
    public List<Place> getSavedPlaces() {
        return savedPlaces;
    }

    /**
     * @param savedAddresses the savedAddresses to set
     */
    public void setSavedPlaces(List<Place> savedPlaces) {
        this.savedPlaces = savedPlaces;
    }

   /**
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * @param errorMessage the errorMessage to set
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void initializePerView(PhaseEvent event)  {
        if (event.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);

            savedPlaces = placeDAO.getFavoritePlaces(session.getId());

            if (savedPlaces.size() >= MAXIMUM_VISITS) {
                errorMessage = YOU_CANNOT_ADD_MORE_THAN_FIVE_PLACES;
            } else {
                errorMessage = "";
            }
            
            System.err.println("Init per view is called ...");
        }
    }
    
    public String addFavoritePlace() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Place       place   = new Place();

        savedPlaces = placeDAO.getFavoritePlaces(session.getId());

        if (savedPlaces.size() >= MAXIMUM_VISITS) {
            errorMessage = YOU_CANNOT_ADD_MORE_THAN_FIVE_PLACES;
            return null;
        }

        place.setPlaceAddress(getAddress());
        place.setUserId(session.getId());

        placeDAO.addFavoritePlace(place);
        savedPlaces = placeDAO.getFavoritePlaces(session.getId());

        errorMessage = "";
        return null;
    }
}
