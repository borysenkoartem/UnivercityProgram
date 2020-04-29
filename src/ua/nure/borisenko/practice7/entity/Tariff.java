package ua.nure.borisenko.practice7.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Tariff", propOrder = {"name","operatorName","payroll","callPrice","sms","parameters"})
public class Tariff {

    @XmlElement(name = "Name", required = true)
    private String name;
    @XmlElement(name = "OperatorName", required = true)
    private String operatorName;
    @XmlElement(name = "Payroll")
    @XmlSchemaType(name = "nonNegativeInteger")
    private int payroll;
    @XmlElement(name = "CallPrice", required = true)
    private CallPrice callPrice;
    @XmlElement(name = "SMS")
    @XmlSchemaType(name = "nonNegativeInteger")
    private int sms;
    @XmlElement(name = "Parameters", required = true)
    private Parameters parameters;

    public String getName() {
        return name;
    }

    public void setName(String value) {
        this.name = value;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String value) {
        this.operatorName = value;
    }

    public int getPayroll() {
        return payroll;
    }

    public void setPayroll(int value) {
        this.payroll = value;
    }

    public CallPrice getCallPrice() {
        return callPrice;
    }

    public void setCallPrice(CallPrice value) {
        this.callPrice = value;
    }

    public int getSMS() {
        return sms;
    }

    public void setSMS(int value) {
        this.sms = value;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    @Override
    public String toString() {
        return "Tariff{" +
                "name='" + name + '\'' +
                ", operatorName='" + operatorName + '\'' +
                ", payroll=" + payroll +
                ", callPrice=" + callPrice +
                ", sms=" + sms +
                ", parameters=" + parameters +
                '}';
    }
}
