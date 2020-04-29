package ua.nure.borisenko.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;


@XmlAccessorType(XmlAccessType.FIELD)
public class FavoriteNumber {
    private static final String NUMBER_REGEX="[+]\\d{12}";
    private String fNumber;

    public String getFavoriteNumber() {
        return fNumber;
    }

    public void setFavoriteNumber(String favoriteNumber) {
        if (favoriteNumber.matches(NUMBER_REGEX)) {
            this.fNumber = favoriteNumber;
        } else {
            throw new IllegalArgumentException("Wrong Favorites number format");
        }
    }

    @Override
    public String toString() {
        return fNumber ;
    }
}
