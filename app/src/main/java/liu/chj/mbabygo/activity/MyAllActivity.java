package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.popupwindow.AnimationUtil;
import liu.chj.mbabygo.popupwindow.ViewUtils;

/**
 * 作者：柳成建
 * 日期：2017/1/3 - 9:49
 * 注释：动态
 */
public class MyAllActivity extends AutoLayoutActivity implements View.OnClickListener{
    private Intent intent;
    @ViewInject(R.id.ll_my_title_all_classify)
    private LinearLayout ll_my_title_all_classify;
    private PopupWindow mPopupWindow = null;
    private int fromYDelta;
    @ViewInject(R.id.iv_all_classify)
    private ImageView iv_all_classify;
    @ViewInject(R.id.iv_trends_back)
    private ImageView iv_trends_back;
    @ViewInject(R.id.tv_all_classify_title)
    private TextView tv_all_classify_title ;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_all_activity);
        intent = getIntent();
        x.view().inject(this);
        ll_my_title_all_classify.setOnClickListener(this);
        iv_trends_back.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_my_title_all_classify:
                showPopupWindow();
                break;
            case R.id.iv_trends_back:
                finish();
                break;
            default:
                break;
        }
    }
    /**
     * 帖子详情顶部弹框的显示
     */
    private void showPopupWindow() {
        View view;
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.popup_my_all_classify, null);
        LinearLayout ll_popup_classify = (LinearLayout) view.findViewById(R.id.ll_popup_classify);
        TextView tv_popup_all_trends = (TextView) view.findViewById(R.id.tv_popup_all_trends);
        TextView tv_popup_activity = (TextView) view.findViewById(R.id.tv_popup_activity);
        TextView tv_popup_article = (TextView) view.findViewById(R.id.tv_popup_article);
        TextView tv_popup_tells = (TextView) view.findViewById(R.id.tv_popup_tells);
        mPopupWindow = new PopupWindow(view,
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        // 实现背景图先出现，然后弹框动画出现效果
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
//		mPopupWindow.setBackgroundDrawable(getResources().getDrawable(
//				R.drawable.bg_transparent));
        // 将这两个属性设置为false，使点击popupwindow外面其他地方不会消失
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.setFocusable(true);
        // 获取popupwindow高度确定动画开始位置
        int contentHeight = ViewUtils.getViewMeasuredHeight(view);
        mPopupWindow.showAsDropDown(ll_my_title_all_classify);
//        mPopupWindow.showAsDropDown(ll_my_title_all_classify, 0, -ll_my_title_all_classify.getHeight());
//		mPopupWindow.showAtLocation(iv_card_information, Gravity.CENTER, 0, 0);
        backgroundAlpha(0.5f);
        fromYDelta = -contentHeight;
        iv_all_classify.setImageResource(R.mipmap.icon_all_classify_top);
        mPopupWindow.getContentView().startAnimation(
                AnimationUtil.createInAnimation(this, fromYDelta));
        tv_popup_all_trends.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                backgroundAlpha(1f);
                tv_all_classify_title.setText("全部动态");

            }
        });
        tv_popup_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                backgroundAlpha(1f);
                tv_all_classify_title.setText("活动");
            }
        });
        tv_popup_article .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                backgroundAlpha(1f);
                tv_all_classify_title.setText("文章");
            }
        });
        tv_popup_tells.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closePopupWindow();
                backgroundAlpha(1f);
                tv_all_classify_title.setText("说说");
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            public void onDismiss() {
                closePopupWindow();
                backgroundAlpha(1f);
            }
        });
    }

    /**
     * 帖子详情顶部弹框的关闭
     */
    private void closePopupWindow() {
        mPopupWindow.getContentView().startAnimation(
                AnimationUtil.createOutAnimation(MyAllActivity.this,
                        fromYDelta));
        mPopupWindow.getContentView().postDelayed(new Runnable() {
            public void run() {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
                // isCollect放这里无用
            }
        }, AnimationUtil.ANIMATION_OUT_TIME);
        iv_all_classify.setImageResource(R.mipmap.icon_all_classify_button);
    }

    // 设置屏幕透明度
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = bgAlpha; // 0.0~1.0
        getWindow().setAttributes(lp);
    }
}
