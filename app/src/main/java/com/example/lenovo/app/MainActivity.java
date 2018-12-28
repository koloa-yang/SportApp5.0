package com.example.lenovo.app;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.telephony.SmsManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import Coach.CoachFragment;
import Course.CourseFragment;
import Home.HomeFragment;
import Mine.LoginFragment;
import Mine.MineFragment;
import Mine.RegisterFragment;
import Video.VideoFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,CoachFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener,
        LoginFragment.OnFragmentInteractionListener , CourseFragment.OnFragmentInteractionListener,
        RegisterFragment.OnFragmentInteractionListener,MineFragment.OnFragmentInteractionListener ,VideoFragment.OnFragmentInteractionListener{

    private Fragment currentFragment;
    private ImageView imageView;
    private TextView userName;
    private TextView trainLevel;

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //打开数据库

        //读取数据库中个人信息
        imageView = (ImageView) findViewById(R.id.imageView);
        userName = (TextView) findViewById(R.id.userName);
        trainLevel = (TextView) findViewById(R.id.trainLevel);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        replaceFragment("home");
        startActivity(new Intent(this, LoginActivity.class));
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
        if (id == R.id.navigation_home) {
            replaceFragment("home");
        } else if (id == R.id.navigation_course) {
            replaceFragment("course");
        } else if (id == R.id.navigation_coach) {
            replaceFragment("coach");
        } else if (id == R.id.navigation_mine) {
//            replaceFragment("login");
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.video_player) {
            replaceFragment("video");
        } else if (id == R.id.phone) {
            // Handle the camera action
            Uri uri = Uri.parse("tel:" + 10010);
            Intent intent = new Intent(Intent.ACTION_DIAL,uri);
            startActivity(intent);
//            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
//                startActivity(intent);
//            }
        } else if (id == R.id.nav_message){
            Uri uri2 = Uri.parse("smsto:"+"13785780994");
            Intent intentMessage = new Intent(Intent.ACTION_VIEW,uri2);
            startActivity(intentMessage);
        }else if(id == R.id.nav_map){
            replaceFragment("map");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(String tag) {
        if (currentFragment != null) {
            getSupportFragmentManager().beginTransaction().hide(currentFragment).commit();
        }
        currentFragment = getSupportFragmentManager().findFragmentByTag(tag);
        if (currentFragment == null) {
            switch (tag) {
                case "home":
                    currentFragment = new HomeFragment();
                    break;
                case "course":
                    currentFragment = new CourseFragment();
                    break;
                case "coach":
                    currentFragment = new CoachFragment();
                    break;
                case "login":
                    currentFragment = new LoginFragment();
                    break;
                case "register":
                    currentFragment = new RegisterFragment();
                    break;
                case "mine":
                    currentFragment = new MineFragment();
                    break;
                case "video":
                    currentFragment = new VideoFragment();
                    break;
                case "map":
                    //切换fragment
                    break;
            }
            getSupportFragmentManager().beginTransaction().add(R.id.fragment, currentFragment, tag).commit();
        }else {
            getSupportFragmentManager().beginTransaction().show(currentFragment).commit();
        }
    }

}
