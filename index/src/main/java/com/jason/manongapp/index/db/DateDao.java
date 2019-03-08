package com.jason.manongapp.index.db;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "index_date")
public class DateDao {


    @Property(nameInDb = "date_string")
    private String dateString;


    @Generated(hash = 1245827399)
    public DateDao(String dateString) {
        this.dateString = dateString;
    }

    @Generated(hash = 890239956)
    public DateDao() {
    }


    public String getDateString() {
        return this.dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    @Override
    public String toString() {
        return "DateDao{" +
                "dateString='" + dateString + '\'' +
                '}';
    }
}
