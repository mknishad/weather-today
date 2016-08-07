package com.elitedevelopers.weathertoday.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Nishad on 07-Aug-16.
 */
public class Rain {

    @SerializedName("3h")
    @Expose
    private Double _3h;

    /**
     *
     * @return
     * The _3h
     */
    public Double get3h() {
        return _3h;
    }

    /**
     *
     * @param _3h
     * The 3h
     */
    public void set3h(Double _3h) {
        this._3h = _3h;
    }

}
