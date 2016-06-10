package com.example.leo.mypracticemenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    protected static final int MENU_SETTING = Menu.FIRST;

    // 只有在讀入Activity時會被執行一次
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //XML，方式定義選單
        getMenuInflater().inflate(R.menu.menu, menu);

        //程式碼，方式定義選單
//        menu.add(0,MENU_SETTING,100,R.string.action_about)
//            .setIcon(R.drawable.ic_action_about);
        return super.onCreateOptionsMenu(menu);
    }

    //每次執行Menu選單，會執行一次
    /*@Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_about:
                setOptionsDialog();
                break;
            case R.id.action_close:
                finish();
                break;
            case R.id.action_navi_railway:
                setOptionsNavigation();
                break;
            case R.id.action_dial:
                setOptionsDial();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setOptionsDialog() {
        // 使用匿名的實體
        // 當運行完Dialog後，系統會自行回收這個匿名實體所佔用的記憶體空間。
        new AlertDialog.Builder(this)
                .setIcon(R.drawable.ic_action_about_blue)
                .setTitle(R.string.action_about)
                .setMessage(R.string.action_dialog_message)
                .setCancelable(false)
                .setPositiveButton(R.string.action_dialog_close, null)
                .setNegativeButton(R.string.label_homepage, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Uri uri = Uri.parse(getString(R.string.uri_homepage));
                        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                        startActivity(intent);
                    }
                })
                .show();
    }

    protected void setOptionsNavigation() {
        // ?z=16 , 指定縮放程度
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uri_railway)));
        startActivity(intent);
    }

    protected void setOptionsDial() {
        Uri uri = Uri.parse(getString(R.string.uri_dial));
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }
}
