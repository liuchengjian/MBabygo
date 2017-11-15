package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/7 - 9:34
 * 注释：参与人信息界面
 */
public class ActorActivity extends AutoLayoutActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);
        Intent intent = getIntent();
        x.view().inject(this);
        //设置监听
        setlisten();

    }
    public void setlisten(){
    }
}
