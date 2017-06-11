package com.leon.lgank.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.leon.lgank.R;
import com.leon.lgank.adapter.MainAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.fragment.HomeFragment;
import com.leon.lgank.fragment.MeFragment;
import com.leon.lgank.fragment.ReadFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 主Activity
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private final int NAVIGATION_HOME = 0;
    private final int NAVIGATION_READ = 1;
    private final int NAVIGATION_ME = 2;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mVpMain.setCurrentItem(NAVIGATION_HOME);
                    return true;
                case R.id.navigation_read:
                    mVpMain.setCurrentItem(NAVIGATION_READ);
                    return true;
                case R.id.navigation_me:
                    mVpMain.setCurrentItem(NAVIGATION_ME);
                    return true;
            }
            return false;
        }

    };
    private ViewPager mVpMain;//主界面ViewPager
    private List<Fragment> mList;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;//侧滑菜单
    private BottomNavigationView mBottomNavigation;//底部导航


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        initView();
        initFragment();
        MainAdapter adapter = new MainAdapter(getSupportFragmentManager(), getApplicationContext(), mList);
        mVpMain.setAdapter(adapter);
        //mVpMain.setOffscreenPageLimit(1);
        mVpMain.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case NAVIGATION_HOME:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_home);
                        break;
                    case NAVIGATION_READ:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_read);
                        break;
                    case NAVIGATION_ME:
                        mBottomNavigation.setSelectedItemId(R.id.navigation_me);
                        break;
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * 初始化Toolbar信息
     */
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    /**
     * 初始化控件
     */
    private void initView() {
        mBottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
        mVpMain = (ViewPager) findViewById(R.id.vp_main);
        mBottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mDrawer = (DrawerLayout) findViewById(R.id.drawlayout);
        mNavigationView.setNavigationItemSelectedListener(this);

    }

    /**
     * 初始化Fragment对象
     */
    private void initFragment() {
        mList = new ArrayList<>();
        mList.add(new HomeFragment());
        mList.add(new ReadFragment());
        mList.add(new MeFragment());
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        Intent intent = new Intent(this,OtherCategoryActivity.class);
        String categroy= Constant.CATEGORY_ALL;
        switch (id) {
            case R.id.menu_draw_client:
                categroy = Constant.CATEGORY_CLIENT;
                break;
            case R.id.menu_draw_recommend:
                categroy = Constant.CATEGROY_RECOMMEND;
                break;
            case R.id.menu_draw_app:
                categroy = Constant.CATEGORY_APP;
                break;
            case R.id.menu_draw_video:
                categroy = Constant.CATEGORY_VIDEO;
                break;
        }
        intent.putExtra("categroy",categroy);
        startActivity(intent);
        item.setCheckable(true);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawlayout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
