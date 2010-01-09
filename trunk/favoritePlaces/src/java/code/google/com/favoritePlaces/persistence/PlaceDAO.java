/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.favoritePlaces.persistence;

import java.util.List;

/**
 *
 * @author hazems
 */
public interface PlaceDAO {
    public void addFavoritePlace(Place place);
    public List<Place> getFavoritePlaces(String userID);
}
