package personal.rohit.costsplitter;

import android.util.Pair;

import java.util.ArrayList;

/**
 * Created by rohit on 8/9/15.
 */
public class Lobby {

    ArrayList<Pair<User, Double>> userBalanceList;
    ArrayList<Transaction> transactionHistory;

    public Lobby() {
        userBalanceList = new ArrayList<>();
        transactionHistory = new ArrayList<>();
    }

    public boolean addUser(User user) {
        boolean ret = false;
        try {
            ret = userBalanceList.add(new Pair<User, Double>(user, 0.0));
        } catch (Exception e) {
            ret = false;
        }

        return ret;
    }

    public boolean acceptTransaction(Transaction transaction) {
        boolean ret = false;

        

        return ret;
    }

}
