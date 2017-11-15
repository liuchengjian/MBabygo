package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/11/16 - 16:49
 * 注释：文章界面Activity
 */
public class RecommendArticleActivity extends AutoLayoutActivity implements View.OnClickListener{
    //评论按钮
    @ViewInject(R.id.ll_comment)
    private LinearLayout mComment;
    //返回按钮
    @ViewInject(R.id.recommend_article_back)
    private ImageView recommend_article_back;
    //分享按钮
    @ViewInject(R.id.recommend_article_share)
    private ImageView recommend_article_share;
    //分享弹框
    private PopupWindow popShare = null;
    private LinearLayout  ll_popup;
    private RelativeLayout  parent;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommend_article);
        Intent intent = getIntent();
        x.view().inject(this);
        mComment.setOnClickListener(this);
        recommend_article_back.setOnClickListener(this);
        recommend_article_share.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_comment:
                Intent intent = new Intent(RecommendArticleActivity.this, CommentArticleActivity.class);
                startActivity(intent);
                break;
            case R.id.recommend_article_back:
                finish();
                break;
            /**
             * 分享按钮
             */
            case R.id.recommend_article_share:
                popupshare();
                ll_popup.startAnimation(AnimationUtils.loadAnimation(
                        RecommendArticleActivity.this, R.anim.activity_translate_in));
                popShare.showAtLocation(recommend_article_share, Gravity.BOTTOM, 0, 0);
                backgroundAlpha(0.5f);
                break;
        }
    }
    public void popupshare(){
        popShare = new PopupWindow(RecommendArticleActivity.this);

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



}
