package personal.rohit.costsplitter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AddNewUserActivity extends AppCompatActivity {

    public final static String NEW_USER = "personal.rohit.costsplitter.NEW_USER";

    //ArrayList<UserBalancePair> userBalancePairs;
    TextView userListPreview;

    Intent intent;

    EditText userName;
    //EditText balanceAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_user);

        //userListPreview = (TextView)findViewById(R.id.user_list_preview);

        intent = getIntent();
        //userBalancePairs = intent.getParcelableArrayListExtra(MainActivity.EXTRA_USER_BALANCE_LIST);

        //for(UserBalancePair p : userBalancePairs) {
            //userListPreview.append(p.toString());
        //}
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_new_user, menu);
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

    public void updateUserList(View view) {
        userName = (EditText)findViewById(R.id.user_name);
        if(userName.getText().toString().replaceAll(" ", "").equals("")) {
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_SHORT).show();
            return;
        }
       // balanceAmount = (EditText)findViewById(R.id.balance_amount);
        //if(balanceAmount.getText().toString().replaceAll(" ", "").equals("")) {
        //    balanceAmount.getText().append("0.0");
        //}

        //Double amount = Double.parseDouble(balanceAmount.getText().toString());

        //userBalancePairs.add(new UserBalancePair(new User(userName.getText().toString()), amount));

        //intent.removeExtra(MainActivity.EXTRA_USER_BALANCE_LIST);
        //intent.putParcelableArrayListExtra(UPDATED_USER_BALANCE_LIST, userBalancePairs);
        intent.putExtra(NEW_USER, new User(userName.getText().toString()));
        setResult(Activity.RESULT_OK, intent);
        finish();
    }


}
