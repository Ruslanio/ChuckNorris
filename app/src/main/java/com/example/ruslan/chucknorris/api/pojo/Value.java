package com.example.ruslan.chucknorris.api.pojo;

/**
 * Created by Ruslan on 27.12.2016.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("joke")
    @Expose
    private String joke;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

}
