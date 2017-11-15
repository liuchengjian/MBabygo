package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.PopupAdapter;

/**
 * 作者：柳成建
 * 日期：2016/12/1 - 15:03
 * 注释：评论文章界面activity
 */
public class CommentArticleActivity extends AutoLayoutActivity implements View.OnClickListener{
    private PopupWindow mPopupWindow;
    private PopupWindow popupWindow;
    int fromYDelta;
    ListView lv_group;
    View view;

    private List<String> groups;
    // 显示弹框
    private boolean isPopWindowShowing = false;
    //分类按钮
    @ViewInject(R.id.ll_classification)
    private LinearLayout mClassification;
    @ViewInject(R.id.lv_comment)
    private LinearLayout mCommentListview;
    //@按钮——>关注的人界面
    @ViewInject(R.id.tab_comment_a)
    private ImageView tab_comment_a;
    //返回按键
    @ViewInject(R.id.rl_comment_back)
    private RelativeLayout mBack;
    //评论输入框
    @ViewInject(R.id.et_tab_comment)
    private EditText et_tab_comment;
    //评论输入框
    @ViewInject(R.id.tv_hot_or_time)
    private TextView tv_hot_or_time;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_article);
        Intent intent = getIntent();
        x.view().inject(this);
        //评论业务逻辑
        //点击实现分类弹框
        setClassifition();
//        setCommentListview();
        mBack.setOnClickListener(this);
       tab_comment_a.setOnClickListener(this);


    }

    public void setClassifition() {
        mClassification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopupWindow(v);
                //关闭输入法
                setclosetypewriting();
//                if (isPopWindowShowing) {
//                    closePopupWindow();
//                    isPopWindowShowing = false;
//                } else {
//                    showPopupWindow();
//                    isPopWindowShowing = true;
//                }
            }
        });
    }
    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 返回按钮
             */
            case R.id.rl_comment_back:
                setclosetypewriting();
                finish();
                break;
            /**
             * @按钮——>关注的人界面
             */
            case R.id.tab_comment_a:
                setclosetypewriting();
                Intent intentA = new Intent(CommentArticleActivity.this,FollowPeopleActivity.class);
//                startActivity(intentA);
                Bundle bundle=new Bundle();
                String strName="aaaaaa";
                bundle.putString("strName",strName);
                intentA.putExtras(bundle);
                startActivityForResult(intentA, 1);//这里采用startActivityForResult来做跳转，此处的0为一个依据，可以写其他的值，但一定要>=0

                break;

            default:
                break;
        }
    }
    /**
     * 关闭输入法
     */
    public void setclosetypewriting(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(getCurrentFocus()
                            .getApplicationWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }

    public void setCommentListview() {
        //滑动关闭输入法
        mCommentListview.setOnTouchListener(new View.OnTouchListener() {

            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // 触摸屏幕时刻
                    case MotionEvent.ACTION_DOWN:
                        break;
                    // 触摸并移动时刻
                    case MotionEvent.ACTION_MOVE:
                        //实现上下滑动界面关闭输入法键盘
                        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                        if (imm.isActive()) {
                            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                            im.hideSoftInputFromWindow(getCurrentFocus()
                                            .getApplicationWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                        }
                        break;
                    // 终止触摸时刻
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    private void showPopupWindow(View parent) {
        if (popupWindow == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.popup_classifition, null);
            lv_group = (ListView) view.findViewById(R.id.lv_group);
            groups = new ArrayList<>();
            groups.add("按热度");
            groups.add("按时间");
//            Collections.reverse(groups);
            PopupAdapter popupAdapter = new PopupAdapter(this, groups);
            lv_group.setAdapter(popupAdapter);
            popupWindow = new PopupWindow(view, LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);
        }

        // 使其聚集
        popupWindow.setFocusable(true);
        // 设置允许在外点击消失
        popupWindow.setOutsideTouchable(true);

        // 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        WindowManager windowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        // 显示的位置为:屏幕的宽度的一半-PopupWindow的高度的一半
        int xPos = windowManager.getDefaultDisplay().getWidth() / 2
                - popupWindow.getWidth() / 2;

        popupWindow.showAsDropDown(parent, xPos, 0);

        lv_group.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view,
                                    int position, long id) {
//                loadNew(((StringItem) (groups.get(position))).getId());
                tv_hot_or_time.setText(groups.get(position));
                Toast.makeText(CommentArticleActivity.this,
                        groups.get(position) + "刷新界面", Toast.LENGTH_LONG)
                        .show();

                if (popupWindow != null)
                    popupWindow.dismiss();
            }
        });
    }

    /**
     * 回传@联系人的数据
     */

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (resultCode) { //resultCode为回传的标记，我在B中回传的是RESULT_OK
            case RESULT_OK:
                Bundle b=data.getExtras(); //data为B中回传的Intent
                String strName=b.getString("strName");//strName即为回传的值
                Log.e("lchj","aaaaaaaaaa"+strName);
                et_tab_comment.setText(strName);
                break;
            default:
                break;
        }
    }

//    private void showPopupWindow() {
//        View view;
//        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
//        view = inflater.inflate(R.layout.popup_classifition, null);
//        // LayoutInflater inflater = getLayoutInflater();
//        // final View contentView = LayoutInflater.from(this).inflate(
//        // R.layout.topic_top_popupwindow, null);
//        // TextView t1= (TextView) contentView.findViewById(R.id.text1);
//        //按热度
//        TextView tv_hot = (TextView)view.findViewById(R.id.tv_hot);
//        //按时间
//        TextView tv_time = (TextView)view.findViewById(R.id.tv_time);
//        mPopupWindow = new PopupWindow(view,
//                LinearLayout.LayoutParams.MATCH_PARENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT);
//        // 实现背景图先出现，然后弹框动画出现效果
//        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
////		mPopupWindow.setBackgroundDrawable(getResources().getDrawable(
////				R.drawable.bg_transparent));
//        // 将这两个属性设置为false，使点击popupwindow外面其他地方不会消失
//        mPopupWindow.setOutsideTouchable(true);
//        mPopupWindow.setFocusable(true);
//        // topic_popupwindow.setVisibility(View.VISIBLE);
//        // 获取popupwindow高度确定动画开始位置
//        int contentHeight = ViewUtils.getViewMeasuredHeight(view);
//        mPopupWindow.showAsDropDown(mClassification, 0, -mClassification.getHeight());
////		mPopupWindow.showAtLocation(iv_card_information, Gravity.CENTER, 0, 0);
//        fromYDelta = -contentHeight;
//        mPopupWindow.getContentView().startAnimation(
//                AnimationUtil.createInAnimation(this, fromYDelta));
//
//        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            public void onDismiss() {
//                closePopupWindow();
//                // topic_popupwindow.setVisibility(View.GONE);
//            }
//        });
//        tv_hot.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closePopupWindow();
//                Toast.makeText(CommentArticleActivity.this,"按热点刷新界面",Toast.LENGTH_LONG).show();
//            }
//        });
//        tv_time.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                closePopupWindow();
//                Toast.makeText(CommentArticleActivity.this,"按时间刷新界面",Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//    private void closePopupWindow() {
//        mPopupWindow.getContentView().startAnimation(
//                AnimationUtil.createOutAnimation(CommentArticleActivity.this,
//                        fromYDelta));
//        mPopupWindow.getContentView().postDelayed(new Runnable() {
//            public void run() {
//                mPopupWindow.dismiss();
//            }
//        }, AnimationUtil.ANIMATION_OUT_TIME);
//    }
}
