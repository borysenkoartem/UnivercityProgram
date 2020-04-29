package ua.nure.borisenko.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Parameters", propOrder = {"favoriteNumbers", "charging", "paymentValue"})
public class Parameters {

    @XmlElement(required = true)
    private FavoriteNumbers favoriteNumbers;
    @XmlElement(required = true)
    private Charging charging;
    @XmlSchemaType(name = "nonNegativeInteger")
    private int paymentValue;


    public FavoriteNumbers getFavoriteNumbers() {
        return favoriteNumbers;
    }

    public void setFavoriteNumbers(FavoriteNumbers value) {
        this.favoriteNumbers = value;
    }

    public Charging getCharging() {
        return charging;
    }

    public void setCharging(Charging value) {
        this.charging = value;
    }

    public int getPaymentValue() {
        return paymentValue;
    }

    public void setPaymentValue(int value) {
        this.paymentValue = value;
    }

    @Override
    public String toString() {
        return "Parameters{" +
                "FavoriteNumbers=" + favoriteNumbers.toString() +
                ", charging=" + charging.value() +
                ", paymentValue=" + paymentValue +
                '}';
    }
}
