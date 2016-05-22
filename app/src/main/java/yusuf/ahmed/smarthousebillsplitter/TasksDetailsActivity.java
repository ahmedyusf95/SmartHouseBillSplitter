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

import yusuf.ahmed.smarthousebillsplitter.Model.Tasks;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;

/**
 * Created by ahmed on 18/05/2016.
 */
public class TasksDetailsActivity extends AppCompatActivity {
    private Firebase mTaskRef;
    private final static String LOG_TAG = TasksDetailsActivity.class.getSimpleName();
    private Tasks mTask;
    private String mTaskId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_layout);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent intent = this.getIntent();

        mTaskId = intent.getStringExtra(Constants.KEY_TASK_ID);
        if (mTaskId == null) {

            finish();

            return;
        }


        mTaskRef = new Firebase(Constants.Firebase_URL_Tasks).child(mTaskId);

        mTaskRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Tasks task = dataSnapshot.getValue(Tasks.class);

                if (task == null) {

                    finish();


                    return;

                }


                toolbar.setTitle(task.getName());


                mTask = task;


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
            showEditTaskDialog();
            return true;
        } else

        if(id == R.id.delete){
            showDeleteTaskDialog();
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void showEditTaskDialog(){

        DialogFragment fragment = EditTaskDialogFragment.newInstanace(mTask, mTaskId);
        fragment.show(this.getFragmentManager(), "EditTaskDialogFragment");
    }

    public  void  showDeleteTaskDialog(){

        DialogFragment fragment = DeleteTaskDialogFragment.newInstance(mTask, mTaskId);
        fragment.show(this.getFragmentManager(), "DeleteTaskDialogFragment");
    }





}
