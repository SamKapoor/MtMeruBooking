package in.infinium.mtmerubooking.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import in.infinium.mtmerubooking.R;
import in.infinium.mtmerubooking.fragment.FragCompanySelection;
import in.infinium.mtmerubooking.fragment.FragSelectApproveDeliver;
import in.infinium.mtmerubooking.utils.Common;

/**
 * This is the main class of the app , it is used to handle all the functionalities. it shows dashboard and menu to the user.
 */
public class SelectCompanyActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {


    private NavigationView navigationView;
    private FragmentManager fragmentManager;
    Fragment fragment = null;
    private Context context;
    private DrawerLayout drawer;
    private String screenTitles[] = {"Live listings", "Pending listings", "My vehicles", "Pending vehicles"};
    private FrameLayout frame_container;
    private CoordinatorLayout coordinatorLayout;


    /**
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = SelectCompanyActivity.this;

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.mCoordinatorLayout);
        frame_container = (FrameLayout) findViewById(R.id.frame_container);
        frame_container.setVisibility(View.GONE);

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, R.string.open_drawer, R.string.close_drawer);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (Common.getStringPrefrences(this, getString(R.string.pref_userType), getString(R.string.app_name)).equalsIgnoreCase("F")) {
            navigationView.findViewById(R.id.ll_nav_petrol).setVisibility(View.GONE);
            navigationView.findViewById(R.id.ll_nav_gas).setVisibility(View.GONE);
            setApproveDeliverFragment();
        } else {
            navigationView.findViewById(R.id.ll_nav_approve_users).setVisibility(View.GONE);
            navigationView.findViewById(R.id.ll_nav_deliver_bookings).setVisibility(View.GONE);
            setCompanyFragment();
        }

    }

    /**
     * set side menu view by responsibility
     */
    private void setApproveDeliverFragment() {
        fragment = new FragSelectApproveDeliver();
        setFragment(fragment);
    }

    /**
     * set side menu view by responsibility
     */
    private void setCompanyFragment() {
        fragment = new FragCompanySelection();
        setFragment(fragment);
    }

    /**
     * set fragment while change list
     *
     * @param mFragment
     */
    private void setFragment(Fragment mFragment) {
        fragmentManager = getFragmentManager();
        if (mFragment != null) {
            frame_container.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().replace(R.id.frame_container, mFragment).commit();
        }
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


    /**
     * sign out process here
     */
    private void SignOut() {
        Common.removeAllPrefrences(context, getString(R.string.app_name));
        Intent mIntent = new Intent(context, LoginActivity.class);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(mIntent);
        finish();
    }


    /**
     * This method change fragment on click of particular item.
     *
     * @param v
     */
    public void onClick(View v) {
        Common.preventDoubleClick(v);
        int id = v.getId();

        String title = "";
        if (id == R.id.ll_nav_petrol) {
            title = screenTitles[0];
            fragment = new FragCompanySelection();
        } else if (id == R.id.ll_nav_gas) {
            title = screenTitles[1];
            fragment = new FragCompanySelection();
        } else if (id == R.id.ll_nav_approve_users) {
            title = screenTitles[1];
            fragment = new FragCompanySelection();
        } else if (id == R.id.ll_nav_deliver_bookings) {
            title = screenTitles[12];
            fragment = new FragCompanySelection();
        } else if (v.getId() == R.id.ll_nav_sign_out) {
            SignOut();
        } else {
            drawer.closeDrawer(GravityCompat.START);
        }

        if (fragment != null) {
            getSupportActionBar().setTitle(title);
            frame_container.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (fragment != null) {
            frame_container.setVisibility(View.VISIBLE);
            fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}