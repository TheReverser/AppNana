package com.appnana.android.giftcardrewards.model;

import com.appnana.android.giftcardrewards.R;
import com.appnana.android.utils.Device;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CountryInfoModel {
    private static CountryInfoModel instance;
    private List<CountryInfo> countries = new ArrayList();

    private class CountryByNameComparer implements Comparator<CountryInfo> {
        public int compare(CountryInfo a, CountryInfo b) {
            if (a.getPlace() == -1) {
                return 1;
            }
            if (b.getPlace() == -1 || a.getPlace() < b.getPlace()) {
                return -1;
            }
            return 1;
        }
    }

    private CountryInfoModel() {
        CountryInfo ww = new CountryInfo("ww", R.string.country_ww, R.string.country_ww, R.drawable.ic_country_ww, 3);
        CountryInfo au = new CountryInfo("au", R.string.country_au, R.string.country_au, R.drawable.ic_country_au, 4);
        CountryInfo ca = new CountryInfo("ca", R.string.country_ca, R.string.country_ca, R.drawable.ic_country_ca, 6);
        CountryInfo us = new CountryInfo("us", R.string.country_us, R.string.country_us_short, R.drawable.ic_country_us, 1);
        CountryInfo gb = new CountryInfo("gb", R.string.country_uk, R.string.country_uk_short, R.drawable.ic_country_gb, 2);
        CountryInfo fr = new CountryInfo("fr", R.string.country_fr, R.string.country_fr, R.drawable.ic_country_fr, 7);
        CountryInfo de = new CountryInfo("de", R.string.country_de, R.string.country_de, R.drawable.ic_country_de, 8);
        CountryInfo it = new CountryInfo("it", R.string.country_it, R.string.country_it, R.drawable.ic_country_it, 10);
        CountryInfo es = new CountryInfo("es", R.string.country_es, R.string.country_es, R.drawable.ic_country_es, 12);
        CountryInfo nl = new CountryInfo("nl", R.string.country_nl, R.string.country_nl, R.drawable.ic_country_nl, 11);
        CountryInfo br = new CountryInfo("br", R.string.country_br, R.string.country_br, R.drawable.ic_country_br, 5);
        CountryInfo in = new CountryInfo("in", R.string.country_in, R.string.country_in, R.drawable.ic_country_in, 9);
        this.countries.add(ww);
        this.countries.add(au);
        this.countries.add(ca);
        this.countries.add(us);
        this.countries.add(gb);
        this.countries.add(fr);
        this.countries.add(de);
        this.countries.add(it);
        this.countries.add(es);
        this.countries.add(nl);
        this.countries.add(br);
        this.countries.add(in);
        Collections.sort(this.countries, new CountryByNameComparer());
    }

    public static CountryInfoModel getInstance() {
        if (instance == null) {
            instance = new CountryInfoModel();
        }
        return instance;
    }

    public List<CountryInfo> getCountryInfoList() {
        return this.countries;
    }

    public CountryInfo getCountryInfoByCode(String code) {
        for (CountryInfo countryInfo : this.countries) {
            if (code.equals(countryInfo.getCode())) {
                return countryInfo;
            }
        }
        return null;
    }

    public int getPosition(String localeCode) {
        int countryCount = this.countries.size();
        for (int i = 0; i < countryCount; i++) {
            if (localeCode.equals(((CountryInfo) this.countries.get(i)).getCode())) {
                return i;
            }
        }
        return -1;
    }

    public boolean isAvailableLocale(String localeCode) {
        return (localeCode.equals("br") || getPosition(localeCode.toLowerCase()) == -1) ? false : true;
    }

    public String getDefaultLocale() {
        try {
            String locale = Device.getInstance().getCountry().toLowerCase();
            if (isAvailableLocale(locale)) {
                return locale;
            }
            return "us";
        } catch (Exception e) {
            return "us";
        }
    }
}
