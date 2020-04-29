package ua.nure.borisenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FavoriteNumbers", propOrder = {"favoriteNumber"})
public class FavoriteNumbers {

    private List<FavoriteNumber> favoriteNumber;

    public List<FavoriteNumber> getFavoriteNumber() {
        return this.favoriteNumber;
    }

    public void addFavoriteNumber(FavoriteNumber value) {
        if (favoriteNumber==null){
            favoriteNumber = new ArrayList<>();
        }
        favoriteNumber.add(value);
    }

    @Override
    public String toString() {
        return favoriteNumber.toString();
    }
}
