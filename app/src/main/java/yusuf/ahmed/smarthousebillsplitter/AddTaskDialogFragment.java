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

import yusuf.ahmed.smarthousebillsplitter.Model.Tasks;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;

/**
 * Created by ahmed on 06/05/2016.
 */
public class AddTaskDialogFragment extends DialogFragment {



    private EditText editTexttitle;
    private EditText editTextdescription;

    public static AddTaskDialogFragment newInstance() {
        AddTaskDialogFragment addTaskDialogFragment = new AddTaskDialogFragment();
        Bundle bundle = new Bundle();
        addTaskDialogFragment.setArguments(bundle);
        return addTaskDialogFragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();

        View rootview = inflater.inflate(R.layout.dialog_add_task, null);
        editTexttitle = (EditText) rootview.findViewById(R.id.task_title);
        editTextdescription = (EditText) rootview.findViewById(R.id.task_description);


        editTexttitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == event.ACTION_DOWN ){
                    AddTask();
                }
                return true;
            }
        });

        editTextdescription.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE || event.getAction() == event.ACTION_DOWN) {
                    AddTask();

                }

                return true;
            }
        });



        builder.setView(rootview);




        builder.setPositiveButton("Create", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                AddTask();
            }
        });

        return builder.create();
    }
    public void AddTask(){

        Firebase ref = new Firebase(Constants.Firebase_URL);
        Firebase Taskref = new Firebase(Constants.Firebase_URL_Tasks);

        Firebase newTaskListRef = Taskref.push();
        String userEnteredTitle = editTexttitle.getText().toString();
        String userEnteredDescription = editTextdescription.getText().toString();

        Tasks task = new Tasks(userEnteredTitle, userEnteredDescription, "anon" );


        newTaskListRef.setValue(task);

    }

}


