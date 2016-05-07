package com.rebtel.restflags.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gabordudas on 07/05/16.
 * Copyright (c) 2015 RestFlags. All rights reserved.
 */

//{
//      "name": "Andorra",
//      "capital": "Andorra la Vella",
//      "altSpellings": [
//          "AD",
//          "Principality of Andorra",
//          "Principat d'Andorra"
//      ],
//      "relevance": "0.5",
//      "region": "Europe",
//      "subregion": "Southern Europe",
//      "translations": {
//          "de": "Andorra",
//          "es": "Andorra",
//          "fr": "Andorre",
//          "ja": "アンドラ",
//          "it": "Andorra"
//      },
//      "population": 76949,
//      "latlng": [
//          42.5,
//          1.5
//      ],
//      "demonym": "Andorran",
//      "area": 468,
//      "gini": null,
//      "timezones": [
//          "UTC+01:00"
//      ],
//      "borders": [
//          "FRA",
//          "ESP"
//      ],
//      "nativeName": "Andorra",
//      "callingCodes": [
//          "376"
//      ],
//      "topLevelDomain": [
//          ".ad"
//      ],
//      "alpha2Code": "AD",
//      "alpha3Code": "AND",
//      "currencies": [
//          "EUR"
//      ],
//      "languages": [
//          "ca"
//      ]
//}
public class CountryDetails implements Serializable, Parcelable {
    @SerializedName("name")
    private String name;
    @SerializedName("capital")
    private String capital;
    @SerializedName("altSpellings")
    private List<String> altSpellings;
    @SerializedName("relevance")
    private String relevance;
    @SerializedName("region")
    private String region;
    @SerializedName("subregion")
    private String subregion;
    @SerializedName("translations")
    private Translations translations;
    @SerializedName("population")
    private int population;
    @SerializedName("latlng")
    private List<String> latlng;
    @SerializedName("demonym")
    private String demonym;
    @SerializedName("area")
    private int area;
    @SerializedName("gini")
    private Object gini;
    @SerializedName("timezones")
    private List<String> timezones;
    @SerializedName("borders")
    private List<String> borders;
    @SerializedName("nativeName")
    private String nativeName;
    @SerializedName("callingCodes")
    private List<String> callingCodes;
    @SerializedName("topLevelDomain")
    private List<String> topLevelDomain;
    @SerializedName("alpha2Code")
    private String alpha2Code;
    @SerializedName("alpha3Code")
    private String alpha3Code;
    @SerializedName("currencies")
    private List<String> currencies;
    @SerializedName("languages")
    private List<String> languages;

