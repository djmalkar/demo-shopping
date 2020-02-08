package com.dipesh.demoshopping.screens.common.navdrawer;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.model.tables.CategoryTable;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NavDrawerViewMvcImpl extends BaseObservableViewMvc<NavDrawerViewMvc.Listener>
        implements NavDrawerViewMvc {

    private final DrawerLayout mDrawerLayout;
    private final FrameLayout mFrameLayout;
    private final RelativeLayout mProgress;
    private final NavigationView mNavigationView;
    private ArrayList<CategoryTable> mCategoryTables = new ArrayList<>();

    public NavDrawerViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_drawer, parent, false));
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mFrameLayout = findViewById(R.id.content);
        mProgress = findViewById(R.id.progress);
        mNavigationView = findViewById(R.id.nav_view);

        mNavigationView.setNavigationItemSelectedListener(item -> {
            mDrawerLayout.closeDrawers();
            for (CategoryTable categoryTable : mCategoryTables) {
                if(categoryTable.name.equals(item.toString()) && getListeners() != null){
                    getListeners().onNavItemClicked(categoryTable.id);
                    break;
                }
            }
            return false;
        });
    }

    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void showProgress() {
        mProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgress.setVisibility(View.GONE);
    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public FrameLayout getFragmentFrame() {
        return mFrameLayout;
    }

    @Override
    public void setMenuItems(List<CategoryTable> categories) {
        final Menu menu = mNavigationView.getMenu();
        mCategoryTables = new ArrayList<>(categories);
        for (CategoryTable category : categories) {
            menu.add(category.name);
        }
    }
}
