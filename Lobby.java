package personal.rohit.costsplitter;

import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by rohit on 8/9/15.
 */
public class Lobby {

    private ArrayList<UserBalancePair> userBalanceList;
    private ArrayList<Transaction> transactionHistory;
    private Double totalBalance;

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

    public int getUserIndex(User u) {
        //O(n) search of list. Should be small list so not very expensive
        for(int i = 0; i < userBalanceList.size(); ++i) {
            if(userBalanceList.get(i).getUser().equals(u)) {
                return i;
            }
        }

        return -1;
    }

    public boolean updateUser(User user, double amount) {
        //boolean ret = false;

        if(!userBalanceList.contains(user)) {
            //Add user first
            return false;
        }

        int index = getUserIndex(user);
        if(index == -1) {
            //wtf
            return false;
        }
        userBalanceList.get(index).addToBalance(amount);

        return true;
    }

    public ArrayList<UserBalancePair> getUserBalanceList() {
        return userBalanceList;
    }

    public boolean acceptTransaction(Transaction transaction) {
        boolean ret = false;

        if(transaction == null) {
            return false;
        }

        if(!updateUser(transaction.getUserAmountDetails().getUser(), transaction.getUserAmountDetails().getBalance())) {
            return false;
        }
        totalBalance += transaction.getUserAmountDetails().getBalance();

        return true;
    }

    public void rebalanceLobby() {
        if(totalBalance == 0.0) {
            //lobby already balanced
            return;
        }
        double amountPerUser = totalBalance / userBalanceList.size();
        for(UserBalancePair pair : userBalanceList) {
            pair.addToBalance(amountPerUser * -1); //subtract balance from what user has already put into lobby
            totalBalance -= amountPerUser;
        }
        if(totalBalance != 0.0) {
            //figure out who to give remaining amount to.

            totalBalance = 0.0;
        }
    }

}
