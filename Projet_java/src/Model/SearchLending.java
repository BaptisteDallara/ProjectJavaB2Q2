package Model;

import java.util.Date;

public class SearchLending {
    private String exemplarId;
    private String title;
    private String beginDate;
    private String endDate;
    private String isReturned;

    public SearchLending(String exemplarId, String title, String beginDate, String endDate, String isReturned) {
        this.exemplarId = exemplarId;
        this.title = title;
        this.beginDate = beginDate;
        this.endDate = endDate;
        this.isReturned = isReturned;
    }
}