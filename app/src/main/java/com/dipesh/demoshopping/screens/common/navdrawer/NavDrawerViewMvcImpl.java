package com.dipesh.demoshopping.screens.common.navdrawer;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

public class NavDrawerViewMvcImpl extends BaseObservableViewMvc<NavDrawerViewMvc.Listener>
        implements NavDrawerViewMvc {

    private final DrawerLayout mDrawerLayout;
    private final ConstraintLayout mConstrainLayout;
    private final NavigationView mNavigationView;

    public NavDrawerViewMvcImpl(LayoutInflater inflater, @Nullable ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_drawer, parent, false));
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mConstrainLayout = findViewById(R.id.content);
        mNavigationView = findViewById(R.id.nav_view);

        /*mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                if (item.getItemId() == R.id.drawer_menu_questions_list) {
                    for (Listener listener : getListeners()) {
                        listener.onQuestionsListClicked();
                    }
                }
                return false;
            }
        });*/
    }

    @Override
    public void openDrawer() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public boolean isDrawerOpen() {
        return mDrawerLayout.isDrawerOpen(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        mDrawerLayout.closeDrawers();
    }

    @Override
    public ConstraintLayout getFragmentFrame() {
        return mConstrainLayout;
    }
}
