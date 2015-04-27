package com.sam58.drv.model;

import com.fasterxml.jackson.annotation.JsonView;

/**
 * Created by sam158 on 22.04.2015.
 */
public class DriverPerson extends AbstractHuman {
    @JsonView(JSViews.AllData.class)
    private String drvClass;


    public String getDrvClass() {
        return drvClass;
    }
    public void setDrvClass(String drvClass) {
        this.drvClass = drvClass;
    }


}
