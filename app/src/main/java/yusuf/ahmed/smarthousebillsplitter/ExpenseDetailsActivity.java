package yusuf.ahmed.smarthousebillsplitter;

import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import yusuf.ahmed.smarthousebillsplitter.Model.Expense;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;

public class ExpenseDetailsActivity extends AppCompatActivity {

    private Firebase mExpenseRef;
    private final static String LOG_TAG = ExpenseDetailsActivity.class.getSimpleName();
    private Expense mExpense;
    private String mExpenseId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();

        mExpenseId = intent.getStringExtra(Constants.KEY_EXPENSE_ID);
        if (mExpenseId == null) {

            finish();

            return;
        }


        mExpenseRef = new Firebase(Constants.Firebase_URL_Expenses).child(mExpenseId);

        mExpenseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Expense expense = dataSnapshot.getValue(Expense.class);

                if (expense == null) {

                    finish();


                    return;

                }


                toolbar.setTitle(expense.getName());


                mExpense = expense;


            }


            @Override
            public void onCancelled(FirebaseError firebaseError) {

                Log.e(LOG_TAG, getString(R.string.log_error) + firebaseError.getMessage());
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return super.onCreateOptionsMenu(menu);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.edit) {
            showEditExpenseDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showEditExpenseDialog(){

        DialogFragment fragment = EditExpenseDialogFragment.newInstanace(mExpense, mExpenseId);
        fragment.show(this.getFragmentManager(), "EditExpenseDialogFragment");
    }


}