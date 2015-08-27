package personal.rohit.costsplitter;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Pair;

import java.math.BigDecimal;

/**
 * Created by rohit on 8/10/15.
 */
public class UserBalancePair implements Comparable, Parcelable {

    private User user;
    private Double balance;

    UserBalancePair() {
        user = null;
        balance = 0.0;
    }

    UserBalancePair(User u, Double d) {
        user = u;
        balance = d;
    }


    protected UserBalancePair(Parcel in) {
        user = in.readParcelable(User.class.getClassLoader());
        balance = in.readDouble();
    }

    public static final Creator<UserBalancePair> CREATOR = new Creator<UserBalancePair>() {
        @Override
        public UserBalancePair createFromParcel(Parcel in) {
            return new UserBalancePair(in);
        }

        @Override
        public UserBalancePair[] newArray(int size) {
            return new UserBalancePair[size];
        }
    };

    User getUser() {
        return user;
    }

    Double getBalance() {
        return balance;
    }

    public void addToBalance(Double d) {
        balance += d;
    }

    public void updateBalance(Double d) {
        balance = d;
    }

    @Override
    public int compareTo(Object another) {
        if(user.equals(((UserBalancePair)another).user)) {
            return balance.compareTo(((UserBalancePair)another).balance);
        }
        else {
            return user.compareTo(((UserBalancePair)another).user);
        }
    }

    @Override
    public String toString() {
        Double balanceRounded = new BigDecimal(balance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(); //Should probably do this while adjusting actual balances
        return user.toString() + " : " + balanceRounded;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(user, flags);
        dest.writeDouble(balance);
    }
}
