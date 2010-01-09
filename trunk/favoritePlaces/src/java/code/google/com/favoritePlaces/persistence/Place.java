/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package code.google.com.favoritePlaces.persistence;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author hazems
 */
@Entity
@Table(name = "PLACE", catalog = "", schema = "APP")
@NamedQueries({
    @NamedQuery(name = "Place.findAll", query = "SELECT p FROM Place p"),
    @NamedQuery(name = "Place.findByPlaceId", query = "SELECT p FROM Place p WHERE p.placeId = :placeId"),
    @NamedQuery(name = "Place.findByUserId", query = "SELECT p FROM Place p WHERE p.userId = :userId"),
    @NamedQuery(name = "Place.findByPlaceAddress", query = "SELECT p FROM Place p WHERE p.placeAddress = :placeAddress")})
public class Place implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PLACE_ID")
    private Long placeId;
    @Column(name = "USER_ID")
    private String userId;
    @Column(name = "PLACE_ADDRESS")
    private String placeAddress;

    public Place() {
    }

    public Place(Long placeId) {
        this.placeId = placeId;
    }

    public Long getPlaceId() {
        return placeId;
    }

    public void setPlaceId(Long placeId) {
        this.placeId = placeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPlaceAddress() {
        return placeAddress;
    }

    public void setPlaceAddress(String placeAddress) {
        this.placeAddress = placeAddress;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (placeId != null ? placeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Place)) {
            return false;
        }
        Place other = (Place) object;
        if ((this.placeId == null && other.placeId != null) || (this.placeId != null && !this.placeId.equals(other.placeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "code.google.com.favoritePlaces.persistence.Place[placeId=" + placeId + "]";
    }

}
