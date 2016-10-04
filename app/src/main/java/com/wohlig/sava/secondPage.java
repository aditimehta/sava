/**
 * Created by adiam on 5/31/2016.
 */

package com.wohlig.sava;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class secondPage extends AppCompatActivity {

    TextView bold,regular,light,thin,medium,semibold;


    private TabLayout tabLayout;
    private ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_page);
        regular = (TextView) findViewById(R.id.pizza_restro);
        regular = (TextView) findViewById(R.id.more_offers);
        regular = (TextView) findViewById(R.id.some_wrong);
        regular = (TextView) findViewById(R.id.see_other_offers);
        regular = (TextView) findViewById(R.id.more_redemption_offers);
        regular = (TextView) findViewById(R.id.ccd_loyalty);
        regular = (TextView) findViewById(R.id.claim_offer);
        regular = (TextView) findViewById(R.id.offers);
        regular = (TextView) findViewById(R.id.fluer);
        regular = (TextView) findViewById(R.id.choco);
        regular = (TextView) findViewById(R.id.chicken);
        regular = (TextView) findViewById(R.id.gold);
        regular = (TextView) findViewById(R.id.points);
        regular = (TextView) findViewById(R.id.submit);
        light = (TextView) findViewById(R.id.phone_number);
        light = (TextView) findViewById(R.id.address_info);
        light = (TextView) findViewById(R.id.menu);
        light = (TextView) findViewById(R.id.other);
        light = (TextView) findViewById(R.id.min_spend_fluer);
        light = (TextView) findViewById(R.id.Modern_Meadow_chicken);
        light = (TextView) findViewById(R.id.modern_meadow_fluer);
        light = (TextView) findViewById(R.id.modern_meadow_choco);
        light = (TextView) findViewById(R.id.expires_choco);
        light = (TextView) findViewById(R.id.expires_in_fluer);
        light = (TextView) findViewById(R.id.expires_in_chicken);
        light = (TextView) findViewById(R.id.cancelled_price_fluer);
        light = (TextView) findViewById(R.id.address);
        light = (TextView) findViewById(R.id.cancelled_price_chicken);
        light = (TextView) findViewById(R.id.expires_in);
        light = (TextView) findViewById(R.id.websites);
        light = (TextView) findViewById(R.id.Carte_menu);
        light = (TextView) findViewById(R.id.meal_from_menu);
        light = (TextView) findViewById(R.id.total_points);
        light = (TextView) findViewById(R.id.total_points);
        light = (TextView) findViewById(R.id.free_beer);
        light = (TextView) findViewById(R.id.text_33_off);
        light = (TextView) findViewById(R.id.loyalty_statement);
        light = (TextView) findViewById(R.id.comm);
        light = (TextView) findViewById(R.id.more_business_info);
        light = (TextView) findViewById(R.id.distance);
        bold = (TextView) findViewById(R.id.modern_ccd);
        bold = (TextView) findViewById(R.id.free_burger);
        bold = (TextView) findViewById(R.id.points_500);
        bold = (TextView) findViewById(R.id.points_650);
        bold = (TextView) findViewById(R.id.free_pizza);
        bold = (TextView) findViewById(R.id.free_fluer);
        bold = (TextView) findViewById(R.id.upto);
        bold = (TextView) findViewById(R.id.price_chicken);
        Typeface myCustomFont2=Typeface.createFromAsset(getAssets(),"fonts/Lato-Regular.ttf");
        Typeface myCustomFont3=Typeface.createFromAsset(getAssets(),"fonts/Lato-Light.ttf");
        Typeface myCustomFont=Typeface.createFromAsset(getAssets(),"fonts/Lato-Bold.ttf");
        Typeface myCustomFont4=Typeface.createFromAsset(getAssets(),"fonts/Lato-Thin.ttf");
        Typeface myCustomFont5=Typeface.createFromAsset(getAssets(),"fonts/Lato-Medium.ttf");
        Typeface myCustomFont6=Typeface.createFromAsset(getAssets(),"fonts/Lato-Semibold.ttf");
        bold.setTypeface(myCustomFont);
        regular.setTypeface(myCustomFont2);
        light.setTypeface(myCustomFont3);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);


    }

    public void setupViewPager(ViewPager upViewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new OneFragment(), "Card A");
        adapter.addFragment(new TwoFragment(), "Card B");
        adapter.addFragment(new ThreeFragment(), "Card C");

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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void backPage(View view){
        Intent intent = new Intent(this,SavaActivity.class);
        startActivity(intent);
    }
}
