package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2017/1/5 - 14:00
 * 注释：
 */
public class MyCollectActivity extends AutoLayoutActivity implements View.OnClickListener{
    private Intent intent;
    @ViewInject(R.id.iv_my_collect_back)
    private ImageView iv_my_collect_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collect);
        intent = getIntent();
        x.view().inject(this);
        iv_my_collect_back.setOnClickListener(this);
    }
    public void onClick(View v){

        switch (v.getId()){
            case R.id.iv_my_collect_back:
                finish();
                break;
            default:
                break;
        }
    }
}
