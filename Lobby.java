package personal.rohit.costsplitter;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rohit on 8/9/15.
 */
public class Lobby {

    ArrayList<UserBalancePair> userBalanceList;
    ArrayList<Transaction> transactionHistory;

    public Lobby() {
        userBalanceList = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }

    public boolean addUser(User user) {
        boolean ret = false;
        try {
            ret = userBalanceList.add(new UserBalancePair(user, 0.0));
            Collections.sort(userBalanceList);
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

    public boolean updateUser(User user, double amount) {
        boolean ret = false;

        if(!userBalanceList.contains(user)) {
            //Add user first
            return false;
        }


        return ret;
    }

    public boolean acceptTransaction(Transaction transaction) {
        boolean ret = false;

        if(transaction == null) {
            return false;
        }



        return ret;
    }

}
