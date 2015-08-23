package personal.rohit.costsplitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AddNewTransaction extends AppCompatActivity {

    public final static String NEW_TRANSACTION = "personal.rohit.costsplitter.NEW_TRANSACTION";

    Spinner userDropDown;
    ArrayAdapter<User> userListAdapter;
    Intent intent;


    public ArrayList<User> getUsers(List<UserBalancePair> list) {
        ArrayList<User> users = new ArrayList<>();

        for(UserBalancePair p : list) {
            users.add(p.getUser());
        }

        return users;
    }

    public void submitTransaction(View view) {
        EditText amountText = (EditText)findViewById(R.id.transaction_amount);
        Double amount = Double.parseDouble(amountText.getText().toString());
        if(amount == 0) {
            Toast.makeText(this, "Transaction Amount Not Specified", Toast.LENGTH_SHORT).show();
            return;
        }
        intent.removeExtra(MainActivity.EXTRA_USER_BALANCE_LIST);
        intent.putExtra(NEW_TRANSACTION, new Transaction((User) userDropDown.getSelectedItem(), amount));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_transaction);

        intent = getIntent();

        ArrayList<UserBalancePair> pairList = intent.getParcelableArrayListExtra(MainActivity.EXTRA_USER_BALANCE_LIST);
        //userList = getUsers(pairList);
        userListAdapter = new ArrayAdapter<User>(this, R.layout.support_simple_spinner_dropdown_item, getUsers(pairList));
        userDropDown = (Spinner)findViewById(R.id.user_drop_down);
        userDropDown.setAdapter(userListAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_transaction, menu);
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
