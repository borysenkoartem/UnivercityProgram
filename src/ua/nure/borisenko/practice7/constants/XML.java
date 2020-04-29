package ua.nure.borisenko.practice7.constants;

public enum XML {

    TARIFFS("tariffs"),
    TARIFF("tariff"),
    NAME("Name"),
    OPERATOR_NAME("OperatorName"),
    PAYROLL("Payroll"),
    CALL_PRICE("CallPrice"),
    IN_NETWORK("inNetwork"),
    OUT_NETWORK("outNetwork"),
    CITY_NETWORK("cityNetwork"),
    SMS("SMS"),
    PARAMETERS("Parameters"),
    FAVORITE_NUMBERS("favoriteNumbers"),
    FAVORITE_NUMBER("favoriteNumber"),
    CHARGING("charging"),
    PAYMENT_VALUE("paymentValue");

    private String value;

    XML(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }
}
