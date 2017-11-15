package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.app.StatusBarUtils;

/**
 * 作者：柳成建
 * 日期：2016/11/18 - 10:09
 * 注释：首页推荐活动界面Activity
 */
public class RecommendActivityActivity extends AutoLayoutActivity implements View.OnClickListener{
    /**
     * 更多详情按钮
     */
    @ViewInject(R.id.bt_more)
    private Button bt_more;
    /**
     * 返回按钮
     */
    @ViewInject(R.id.iv_header_activity_back)
    private ImageView mIvBack;
    /**
     * 分享按钮
     */
    @ViewInject(R.id.iv_header_activity_share)
    private ImageView mIvShare;
    /**
     * 立即参加按钮
     */
    @ViewInject(R.id.tv_join_activity)
    private TextView mTvJoin;
    /**
     * 咨询按钮
     */
    @ViewInject(R.id.ll_consult)
    private LinearLayout mConsult;
    /**
     * 收藏按钮
     */
    @ViewInject(R.id.ll_synopsis_collect)
    private LinearLayout ll_synopsis_collect;

    //分享弹框
    private PopupWindow popShare = null;
    private LinearLayout  ll_popup;
    private RelativeLayout  parent;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_activity);
        Intent intent = getIntent();
        x.view().inject(this);

        StatusBarUtils.from(this)
                //沉浸状态栏
                .setTransparentStatusbar(true)
                //白底黑字状态栏
//                .setLightStatusBar(true)
                .setTransparentStatusbar(true)
                .setTransparentNavigationbar(true)
                //设置toolbar,actionbar等view
//                .setActionbarView(mNavigationBar)
                .process();

        /**
         * 设置监听器
         */
        setListener();
    }
    public void setListener(){
        bt_more.setOnClickListener(this);
        mIvBack.setOnClickListener(this);
        mIvShare.setOnClickListener(this);
        mTvJoin.setOnClickListener(this);
        mConsult.setOnClickListener(this);
        ll_synopsis_collect.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()) {
            /**
             * 返回按钮
             */
            case R.id.iv_header_activity_back:
                finish();
                break;
            /**
             * 分享按钮
             */
            case R.id.iv_header_activity_share:
                new ShareAction(this)
                        .withText("hello")
                        .setDisplayList(SHARE_MEDIA.SINA,SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE,SHARE_MEDIA.WEIXIN,SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(umShareListener)
                        .open();
//                popupshare();
//                ll_popup.startAnimation(AnimationUtils.loadAnimation(
//                        RecommendActivityActivity.this, R.anim.activity_translate_in));
//                popShare.showAtLocation(mIvShare, Gravity.BOTTOM, 0, 0);
//                backgroundAlpha(0.5f);
                break;
            /**
             * 更多详情按钮
             */
            case R.id.bt_more:
                Intent intent = new Intent(RecommendActivityActivity.this,RotateImageActivity.class);
                startActivity(intent);
                break;
            /**
             * 立即参加按钮
             */
            case R.id.tv_join_activity:
                Intent Joinintent = new Intent(RecommendActivityActivity.this,JoinActivity.class);
                startActivity(Joinintent);
                break;
            /**
             * 咨询按钮
             */
            case R.id.ll_consult:
                Intent consultintent = new Intent(RecommendActivityActivity.this,ConsultActivity.class);
                startActivity(consultintent);
                break;
            /**
             * 收藏按钮
             */
            case R.id.ll_synopsis_collect:
                Toast.makeText(this,"收藏成功",Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }

    }

    /***
     * 分享弹框
     */
    public void popupshare(){
        popShare = new PopupWindow(RecommendActivityActivity.this);

        View view = getLayoutInflater().inflate(R.layout.popup_article_share,
                null);

        ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);

        popShare.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);
        popShare.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        popShare.setBackgroundDrawable(new BitmapDrawable());
        popShare.setFocusable(true);
        popShare.setOutsideTouchable(true);
        popShare.setContentView(view);

        parent = (RelativeLayout) view.findViewById(R.id.parent);
        LinearLayout ll_wechat =(LinearLayout) view.findViewById(R.id.ll_wechat);
        LinearLayout ll_circle_of_friends =(LinearLayout) view.findViewById(R.id.ll_circle_of_friends);
        LinearLayout ll_micro_blog =(LinearLayout) view.findViewById(R.id.ll_micro_blog);
        LinearLayout ll_qq =(LinearLayout) view.findViewById(R.id.ll_qq);
        LinearLayout ll_qq_room =(LinearLayout) view.findViewById(R.id.ll_qq_room);
        popShare.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        parent.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // TODO Auto-generated method stub
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        ll_wechat.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        ll_circle_of_friends.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        ll_micro_blog.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        ll_qq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });
        ll_qq_room.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                popShare.dismiss();
                ll_popup.clearAnimation();
                backgroundAlpha(1f);
            }
        });


    }
    // 设置屏幕透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0~1.0
        getWindow().setAttributes(lp);
    }
    /**
     * 友盟分享回调接口
     */
    private UMShareListener umShareListener = new UMShareListener() {
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Log.d("plat","platform"+platform);

            Toast.makeText(RecommendActivityActivity.this, platform + " 分享成功啦", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(RecommendActivityActivity.this,platform + " 分享失败啦", Toast.LENGTH_SHORT).show();
            if(t!=null){
                Log.d("throw","throw:"+t.getMessage());
            }
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(RecommendActivityActivity.this,platform + " 分享取消了", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 防止后台杀死app
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        UMShareAPI.get(this).onSaveInstanceState(outState);
    }

    /**
     * 防止内存泄漏
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }

}
