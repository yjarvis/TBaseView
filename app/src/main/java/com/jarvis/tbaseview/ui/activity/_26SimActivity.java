package com.jarvis.tbaseview.ui.activity;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.widget.CheckBox;
import android.widget.EditText;

import com.jarvis.tbaseviewlib.ui.common.TFragmentActivity;
import com.jarvis.tbaseviewlib.ui.common.TitleBackFragment;

import java.util.ArrayList;

/**
 * Created by tansheng on 2017/7/28.
 */

public class _26SimActivity extends TFragmentActivity {
    private TitleBackFragment titleBackFragment;
    private EditText sendEmailEdt;
    private EditText sendPasswordEdt;
    private EditText receiverEmailEdt;
    private CheckBox saveBtn;

    private SmsUtils smsUtils;
//    private SmsObserver smsObserver;
    private final Uri SMS_INBOX = Uri.parse("content://sms");
    private ArrayList<MessageInfo> listData;//短息内容
    private int oldNumber;

    private Handler smsHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            for (int i = 0; i < listData.size(); i++) {
//                if (!listData.get(i).isRead()) {
//                    EmailSendUtils.sendMail(sendEmailEdt.getText().toString().trim(), sendPasswordEdt.getText().toString().trim(), receiverEmailEdt.getText().toString().trim(),
//                            listData.get(i).getName()+"("+listData.get(i).getPhone()+")发来短息", listData.get(i).getName()+"\n"+listData.get(i).getPhone()+"\n\n"+listData.get(i).getBody()+"\n\n"+listData.get(i).getDate());
//                    LogUtils.e("发送成功第"+i+"条");
//                    smsUtils.updateSmsRead(listData.get(i).getId(),"1");
//                }
//            }
        }
   };
   static {
       System.loadLibrary("ndk_tbaseview_test");
   }

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
//        setContentView(R.layout.activity_sim);
//        //注册短息监听
//        smsObserver = new SmsObserver(smsHandler);
//        getContentResolver().registerContentObserver(SMS_INBOX, true, smsObserver);
//        //创建短息工具类
//        smsUtils = new SmsUtils(context);
//        initView(true);
//        setData();
//
//        String st=JniHelloWorld.setSay("TBase");
//        int aa= JniHelloWorld.square(33);
//        LogUtils.e("Jni输出："+st);
//        LogUtils.e("Jni输出："+aa);
    }


    @Override
    public void initView(boolean isStatusBar) {
        super.initView(isStatusBar);
//        titleBackFragment = new TitleBackFragment().newInstance("短信监控", "");
//        addTitleFragment(titleBackFragment);
//
//        sendEmailEdt = (EditText) findViewById(R.id.sim_send_email);
//        sendPasswordEdt = (EditText) findViewById(R.id.sim_send_password);
//        receiverEmailEdt = (EditText) findViewById(R.id.sim_receiver_email);
//        saveBtn = (CheckBox) findViewById(R.id.sim_save_btn);
//        saveBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//                    sendEmailEdt.setEnabled(false);
//                    sendPasswordEdt.setEnabled(false);
//                    receiverEmailEdt.setEnabled(false);
//
//                    TUtils.savaData("sendEamil",sendEmailEdt.getText().toString().trim(),context);
//                    TUtils.savaData("sendPassword",sendPasswordEdt.getText().toString().trim(),context);
//                    TUtils.savaData("receiverEmail",receiverEmailEdt.getText().toString().trim(),context);
//                }else {
//                    sendEmailEdt.setEnabled(true);
//                    sendPasswordEdt.setEnabled(true);
//                    receiverEmailEdt.setEnabled(true);
//                }
//            }
//        });
    }

    @Override
    public void setData() {
//        listData = smsUtils.getSmsContent();
//        oldNumber=listData.size();
//        sendEmailEdt.setText(TUtils.getSaveData("sendEamil",context));
//        sendPasswordEdt.setText(TUtils.getSaveData("sendPassword",context));
//        receiverEmailEdt.setText(TUtils.getSaveData("receiverEmail",context));
    }

    @Override
    public void requestData(boolean isShow) {

    }

    @Override
    public void showFragment(Fragment fragment) {

    }


//    public class SmsObserver extends ContentObserver {
//
//        public SmsObserver(Handler handler) {
//            super(handler);
//        }
//
//        @Override
//        public void onChange(boolean selfChange) {
//            super.onChange(selfChange);
//            //每当有短信来就调用
//            listData = smsUtils.getSmsContent();
//            if (listData.size()<=oldNumber){
//                LogUtils.e("暂无信息");
//                oldNumber=listData.size();
//                return;
//            }
//            oldNumber=listData.size();
//            if (listData.get(0).getType().equals("1")){
//                //接收
//                EmailSendUtils.sendMail(sendEmailEdt.getText().toString().trim(), sendPasswordEdt.getText().toString().trim(), receiverEmailEdt.getText().toString().trim(),
//                        listData.get(0).getName()+"("+listData.get(0).getPhone()+")，发来短息",
//                        "收到信息:"+"\n"+listData.get(0).getName()+"\n"+listData.get(0).getPhone()+"\n\n内容:\n"+listData.get(0).getBody()+"\n\n"+listData.get(0).getDate());
//                smsUtils.updateSmsRead(listData.get(0).getId(),"1");
//            }else {
//                //发送
//                EmailSendUtils.sendMail(sendEmailEdt.getText().toString().trim(), sendPasswordEdt.getText().toString().trim(), receiverEmailEdt.getText().toString().trim(),
//                        "发给："+ listData.get(0).getName()+"("+listData.get(0).getPhone()+")的信息",
//                        "发送给:"+"\n"+listData.get(0).getName()+"\n"+listData.get(0).getPhone()+"\n\n内容:\n"+listData.get(0).getBody()+"\n\n"+listData.get(0).getDate());
//
//                smsUtils.updateSmsRead(listData.get(0).getId(),"1");
//            }
//            LogUtils.e("发送完毕");
//        }
//    }

}
