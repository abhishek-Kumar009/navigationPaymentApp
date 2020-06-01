package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    private static final String TAGMenu = "Menu Item pressed:";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainAppBar = findViewById(R.id.mainAppBar);
        setSupportActionBar(mainAppBar);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        super.onOptionsItemSelected(item);
        switch (item.getItemId()){
            case R.id.settingsOption:
                Log.d(TAGMenu, "settings option was pressed!");
                return true;
            case R.id.favoriteOption:
                Log.d(TAGMenu, "Favorite option was pressed!");
                return true;
            case R.id.profileOption:
                Log.d(TAGMenu, "Profile option was pressed!");
                return true;
            case R.id.logoutOption:
                Log.d(TAGMenu, "Logout option was pressed!");
                return true;
            case R.id.app_bar_search:
                MenuItem searchItem = findViewById(R.id.app_bar_search);
                SearchView searchView = (SearchView) searchItem.getActionView();
                //Add event listeners here to search items in the app


                return true;

                default:
                    return super.onOptionsItemSelected(item);
        }

    }
}
