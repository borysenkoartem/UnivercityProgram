package ua.nure.borisenko.practice7.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {"tariff"})
@XmlRootElement(name = "tariffs")
public class Tariffs {

    private List<Tariff> tariff;


    public List<Tariff> getTariff() {
        return this.tariff;
    }

    public void addTariff(Tariff newTariff) {
        if (tariff == null) {
            tariff = new ArrayList<>();
            tariff.add(newTariff);
        } else {
            tariff.add(newTariff);
        }
    }

    @Override
    public String toString() {
        return "Tariffs{" +
                "tariff=" + tariff +
                '}';
    }
}
