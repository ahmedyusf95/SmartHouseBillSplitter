package yusuf.ahmed.smarthousebillsplitter.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.firebase.client.ServerValue;

/**
 * Created by ahmed on 06/05/2016.
 */
@JsonIgnoreProperties(ignoreUnknown = true)

public class Tasks {

    private String name;
    private String description;
    private String createdBy;
    @JsonProperty
    private Object TaskLastUpdated;

    public Tasks() {
    }
    public Tasks(String name, String description, String createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
        this.TaskLastUpdated = ServerValue.TIMESTAMP;
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCreatedBy() {
        return createdBy;
    }
    @JsonIgnore
    public Long getLastUpdatedTimestamp() {
        if (TaskLastUpdated instanceof Long) {
            return (Long) TaskLastUpdated;
        }
        else {
            return null;
        }
    }


}
