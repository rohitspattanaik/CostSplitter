package personal.rohit.costsplitter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Lobby testLobby;

    ArrayAdapter<UserBalancePair> userBalancePairArrayAdapter;
    ListView userBalanceList;

    public static final String EXTRA_USER_BALANCE_LIST = "personal.rohit.costsplitter.USER_BALANCE_LIST";

    //Debugging Stuff
    String debugTag = "RSP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testLobby = new Lobby();
        Log.d(debugTag, "created lobby");
        testLobby.addUser(new User("User 1"));
        Log.d(debugTag, "added user 1");
        testLobby.addUser(new User("User 2"));
        Log.d(debugTag, "added user 2");
        testLobby.updateUser("User 1", 100);
        Log.d(debugTag, "updated user 1");
        testLobby.addUser(new User("User 3"));
        Log.d(debugTag, "added user 3");
        testLobby.updateUser("User 3", -100);
        Log.d(debugTag, "updated user 3");

        userBalancePairArrayAdapter = new ArrayAdapter<UserBalancePair>(this, android.R.layout.simple_list_item_1, testLobby.getUserBalanceList());
        userBalanceList = (ListView)findViewById(R.id.user_balance_list);
        userBalanceList.setAdapter(userBalancePairArrayAdapter);
        testLobby.addUser(new User("User 4"));
    }

    public void addUser(View view) {
        Intent intent = new Intent(this, AddNewUserActivity.class);
        intent.putParcelableArrayListExtra(EXTRA_USER_BALANCE_LIST, testLobby.getUserBalanceList());
        startActivity(intent);
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
}
