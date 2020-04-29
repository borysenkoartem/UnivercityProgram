package ua.nure.borisenko.practice7.entity;


import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "charge")
@XmlEnum
public enum Charging {
    @XmlEnumValue("12")
    TWELVE("12"),
    @XmlEnumValue("60")
    SIXTY("60");

    private String value;

    Charging(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static Charging fromValue(String value) {
        for (Charging c : Charging.values()) {
            if (c.value.equals(value)) {
                return c;
            }
        }
        throw new IllegalArgumentException(value);
    }
}
