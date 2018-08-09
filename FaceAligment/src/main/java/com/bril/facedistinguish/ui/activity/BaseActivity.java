package com.bril.facedistinguish.ui.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


import com.bril.facedistinguish.R;
import com.bril.facedistinguish.utils.StatusBarUtil;


public class BaseActivity extends AppCompatActivity {
    public Context context;
    public Toolbar toolbar;
    public TextView toolbarTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarTitle = (TextView) findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getTitle());
        toolbar.setTitle("");
        setSupportActionBar(toolbar);


    }
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        setStatusBar();
    }

    public void setStatusBar() {
        StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary), 20);
    }

    public void showMsg(View view, String msg) {
        Snackbar.make(view, msg, Snackbar.LENGTH_LONG).setDuration(1000).show();
    }

    public void setBackVisible() {
//		toolbar.setNavigationIcon(R.drawable.icon_back);
//		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				onBackPressed();
//			}
//		});
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    public void clickBack() {
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                clickBack();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}

