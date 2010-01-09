/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code.google.com.favoritePlaces.beans;

import code.google.com.favoritePlaces.ejbs.Place;
import code.google.com.favoritePlaces.ejbs.PlaceEJB;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

@Named
@RequestScoped
public class FavoritePlaces {

    public static final int MAXIMUM_VISITS = 5;
    public static final String YOU_CANNOT_ADD_MORE_THAN_FIVE_PLACES = "You cannot add more than five places in a single session ...";
    private String address;
    private List<Place> savedPlaces;
    private String errorMessage;
   
    @EJB PlaceEJB placeEJB;

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

    public void loadUserSettings(PhaseEvent phaseEvent) {
        if (phaseEvent.getPhaseId() == PhaseId.RENDER_RESPONSE) {
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            savedPlaces = placeEJB.getFavoritePlaces(session.getId());
        }
    }

    public String addFavoritePlace() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        Place place = new Place();

        savedPlaces = placeEJB.getFavoritePlaces(session.getId());

        if (savedPlaces.size() >= MAXIMUM_VISITS) {
            errorMessage = YOU_CANNOT_ADD_MORE_THAN_FIVE_PLACES;
            return null;
        }

        place.setPlaceAddress(getAddress());
        place.setUserId(session.getId());

        placeEJB.addFavoritePlace(place);
        savedPlaces = placeEJB.getFavoritePlaces(session.getId());

        errorMessage = "";
        return null;
    }
}
