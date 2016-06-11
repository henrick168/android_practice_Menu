package com.example.leo.mypracticemenu;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "myLog";
    Debug debug ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        debug = new Debug();
        if(debug.On) Log.d(TAG, "onCreate");
    }

    protected static final int MENU_SETTING = Menu.FIRST;

    // 只有在讀入Activity時會被執行一次
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //XML，方式定義選單
        if(debug.On) Log.d(TAG, "onCreateOptionsMenu");
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
        if(debug.On) Log.d(TAG, "onOptionsItemSelected");
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
            case R.id.action_notification:
                sendNotification();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void setOptionsDialog() {
        if(debug.On) Log.d(TAG, "setOptionsDialog");
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
        if(debug.On) Log.d(TAG, "setOptionsNavigation");
        // ?z=16 , 指定縮放程度
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.uri_railway)));
        startActivity(intent);
    }

    protected void setOptionsDial() {
        if(debug.On) Log.d(TAG, "setOptionsDial");
        Uri uri = Uri.parse(getString(R.string.uri_dial));
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        startActivity(intent);
    }

    protected void sendNotification() {
        if(debug.On) Log.d(TAG, "sendNotification");
        NotificationManager barManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

//         改用Notification.Builder
//        Notification barMsg = new Notification(
//                R.drawable.ic_action_about,
//                "hello!!",
//                System.currentTimeMillis()
//        );

        PendingIntent contentIntent = PendingIntent.getActivity(
                this,
                0,
                new Intent(this, MainActivity.class),
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        // 也可使用 NotificationCompat.Builder
        Notification.Builder barMsg = new Notification.Builder(this)
                .setTicker("HELLO")
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                        // 加入setStyle() 解決問題: 在Notification中若文字過多，導致文字被畫面裁切的問題。
                .setStyle(new Notification.BigTextStyle().bigText("訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息"))
                .setContentTitle("彈出狀態欄訊息")
                .setContentText("訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息")
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
                .setContentIntent(contentIntent);   //若有其他需求(例如點下去換頁、播打電話等)，需要透過PendingIntent來控制。
//                .setContentIntent(null);  //按下Notification不會有任何事件發生。
        barManager.notify(0, barMsg.build());

//        setLatestEventInfo(),已於Android 6.0中移除了。
//        改用Notification.Builder開發
//        barMsg.setLatestEventInfo(
//                NewActivity.this,
//                "彈出狀態欄訊息",
//                "訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息訊息",
//                contentIntent
//        );
    }
}
