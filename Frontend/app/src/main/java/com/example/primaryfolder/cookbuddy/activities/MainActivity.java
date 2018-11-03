package com.example.primaryfolder.cookbuddy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.primaryfolder.cookbuddy.R;
import com.example.primaryfolder.cookbuddy.fragments.MessagingFragment;
import com.example.primaryfolder.cookbuddy.fragments.ViewRecipesFragment;


public class MainActivity extends AppCompatActivity {

    // instantiate variables
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private Toolbar toolbar;
    private FloatingActionButton fab;

    public static int navItemIndex = 0; // index to identify the current nav menu item

    // tags used to attach fragments
    private static final String TAG_HOME = "home";
    private static final String TAG_VIEW_RECIPES = "view_recipes";
    private static final String TAG_MESSAGING = "messaging";
    private static final String TAG_PROFILE = "profile";
    public static String CURRENT_TAG = TAG_HOME;

    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;

    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar); // instantiate the toolbar
        setSupportActionBar(toolbar); // set the toolbar as the action bar

        handler = new Handler(); // create a new handler

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout); // initialize the drawer used
        navigationView = (NavigationView) findViewById(R.id.nav_view); // initialize the navigation view
        fab = (FloatingActionButton) findViewById(R.id.fab); // initialize the floating action button

        // load toolbar titles from string resources
        activityTitles = getResources().getStringArray(R.array.nav_item_activity_titles);

        // TODO create functionality for the floating action button
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });

        setUpNavigationView(); // initialize the navigation menu

        if (savedInstanceState == null) {
            navItemIndex = 0;
            CURRENT_TAG = TAG_HOME;
            loadHomeFragment();
        }
    }

    /***
     * Initializes the Navigation Drawer by creating necessary click listeners and other functions.
     * // TODO IMPLEMENT FRAGMENTS INSTEAD OF ACTIVITIES
     */
    private void setUpNavigationView() {
        // setting NavigationView Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            // this method will trigger on item click of the navigation menu
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // check to see which item is being selected and perform appropriate action
                switch (item.getItemId()) {
                    // replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_home:
                        navItemIndex = 0; // change the index of the item selected
                        CURRENT_TAG = TAG_HOME; // set the current tag
                        // launch new intent (or load fragment)
                        break;
                    case R.id.nav_view_recipes:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_VIEW_RECIPES;
                        startActivity(new Intent(MainActivity.this, ViewRecipes.class)); // launch new intent (or load fragment)
                        drawer.closeDrawers(); // close the drawer
                        return true;
                    case R.id.nav_messaging:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MESSAGING;
                        startActivity(new Intent(MainActivity.this, MessagingActivity.class)); // launch new intent (or load fragment)
                        drawer.closeDrawers(); // close the drawer
                    case R.id.nav_profile:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_PROFILE;
                        break;
                    default:
                        navItemIndex = 0;
                }

                // check if the item is in checked state or not, if not make it in checked state
                if (item.isChecked()) {
                    item.setChecked(false);
                } else {
                    item.setChecked(true);
                }
                item.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {
            @Override
            public void onDrawerOpened(View drawerView) {
                // code here will be triggered once the drawer opens as we don't want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                // code here will be triggered once the drawer closes as we don't want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }
        };

        // setting the actionbarToggle to drawer layout
        drawer.addDrawerListener(actionBarDrawerToggle);

        // calling sync state is necessary or else the hamburger icon won't show
        actionBarDrawerToggle.syncState();
    }

    /***
     * Loads the fragment returned from getHomeFragment() function into FrameLayout.
     * It also takes care of other things like changing the toolbar title, hiding / showing fab,
     * invalidating the options menu so that new menu can be loaded for different fragment
     */
    private void loadHomeFragment() throws NullPointerException {
        // select appropriate nav menu item
        selectNavMenu();

        // set toolbar title
        setToolbarTitle();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            // show or hide the fab button
            toggleFab();
            return;
        }
        // Sometimes, when fragment has huge data, screen seems hanging
        // when switching between navigation menus
        // So using runnable, the fragment is loaded with cross fade effect
        // This effect can be seen in GMail app
        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();
            }
        };
        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            handler.post(mPendingRunnable);
        }

        // show or hide the fab button
        toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    /***
     * Returns the appropriate Fragment depending on the nav menu item user selected.
     * For example if user selects Messaging from nav menu, it returns MessagingFragment.
     * This can be done by using the variable navItemIndex.
     */
    private Fragment getHomeFragment() {
        switch (navItemIndex) {
            case 1:
                // view recipes
                ViewRecipesFragment viewRecipesFragment = new ViewRecipesFragment();
                return viewRecipesFragment;
            case 2:
                // messaging
                MessagingFragment messagingFragment = new MessagingFragment();
                return messagingFragment;
            default:
                return new ViewRecipesFragment();
        }
    }

    private void setToolbarTitle() throws NullPointerException {
        getSupportActionBar().setTitle(activityTitles[navItemIndex]);
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }

        // This code loads home fragment when back key is pressed
        // when user is in other fragment than home
        if (shouldLoadHomeFragOnBackPress) {
            // checking if user is on other navigation menu
            // rather than home
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        // show menu only when home fragment is selected
        if (navItemIndex == 0) {
            getMenuInflater().inflate(R.menu.activity_main_drawer, menu);
        }

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }

    // show or hide the fab
    private void toggleFab() {
        if (navItemIndex == 0)
            fab.show();
        else
            fab.hide();
    }
}
