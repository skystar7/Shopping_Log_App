package mobi.hoopoe.android.shoppinglog.model;

import android.text.format.DateFormat;

import java.util.Date;
import java.util.UUID;


/**
 * Created by Ahmad on 3/13/2016.
 */

public class Shop {

    private UUID mID;
    private String mTitle;
    private Date mDate;
    private boolean mCompleted;


    public Shop() {
        mID=UUID.randomUUID();
        mDate = new Date();
    }

    public UUID getID() {
        return mID;
    }


    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public void setCompleted(boolean completed) {
        mCompleted = completed;
    }

    public String getFormattedDate(){
        DateFormat formatter=new DateFormat();

        return (String)formatter.format("EEE, MMM d, yyyy", mDate);
    }
}
