package com.komori.sophix;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;

public class MainActivity extends AppCompatActivity {
    private ComponentName defaultComponent;
    private ComponentName testComponent;
    private PackageManager packageManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
//        tv.setText("hello sophix");
//        Toast.makeText(this, "hello sophix", Toast.LENGTH_SHORT).show();

        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "加载补丁....", Toast.LENGTH_SHORT).show();
                SophixManager.getInstance().queryAndLoadNewPatch();
                startActivity(new Intent(MainActivity.this,MainActivity.class));
            }
        });

        //拿到当前activity注册的组件名称
        ComponentName componentName = getComponentName();

        //拿到我们注册的MainActivity组件
        defaultComponent = new ComponentName(getBaseContext(),
                "com.komori.sophix.MainActivity");  //拿到默认的组件
        //拿到我注册的别名test组件
        testComponent = new ComponentName(getBaseContext(),
                "com.komori.sophix.icon_new");

        packageManager = getApplicationContext().getPackageManager();

        disableComponent(defaultComponent);
        enableComponent(testComponent);
    }
    /**
     * 启用组件
     *
     * @param componentName
     */
    private void enableComponent(ComponentName componentName) {
        int state = packageManager.getComponentEnabledSetting(componentName);
        if (state == PackageManager.COMPONENT_ENABLED_STATE_ENABLED) {
            //已经启用
            return;
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP);
    }

    /**
     * 禁用组件
     *
     * @param componentName
     */
    private void disableComponent(ComponentName componentName) {
        int state = packageManager.getComponentEnabledSetting(componentName);
        if (state == PackageManager.COMPONENT_ENABLED_STATE_DISABLED) {
            //已经禁用
            return;
        }
        packageManager.setComponentEnabledSetting(componentName,
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP);
    }

}
