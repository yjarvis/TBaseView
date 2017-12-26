//package com.jarvis.tbaseview.ui.activity;
//
//import android.accounts.Account;
//import android.app.Activity;
//import android.content.ContentUris;
//import android.content.Context;
//import android.content.Intent;
//import android.location.Address;
//import android.net.Uri;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.util.Log;
//import android.view.Window;
//import android.widget.Toast;
//
//import com.sun.mail.imap.Utility;
//
//import javax.mail.MessagingException;
//
///**
// * Created by tansheng on 2017/8/23.
// */
//
//public class EmailSendAutoActivity extends Activity implements SendListener{
//    private static String tag = "EmailSendAutoAcitivity";
//    private Context mContext;
//
//    private String mTo;
//    private String mCc;
//    private String mBcc;
//    private String mSubject;
//    private String mBody;
//
//    private EmailAddressValidator mValidator = new EmailAddressValidator();
//    private SendListener mListener;
//    private Toast mWaiting;
//    private boolean enableLog = true;
//
//    /**
//     * Sending account email address.
//     */
//    private String mSendEmail;
//    /**
//     * Sending account id
//     */
//    private long mAccountId = -1;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        this.mContext = this;
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//
//        mListener = this;
//        Intent intent = getIntent();
//        initMessageFromIntent(intent);
//        initAccountFromIntent(intent);
//
//        new SendTask().execute();
//        String msg = intent.getStringExtra("sendMsg");
//        if (msg == null) {
//            msg = "Sending message...";
//        }
//        // mWaiting = ProgressDialog.show(this, "", "sending...", true, true,
//        // null);
//        mWaiting = Toast.makeText(this, msg, Toast.LENGTH_LONG);
//        mWaiting.show();
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (mWaiting != null) {
//            mWaiting.cancel();
//        }
//        super.onBackPressed();
//    }
//
//    @Override
//    public void finish() {
//        if (mWaiting != null) {
//            mWaiting.cancel();
//        }
//        super.finish();
//    }
//
//    /**
//     * Initialize sending account from intent.
//     *
//     * @param intent
//     *            imcoming intent
//     */
//    private void initAccountFromIntent(Intent intent) {
//        String email = intent.getStringExtra("sendAccount");
//        if (email != null) {
//            log(String.format("send email use account (%s) ", email));
//            mSendEmail = email;
//            Long[] ids = EmailContent.Account.getAllAccountIds(this);
//            if (ids != null && ids.length > 0) {
//                for (int i = 0; i < ids.length; i++) {
//                    EmailContent.Account temp = EmailContent.Account
//                            .restoreAccountWithId(this, ids[i]);
//                    if (temp != null && email.equals(temp.getEmailAddress())) {
//                        log("valid account");
//                        mAccountId = ids[i];
//                        break;
//                    }
//                }
//            }
//        }
//    }
//
//    /**
//     * Initialize message from intent.
//     *
//     * @param intent
//     *            intent
//     */
//    private void initMessageFromIntent(Intent intent) {
//        String to = intent.getStringExtra(Intent.EXTRA_EMAIL);
//        mTo = composeAddress(to);
//
//        String cc = intent.getStringExtra(Intent.EXTRA_CC);
//        mCc = composeAddress(cc);
//
//        String bcc = intent.getStringExtra(Intent.EXTRA_BCC);
//        mBcc = composeAddress(bcc);
//
//        mSubject = intent.getStringExtra(Intent.EXTRA_SUBJECT);
//
//        mBody = intent.getStringExtra(Intent.EXTRA_TEXT);
//
//        log("to:" + mTo);
//        log("cc:" + mCc);
//        log("bcc:" + mBcc);
//
//    }
//
//    /**
//     * change to stand email address reference to Rfc822
//     *
//     * @param address
//     *            email address
//     * @return RFC822 format email address
//     */
//    private String composeAddress(String address) {
//        String addr = null;
//        if (!TextUtils.isEmpty(address)) {
//            Address[] addresses = Address.parse(address.trim());
//            addr = Address.pack(addresses);
//        }
//        return addr;
//    }
//
//    /**
//     * Update message. fill fields.
//     *
//     * @param message
//     *            email message
//     * @param account
//     *            sending account
//     */
//    private void updateMessage(Message message, Account account) {
//        if (message.mMessageId == null || message.mMessageId.length() == 0) {
//            message.mMessageId = Utility.generateMessageId();
//        }
//        message.mTimeStamp = System.currentTimeMillis();
//        // it will : Name<Address>
//        message.mFrom = new Address(account.getEmailAddress(), account
//                .getSenderName()).pack();
//        message.mTo = mTo;
//        message.mCc = mCc;
//        message.mBcc = mBcc;
//        message.mSubject = mSubject;
//        message.mText = mBody;
//        message.mAccountKey = account.mId;
//        // here just used account setting simply
//        message.mDisplayName = account.getSenderName();
//        message.mFlagRead = true;
//        message.mFlagLoaded = Message.FLAG_LOADED_COMPLETE;
//
//    }
//
//    private void log(String msg) {
//        if (enableLog) {
//            Log.i(tag, msg);
//        }
//    }
//
//    /**
//     * Really send message.
//     *
//     * @param account
//     *            sending account
//     * @param messageId
//     *            message id
//     */
//    public void sendMessage(Account account, long messageId) {
//        // message uri
//        Uri uri = ContentUris.withAppendedId(EmailContent.Message.CONTENT_URI,
//                messageId);
//        try {
//            // get a sender, ex. smtp sender.
//            Sender sender = Sender.getInstance(mContext, account
//                    .getSenderUri(mContext));
//            // sending started
//            mListener.onStarted(account.mId, messageId);
//            // sending
//            sender.sendMessage(messageId);
//            // send completed
//            mListener.onCompleted(account.mId);
//
//        } catch (MessagingException me) {
//            // report error
//            mListener.onFailed(account.mId, messageId, me);
//        } finally {
//            try {
//                // delete this message whenever send successfully or not
//                mContext.getContentResolver().delete(uri, null, null);
//            } catch (Exception ex) {
//                Log.w(tag, "delete message occur exception message uri:" + uri);
//            }
//        }
//    }
//
//    public void onCompleted(long accountId) {
//        log("send mail ok");
//        // return Activity.RESULT_OK when send successfully
//        setResult(RESULT_OK);
//        finish();
//    }
//
//    public void onFailed(long accountId, long messageId, Exception ex) {
//        log("send mail failed : " + ex.toString());
//        finish();
//    }
//
//    public void onStarted(long messageId, long accountId) {
//        log("send mail started");
//    }
//
//    /**
//     * Send message task, it is an async task
//     *
//     * @author melord_li
//     *
//     */
//    private class SendTask extends AsyncTask<Void, Void, Void> {
//        @Override
//        protected Void doInBackground(Void... params) {
//            // get default account, if not set, first record is treated as
//            // default.
//            // long id = Account.getDefaultAccountId(mContext);
//            long id = mAccountId;
//            if (id < 0) {
//                id = Account.getDefaultAccountId(mContext);
//            }
//            if (id < 0) {
//                Log.w(tag, "no account exists");
//                finish();
//                return null;
//            }
//            // get full account message
//            Account account = Account.restoreAccountWithId(mContext, id);
//
//            // A empty message
//            Message message = new Message();
//            // fill message field
//            updateMessage(message, account);
//
//            // Save this message. Because send API will read message in message
//            // db.
//            Uri uri = message.save(mContext);
//            if (uri == null) {
//                Log.e(tag, "save message occured an error");
//                finish();
//                return null;
//            }
//
//            // send
//            sendMessage(account, message.mId);
//            return null;
//        }
//    }
//}