    public CountryDetails(String name, String capital, List<String> altSpellings, String relevance,
                          String region, String subregion, Translations translations, int population,
                          List<String> latlng, String demonym, int area, Object gini, List<String> timezones,
                          List<String> borders, String nativeName, List<String> callingCodes,
                          List<String> topLevelDomain, String alpha2Code, String alpha3Code,
                          List<String> currencies, List<String> languages) {
        this.name = name;
        this.capital = capital;
        this.altSpellings = altSpellings;
        this.relevance = relevance;
        this.region = region;
        this.subregion = subregion;
        this.translations = translations;
        this.population = population;
        this.latlng = latlng;
        this.demonym = demonym;
        this.area = area;
        this.gini = gini;
        this.timezones = timezones;
        this.borders = borders;
        this.nativeName = nativeName;
        this.callingCodes = callingCodes;
        this.topLevelDomain = topLevelDomain;
        this.alpha2Code = alpha2Code;
        this.alpha3Code = alpha3Code;
        this.currencies = currencies;
        this.languages = languages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public List<String> getAltSpellings() {
        return altSpellings;
    }

    public void setAltSpellings(List<String> altSpellings) {
        this.altSpellings = altSpellings;
    }

    public String getRelevance() {
        return relevance;
    }

    public void setRelevance(String relevance) {
        this.relevance = relevance;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public Translations getTranslations() {
        return translations;
    }

    public void setTranslations(Translations translations) {
        this.translations = translations;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public List<String> getLatlng() {
        return latlng;
    }

    public void setLatlng(List<String> latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public Object getGini() {
        return gini;
    }

    public void setGini(Object gini) {
        this.gini = gini;
    }

    public List<String> getTimezones() {
        return timezones;
    }

    public void setTimezones(List<String> timezones) {
        this.timezones = timezones;
    }

    public List<String> getBorders() {
        return borders;
    }

    public void setBorders(List<String> borders) {
        this.borders = borders;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public List<String> getCallingCodes() {
        return callingCodes;
    }

    public void setCallingCodes(List<String> callingCodes) {
        this.callingCodes = callingCodes;
    }

    public List<String> getTopLevelDomain() {
        return topLevelDomain;
    }

    public void setTopLevelDomain(List<String> topLevelDomain) {
        this.topLevelDomain = topLevelDomain;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getAlpha3Code() {
        return alpha3Code;
    }

    public void setAlpha3Code(String alpha3Code) {
        this.alpha3Code = alpha3Code;
    }

    public List<String> getCurrencies() {
        return currencies;
    }

    public void setCurrencies(List<String> currencies) {
        this.currencies = currencies;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    @Override
    public String toString() {
        return "CountryDetails{" +
                "name='" + name + '\'' +
                ", capital='" + capital + '\'' +
                ", altSpellings=" + altSpellings +
                ", relevance='" + relevance + '\'' +
                ", region='" + region + '\'' +
                ", subregion='" + subregion + '\'' +
                ", translations=" + translations +
                ", population=" + population +
                ", latlng=" + latlng +
                ", demonym='" + demonym + '\'' +
                ", area=" + area +
                ", gini=" + gini +
                ", timezones=" + timezones +
                ", borders=" + borders +
                ", nativeName='" + nativeName + '\'' +
                ", callingCodes=" + callingCodes +
                ", topLevelDomain=" + topLevelDomain +
                ", alpha2Code='" + alpha2Code + '\'' +
                ", alpha3Code='" + alpha3Code + '\'' +
                ", currencies=" + currencies +
                ", languages=" + languages +
                '}';
    }



    public static class Translations implements Serializable, Parcelable {
        @SerializedName("de")
        private String de;
        @SerializedName("es")
        private String es;
        @SerializedName("fr")
        private String fr;
        @SerializedName("ja")
        private String ja;
        @SerializedName("it")
        private String it;

        public Translations(String de, String es, String fr, String ja, String it) {
            this.de = de;
            this.es = es;
            this.fr = fr;
            this.ja = ja;
            this.it = it;
        }

        public String getDe() {
            return de;
        }

        public void setDe(String de) {
            this.de = de;
        }

        public String getEs() {
            return es;
        }

        public void setEs(String es) {
            this.es = es;
        }

        public String getFr() {
            return fr;
        }

        public void setFr(String fr) {
            this.fr = fr;
        }

        public String getJa() {
            return ja;
        }

        public void setJa(String ja) {
            this.ja = ja;
        }

        public String getIt() {
            return it;
        }

        public void setIt(String it) {
            this.it = it;
        }

        @Override
        public String toString() {
            return "Translations{" +
                    "de='" + de + '\'' +
                    ", es='" + es + '\'' +
                    ", fr='" + fr + '\'' +
                    ", ja='" + ja + '\'' +
                    ", it='" + it + '\'' +
                    '}';
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.de);
            dest.writeString(this.es);
            dest.writeString(this.fr);
            dest.writeString(this.ja);
            dest.writeString(this.it);
        }

        protected Translations(Parcel in) {
            this.de = in.readString();
            this.es = in.readString();
            this.fr = in.readString();
            this.ja = in.readString();
            this.it = in.readString();
        }

        public static final Parcelable.Creator<Translations> CREATOR = new Parcelable.Creator<Translations>() {
            @Override
            public Translations createFromParcel(Parcel source) {
                return new Translations(source);
            }

            @Override
            public Translations[] newArray(int size) {
                return new Translations[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.capital);
        dest.writeStringList(this.altSpellings);
        dest.writeString(this.relevance);
        dest.writeString(this.region);
        dest.writeString(this.subregion);
        dest.writeParcelable(this.translations, flags);
        dest.writeInt(this.population);
        dest.writeStringList(this.latlng);
        dest.writeString(this.demonym);
        dest.writeInt(this.area);
        dest.writeString(String.valueOf(this.gini));
        dest.writeStringList(this.timezones);
        dest.writeStringList(this.borders);
        dest.writeString(this.nativeName);
        dest.writeStringList(this.callingCodes);
        dest.writeStringList(this.topLevelDomain);
        dest.writeString(this.alpha2Code);
        dest.writeString(this.alpha3Code);
        dest.writeStringList(this.currencies);
        dest.writeStringList(this.languages);
    }

    protected CountryDetails(Parcel in) {
        this.name = in.readString();
        this.capital = in.readString();
        this.altSpellings = in.createStringArrayList();
        this.relevance = in.readString();
        this.region = in.readString();
        this.subregion = in.readString();
        this.translations = in.readParcelable(Translations.class.getClassLoader());
        this.population = in.readInt();
        this.latlng = in.createStringArrayList();
        this.demonym = in.readString();
        this.area = in.readInt();
        this.gini = in.readString();
        this.timezones = in.createStringArrayList();
        this.borders = in.createStringArrayList();
        this.nativeName = in.readString();
        this.callingCodes = in.createStringArrayList();
        this.topLevelDomain = in.createStringArrayList();
        this.alpha2Code = in.readString();
        this.alpha3Code = in.readString();
        this.currencies = in.createStringArrayList();
        this.languages = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CountryDetails> CREATOR = new Parcelable.Creator<CountryDetails>() {
        @Override
        public CountryDetails createFromParcel(Parcel source) {
            return new CountryDetails(source);
        }

        @Override
        public CountryDetails[] newArray(int size) {
            return new CountryDetails[size];
        }
    };
}
