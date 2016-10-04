package com.wohlig.sava;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.support.v8.renderscript.Allocation;
import android.support.v8.renderscript.Element;
import android.support.v8.renderscript.RenderScript;
import android.support.v8.renderscript.ScriptIntrinsicBlur;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by adiam on 7/6/2016.
 */
public class SavaActivity extends AppCompatActivity implements Transformation {

    TabLayout tabLayout;
    ViewPager viewPager;
    private String mtitle;
    Toolbar toolbar;
    private String[] mNavigationDrawerItemTitles;
    CharSequence mDrawerTitle;
    CharSequence mTitle;
    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    ImageView imageView;
    ImageView expandableImageViewChild;
    ImageView expandableImageViewParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);
        mTitle = mDrawerTitle = getTitle();


        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setUpViewPager(viewPager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Store");

        tabLayout = (TabLayout) findViewById(R.id.bottom_tab);
        tabLayout.setupWithViewPager(viewPager);
        setUpIcons();

        expListView = (ExpandableListView) findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(this, listDataChild, listDataHeader);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        if(listDataHeader.equals("Categories")){
            imageView.setVisibility(View.VISIBLE);
        }


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    toolbar.setTitle("Store");
                }
                if (position == 1) {
                    toolbar.setTitle("Loyalty");
                }
                if (position == 2) {
                    toolbar.setTitle("Offers");
                }
                if (position == 3) {
                    toolbar.setTitle("Notifications");
                }
                hideKeyboard();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);

        android.support.v7.app.ActionBarDrawerToggle toggle = new android.support.v7.app.ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        ImageView imageView = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.img_blur);

        Picasso
                .with(SavaActivity.this)
                .load(R.drawable.offer4)
                .transform(SavaActivity.this)
                .into(imageView);

    }

    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        expandableImageViewChild = (ImageView) findViewById(R.id.img_ex_list);
        expandableImageViewParent = (ImageView) findViewById(R.id.imageView_group);

        // Adding child data
        listDataHeader.add("Notifications");
        listDataHeader.add("All Offers");
        listDataHeader.add("Saved Offers");
        listDataHeader.add("Categories");
        listDataHeader.add("Setting");
        listDataHeader.add("About");
        listDataHeader.add("Help & Feedback");



        // Adding child data
        List<String> Categories = new ArrayList<String>();
        Categories.add("Cafe");
        Categories.add("Restaurant");
        Categories.add("Lunch");
        Categories.add("Bars");
        Categories.add("Gas stations");

        List<String> notification = new ArrayList<>();
        List<String> alloffers = new ArrayList<>();
        List<String> savedoffers = new ArrayList<>();
        List<String> settings = new ArrayList<>();
        List<String> about = new ArrayList<>();
        List<String> help = new ArrayList<>();

        listDataChild.put(listDataHeader.get(0),notification);
        listDataChild.put(listDataHeader.get(1), alloffers);
        listDataChild.put(listDataHeader.get(2), savedoffers);
        listDataChild.put(listDataHeader.get(3), Categories);// Header, Child data
        listDataChild.put(listDataHeader.get(4),settings);
        listDataChild.put(listDataHeader.get(5),about);
        listDataChild.put(listDataHeader.get(6),help);

    }


    private void setUpIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.my_selector);
        tabLayout.getTabAt(1).setIcon(R.drawable.my_loyalty_selector);
        tabLayout.getTabAt(2).setIcon(R.drawable.my_offers_selector);
        tabLayout.getTabAt(3).setIcon(R.drawable.my_notifications_selector);
    }

    private void setUpViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new StoreFragment(), "STORE");
        adapter.addFrag(new LoyaltyFragment(), "LOYALTY");
        adapter.addFrag(new OffersFragment(), "OFFERS");
        adapter.addFrag(new NotificationsFragment(), "NOTIFICATIONS");

        viewPager.setAdapter(adapter);
    }


    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void showSecondPage(View view) {
        Intent intent = new Intent(this, secondPage.class);
        startActivity(intent);

    }


    @Override
    public Bitmap transform(Bitmap source) {

        RenderScript rs = RenderScript.create(this);

        Bitmap blurredBitmap = source.copy(Bitmap.Config.ARGB_8888, true);

        // Allocate memory for Renderscript to work with
        Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
        Allocation output = Allocation.createTyped(rs, input.getType());

        // Load up an instance of the specific script that we want to use.
        ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
        script.setInput(input);

        // Set the blur radius
        script.setRadius(25);

        // Start the ScriptIntrinisicBlur
        script.forEach(output);

        // Copy the output to the blurred bitmap
        output.copyTo(blurredBitmap);

        source.recycle();

        return blurredBitmap;
    }

    @Override
    public String key() {
        return "blur";
    }

    public void hideKeyboard() {

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getWindow().getCurrentFocus() != null) {
            inputMethodManager.hideSoftInputFromWindow(getWindow().getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}


