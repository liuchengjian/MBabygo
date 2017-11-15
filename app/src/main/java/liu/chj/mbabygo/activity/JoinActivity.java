package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.ContactsMessageAdapter;
import liu.chj.mbabygo.entity.Actor;

/**
 * 作者：柳成建
 * 日期：2016/12/6 - 14:38
 * 注释：立即参加界面Activty
 */
public class JoinActivity extends AutoLayoutActivity implements View.OnClickListener {
    /**
     * 返回按钮
     */
    @ViewInject(R.id.iv_join_back)
    private ImageView mJoinBack;
    /**
     *ScrollView
     */
    @ViewInject(R.id.sv_join)
    private ScrollView mSvJoin;
    /**
     *添加联系人按钮
     */
    @ViewInject(R.id.ll_add_contacts)
    private LinearLayout mAddContacts;
    /**
     *支付按钮
     */
    @ViewInject(R.id.pay_money)
    private TextView pay_money;
    /**
     *联系人信息列表(比如参与人1，参与人2)
     */
    @ViewInject(R.id.lv_contacts_message)
    private ListView lv_contacts_message;

    private List<Actor>list = new ArrayList<>();
    private ContactsMessageAdapter madapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        Intent intent = getIntent();
        x.view().inject(this);
        //设置监听
        setlisten();
        setContactsMessageData();
    }

    public void setContactsMessageData(){
        madapter = new ContactsMessageAdapter(this,list);
        if(lv_contacts_message.getAdapter()==null){
            lv_contacts_message.setAdapter(madapter);
        }else {
            madapter.notifyDataSetChanged();
        }
        for(int i=0;i<3;i++){
            list.add(new Actor("圆子妈妈","",0));
        }

    }

    /**
     * 设置监听
     */
    public void setlisten() {
        mJoinBack.setOnClickListener(this);
        mAddContacts.setOnClickListener(this);
        pay_money.setOnClickListener(this);
        mSvJoin.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // 触摸屏幕时刻
                    case MotionEvent.ACTION_DOWN:
                        break;
                    // 触摸并移动时刻
                    case MotionEvent.ACTION_MOVE:
                        //实现上下滑动界面关闭输入法键盘
                        setclosetypewriting();
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_join_back:
                finish();
                break;
            case R.id.ll_add_contacts:
                Intent intent = new Intent(JoinActivity.this,AddContactsActivity.class);
                startActivity(intent);
                break;
            case R.id.pay_money:
                Intent paymoneyintent = new Intent(JoinActivity.this,PayMoneyActivty.class);
                startActivity(paymoneyintent);
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
}
