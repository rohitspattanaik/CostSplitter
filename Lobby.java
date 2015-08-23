package personal.rohit.costsplitter;

import android.util.Pair;
import android.widget.Toast;

import java.math.BigDecimal;
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
        totalBalance = new Double(0.0);
    }

    public boolean addUser(User user) {
        boolean ret = false;
        if(getUserIndex(user) != -1) {
            return false;
        }
        try {
            ret = userBalanceList.add(new UserBalancePair(user, 0.0));
            Collections.sort(userBalanceList);
        } catch (Exception e) {
            ret = false;
        }
        return ret;
    }

//    public boolean addUser(UserBalancePair user) {
//        boolean ret = false;
//        try {
//            ret = userBalanceList.add(user);
//            Collections.sort(userBalanceList);
//        } catch (Exception e) {
//            ret = false;
//        }
//        return ret;
//    }

    private int getUserIndex(User u) {
        //O(n) search of list. Should be small list so not very expensive
        for(int i = 0; i < userBalanceList.size(); ++i) {
            if(userBalanceList.get(i).getUser().equals(u)) {
                return i;
            }
        }

        return -1;
    }

    private int getUserIndex(String u) {
        //O(n) search of list. Should be small list so not very expensive
        for(int i = 0; i < userBalanceList.size(); ++i) {
            if(userBalanceList.get(i).getUser().toString().equals(u)) {
                return i;
            }
        }

        return -1;
    }

    public boolean updateUser(User user, Double amount) {
        //boolean ret = false;

        if(getUserIndex(user) == -1) {
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

    public boolean updateUser(String user, double amount) {
        //boolean ret = false;

        int index = getUserIndex(user);
        if(index == -1) {
            return false;
        }

        userBalanceList.get(index).addToBalance(amount);

        return true;
    }

    public void setUserBalanceList(ArrayList<UserBalancePair> list) {
        //userBalanceList = list;
        userBalanceList.clear();
        for(UserBalancePair b : list) {
            userBalanceList.add(b);
        }
    }


    public void setTransactionHistory(ArrayList<Transaction> list) {
        //userBalanceList = list;
        transactionHistory.clear();
        for(Transaction b : list) {
            transactionHistory.add(b);
        }
    }

    public ArrayList<UserBalancePair> getUserBalanceList() {
        return userBalanceList;
    }


    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public boolean acceptTransaction(Transaction transaction) {
        boolean ret = false;

        if(transaction == null) {
            return false;
        }

        User tempUser = transaction.getUserAmountDetails().getUser();
        Double bal = transaction.getUserAmountDetails().getBalance();
        if(!updateUser(tempUser, bal)) {
            return false;
        }
        //totalBalance += transaction.getUserAmountDetails().getBalance();
        totalBalance += bal;

//        double amountPerUser = bal/userBalanceList.size();
//        for(UserBalancePair p : userBalanceList) {
//            if(p.getUser().equals(tempUser)) {
//                p.updateBalance(amountPerUser);
//            }
//            else {
//                p.updateBalance(amountPerUser * -1);
//            }
//        }

        transactionHistory.add(transaction);
        Collections.sort(transactionHistory); //TODO: Check whether transactions getting sorted properly
        return true;
    }

    public void rebalanceLobby() {
        if(totalBalance == 0.0) {
            //lobby already balanced
            return;
        }
        double amountPerUser = totalBalance / userBalanceList.size();
        //Double amountPerUserRounded = new BigDecimal(amountPerUser).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        for(UserBalancePair pair : userBalanceList) {
            pair.addToBalance(amountPerUser * -1); //subtract balance from what user has already put into lobby
            totalBalance -= amountPerUser;
        }
        if(totalBalance > 0.0) {
            //figure out who to give remaining amount to. for now, passing it on to whoever owes the least
            //totalBalance = new BigDecimal(totalBalance).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            int index = 0;
            double max = 0.0;
            for(int i = 0; i < userBalanceList.size(); ++i) {
                if(userBalanceList.get(i).getBalance() > max) {
                    index = i;
                    max = userBalanceList.get(i).getBalance();
                }
            }
            userBalanceList.get(index).addToBalance(totalBalance * -1);
            totalBalance = 0.0;
        }
    }

}
