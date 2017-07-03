package com.leon.lgank.ui;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;

import com.leon.lgank.R;
import com.leon.lgank.adapter.MainAdapter;
import com.leon.lgank.common.Constant;
import com.leon.lgank.fragment.HomeFragment;
import com.leon.lgank.fragment.MeFragment;
import com.leon.lgank.fragment.ReadFragment;

import java.util.ArrayList;
import java.util.List;

import skin.support.SkinCompatManager;
import skin.support.app.SkinCompatDelegate;
import skin.support.content.res.SkinCompatResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatThemeUtils;

import static skin.support.widget.SkinCompatHelper.INVALID_ID;
import static skin.support.widget.SkinCompatHelper.checkResourceId;

/**
 * 主Activity
 */
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, SkinObserver {
    private final int NAVIGATION_HOME = 0;
    private final int NAVIGATION_READ = 1;
    private final int NAVIGATION_ME = 2;
    private SkinCompatDelegate mSkinDelegate;//换肤实现
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
    private Menu mMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(getLayoutInflater(), getSkinDelegate());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupWindowAnimations();
        updateStatusBarColor();
        updateWindowBackground();
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

    private void setupWindowAnimations() {
        Fade fade = new Fade();
        fade.setDuration(10000);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setEnterTransition(fade);
        }
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_toolbar, menu);
        menu.findItem(R.id.action_edit).setVisible(false);
        mMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_search) {
            startActivity(new Intent(this, SearchActivity.class));
        }
        if (item.getItemId() == R.id.action_edit) {
            startActivity(new Intent(this, EditActivity.class));
        }
        return true;
    }

    /**
     * 展示编辑功能
     */
    public void showEdit() {
        mMenu.findItem(R.id.action_edit).setVisible(true);
    }

    /**
     * 隐藏编辑功能
     */
    public void hideEdit() {
        mMenu.findItem(R.id.action_edit).setVisible(false);
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
        String categroy = Constant.CATEGORY_ALL;
        switch (id) {
            case R.id.menu_draw_client:
                categroy = Constant.CATEGORY_CLIENT;
                showCategoryInfo(categroy);
                break;
            case R.id.menu_draw_recommend:
                categroy = Constant.CATEGROY_RECOMMEND;
                showCategoryInfo(categroy);
                break;
            case R.id.menu_draw_app:
                categroy = Constant.CATEGORY_APP;
                showCategoryInfo(categroy);
                break;
            case R.id.menu_draw_expandresource:
                categroy = Constant.CATEGORY_EXPANDRESOURCE;
                showCategoryInfo(categroy);
                break;
            case R.id.menu_draw_video:
                categroy = Constant.CATEGORY_VIDEO;
                showCategoryInfo(categroy);
                break;
            case R.id.menu_draw_theme:
                Intent intent = new Intent(this, ThemeActivity.class);
                startActivity(intent);
                break;
        }
        item.setCheckable(true);
        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * 跳转到分类展示界面
     *
     * @param categroy
     */
    private void showCategoryInfo(String categroy) {
        Intent intent = new Intent(this, OtherCategoryActivity.class);
        intent.putExtra("categroy", categroy);
        startActivity(intent);
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

    @NonNull
    public SkinCompatDelegate getSkinDelegate() {
        if (mSkinDelegate == null) {
            mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return mSkinDelegate;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }

    /**
     * @return true: 打开5.0以上状态栏换肤, false: 关闭5.0以上状态栏换肤;
     */
    protected boolean skinStatusBarColorEnable() {
        return true;
    }

    protected void updateStatusBarColor() {
        if (skinStatusBarColorEnable() && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int statusBarColorResId = SkinCompatThemeUtils.getStatusBarColorResId(this);
            int colorPrimaryDarkResId = SkinCompatThemeUtils.getColorPrimaryDarkResId(this);
            if (checkResourceId(statusBarColorResId) != INVALID_ID) {
                getWindow().setStatusBarColor(SkinCompatResources.getInstance().getColor(statusBarColorResId));
            } else if (checkResourceId(colorPrimaryDarkResId) != INVALID_ID) {
                getWindow().setStatusBarColor(SkinCompatResources.getInstance().getColor(colorPrimaryDarkResId));
            }
        }
    }

    protected void updateWindowBackground() {
        int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(this);
        if (checkResourceId(windowBackgroundResId) != INVALID_ID) {
            String typeName = getResources().getResourceTypeName(windowBackgroundResId);
            if ("color".equals(typeName)) {
                Drawable drawable = new ColorDrawable(SkinCompatResources.getInstance().getColor(windowBackgroundResId));
                getWindow().setBackgroundDrawable(drawable);
            } else if ("drawable".equals(typeName)) {
                Drawable drawable = SkinCompatResources.getInstance().getDrawable(windowBackgroundResId);
                getWindow().setBackgroundDrawable(drawable);
            } else if ("mipmap".equals(typeName)) {
                Drawable drawable = SkinCompatResources.getInstance().getMipmap(windowBackgroundResId);
                getWindow().setBackgroundDrawable(drawable);
            }
        }
    }

    @Override
    public void updateSkin(SkinObservable observable, Object o) {
        updateStatusBarColor();
        updateWindowBackground();
        getSkinDelegate().applySkin();
    }
}
