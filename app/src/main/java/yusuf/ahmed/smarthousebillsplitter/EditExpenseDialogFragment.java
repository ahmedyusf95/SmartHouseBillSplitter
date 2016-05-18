package yusuf.ahmed.smarthousebillsplitter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.client.ServerValue;

import java.util.HashMap;

import yusuf.ahmed.smarthousebillsplitter.Model.Expense;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;

/**
 * Created by ahmed on 17/05/2016.
 */
public class EditExpenseDialogFragment extends DialogFragment {

    private String mExpensetitle;
    private String mExpenseamount;
    String  id;


    public static EditExpenseDialogFragment newInstanace(Expense expense, String expenseid){

        EditExpenseDialogFragment editExpenseDialogFragment = new EditExpenseDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constants.KEY_EXPENSE_TITLE, expense.getName());
        bundle.putString(Constants.KEY_EXPENSE_AMOUNT, expense.getAmount());
        bundle.putString(Constants.KEY_EXPENSE_ID, expenseid);
        editExpenseDialogFragment.setArguments(bundle);



        return editExpenseDialogFragment;


    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        Firebase.setAndroidContext(getActivity());

        id = getArguments().getString(Constants.KEY_EXPENSE_ID);

        super.onCreate(savedInstanceState);


    }

    private EditText edittitleinput;
    private EditText editamountninput;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootview = inflater.inflate(R.layout.edit_expense_dialog, null);
        editamountninput = (EditText) rootview.findViewById(R.id.edit_amount);
        edittitleinput = (EditText) rootview.findViewById(R.id.edit_title);


        edittitleinput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == event.ACTION_DOWN ){
                    EditExpenseTitle();
                    EditExpenseAmount();
                }
                return true;
            }
        });

        editamountninput.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == event.ACTION_DOWN) {
                    EditExpenseTitle();
                    EditExpenseAmount();

                }

                return true;
            }
        });



        builder.setView(rootview);




        builder.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                EditExpenseTitle();
                EditExpenseAmount();
            }
        });

        return builder.create();
    }

    public void EditExpenseTitle(){

        final String EditExpenseTitle = edittitleinput.getText().toString();



        if(!EditExpenseTitle.equals("")){

            if(!EditExpenseTitle.equals(mExpensetitle)){

                Firebase Expenseref = new Firebase(Constants.Firebase_URL_Expenses).child(id);

                HashMap<String, Object> updatedFeilds = new HashMap<String, Object>();
                updatedFeilds.put(Constants.FIREBASE_PROPERTY_EXPENSE_NAME, EditExpenseTitle);

                HashMap<String, Object> changedTime = new HashMap<>();
                changedTime.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                updatedFeilds.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, changedTime);

                Expenseref.updateChildren(updatedFeilds);
            }
        }




    }

    public void EditExpenseAmount(){

        final String EditExpenseamount = editamountninput.getText().toString();


        if(!EditExpenseamount.equals("")){

            if(!EditExpenseamount.equals(mExpenseamount)){
                Firebase Expenseref = new Firebase(Constants.Firebase_URL_Expenses).child(id);

                HashMap<String, Object> updatedFeilds = new HashMap<String, Object>();
                updatedFeilds.put(Constants.FIREBASE_PROPERTY_EXPENSE_AMOUNT, EditExpenseamount);

                HashMap<String, Object> changedTime = new HashMap<>();
                changedTime.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, ServerValue.TIMESTAMP);

                updatedFeilds.put(Constants.FIREBASE_PROPERTY_TIMESTAMP, changedTime);

                Expenseref.updateChildren(updatedFeilds);


            }
        }


    }
}
