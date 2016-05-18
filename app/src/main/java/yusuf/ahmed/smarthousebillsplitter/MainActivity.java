package yusuf.ahmed.smarthousebillsplitter;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;
import com.firebase.client.Logger;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);


        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setLogLevel(Logger.Level.DEBUG);








//
//        ExpenseFragment expenseFragment = new ExpenseFragment();
//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().replace(R.id.container , expenseFragment).commit();
//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);



        SetScreen();




        }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.fragment_expense_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public void setHasOptionsMenu(boolean hasOptionsMenu) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_item_new_expense) {
            AddExpenseDialogFragment addExpenseDialogFragment = new AddExpenseDialogFragment();
            addExpenseDialogFragment.show(getFragmentManager(), "AddExpenseDialog");

        }

        return super.onOptionsItemSelected(item);
    }


    public void SetScreen(){


        ViewPager viewPager = (ViewPager) findViewById(R.id.ViewPager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        Toolbar toolbar = (Toolbar)  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SectionPagerAdapter adpater = new SectionPagerAdapter(getSupportFragmentManager());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(adpater);


        tabLayout.setupWithViewPager(viewPager);



    }

    public void ShowAddExpenseDialog(View view){

        AddExpenseDialogFragment addExpenseDialogFragment = new AddExpenseDialogFragment();
        addExpenseDialogFragment.show(MainActivity.this.getFragmentManager(), "AddExpenseDialog");
    }

    public void ShowAddTaskDialog(View view){
        AddTaskDialogFragment addTaskDialogFragment = new AddTaskDialogFragment();
        addTaskDialogFragment.show(MainActivity.this.getFragmentManager(),  "AddTaskDialog");
    }

    public class SectionPagerAdapter extends FragmentStatePagerAdapter{


        public SectionPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position){

                case 0:
                    fragment = ExpenseFragment.newInstance();
                    break;
                case 1:
                    fragment = TasksFragment.newInstance();
                    break;

                default:
                    fragment = ExpenseFragment.newInstance();
                    break;


            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position){

            switch (position){
                case 0 :
                    return getString(R.string.Expense_Fragment_Title);
                case 1 : return getString(R.string.Task_Fragment_Title);

                default:

                    return getString(R.string.Expense_Fragment_Title);


            }


        }
    }


    }









