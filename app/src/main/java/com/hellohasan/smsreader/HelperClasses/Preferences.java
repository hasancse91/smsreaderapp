package com.hellohasan.smsreader.HelperClasses;

import android.content.Context;
import android.content.SharedPreferences;


public class Preferences {

    public static final String USER_ID = "user_id";
    public static final String USER_NAME = "name";
    public static final String USER_EMAIL = "email";
    public static final String USER_PHONE = "phone";
    public static final String API_LINK = "api";
    public static final String USER_DESIGNATION = "designation";
    public static final String BAF_NUMBER = "baf_number";
    public static final String USER_ADDRESS = "address";
    public static final String SESSION_TOKEN = "sessiontoken";
    public static final String TOTAL_SUBMISSION = "total_submission";
    public static final String COMPLETED_SUBMISSION = "completed";
    public static final String IN_PROGRESS_SUBMISSION = "in_progress";
    public static final String PENDING_SUBMISSION = "pending";
    public static final String IRRELEVANT = "irrelevant";
    public static final String REMEMBER = "remember";
    public static final String COMPLAIN_DATA = "complain_data";
    public static final String COMPLAIN_HISTORY = "complain_history";

    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    private static Preferences preferences = new Preferences();

    private Preferences(){}


    public static Preferences getInstance(Context context){
        sharedPreferences = context.getSharedPreferences("sharedPreferences_data", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        return preferences;
    }



    public long getUserId(){
        return sharedPreferences.getLong(USER_ID, -1);
    }

    public void setUserId(long userId){
        editor.putLong(USER_ID, userId);
        editor.apply();
        editor.commit();
    }

    public String getUserName(){
        return sharedPreferences.getString(USER_NAME, "");
    }

    public void setUserName(String name){
        editor.putString(USER_NAME, name);
        editor.apply();
        editor.commit();
    }

    public String getUserDesignation(){
        return sharedPreferences.getString(USER_DESIGNATION,"");
    }

    public void setUserDesignation(String string) {
        editor.putString(USER_DESIGNATION, string);
        editor.apply();
        editor.commit();
    }

    public String getBafNumber(){
        return sharedPreferences.getString(BAF_NUMBER, "");
    }

    public void setBafNumber(String bafNum){
        editor.putString(BAF_NUMBER, bafNum);
        editor.apply();
        editor.commit();
    }

    public String getUserAddress(){
        return sharedPreferences.getString(USER_ADDRESS, "");
    }

    public void setUserAddress(String string) {
        editor.putString(USER_ADDRESS, string);
        editor.apply();
        editor.commit();
    }

    public String getUserPhoneNumber() {
        return sharedPreferences.getString(USER_PHONE, "");
    }

    public void setUserPhoneNumber(String userPhone){
        editor.putString(USER_PHONE, userPhone);
        editor.apply();
        editor.commit();
    }

    public String getApiLink(){
        return sharedPreferences.getString(API_LINK, "");
    }

    public void setApiLink(String apiLink){
        editor.putString(API_LINK, apiLink);
        editor.apply();
        editor.commit();
    }

    public String getUserEmail(){
        return sharedPreferences.getString(USER_EMAIL, "");
    }

    public void setUserEmail(String userEmail){
        editor.putString(USER_EMAIL, userEmail);
        editor.apply();
        editor.commit();
    }

    public boolean getRememberMeStatus(){
        return sharedPreferences.getBoolean(REMEMBER, false);
    }

    public void setRememberMeStatus(boolean remember){
        editor.putBoolean(REMEMBER, remember);
        editor.apply();
        editor.commit();
    }

    public String getComplainData(){
        return sharedPreferences.getString(COMPLAIN_DATA, "");
    }

    public void setComplainData(String complains){
        editor.putString(COMPLAIN_DATA, complains);
        editor.apply();
        editor.commit();
    }

    public String getUserSessionToken(){
        return sharedPreferences.getString(SESSION_TOKEN, "");
    }

    public void setUserSessionToken(String token) {
        editor.putString(SESSION_TOKEN, token);
        editor.apply();
        editor.commit();
    }

    public long getUserTotalSubmission() {
        return sharedPreferences.getLong(TOTAL_SUBMISSION, 0);
    }

    public void setUserTotalSubmission(long total) {
        editor.putLong(TOTAL_SUBMISSION, total);
        editor.apply();
        editor.commit();
    }

    public long getUserInProgressSubmission() {
        return sharedPreferences.getLong(IN_PROGRESS_SUBMISSION, 0);
    }

    public void setUserInProgressSubmission(long inProgress) {
        editor.putLong(IN_PROGRESS_SUBMISSION, inProgress);
        editor.apply();
        editor.commit();
    }

    public long getUserPendingSubmission() {
        return sharedPreferences.getLong(PENDING_SUBMISSION, 0);
    }

    public void setUserPendingSubmission(long pending) {
        editor.putLong(PENDING_SUBMISSION, pending);
        editor.apply();
        editor.commit();
    }

    public long getUserCompletedSubmission() {
        return sharedPreferences.getLong(COMPLETED_SUBMISSION, 0);
    }

    public void setUserCompletedSubmission(long completed) {
        editor.putLong(COMPLETED_SUBMISSION, completed);
        editor.apply();
        editor.commit();
    }

    public String getHistory(){
        return sharedPreferences.getString(COMPLAIN_HISTORY, "");
    }

    public void setHistory(String history) {
        editor.putString(COMPLAIN_HISTORY, history);
        editor.apply();
        editor.commit();
    }

    public long getUserIrrelevantSubmission() {
        return sharedPreferences.getLong(IRRELEVANT, 0);
    }

    public void setUserIrrelevantSubmission(long rejected){
        editor.putLong(IRRELEVANT, rejected);
        editor.apply();
        editor.commit();
    }
}
