package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/30 - 14:28
 * 注释：
 */
public class SystemSetActivity extends AutoLayoutActivity implements View.OnClickListener{
    private Intent intent;
    @ViewInject(R.id.ll_cache)
    private LinearLayout ll_cache;
    @ViewInject(R.id.tv_cache)
    private TextView tv_cache;
    private  String s;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Color1SwitchStyle);
        setContentView(R.layout.activity_system_set);
        intent = getIntent();
        x.view().inject(this);
        setCache();
        ll_cache.setOnClickListener(this);
    }
    private void setCache(){
        try {

            s =  DataCleanManager.getTotalCacheSize(SystemSetActivity.this);
            Log.e("lchj","lchjj"+s);
            tv_cache.setText( s);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }
    public void onClick(View v){
        switch (v.getId()){
            case R.id.ll_cache:
                DataCleanManager.clearAllCache(SystemSetActivity.this);
                setCache();
                if (tv_cache.getText().toString().equals("0k")){

                    Toast.makeText(SystemSetActivity.this,"清除成功！",Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }


    }
}
