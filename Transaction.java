package personal.rohit.costsplitter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/**
 * Created by rohit on 8/9/15.
 */
public class Transaction implements Comparable, Parcelable {

    String debugTag = "RSP";

    private User initiator;
    private Double amount;
    private String description;
    private GregorianCalendar dateCreated;

    protected Transaction(Parcel in) {
        initiator = in.readParcelable(User.class.getClassLoader());
        amount = in.readDouble();
        description = in.readString();
        dateCreated = (GregorianCalendar)in.readSerializable();
    }

    public static final Creator<Transaction> CREATOR = new Creator<Transaction>() {
        @Override
        public Transaction createFromParcel(Parcel in) {
            return new Transaction(in);
        }

        @Override
        public Transaction[] newArray(int size) {
            return new Transaction[size];
        }
    };

    private void setDate() {
        //TODO get date for transaction

        String[] ids = TimeZone.getAvailableIDs(-6 * 60 * 60 * 1000);
        if(ids.length == 0) {
            Log.wtf(debugTag, "No IDs found for time zone");
        }
        //no daylight savings time stuff done yet

        SimpleTimeZone cst = new SimpleTimeZone(-6 * 60 * 60 * 1000, ids[0]);
        dateCreated = new GregorianCalendar(cst);
        dateCreated.setTime(new Date()); //set to current date
    }

    public Transaction() {
        initiator = new User();
        amount = 0.0;
        description = null;
        dateCreated = new GregorianCalendar();
    }

    public Transaction(User u, Double a) {
        initiator = u;
        amount = a;
        description = "";
        setDate();
    }

    public Transaction(User u, Double a, String d) {
        initiator = u;
        amount = a;
        description = d;
        setDate();
    }

    public Transaction(User u, Double a, String d, GregorianCalendar c) {
        initiator = u;
        amount = a;
        description = d;
        dateCreated = c;
    }

    public UserBalancePair getUserAmountDetails() {
        return new UserBalancePair(initiator, amount);
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("initiator", initiator.toJSON());
            jsonObject.put("amount", amount);
            jsonObject.put("description", description);
            //TODO: figure out date
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

        return jsonObject;
    }

    @Override
    public int compareTo(Object another) {
        if(!dateCreated.equals(((Transaction)another).dateCreated)) {
            return dateCreated.compareTo(((Transaction) another).dateCreated);
        }
        else if(!amount.equals(((Transaction)another).amount)) {
            return amount.compareTo(((Transaction) another).amount);
        }
        else {
            return initiator.compareTo(((Transaction)another).initiator);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(initiator, flags);
        dest.writeDouble(amount);
        dest.writeString(description);
        dest.writeSerializable(dateCreated);
    }

    @Override public String toString() {

        return "Date: " + dateCreated.get(Calendar.HOUR) + ":" + dateCreated.get(Calendar.MINUTE) + " "
                + dateCreated.get(Calendar.MONTH) + "/" + dateCreated.get(Calendar.DATE) + "/" + dateCreated.get(Calendar.YEAR)
                + "\nUser: " + initiator.toString() + " Amount: " + amount + "\nDescription: " + description;
    }
}
