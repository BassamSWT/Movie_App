
package com.bassam.movieapp.Models;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Videos {

    @SerializedName("results")
    @Expose
    private List<ResultDetail> results = null;

    public List<ResultDetail> getResults() {
        return results;
    }

    public void setResults(List<ResultDetail> results) {
        this.results = results;
    }

}
