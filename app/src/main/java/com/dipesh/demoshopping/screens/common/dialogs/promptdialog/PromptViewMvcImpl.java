package com.dipesh.demoshopping.screens.common.dialogs.promptdialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;

import androidx.annotation.Nullable;

public class PromptViewMvcImpl extends BaseObservableViewMvc<PromptViewMvc.Listener>
        implements PromptViewMvc {

    private TextView mTxtTitle;
    private TextView mTxtMessage;
    private Button mBtnPositive;
    private Button mBtnNegative;

    
    public PromptViewMvcImpl(LayoutInflater inflater,
                             @Nullable ViewGroup parent) {

        setRootView(inflater.inflate(R.layout.dialog_prompt, parent, false));

        mTxtTitle = findViewById(R.id.txt_title);
        mTxtMessage = findViewById(R.id.txt_message);
        mBtnPositive = findViewById(R.id.btn_positive);
        mBtnNegative = findViewById(R.id.btn_negative);

        mBtnPositive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getListeners() != null){
                    getListeners().onPositiveButtonClicked();
                }
            }
        });

        mBtnNegative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getListeners() != null){
                    getListeners().onNegativeButtonClicked();
                }
            }
        });
        
    }

    @Override
    public void setTitle(String title) {
        mTxtTitle.setText(title);
    }

    @Override
    public void setMessage(String message) {
        mTxtMessage.setText(message);
    }

    @Override
    public void setPositiveButtonCaption(String caption) {
        mBtnPositive.setText(caption);
    }

    @Override
    public void setNegativeButtonCaption(String caption) {
        mBtnNegative.setText(caption);
    }
}
