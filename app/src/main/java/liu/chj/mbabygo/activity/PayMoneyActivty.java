package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/8 - 10:24
 * 注释：支付界面Activty
 */
public class PayMoneyActivty extends AutoLayoutActivity{

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_money);
        Intent intent = getIntent();
        x.view().inject(this);
        //设置监听
        setlisten();

    }
    /**
     * 设置监听
     */
    public void setlisten() {
    }
}
