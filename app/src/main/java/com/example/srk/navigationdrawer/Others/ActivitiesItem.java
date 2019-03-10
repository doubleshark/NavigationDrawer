package com.example.srk.navigationdrawer.Others;

public class ActivitiesItem {

    private String booktitle_tv;
    private String accno_tv;
    private String authorname_tv;
    private String issuedate_tv;
    private String returndate_tv;


    public ActivitiesItem(String booktitle, String accno, String authername, String issuedate, String returndate) {

        booktitle_tv = booktitle;
        accno_tv = accno;
        authorname_tv = authername;
        issuedate_tv = issuedate;
        returndate_tv = returndate;

    }

    public String getBooktitle_tv() {
        return booktitle_tv;
    }

    public void setBooktitle_tv(String booktitle_tv) {
        this.booktitle_tv = booktitle_tv;
    }

    public String getAccno_tv() {
        return accno_tv;
    }

    public void setAccno_tv(String accno_tv) {
        this.accno_tv = accno_tv;
    }

    public String getAuthorname_tv() {
        return authorname_tv;
    }

    public void setAuthorname_tv(String authorname_tv) {
        this.authorname_tv = authorname_tv;
    }

    public String getIssuedate_tv() {
        return issuedate_tv;
    }

    public void setIssuedate_tv(String issuedate_tv) {
        this.issuedate_tv = issuedate_tv;
    }

    public String getReturndate_tv() {
        return returndate_tv;
    }

    public void setReturndate_tv(String returndate_tv) {
        this.returndate_tv = returndate_tv;
    }
}
