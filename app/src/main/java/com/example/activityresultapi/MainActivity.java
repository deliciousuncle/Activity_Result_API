package com.example.activityresultapi;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textview;
    /*Activity Result API:
    https://juejin.cn/post/7061993155759095845
     */
    ActivityResultContracts.StartActivityForResult contrast=new ActivityResultContracts.StartActivityForResult();
   //决定了 输入类型 和 输出类型。
   //启动 Activity 的时候，可以指定输入类型，并将这个输入在启动器的 launch 方法中传入进去，并通过 createIntent 方法来构造出Intent；
   //获取 Activity 的结果时，通过 parseResult 方法来获取指定地输出类型 O。
    ActivityResultCallback<ActivityResult> callback=new ActivityResultCallback<ActivityResult>() {
       @Override
       public void onActivityResult(ActivityResult result) {
           Toast.makeText(MainActivity.this,result.getData().getStringExtra("Activity_A"), Toast.LENGTH_SHORT).show();
       }
   };//ActiyityB的回傳
   ActivityResultLauncher<Intent> launcher=registerForActivityResult(contrast,callback);//啟動器
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gototwo(View view) {
        Intent intent=new Intent(this,MainActivity2.class);
        intent.putExtra("Activity_B","傳到了ActivityB");
        launcher.launch(intent);
    }
}