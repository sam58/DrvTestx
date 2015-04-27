package com.sam58.drv.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by sam158 on 22.04.2015.
 */
public abstract class AbstractHuman extends AbstractStoreObject {
    static  DateTimeFormatter FMT= DateTimeFormat.forPattern("dd-MM-yyyy");
    private static final Logger log = Logger.getLogger(AbstractHuman.class);


    @JsonView(JSViews.Base.class)
    private String name;
    @JsonView(JSViews.Base.class)
    private String secondName;
    @JsonView(JSViews.Base.class)
    private String lastName;

    @JsonView(JSViews.PersonalData.class)
    @JsonFormat(shape= JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
    private DateTime birthday;

    @JsonView(JSViews.PersonalData.class)
    private boolean sex; //true - men else women

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public DateTime getBirthday() {
        return birthday;
    }

    @JsonIgnore
    public void setBirthday(DateTime birthday) {
        this.birthday = birthday;
    }

    public void setBirthday(String birthday) {
        try {
            this.birthday = DateTime.parse(birthday, FMT);
        }catch (Exception e){
            log.debug(e);
        }
    }

    public boolean isSex() {return sex;}

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    @JsonView(JSViews.PersonalData.class)
    public int getAge(){
            if(this.birthday!=null){
                Years years= Years.yearsBetween(this.birthday , DateTime.now());
                return years.getYears();
            }else{
                return -1;
            }
    }
}
