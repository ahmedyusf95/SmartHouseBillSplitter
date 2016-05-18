package yusuf.ahmed.smarthousebillsplitter.Model;

/**
 * Created by ahmed on 06/05/2016.
 */
public class Tasks {

    private String name;
    private String description;
    private String createdBy;




    public Tasks() {
    }


    public Tasks(String name, String description, String createdBy) {
        this.name = name;
        this.description = description;
        this.createdBy = createdBy;
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

}
