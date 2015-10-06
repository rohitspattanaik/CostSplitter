package personal.rohit.costsplitter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;


public class Transaction implements Comparable, Parcelable {

    String debugTag = "RSP";

    private User    initiator;
    private Double  amount;
    private String  description;
    private Integer id;

    private static int idSeed = 0;

    protected Transaction(Parcel in) {
        initiator = in.readParcelable(User.class.getClassLoader());
        amount = in.readDouble();
        description = in.readString();
        id = in.readInt();
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


    public Transaction() {
        initiator = new User();
        amount = 0.0;
        description = null;
        id = new Integer(idSeed++);
    }

    public Transaction(User u, Double a) {
        initiator = u;
        amount = a;
        description = "No description provided";
        id = new Integer(idSeed++);
    }

    public Transaction(User u, Double a, String d) {
        initiator = u;
        amount = a;
        description = d;
        id = new Integer(idSeed++);
    }

//    public Transaction(Transaction toCopy) {
//        this.initiator = toCopy.initiator;
//        this.amount = toCopy.amount;
//        this.description = toCopy.description;
//        this.id = toCopy.id;
//    }



    public UserBalancePair getUserAmountDetails() {
        return new UserBalancePair(initiator, amount);
    }


    @Override
    public int compareTo(Object another) {
        if(another != null) {
            return this.id.compareTo(((Transaction)another).id);
        }
        return 0; //arbitrary. need to see what works
    }

    @Override
    public boolean equals(Object another) {
        if(another != null) {
            return this.id.equals(((Transaction)another).id);
        }
        return false;
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
        dest.writeInt(id);
        //dest.writeSerializable(dateCreated);
    }

    @Override public String toString() {

        return "\nUser: " + initiator.toString() + "\nAmount: " + amount + "\nDescription: " + description;
    }
}
