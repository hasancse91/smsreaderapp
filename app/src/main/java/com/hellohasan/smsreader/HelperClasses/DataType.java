package com.hellohasan.smsreader.HelperClasses;


/**
 * Created by hasan on 5/16/16.
 */
public class DataType {

    private String englishDateString;
    private String arabicDateString;
    private String sehriTimeString;
    private String iftarTimeString;

    public DataType(String englishDateString, String arabicDateString, String sehriTimeString, String iftarTimeString) {
        this.englishDateString = englishDateString;
        this.arabicDateString = arabicDateString;
        this.sehriTimeString = sehriTimeString;
        this.iftarTimeString = iftarTimeString;
    }

    public String getEnglishDateString() {
        return englishDateString;
    }

    public String getArabicDateString() {
        return arabicDateString;
    }

    public String getSehriTimeString() {
        return sehriTimeString;
    }

    public String getIftarTimeString() {
        return iftarTimeString;
    }
}
