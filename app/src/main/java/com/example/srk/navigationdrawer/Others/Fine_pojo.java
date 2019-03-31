package com.example.srk.navigationdrawer.Others;

import java.util.ArrayList;

public class Fine_pojo {

    String booktitle;
    String accno;
    String authorname;
    String booktype;
    String fine;

    public static ArrayList<Integer> selected = new ArrayList<>();
    public static boolean selecter = false;
    public static int fineamount = 0;



    public Fine_pojo(){

    }

    public Fine_pojo(String booktitle, String accno, String authorname, String booktype, String fine) {
        this.booktitle = booktitle;
        this.accno = accno;
        this.authorname = authorname;
        this.booktype = booktype;
        this.fine = fine;
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

    public String getBooktype() {
        return booktype;
    }

    public void setBooktype(String booktype) {
        this.booktype = booktype;
    }

    public String getFine() {
        return fine;
    }

    public void setFine(String fine) {
        this.fine = fine;
    }
}
