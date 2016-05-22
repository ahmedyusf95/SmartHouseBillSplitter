package yusuf.ahmed.smarthousebillsplitter;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;

import yusuf.ahmed.smarthousebillsplitter.Model.Expense;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;

/**
 * Created by ahmed on 23/04/2016.
 */
public class AddExpenseDialogFragment extends DialogFragment {

    String EncodedEmail;


    private EditText editTextexpensetitle;
    private EditText editTextexpenseamount;

    public static AddExpenseDialogFragment newInstance(String encodedEmail) {
     AddExpenseDialogFragment addExpenseDialogFragment = new AddExpenseDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_ENCODED_EMAIL, encodedEmail);
        addExpenseDialogFragment.setArguments(bundle);
        return addExpenseDialogFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        EncodedEmail = getArguments().getString(Constants.KEY_ENCODED_EMAIL);

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootview = inflater.inflate(R.layout.dialog_add_expense, null);
        editTextexpensetitle = (EditText) rootview.findViewById(R.id.expense_title);
        editTextexpenseamount = (EditText) rootview.findViewById(R.id.expense_amount);


       editTextexpenseamount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
           @Override
           public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
               if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN){
                   Addexpense();
               }
               return true;
           }
       });

        editTextexpensetitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == KeyEvent.ACTION_DOWN) {
                    Addexpense();

                }

                return true;
            }
        });



        builder.setView(rootview);




        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Addexpense();
            }
        });

        return builder.create();
    }
public void Addexpense(){


    Firebase ref = new Firebase(Constants.Firebase_URL);
    Firebase Expenseref = new Firebase(Constants.Firebase_URL_Expenses);

    Firebase newExpenseListRef = Expenseref.push();
    String userEnteredTitle = editTextexpensetitle.getText().toString();
    String userEnteredAmount = editTextexpenseamount.getText().toString();

    Expense expense = new Expense(userEnteredAmount, EncodedEmail, userEnteredTitle );


         newExpenseListRef.setValue(expense);
}

}
