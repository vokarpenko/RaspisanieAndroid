package com.example.cardfragment;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.mxn.soul.flowingdrawer_core.ElasticDrawer;
import com.mxn.soul.flowingdrawer_core.FlowingDrawer;

import java.util.ArrayList;
import java.util.List;

import static com.example.cardfragment.DaysFromJSONarray.getDayWeekNumber;

public class MainActivity extends AppCompatActivity {
    public static final String PREFS_FILE = "Setting";
    public static final String PREF_GROUP = "MyGroup";;
    public SharedPreferences settings;

    private FlowingDrawer mDrawer;
    public String tag="mytag";
    private RecyclerView rv;
    private String currentGroup="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settings = getSharedPreferences(PREFS_FILE,MODE_PRIVATE);
        firstRun();
        rv = findViewById(R.id.rv);
        mDrawer = findViewById(R.id.drawerlayout);
        mDrawer.setTouchMode(ElasticDrawer.TOUCH_MODE_BEZEL);
        setupToolbar();
        setupRecycleView();
        setupMenu();
        Log.i(tag,settings.getString(PREF_GROUP,""));
    }

    private void setupRecycleView(){
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);
        AsyncRequest asyncRequest =new AsyncRequest();
        asyncRequest.execute();
        try{
            List<Day> days;
            days = asyncRequest.get();
            RVAdapter adapter = new RVAdapter(days);
            rv.setAdapter(adapter);
            //rv.smoothScrollToPosition(getDayWeekNumber()+1);
        }
        catch (Exception e){
            Log.i(tag,e.toString());
        }
    }

    private void setupMenu() {
        FragmentManager fm = getSupportFragmentManager();
        MenuListFragment mMenuFragment = (MenuListFragment) fm.findFragmentById(R.id.id_container_menu);
        if (mMenuFragment == null) {
            mMenuFragment = new MenuListFragment();
            fm.beginTransaction().add(R.id.id_container_menu, mMenuFragment).commit();
        }
    }

    protected void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu_white);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawer.toggleMenu();
            }
        });
    }

    void firstRun() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean(getString(R.string.pref_previously_started), false);
        if(!previouslyStarted) {
            new ChoiceGroupDialog(this).showHelp();
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean(getString(R.string.pref_previously_started), Boolean.TRUE);
            edit.apply();
        }
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isMenuVisible()) {
            mDrawer.closeMenu();
        } else {
            super.onBackPressed();
        }
    }
}