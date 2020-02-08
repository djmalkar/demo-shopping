package com.dipesh.demoshopping.screens.common.toolbar;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.views.BaseViewMvc;

public class ToolbarViewMvc extends BaseViewMvc {

    public interface HamburgerClickListener {
        void onHamburgerClicked();
    }

    private final TextView mTxtTitle;
    private final ImageView mBtnHamburger;
    private HamburgerClickListener mHamburgerClickListener;

    public ToolbarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar, parent, false));
        mTxtTitle = findViewById(R.id.txt_toolbar_title);
        mBtnHamburger = findViewById(R.id.btn_hamburger);
        mBtnHamburger.setOnClickListener(view -> mHamburgerClickListener.onHamburgerClicked());
    }

    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    public void enableHamburgerButtonAndListen(HamburgerClickListener hamburgerClickListener) {
        mHamburgerClickListener = hamburgerClickListener;
    }

}
