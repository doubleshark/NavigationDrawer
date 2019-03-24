package com.example.srk.navigationdrawer.Others;

import java.util.ArrayList;

public class Fine_ActivitiesItem {

    private String booktitle;
    private String accno;
    private String authorname;
    private String issuedate;
    private String returndate;

    public static ArrayList<Integer> selected = new ArrayList<>();
    public static boolean selecter = false;

    public Fine_ActivitiesItem() {

    }


   /* public Fine_ActivitiesItem(String booktitle, String accno, String authername, String issuedate, String returndate) {

        this.booktitle = booktitle;
        this.accno = accno;
        this.authorname = authorname;
        this.issuedate = issuedate;
        this.returndate = returndate;

    }
*/
    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public String getAccno() {
        return accno;
    }

    public void setAccno(String accno) {
        this.accno = accno;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getIssuedate() {
        return issuedate;
    }

    public void setIssuedate(String issuedate) {
        this.issuedate = issuedate;
    }

    public String getReturndate() {
        return returndate;
    }

    public void setReturndate(String returndate) {
        this.returndate = returndate;
    }
}
