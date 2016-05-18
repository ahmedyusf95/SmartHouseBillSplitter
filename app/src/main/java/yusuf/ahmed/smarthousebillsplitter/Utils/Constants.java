package yusuf.ahmed.smarthousebillsplitter.Utils;

import yusuf.ahmed.smarthousebillsplitter.BuildConfig;

/**
 * Created by ahmed on 24/04/2016.
 */
public final class Constants {

    public  static final String Firebase_URL = BuildConfig.UNIQUE_FIREBASE_ROOT_URL;
    public static final String Firebase_Location_Expenses = "Expenses";
    public static final String Firebase_Location_Tasks = "Tasks";

    public static final String Firebase_URL_Expenses = Firebase_URL + "/" + Firebase_Location_Expenses;
    public static final String Firebase_URL_Tasks = Firebase_URL + "/" + Firebase_Location_Tasks;
    public static final String FIREBASE_PROPERTY_TIMESTAMP = "timestamp";
    public static final String FIREBASE_PROPERTY_TIMESTAMP_Last_Updated = "timestampLastUpdated";
    public static final String FIREBASE_PROPERTY_EXPENSE_NAME = "name";
    public static final String FIREBASE_PROPERTY_EXPENSE_AMOUNT = "amount";





    public static final String KEY_EXPENSE_ID = "EXPENSE_ID";
    public static final String KEY_TASK_ID = "TASK_ID";




    public static final String KEY_EXPENSE_TITLE = "EXPENSE_TITLE";
    public static final String KEY_EXPENSE_AMOUNT = "EXPENSE_AMOUNT";

}
