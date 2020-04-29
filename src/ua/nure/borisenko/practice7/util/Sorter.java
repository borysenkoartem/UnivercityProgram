package ua.nure.borisenko.practice7.util;

import ua.nure.borisenko.practice7.entity.Tariff;
import ua.nure.borisenko.practice7.entity.Tariffs;
import java.util.Collections;
import java.util.Comparator;

public final class Sorter {
	
private Sorter() {
	throw new IllegalStateException();
}
    public static final Comparator<Tariff> sort_tariffs_by_payroll = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff o1, Tariff o2) {
            Integer tariff1 = o1.getPayroll();
            Integer tariff2 = o2.getPayroll();
            return tariff1.compareTo(tariff2);
        }
    };

    public static final Comparator<Tariff>  sort_tariffs_by_name = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff o1, Tariff o2) {
            String tariff1 = o1.getName();
            String tariff2 = o2.getName();
            return tariff1.compareTo(tariff2);
        }
    };

    public static final Comparator<Tariff>  sort_tariffs_by_operator_name = new Comparator<Tariff>() {
        @Override
        public int compare(Tariff o1, Tariff o2) {
            String tariff1 = o1.getOperatorName();
            String tariff2 = o2.getOperatorName();
            return tariff1.compareTo(tariff2);
        }
    };

    public static void sortTariffsByName(Tariffs tariffs){
        Collections.sort(tariffs.getTariff(),sort_tariffs_by_name);
    }
    public static void sortTariffsByPayroll(Tariffs tariffs){
        Collections.sort(tariffs.getTariff(),sort_tariffs_by_payroll);
    }
    public static void sortTariffsOperatorName(Tariffs tariffs){
        Collections.sort(tariffs.getTariff(),sort_tariffs_by_operator_name);
    }
}
