package yusuf.ahmed.smarthousebillsplitter.Utils;

import android.content.Context;

import java.text.SimpleDateFormat;

/**
 * Created by ahmed on 01/05/2016.
 */
public class Utils {

    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Context mContext = null;


    public Utils(Context mContext) {
        this.mContext = mContext;
    }
    public static String encodeEmail(String userEmail) {
        return userEmail.replace(".", ",");
    }

    public static String decodeEmail(String userEmail) {
        return userEmail.replace(",", ".");
    }
}


