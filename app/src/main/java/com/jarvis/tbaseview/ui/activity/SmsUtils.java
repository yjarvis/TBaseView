package com.jarvis.tbaseview.ui.activity;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.jarvis.tbaseviewlib.utils.DateUtil;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by tansheng on 2017/8/24.
 */

public class SmsUtils {
    private Context context;
    //会话
    private static final Uri CONVERSATIONS = Uri.parse("content://sms/conversations/");
    //查询联系人
    private static final Uri CONTACTS_LOOKUP = Uri.parse("content://com.android.contacts/phone_lookup/");
    //全部短信
    private static final Uri SMS_ALL   = Uri.parse("content://sms/");
    //收件箱
  private static final Uri SMS_INBOX = Uri.parse("content://sms/inbox");
    //已发送
//  private static final Uri SMS_SENT  = Uri.parse("content://sms/sent");
    //草稿箱
//  private static final Uri SMS_DRAFT = Uri.parse("content://sms/draft");
    ArrayList<MessageInfo> listData = new ArrayList<>();

    public SmsUtils(Context context) {
        this.context = context;
    }

    /**
     * 获取所有短息内容
     */
    public ArrayList<MessageInfo> getSmsContent() {
        ContentResolver cr = context.getContentResolver();
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type", "read"};
        Cursor cur = cr.query(SMS_ALL, projection, null, null, "_id desc");

        if (null == cur) {
            return null;
        }
        listData.clear();
        while (cur.moveToNext()) {
            MessageInfo messageInfo = new MessageInfo();
            String id = cur.getString(cur.getColumnIndex("_id"));//id
            String phone = cur.getString(cur.getColumnIndex("address"));//手机号
            String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body"));//短信内容
            String date = cur.getString(cur.getColumnIndex("date"));//日期
            date = DateUtil.getInstance().getFormat(new Date(Long.valueOf(date)), "yyyy-MM-dd hh:mm:ss");
            String type = cur.getString(cur.getColumnIndex("type"));//收发状态：1表示接受，2表示发送
            String read = cur.getString(cur.getColumnIndex("read"));//读取状态：1表示已读，0表示未读
            messageInfo.setId(id);
            messageInfo.setPhone(phone);
            messageInfo.setName(getContactNameByAddr(context,phone));
            messageInfo.setDate(date);
            messageInfo.setBody(body);
            messageInfo.setType(type);
            messageInfo.setRead(read);
            listData.add(messageInfo);
        }
        Log.e("demo", listData.size() + "条");
        return listData;
    }

    /**
     * 修改短息读取状态
     *
     * @param read 1已读模式,0未读
     */
    public void updateSmsRead(final String id, final String read) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ContentValues values = new ContentValues();
                //修改短信读取状态
                values.put("read", "1");
//                context.getContentResolver().update(SMS_INBOX, values, "_id=?", new String[]{id});
                context.getContentResolver().update(SMS_INBOX, values, "_id="+id,null);
            }
        }).start();
    }

//    /**
//     * 获取联系人姓名
//     *
//     * @param id
//     */
//    String contactName="";
//
//    private Cursor getContactName(Cursor cur) {
//       return new CursorWrapper(cur){
//            @Override
//            public String getString(int columnIndex) {
//                if(super.getColumnIndex("person") == columnIndex){
//                    String contact = super.getString(columnIndex);
//                    //读取联系人,查询对应的名称
//                    Uri uri = Uri.parse(CONTACTS_LOOKUP + contact);
//                    Cursor cursor = context.getContentResolver().query(uri, null, null, null, null);
//                    if(cursor.moveToFirst()){
//                         contactName = cursor.getString(cursor.getColumnIndex("display_name"));
//                        return contactName;
//                    }
//                    return contact;
//                }
//                return super.getString(columnIndex);
//            }
//        };
//    }

    /**
     * 获取联系人姓名
     * @param phoneNumber  电话号码
     */
    private String getContactNameByAddr(Context context,String phoneNumber) {
        Uri personUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,Uri.encode(phoneNumber));
        Cursor cur = context.getContentResolver().query(personUri, new String[] {"display_name"}, null, null, null);
        try{
            if (cur.moveToFirst()) {
                int nameIdx = cur.getColumnIndex("display_name");
                String name = cur.getString(nameIdx);
                cur.close();
                return name;
            }
        }catch (Exception e){
            cur.close();
        }finally {
            cur.close();
        }

        return "";
    }
}
