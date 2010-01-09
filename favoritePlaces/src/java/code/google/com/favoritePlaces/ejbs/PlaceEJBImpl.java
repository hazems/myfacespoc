/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package code.google.com.favoritePlaces.ejbs;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author hazems
 */
@Stateless
public class PlaceEJBImpl implements PlaceEJB {

    @PersistenceContext(unitName = "favoritePlacesPU")
    EntityManager entityManager;

    public void addFavoritePlace(Place place) {
        entityManager.persist(place);
        System.out.println("Place is added ...");
    }

    public void deleteFavoritePlace(Place place) {
        entityManager.remove(place);
        System.out.println("Place is deleted ...");
    }

    public List<Place> getFavoritePlaces(String userID) {
        Query query = entityManager.createQuery("Select place from Place place where place.userId=:userID");

        query.setParameter("userID", userID);
        List results = query.getResultList();

        return results;
    }
}
