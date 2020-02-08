package com.dipesh.demoshopping.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {

    public interface NavigateUpClickListener {
        void onNavigateUpClicked();
    }

    public interface HamburgerClickListener {
        void onHamburgerClicked();
    }

    private final TextView mTxtTitle;
    private final ImageView mBtnBack;
    private final ImageView mBtnHamburger;

    private HamburgerClickListener mHamburgerClickListener;
    private NavigateUpClickListener mNavigateUpClickListener;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnHamburger = findViewById(R.id.btn_hamburger);
        mBtnHamburger.setOnClickListener(view -> mHamburgerClickListener.onHamburgerClicked());
        mBtnBack = findViewById(R.id.btn_back);
        mBtnBack.setOnClickListener(view -> mNavigateUpClickListener.onNavigateUpClicked());
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void enableHamburgerButtonAndListen(HamburgerClickListener hamburgerClickListener) {
        if (mNavigateUpClickListener != null) {
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }
        mHamburgerClickListener = hamburgerClickListener;
        mBtnHamburger.setVisibility(View.VISIBLE);
    }

    public void enableUpButtonAndListen(NavigateUpClickListener navigateUpClickListener) {
        if (mHamburgerClickListener != null) {
            throw new RuntimeException("hamburger and up shouldn't be shown together");
        }
        mNavigateUpClickListener = navigateUpClickListener;
        mBtnBack.setVisibility(View.VISIBLE);
    }

}
