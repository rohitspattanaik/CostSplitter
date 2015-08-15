package personal.rohit.costsplitter;

import android.util.Pair;

import java.util.Date;

/**
 * Created by rohit on 8/9/15.
 */
public class Transaction implements Comparable {

    private User initiator;
    private Double amount;
    private String description;
    private Date dateCreated;

    public UserBalancePair getUserAmountDetails() {
        return new UserBalancePair(initiator, amount);
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
}
