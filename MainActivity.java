package personal.rohit.costsplitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //TODO: When using back button with no data, lists wiped clean

    Lobby testLobby;

    ArrayAdapter<UserBalancePair> userBalancePairArrayAdapter;
    ListView userBalanceList;

    ArrayAdapter<Transaction> transactionHistoryAdapter;
    ListView transactionHistory;

    public static final String EXTRA_USER_BALANCE_LIST = "personal.rohit.costsplitter.USER_BALANCE_LIST";

    static final int ADD_USER_ACTIVITY = 10;
    static final int ADD_TRANSACTION_ACTIVITY = 20;

    //Debugging Stuff
    String debugTag = "RSP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testLobby = new Lobby();

        //userBalancePairArrayAdapter = new ArrayAdapter<UserBalancePair>(this, android.R.layout.simple_list_item_1, testLobby.getUserBalanceList());
        userBalanceList = (ListView)findViewById(R.id.user_balance_list);
        //userBalanceList.setAdapter(userBalancePairArrayAdapter);
        //testLobby.addUser(new User("User 4"));

        //transactionHistoryAdapter = new ArrayAdapter<Transaction>(this, android.R.layout.simple_list_item_1, testLobby.getTransactionHistory());
        transactionHistory = (ListView)findViewById(R.id.transaction_list);
        //transactionHistory.setAdapter(transactionHistoryAdapter);
    }

    @Override
    public void onStart() {
        super.onStart();

        userBalancePairArrayAdapter = new ArrayAdapter<UserBalancePair>(this, android.R.layout.simple_list_item_1, testLobby.getUserBalanceList());
       // userBalanceList = (ListView)findViewById(R.id.user_balance_list);
        userBalanceList.setAdapter(userBalancePairArrayAdapter);

        transactionHistoryAdapter = new ArrayAdapter<Transaction>(this, android.R.layout.simple_list_item_1, testLobby.getTransactionHistory());
        //transactionHistory = (ListView)findViewById(R.id.transaction_list);
        transactionHistory.setAdapter(transactionHistoryAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();

        userBalancePairArrayAdapter = new ArrayAdapter<UserBalancePair>(this, android.R.layout.simple_list_item_1, testLobby.getUserBalanceList());
        // userBalanceList = (ListView)findViewById(R.id.user_balance_list);
        userBalanceList.setAdapter(userBalancePairArrayAdapter);

        transactionHistoryAdapter = new ArrayAdapter<Transaction>(this, android.R.layout.simple_list_item_1, testLobby.getTransactionHistory());
        //transactionHistory = (ListView)findViewById(R.id.transaction_list);
        transactionHistory.setAdapter(transactionHistoryAdapter);
    }

    public void addUser(View view) {
        Intent intent = new Intent(this, AddNewUserActivity.class);
        //intent.putParcelableArrayListExtra(EXTRA_USER_BALANCE_LIST, testLobby.getUserBalanceList());
        startActivityForResult(intent, ADD_USER_ACTIVITY);
    }

    public void addTransaction(View view) {
        Intent intent = new Intent(this, AddNewTransaction.class);
        intent.putParcelableArrayListExtra(EXTRA_USER_BALANCE_LIST, testLobby.getUserBalanceList());
        //startActivityForResult(intent, ADD_TRANSACTION_ACTIVITY);
        startActivityForResult(intent, ADD_TRANSACTION_ACTIVITY);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        savedInstanceState.putParcelableArrayList("User Balance List", testLobby.getUserBalanceList());
        savedInstanceState.putParcelableArrayList("Transaction History", testLobby.getTransactionHistory());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {

        ArrayList<UserBalancePair> tempUsers = savedInstanceState.getParcelableArrayList("User Balance List");
        ArrayList<Transaction> tempTrans = savedInstanceState.getParcelableArrayList("Transaction History");

        testLobby.setUserBalanceList(tempUsers);
        testLobby.setTransactionHistory(tempTrans);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case (ADD_USER_ACTIVITY): {
                if(resultCode == Activity.RESULT_OK) {
                    User temp = data.getParcelableExtra(AddNewUserActivity.NEW_USER);
                    testLobby.addUser(temp);
                    userBalancePairArrayAdapter.notifyDataSetChanged();
                }
                break;
            }

            case (ADD_TRANSACTION_ACTIVITY): {
                if(resultCode == RESULT_OK) {
                    Transaction transaction = data.getParcelableExtra(AddNewTransaction.NEW_TRANSACTION);
                    Log.d(debugTag, transaction.getUserAmountDetails().toString());
                    testLobby.acceptTransaction(transaction);
                    transactionHistoryAdapter.notifyDataSetChanged();
                    testLobby.rebalanceLobby();
                    userBalancePairArrayAdapter.notifyDataSetChanged();
                }
                break;
            }
        }
    }
}
