package com.alexbagg.sampleapplication.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.alexbagg.sampleapplication.R;
import com.alexbagg.sampleapplication.adapters.LegendListAdapter;
import com.alexbagg.sampleapplication.models.LegendModel;
import com.alexbagg.sampleapplication.utils.Globals;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    /*//////////////////////////////////////////////////////////
    // MEMBERS
    *///////////////////////////////////////////////////////////
    private static final String TAG = Globals.SEARCH_STRING + MainActivity.class.getSimpleName();
    private ArrayList<LegendModel> mLegendModelList;
    private LegendListAdapter mLegendListAdapter;


    /*//////////////////////////////////////////////////////////
    // PROPERTIES
    *///////////////////////////////////////////////////////////
    private ArrayList<LegendModel> getLegendModelList(){
        return mLegendModelList == null ? mLegendModelList = new ArrayList<LegendModel>() : mLegendModelList;
    }
    private LegendListAdapter getLegendListAdapter(){
        return mLegendListAdapter == null ? mLegendListAdapter = new LegendListAdapter(this, getLegendModelList()) : mLegendListAdapter;
    }


    /*//////////////////////////////////////////////////////////
    // UI MEMBERS
    *///////////////////////////////////////////////////////////
    private EditText txtNewLegendName;
    private EditText txtNewLegendGame;
    private Button btnSummon;
    private ListView lstLegends;


    /*//////////////////////////////////////////////////////////
    // UI PROPERTIES
    *///////////////////////////////////////////////////////////
    private String getNewLegendName(){
        String value = "";

        if((txtNewLegendName == null ? txtNewLegendName = (EditText) findViewById(R.id.txtNewLegendName) : txtNewLegendName) != null){
            value = txtNewLegendName.getText().toString();
        }

        return value;
    }
    private void setNewLegendName(String value){
        if((txtNewLegendName == null ? txtNewLegendName = (EditText) findViewById(R.id.txtNewLegendName) : txtNewLegendName) != null){
            txtNewLegendName.setText(value);
        }
    }
    private void focusNewLegendName(){
        if((txtNewLegendName == null ? txtNewLegendName = (EditText) findViewById(R.id.txtNewLegendName) : txtNewLegendName) != null){
            txtNewLegendName.requestFocus();
        }
    }
    private String getNewLegendGame(){
        String value = "";

        if((txtNewLegendGame == null ? txtNewLegendGame = (EditText) findViewById(R.id.txtNewLegendGame) : txtNewLegendGame) != null){
            value = txtNewLegendGame.getText().toString();
        }

        return value;
    }
    private void setNewLegendGame(String value){
        if((txtNewLegendGame == null ? txtNewLegendGame = (EditText) findViewById(R.id.txtNewLegendGame) : txtNewLegendGame) != null){
            txtNewLegendGame.setText(value);
        }
    }


    /*//////////////////////////////////////////////////////////
    // LIFE CYCLE OVERRIDES
    *///////////////////////////////////////////////////////////
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.v(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        syncUI();
        setValues();
    }


    /*//////////////////////////////////////////////////////////
    // BUTTON LISTENERS
    *///////////////////////////////////////////////////////////
    private void btnSummon_onClick() {
        if (btnSummon == null) {
            Log.e(TAG, "btnSummon = null");
            return;
        }

        btnSummon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG, "btnSummon_onClick()");
                LegendModel legend = new LegendModel(getNewLegendName(), getNewLegendGame());
                addLegend(legend);

                clearFields();
                focusNewLegendName();
                closeKeyboard();
            }
        });
    }


    /*//////////////////////////////////////////////////////////
    // METHODS
    *///////////////////////////////////////////////////////////
    private void syncUI(){
        Log.v(TAG, "syncUI()");
        txtNewLegendName = (EditText) findViewById(R.id.txtNewLegendName);
        txtNewLegendGame = (EditText) findViewById(R.id.txtNewLegendGame);
        btnSummon = (Button) findViewById(R.id.btnSummon);
        lstLegends = (ListView) findViewById(R.id.lstLegends);

        btnSummon_onClick();
    }
    private void setValues(){
        Log.v(TAG, "setValues()");

        // Set the actionbar title
        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle(R.string.title_blizzard_legends);
        }

        lstLegends.setAdapter(getLegendListAdapter());
    }
    private void addLegend(LegendModel legend){
        Log.v(TAG, "addLegend()");
        Log.v(TAG, "legend name = " + legend.getName());
        Log.v(TAG, "legend game = " + legend.getGame());

        // Add the legend and update the list
        getLegendModelList().add(legend);
        getLegendListAdapter().notifyDataSetChanged();
    }
    private void clearFields(){
        setNewLegendName("");
        setNewLegendGame("");
    }
    private void closeKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}
