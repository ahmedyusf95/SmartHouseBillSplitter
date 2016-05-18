package yusuf.ahmed.smarthousebillsplitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import yusuf.ahmed.smarthousebillsplitter.Model.Tasks;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;

/**
 * Created by ahmed on 01/05/2016.
 */
public class TasksFragment extends Fragment {


    public static TasksFragment newInstance(){
        TasksFragment fragment = new TasksFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return  fragment;
    }

    public TasksFragment() {
    }

    FirebaseRecyclerAdapter<Tasks,TaskViewHolder> mRecyclerAdapter;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(getActivity());

    }

    private RecyclerView TasksRecyclerView;
    Firebase TaskRef = new Firebase(Constants.Firebase_URL).child(Constants.Firebase_Location_Tasks);



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_tasks , container, false);

        TasksRecyclerView = (RecyclerView) view.findViewById(R.id.tasks_recycler_view);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        TasksRecyclerView.setLayoutManager(layoutManager);

       mRecyclerAdapter = new FirebaseRecyclerAdapter<Tasks, TaskViewHolder>(Tasks.class,
                R.layout.task_custom_row, TaskViewHolder.class,TaskRef ) {
            @Override
            protected void populateViewHolder(TaskViewHolder taskViewHolder, Tasks tasks, final int i) {

                taskViewHolder.TaskCreatedBy.setText(tasks.getCreatedBy());
                taskViewHolder.TaskDescriptionView.setText(tasks.getDescription());
                taskViewHolder.TaskNameTextView.setText(tasks.getName());

                taskViewHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), TasksDetailsActivity.class);

                        String taskid = mRecyclerAdapter.getRef(i).getKey();
                        intent.putExtra(Constants.KEY_TASK_ID, taskid);
                        startActivity(intent);


                    }
                });
            }
        };

        TasksRecyclerView.setAdapter(mRecyclerAdapter);

        return view;

    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private TextView TaskNameTextView;
        private TextView TaskDescriptionView;
        private TextView TaskCreatedBy;



        public TaskViewHolder(View itemView) {
            super(itemView);

            mView = itemView;


            TaskNameTextView = (TextView) itemView.findViewById(R.id.taskname_textview);
            TaskDescriptionView = (TextView) itemView.findViewById(R.id.task_description_textview);
            TaskCreatedBy = (TextView) itemView.findViewById(R.id.task_description_textview);

        }
    }




    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

