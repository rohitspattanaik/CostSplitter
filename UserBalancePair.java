package personal.rohit.costsplitter;

import android.util.Pair;

/**
 * Created by rohit on 8/10/15.
 */
public class UserBalancePair implements Comparable {

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

    User getUser() {
        return user;
    }

    Double getBalance() {
        return balance;
    }

    public void addToBalance(Double d) {
        balance += d;
    }

    public void update(Double d) {
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


}
