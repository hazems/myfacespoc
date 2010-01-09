/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.favoritePlaces.ejbs;

import java.util.List;

/**
 *
 * @author hazems
 */
public interface PlaceEJB {
    public void addFavoritePlace(Place place);
    public void deleteFavoritePlace(Place place);
    public List<Place> getFavoritePlaces(String userID);
}
