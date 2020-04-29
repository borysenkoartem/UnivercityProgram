package ua.nure.borisenko.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CallPrice", propOrder = {"inNetwork","outNetwork","cityNetwork"})
public class CallPrice {

    @XmlSchemaType(name = "nonNegativeInteger")
    private Integer inNetwork;
    @XmlSchemaType(name = "nonNegativeInteger")
    private Integer outNetwork;
    @XmlSchemaType(name = "nonNegativeInteger")
    private Integer cityNetwork;

    public Integer getInNetwork() {
        return inNetwork;
    }

    public void setInNetwork(Integer value) {
        this.inNetwork = value;
    }

    public Integer getOutNetwork() {
        return outNetwork;
    }

    public void setOutNetwork(Integer value) {
        this.outNetwork = value;
    }

    public Integer getCityNetwork() {
        return cityNetwork;
    }

    public void setCityNetwork(Integer value) {
        this.cityNetwork = value;
    }

    @Override
    public String toString() {
        return "CallPrice{" +
                "inNetwork=" + inNetwork +
                ", outNetwork=" + outNetwork +
                ", cityNetwork=" + cityNetwork +
                '}';
    }
}
