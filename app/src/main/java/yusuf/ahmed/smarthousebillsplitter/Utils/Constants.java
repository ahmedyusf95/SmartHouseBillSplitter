package yusuf.ahmed.smarthousebillsplitter.Utils;

import yusuf.ahmed.smarthousebillsplitter.BuildConfig;

/**
 * Created by ahmed on 24/04/2016.
 */
public final class Constants {

    public  static final String Firebase_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String Firebase_Location_Expenses = "Expenses";
    public static final String Firebase_Location_Tasks = "Tasks";
    public static final String Firebase_Location_User = "User";
    public static final String Firebase_Location_HouseMate = "HouseMate";



    public static final String Firebase_URL_Expenses = Firebase_URL + "/" + Firebase_Location_Expenses;
    public static final String Firebase_URL_Tasks = Firebase_URL + "/" + Firebase_Location_Tasks;
    public static final String Firebase_URL_User = Firebase_URL + "/" + Firebase_Location_User;
    public static final String Firebase_URL_HouseMate = Firebase_URL + "/" + Firebase_Location_HouseMate;


    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_Last_Updated = "timestampLastUpdated";
    public static final String FIREBASE_PROPERTY_EXPENSE_NAME = "name";
    public static final String FIREBASE_PROPERTY_EXPENSE_AMOUNT = "amount";
    public static final String FIREBASE_PROPERTY_TASK_NAME = "name";
    public static final String FIREBASE_PROPERTY_TASK_DESCRIPTION = "description";





    public static final String KEY_EXPENSE_ID = "EXPENSE_ID";
    public static final String KEY_TASK_ID = "TASK_ID";





    public static final String KEY_EXPENSE_TITLE = "EXPENSE_TITLE";
    public static final String KEY_EXPENSE_AMOUNT = "EXPENSE_AMOUNT";
    public static final String KEY_TASK_NAME = "TASK_NAME";
    public static final String KEY_TASK_DESCRIPTION = "TASK_DESCRIPTION";
    public static final String KEY_GOOGLE_EMAIL = "GOOGLE_EMAIL";
    public static final String KEY_ENCODED_EMAIL = "ENCODED_EMAIL";
    public static final String KEY_PROVIDER = "PROVIDER";



    public static final String GOOGLE_PROVIDER = "google";
    public static final String PROVIDER_DATA_DISPLAY_NAME = "displayName";


}
