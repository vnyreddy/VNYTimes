package com.vinay.wizdem.vnytimes.models.books;

/**
 * Created by vinay_1 on 10/25/2017.
 */
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuthorHistory {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("copyright")
    @Expose
    private String copyright;
    @SerializedName("num_results")
    @Expose
    private Integer numResults;
    @SerializedName("results")
    @Expose
    private List<AuthorHistoryResult> authorHistoryResults = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Integer getNumResults() {
        return numResults;
    }

    public void setNumResults(Integer numResults) {
        this.numResults = numResults;
    }

    public List<AuthorHistoryResult> getAuthorHistoryResults() {
        return authorHistoryResults;
    }

    public void setResults(List<AuthorHistoryResult> authorHistoryResults) {
        this.authorHistoryResults = authorHistoryResults;
    }

}
