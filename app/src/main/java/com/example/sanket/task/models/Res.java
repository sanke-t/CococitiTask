package com.example.sanket.task.models;

/**
 * Created by sanket on 04/06/17.
 */

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("info")
    @Expose
    private String info;
    @SerializedName("showcases")
    @Expose
    private List<Showcase> showcases = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Showcase> getShowcases() {
        return showcases;
    }

    public void setShowcases(List<Showcase> showcases) {
        this.showcases = showcases;
    }

}