package com.example.richa.attendance100;

import java.io.Serializable;

/**
 * Created by anshul on 12/28/2017.
 */

public class Studentlistitem implements Serializable {
    String rollnolist, namelist, phonelist;


    public String getRollnolist() {
        return rollnolist;
    }

    public void setRollnolist(String rollnolist) {

        this.rollnolist = rollnolist;
    }

    public String getNamelist()

    {
        return namelist;
    }

    public void setNamelist(String namelist)

    {
        this.namelist = namelist;
    }

    public String getPhonelist() {
        return phonelist;
    }

    public void setPhonelist(String phonelist) {
        this.phonelist = phonelist;
    }


}
