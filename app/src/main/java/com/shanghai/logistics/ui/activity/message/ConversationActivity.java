package com.shanghai.logistics.ui.activity.message;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


//public class ConversationActivity extends SimpleActivity {

//    private ConversationFragment fragment;
//    // private MessageListAdapter messageAdapter;
//    private static String TAG = "ConversationActivity";
//    private Uri uri;
//    private Conversation.ConversationType mConversationType;
//
//    @Override
//    protected int getLayout() {
//        return R.layout.activity_conversation;
//    }
//
//    @Override
//    protected void initEventAndData() {
//
//        //   messageAdapter = fragment.getMessageAdapter();
//
//        RongIM.connect("ERiTVYwEQtpLMT+dbGyzm25uo30QF7fp2oUreNq+FRFCdsAjN2kYqQmorwW02yWl432BFjcxmMfpSWGdrZheBl5ioJh9BxROTTN2DBgnKdtO/m3NLMHL2c3MV7z835JMck+FEjL6ZUw=", new RongIMClient.ConnectCallback() {
//
//            /**
//             * Token 错误。可以从下面两点检查 1.  Token 是否过期，如果过期您需要向 App Server 重新请求一个新的 Token
//             * 2.  token 对应的 appKey 和工程里设置的 appKey 是否一致
//             */
//            @Override
//            public void onTokenIncorrect() {
//
//            }
//
//            /**
//             * 连接融云成功
//             *
//             * @param userid 当前 token 对应的用户 id
//             */
//            @Override
//            public void onSuccess(String userid) {
//                Log.e("homeFragment", "用户模块融云连接成功targetId = " + userid);
//            }
//
//            /**
//             * 连接融云失败
//             *
//             * @param errorCode 错误码，可到官网 查看错误码对应的注释
//             */
//            @Override
//            public void onError(RongIMClient.ErrorCode errorCode) {
//                Log.e("homeFragment", "用户模块融云连接失败");
//            }
//        });
//        Intent intent = getIntent();
//        mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase());
//        /* 新建 ConversationFragment 实例，通过 setUri() 设置相关属性*/
//        fragment = new ConversationFragment();
//        uri = Uri.parse("rong://" + getApplicationInfo().packageName).buildUpon()
//                .appendPath("conversation").appendPath(mConversationType.getName().toLowerCase())
//                .appendQueryParameter("targetId", "e33be7a43b674bc397792b99c5658a04").build();
//
//        fragment.setUri(uri);
//        /* 加载 ConversationFragment */
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.conversation, fragment);
//        transaction.show(fragment).commit();
//
//        RongIMClient.setTypingStatusListener(new RongIMClient.TypingStatusListener() {
//            @Override
//            public void onTypingStatusChanged(Conversation.ConversationType type, String targetId, Collection<TypingStatus> typingStatusSet) {
//                //当输入状态的会话类型和targetID与当前会话一致时，才需要显示
//                if (type.equals(PRIVATE) && targetId.equals(targetId)) {
//                    //count表示当前会话中正在输入的用户数量，目前只支持单聊，所以判断大于0就可以给予显示了
//                    int count = typingStatusSet.size();
//                    if (count > 0) {
////                        if(currentIdentity==1){
////                          //  commit(userId, token, interroNeedId);
////                            doctorRequests(ownTargetId,dId);
////                        }
//                        Iterator iterator = typingStatusSet.iterator();
//                        TypingStatus status = (TypingStatus) iterator.next();
//                        String objectName = status.getTypingContentType();
//                        MessageTag textTag = TextMessage.class.getAnnotation(MessageTag.class);
//                        MessageTag voiceTag = VoiceMessage.class.getAnnotation(MessageTag.class);
//                        //匹配对方正在输入的是文本消息还是语音消息
//                        if (objectName.equals(textTag.value())) {
//                            //显示“对方正在输入”
//                            mHandler.sendEmptyMessage(1);
//                        } else if (objectName.equals(voiceTag.value())) {
//                            //显示"对方正在讲话"
//                            mHandler.sendEmptyMessage(2);
//                        }
//                    } else {
//                        //当前会话没有用户正在输入，标题栏仍显示原来标题
//                        mHandler.sendEmptyMessage(0);
//
//                    }
//                }
//            }
//        });
//    }
//    @SuppressLint("HandlerLeak")
//    private Handler mHandler = new Handler() {
//        @SuppressWarnings("unused")
//        public void handleMessage(android.os.Message msg) {
//            switch (msg.what) {
//                case 1: {
//                   // mTextView.setText("对方正在输入");
//                    Log.e(TAG, "onTypingStatusChanged: 对方正在输入");
//                    break;
//                }
//                case 2: {
//                 //   mTextView.setText("对方正在讲话");
//                    Log.e(TAG, "onTypingStatusChanged: 对方正在讲话");
//                    break;
//                }
//                default:
//                 //   mTextView.setText(title);
//                    Log.e(TAG, "onTypingStatusChanged: 当前会话没有用户正在输入，标题栏仍显示原来标题");
//                    break;
//            }
//        }
//    };
//}
