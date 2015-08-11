package personal.rohit.costsplitter;

import android.util.Pair;

/**
 * Created by rohit on 8/9/15.
 */
public class Transaction implements Comparable {

    String name;
    User initiator;
    double amount;
    String description;

    public UserBalancePair getUserAmountDetails() {
        return new UserBalancePair(initiator, amount);
    }

    @Override
    public int compareTo(Object another) {
        return 0;
    }
}
