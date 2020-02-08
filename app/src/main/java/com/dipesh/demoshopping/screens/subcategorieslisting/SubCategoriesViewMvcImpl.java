package com.dipesh.demoshopping.screens.subcategorieslisting;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dipesh.demoshopping.R;
import com.dipesh.demoshopping.screens.common.ViewMvcFactory;
import com.dipesh.demoshopping.screens.common.toolbar.ToolbarViewMvc;
import com.dipesh.demoshopping.screens.common.views.BaseObservableViewMvc;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

public class SubCategoriesViewMvcImpl extends BaseObservableViewMvc<SubCategoriesViewMvc.Listener>
        implements SubCategoriesViewMvc {


    private final ToolbarViewMvc mToolbarViewMvc;
    private final Toolbar mToolbar;
    private final RecyclerView mSubCategoryList;
    private final ProgressBar mProgressBar;

    public SubCategoriesViewMvcImpl(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {

        setRootView(inflater.inflate(R.layout.fragment_subcategories, parent, false));

        mSubCategoryList = findViewById(R.id.sub_category_list);
        mProgressBar = findViewById(R.id.progress);
        mToolbar = findViewById(R.id.toolbar);

        mToolbarViewMvc = viewMvcFactory.getToolbarViewMvc(mToolbar);

        initToolbar();
    }

    private void initToolbar() {
        mToolbar.addView(mToolbarViewMvc.getRootView());

        /*mToolbarViewMvc.setTitle(getString(R.string.question_details_screen_title));

        mToolbarViewMvc.enableUpButtonAndListen(new ToolbarViewMvc.NavigateUpClickListener() {
            @Override
            public void onNavigateUpClicked() {
                for (Listener listener : getListeners()) {
                    listener.onNavigateUpClicked();
                }
            }
        });*/
    }

    /*@Override
    public void bindQuestion(QuestionDetails question) {
        String questionTitle = question.getTitle();
        String questionBody = question.getBody();

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle, Html.FROM_HTML_MODE_LEGACY));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody, Html.FROM_HTML_MODE_LEGACY));
        } else {
            mTxtQuestionTitle.setText(Html.fromHtml(questionTitle));
            mTxtQuestionBody.setText(Html.fromHtml(questionBody));
        }
    }*/


    @Override
    public void showProgressIndication() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressIndication() {
        mProgressBar.setVisibility(View.GONE);
    }
}
