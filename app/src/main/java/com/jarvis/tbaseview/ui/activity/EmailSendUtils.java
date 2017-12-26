package com.jarvis.tbaseview.ui.activity;

import android.text.TextUtils;

import com.jarvis.tbaseviewlib.utils.LogUtils;

/**
 * Created by tansheng on 2017/8/24.
 */

public class EmailSendUtils {
    public static void sendMail(final String sendEmail, final String sendPassword, final String receiverEmail, final String title, final String content) {
        if (TextUtils.isEmpty(sendEmail)||TextUtils.isEmpty(sendPassword)||TextUtils.isEmpty(receiverEmail)||TextUtils.isEmpty(title)||TextUtils.isEmpty(content)){
            LogUtils.e("数据为空，邮件发送失败");
            return;
        }
        final Mail m = new Mail(sendEmail,sendPassword);
        if (sendEmail.split("@")[1].contains("qq")){
            m.set_host(Mail.HOST_QQ);
        }else if (sendEmail.split("@")[1].contains("163")){
            m.set_host(Mail.HOST_163);
        }

        new Thread(new Runnable() {
            @Override
            public void run() {
                String[] toArr = {receiverEmail};
                m.set_to(toArr);
                m.set_from(sendEmail);
                m.set_subject(title);
                m.set_body(content);

                try{
                    //m.addAttachment("/sdcard/filelocation");
                    if (m.send()) {
//                        Toast.makeText(context, "邮件发送成功", Toast.LENGTH_LONG).show();
                    } else {
//                        Toast.makeText(context, "邮件发送失败", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e){
                    LogUtils.e("异常"+e.getMessage());
                    e.printStackTrace();
                }

            }
        }).start();

    }
}
