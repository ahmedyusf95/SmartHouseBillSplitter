package yusuf.ahmed.smarthousebillsplitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.ServerValue;

/**
 * Created by ahmed on 20/04/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Expense {

    private String amount;
    private String createdBy;

    private String name;
    @JsonProperty
    private Object LastUpdated;



    public Expense() {
    }


    public Expense(String amount, String createdBy, String name) {
        this.amount = amount;
        this.createdBy = createdBy;
        this.name = name;
       // HashMap<String, Object> LastUpdatedObj = new HashMap<String, Object>();
        //LastUpdatedObj.put(Constants.FIREBASE_PROPERTY_TIMESTAMP , ServerValue.TIMESTAMP);
            this.LastUpdated = ServerValue.TIMESTAMP;
    }

    public String getName() {
        return name;
    }

    public String getAmount() {
        return amount;
    }

    public String getCreatedBy() {
        return createdBy;
    }
//
//    public HashMap<String, Object> getLastUpdated() {
//        return LastUpdated;
//    }

//    @JsonIgnore
//    public long getLastUpdatedLong(){
//
//        return (long) LastUpdated.get(Constants.FIREBASE_PROPERTY_TIMESTAMP);
//    }

    @JsonIgnore
    public Long getLatUpdatedTimestamp() {
        if (LastUpdated instanceof Long) {
            return (Long) LastUpdated;
        }
        else {
            return null;
        }
    }




}


