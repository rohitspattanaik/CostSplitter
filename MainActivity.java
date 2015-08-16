package personal.rohit.costsplitter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    Lobby testLobby;

    ArrayAdapter<UserBalancePair> userBalancePairArrayAdapter;
    ListView userBalanceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testLobby = new Lobby();
        testLobby.addUser(new User("User 1"));
        testLobby.addUser(new User("User 2"));
        testLobby.updateUser("User 1", 100);
        testLobby.addUser(new User("User 3"));
        testLobby.updateUser("User 3", -100);

        userBalancePairArrayAdapter = new ArrayAdapter<UserBalancePair>(this, android.R.layout.simple_list_item_1, testLobby.getUserBalanceList());
        userBalanceList = (ListView)findViewById(R.id.user_balance_list);
        userBalanceList.setAdapter(userBalancePairArrayAdapter);
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
