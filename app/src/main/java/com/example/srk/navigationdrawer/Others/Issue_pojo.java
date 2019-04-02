package com.example.srk.navigationdrawer.Others;

public class Issue_pojo {

    String booktitle;
    String accno;
    String authorname;
    String issuedate;
    String returndate;

    public Issue_pojo() {

    }

    public Issue_pojo(String booktitle, String accno, String authorname, String issuedate, String returndate) {
        this.booktitle = booktitle;
        this.accno = accno;
        this.authorname = authorname;
        this.issuedate = issuedate;
        this.returndate = returndate;
    }

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
