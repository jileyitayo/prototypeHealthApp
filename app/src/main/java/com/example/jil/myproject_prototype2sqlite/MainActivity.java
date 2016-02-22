package com.example.jil.myproject_prototype2sqlite;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jil.androidrecyclerviewgridview.ItemObject;
import com.example.jil.androidrecyclerviewgridview.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridLayoutManager lLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("error?", "here");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        Log.e("error?", "check here too");
        setSupportActionBar(toolbar);
        Log.e("error?", "this too");


        setTitle(null);
        toolbar.setLogo(R.drawable.logo);
        toolbar.setLogoDescription(getResources().getString(R.string.logo_desc));

        List<ItemObject> rowListItem = getAllItemList();
        lLayout = new GridLayoutManager(MainActivity.this, 4);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(MainActivity.this, rowListItem);
        rView.setAdapter(rcAdapter);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Message.messageSnackBar(view, "Replace with your own action", null);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camara) {
            // Handle the camera action

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private List<ItemObject> getAllItemList(){

        List<ItemObject> allItems = new ArrayList<ItemObject>();
        allItems.add(new ItemObject("United States", R.drawable.one));
        allItems.add(new ItemObject("Canada", R.drawable.two));
        allItems.add(new ItemObject("United Kingdom", R.drawable.three));
        allItems.add(new ItemObject("Germany", R.drawable.four));
        allItems.add(new ItemObject("Sweden", R.drawable.five));
        allItems.add(new ItemObject("United Kingdom", R.drawable.six));
        allItems.add(new ItemObject("Germany", R.drawable.seven));
        allItems.add(new ItemObject("Sweden", R.drawable.eight));
        allItems.add(new ItemObject("United States", R.drawable.one));
        allItems.add(new ItemObject("Canada", R.drawable.two));
        allItems.add(new ItemObject("United Kingdom", R.drawable.three));
        allItems.add(new ItemObject("Germany", R.drawable.four));
        allItems.add(new ItemObject("Sweden", R.drawable.five));
        allItems.add(new ItemObject("United Kingdom", R.drawable.six));
        allItems.add(new ItemObject("Germany", R.drawable.seven));
        allItems.add(new ItemObject("Sweden", R.drawable.eight));

        return allItems;
    }
}
