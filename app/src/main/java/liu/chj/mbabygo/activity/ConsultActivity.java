package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.ConsultAdapter;
import liu.chj.mbabygo.entity.Consult;
import liu.chj.mbabygo.fragment.TimeUtils;
import liu.chj.mbabygo.ui.InputMethodUtil;

/**
 * 作者：柳成建
 * 日期：2016/12/12 - 10:09
 * 注释：咨询界面activity
 */
public class ConsultActivity extends AutoLayoutActivity implements View.OnClickListener {
    /**
     * 咨询中间列表
     */
    @ViewInject(R.id.lv_consult)
    private ListView LvConsult;
    private List<Consult> listConsult = new ArrayList<>();
    private ConsultAdapter mAdapter;
    /**
     * 咨询输入框
     */
    @ViewInject(R.id.et_consult)
    private EditText etConsult;
    /**
     *  返回
     */

    @ViewInject(R.id.iv_consult_back)
    private ImageView iv_consult_backt;



    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            int what = msg.what;
            switch (what) {
                case 1:
                    /**
                     * ListView条目控制在最后一行
                     */
                    LvConsult.setSelection(listConsult.size());

                    break;

                default:
                    break;
            }
        }

        ;
    };

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        Intent intent = getIntent();
        x.view().inject(this);
        iv_consult_backt.setOnClickListener(this);
        setConsultData();
        setConsultAdapter();
        LvConsult.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // 触摸屏幕时刻
                    case MotionEvent.ACTION_DOWN:
                        break;
                    // 触摸并移动时刻
                    case MotionEvent.ACTION_MOVE:
                        //实现上下滑动界面关闭输入法键盘
                        InputMethodUtil.closeInputMethod(ConsultActivity.this);
                        break;
                    // 终止触摸时刻
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
        etConsult.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    if (TextUtils.isEmpty(etConsult.getText().toString())) {

                        Toast.makeText(ConsultActivity.this, "发送信息不能为空", Toast.LENGTH_SHORT).show();
                    } else {
                        Consult consult = new Consult();
                        Date date = new Date(System.currentTimeMillis());
                        //得到发送内容
                        consult.setAsk(etConsult.getText().toString());
                        //加入集合
                        listConsult.add(consult);
                        //清空输入框
                        etConsult.setText("");
                        consult.setDate(TimeUtils.friendly_time(date));
                        //刷新ListView
                        mAdapter.notifyDataSetChanged();
                        handler.sendEmptyMessage(1);
                        Toast.makeText(ConsultActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    }

                }
                return false;
            }
        });

    }

    public void setConsultData() {
        for (int i = 0; i < 5; i++) {
            listConsult.add(new Consult("我的孩子六岁了，可以参加这个活动吗？", "当然可以啦，这个活动面向的人群是6-12岁的孩子，在这个年龄段的孩子都可以参加！","20分钟前"));
        }
    }

    public void setConsultAdapter() {
        mAdapter = new ConsultAdapter(this, listConsult);
        if (LvConsult.getAdapter() == null) {
            LvConsult.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }
    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_consult_back:
                finish();
                break;
            default:
                break;
        }
    }
}
