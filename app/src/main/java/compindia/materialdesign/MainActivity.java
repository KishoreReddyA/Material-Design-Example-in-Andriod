package compindia.materialdesign;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import compindia.materialdesign.fragments.CardFragment;
import compindia.materialdesign.fragments.ThemeFragment;
import compindia.materialdesign.fragments.WidgetsFragment;

/**
 * Created by compindi on 09-11-2017.
 */

public class MainActivity extends AppCompatActivity implements ThemeInterface, View.OnClickListener, ViewPager.OnPageChangeListener {
    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    ViewPagerAdapter adapter;
    FloatingActionButton floatingActionButton;
    CoordinatorLayout loutMain;
    ImageView imgMenu;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    static int themeID;
    ImageView imgMore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(themeID);
        setContentView(R.layout.drawer);
        init();
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragments(new CardFragment(), "CARDS");
        adapter.addFragments(new ThemeFragment(), "THEMES");
        adapter.addFragments(new WidgetsFragment(), "WIDGETS");
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                view.setPivotX(position < 0f ? view.getWidth() : 0f);
                view.setPivotY(view.getHeight() * 0.5f);
                view.setRotationY(90f * position);
            }
        });
        tabLayout.setupWithViewPager(viewPager);
    }

    void init() {
        toolbar = findViewById(R.id.tool_bar);
        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.viewPager);
        floatingActionButton = findViewById(R.id.floaing_button);
        loutMain = findViewById(R.id.lout_main);
        imgMenu = findViewById(R.id.menu_img);
        drawerLayout = findViewById(R.id.drawer_lout);
        navigationView = findViewById(R.id.drawer_view);
        imgMore = findViewById(R.id.img_more);
        imgMore.setOnClickListener(this);
        floatingActionButton.setOnClickListener(this);
        imgMenu.setOnClickListener(this);
        viewPager.setOnPageChangeListener(this);
    }

    @Override
    public void onBackPressed()
    {
     if (drawerLayout.isDrawerOpen(Gravity.START))
     {
       drawerLayout.closeDrawer(Gravity.START);
     }
     else
     {
         finish();
     }
    }

    @Override
    public void changeTheme(int themeNumber) {
        themeID = themeNumber;
        Intent intent=new Intent(MainActivity.this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.floaing_button:
                showSnackBar();
                break;
            case R.id.menu_img:
                drawerLayout.openDrawer(Gravity.START, true);
                break;
            case R.id.img_more:
                PopupMenu popupMenu = new PopupMenu(MainActivity.this, imgMore);
                getMenuInflater().inflate(R.menu.more_options, popupMenu.getMenu());
                popupMenu.show();
                this.openOptionsMenu();
                break;

        }
    }
    private void showSnackBar() {
        Snackbar snackbar = Snackbar.make(loutMain, "This is a snack bar", Snackbar.LENGTH_LONG);
        snackbar.setAction("OKAY", new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

            }
        }).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position)
    {
        View focus=getCurrentFocus();
        if (focus!=null)
        {
            hiddenKeyboard(focus);
        }
        if (position == 2) {
            floatingActionButton.setVisibility(View.VISIBLE);
        } else {
            floatingActionButton.setVisibility(View.GONE);
        }
    }
    private void hiddenKeyboard(View v)
    {
        InputMethodManager keyboard = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        keyboard.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> fragment_list = new ArrayList<>();
        ArrayList<String> tabs_list = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragment_list.get(position);
        }

        @Override
        public int getCount() {
            return fragment_list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position)
        {
            return tabs_list.get(position);
        }

        void addFragments(Fragment fragment, String name) {
            fragment_list.add(fragment);
            tabs_list.add(name);
        }
    }
}
