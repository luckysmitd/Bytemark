package com.bytemark;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.bytemark.adapters.ViewPagerAdapter;
import com.bytemark.commons.AlertDialogBuilder;
import com.bytemark.commons.Constants;
import com.bytemark.di.components.ActivityComponent;
import com.bytemark.di.modules.ActivityModule;
import com.bytemark.fragments.CurrentCityForecastFragment;
import com.bytemark.fragments.MultipleCitiesForecastFragment;
import com.bytemark.permissions.PermissionResultCallback;
import com.bytemark.permissions.PermissionUtils;
import com.google.android.gms.location.FusedLocationProviderClient;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements PermissionResultCallback, Constants {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Inject
    PermissionUtils permissionUtils;

    @Inject
    LocationManager locationManager;

    private AlertDialog alertDialog;

    @Inject
    ViewPagerAdapter viewPagerAdapter;

    @Inject
    FusedLocationProviderClient mFusedLocationClient ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getActivityComponent().inject(this);

        setSupportActionBar(toolbar);


        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 1) {
                    ArrayList<String> permissionList = new ArrayList<>();
                    permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
                    permissionUtils.checkPermission(permissionList,
                            getString(R.string.permission_required_rationale),
                            LOCATION_PERMISSION_REQUEST_CODE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        viewPagerAdapter.addFragment(new MultipleCitiesForecastFragment(), getString(R.string.step_1));
        viewPagerAdapter.addFragment(new CurrentCityForecastFragment(), getString(R.string.step_2));
        viewPager.setAdapter(viewPagerAdapter);
    }

    private ActivityComponent getActivityComponent()
    {
        return ByteMarkApplication.get(this).getAppComponent()
                .getControllerComponent(new ActivityModule(this, getSupportFragmentManager()));
    }


    @Override
    public void permissionGranted(int requestCode) {

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            getLocation();
        } else {
            alertDialog = AlertDialogBuilder.getDialog(this, getString(R.string.enable_location_message), null);
            if (!isFinishing()) {
                alertDialog.show();
            }
        }
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {


        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, location -> {
                    if (location != null) {
                        CurrentCityForecastFragment currentCityForecastFragment = (CurrentCityForecastFragment) viewPagerAdapter.getItem(1);
                        if(currentCityForecastFragment.isVisible() && currentCityForecastFragment.isAdded())
                        {
                            currentCityForecastFragment.updateLocation(location);
                        }
                    }
                });


    }

    @Override
    public void partialPermissionGranted(int requestCode, ArrayList<String> grantedPermissions) {


    }

    @Override
    public void permissionDenied(int requestCode) {

    }

    @Override
    public void neverAskAgain(int requestCode) {
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionUtils.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(alertDialog != null && alertDialog.isShowing())
        {
            alertDialog.dismiss();
        }
    }
}
