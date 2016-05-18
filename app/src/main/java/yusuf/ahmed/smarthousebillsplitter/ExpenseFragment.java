package yusuf.ahmed.smarthousebillsplitter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.firebase.ui.FirebaseRecyclerAdapter;

import java.util.Date;

import yusuf.ahmed.smarthousebillsplitter.Model.Expense;
import yusuf.ahmed.smarthousebillsplitter.Utils.Constants;
import yusuf.ahmed.smarthousebillsplitter.Utils.Utils;

/**
 * A placeholder fragment containing a simple view.
 */
public class ExpenseFragment extends Fragment  {


    public ExpenseFragment() {

    }

    public static ExpenseFragment newInstance(){
         ExpenseFragment fragment = new ExpenseFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return  fragment;

    }

    private RecyclerView ExpenseRecyclerView;
    Firebase ExpenseRef = new Firebase(Constants.Firebase_URL).child(Constants.Firebase_Location_Expenses);
    FirebaseRecyclerAdapter<Expense,ExpenseHolder> mRecyclerAdapter;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        Firebase.setAndroidContext(getActivity());


    }







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_expense , container, false);

        ExpenseRecyclerView = (RecyclerView) view.findViewById(R.id.Expense_Recycler_View);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        ExpenseRecyclerView.setLayoutManager(layoutManager);

             mRecyclerAdapter  = new FirebaseRecyclerAdapter<Expense, ExpenseHolder>(Expense.class ,
                R.layout.custom_expense_row_layout,ExpenseHolder.class, ExpenseRef ) {
            @Override
            public void populateViewHolder(ExpenseHolder expenseHolder, Expense expense, final int i) {
                expenseHolder.nameTextView.setText(expense.getName());
                expenseHolder.amountTextView.setText(expense.getAmount());
               expenseHolder.createdByTextView.setText(expense.getCreatedBy());
                expenseHolder.shoppingImageView.setImageResource(R.drawable.shopping);
                expenseHolder.LastUpdatedTextView.setText(Utils.SIMPLE_DATE_FORMAT.format(new Date(expense.getLatUpdatedTimestamp())));


                expenseHolder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getActivity(), ExpenseDetailsActivity.class);

                       String expenseid = mRecyclerAdapter.getRef(i).getKey();
                        intent.putExtra(Constants.KEY_EXPENSE_ID, expenseid);
                        startActivity(intent);







                    }
                });
            }

        };


        ExpenseRecyclerView.setAdapter(mRecyclerAdapter);

        return view;





    }









    public static class ExpenseHolder extends RecyclerView.ViewHolder  {
        View mView;
      private TextView nameTextView;
        private TextView amountTextView;
        private TextView createdByTextView;
        private ImageView shoppingImageView;
        private TextView LastUpdatedTextView;


        public ExpenseHolder(View itemView) {
            super(itemView);
            mView = itemView;

            nameTextView = (TextView) itemView.findViewById(R.id.name);
            amountTextView = (TextView) itemView.findViewById(R.id.amount);
            createdByTextView = (TextView) itemView.findViewById(R.id.CreatedBy);
            shoppingImageView = (ImageView) itemView.findViewById(R.id.ExpenseImage);
            LastUpdatedTextView = (TextView) itemView.findViewById(R.id.LastUpdatedTextView);


    }



















/*
        public ExpenseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.custom_expense_row_layout, parent, false);
            return  new ExpenseHolder(view);
        }*/

    }


}
