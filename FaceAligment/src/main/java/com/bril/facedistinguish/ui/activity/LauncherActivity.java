package com.bril.facedistinguish.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.bril.facedistinguish.R;

public class LauncherActivity extends BaseActivity {
Switch mSwitch;
    SharedPreferences sp;
    public static String GESTURE = "gesture";
    public static final int REQUEST_GESTURE = 3;
    public static String SAFE_LOGIN_TYPE = "safe_login_type";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_launcher);
        super.onCreate(savedInstanceState);
        sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        mSwitch=findViewById(R.id.switch1);
        mSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    Intent intent = new Intent();
                    intent.setClass(LauncherActivity.this, PatternLockActivity.class);
                    intent.putExtra("type", "setting");
                    startActivityForResult(intent, REQUEST_GESTURE);
                } else {
                    //putSetting(VOICE, false);
                }
            }
        });
        if (sp.getString(SAFE_LOGIN_TYPE, GESTURE).equals(GESTURE) && sp.getBoolean(GESTURE, false)) {
            Intent intent = new Intent();
            intent.setClass(this, PatternLockActivity.class);
            intent.putExtra("type", "open");
            startActivityForResult(intent, REQUEST_GESTURE);
        }
    }
    private void putSetting(String name, boolean val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean(name, val);
        editor.commit();
    }
    public void doClick(View v) {
        switch (v.getId()) {
            case R.id.button0:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.button1:
                break;
            case R.id.button2:
                break;
            default:
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQUEST_GESTURE:
                if (resultCode == RESULT_OK) {
                    //注册
                    if (data != null && data.getStringExtra("type").equals("setting")) {
                        Log.e("aaaaaaaaa",  data.getStringExtra("type")+"" );
                        putSetting(GESTURE, true);
                        putSettingString(SAFE_LOGIN_TYPE, GESTURE);
                    } else {
                        Toast.makeText(this, "欢迎回来", Toast.LENGTH_LONG).show();
                    }
                }
                break;
        }
    }
    private void putSettingString(String name, String val) {
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(name, val);
        editor.commit();
    }

}
