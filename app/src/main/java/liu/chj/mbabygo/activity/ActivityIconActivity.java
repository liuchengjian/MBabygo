package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.Browser;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/20 - 15:18
 * 注释：活动icon界面
 */
public class ActivityIconActivity extends AutoLayoutActivity implements View.OnClickListener{
    private String[] mVals = new String[]
            {"3+", "5+", "10+", "旅行", "户外", "踏青",
                    "教育", "学习", "美食", "运动", "比赛"
                    };
    private ArrayList<String>listicon = new ArrayList<>();
    String icon;
    private TagFlowLayout mFlowLayout;
    @ViewInject(R.id.activity_icon_complete)
    private TextView activity_icon_complete;
    Intent intent;
    Set<Integer>setiocn ;
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_icon);
            intent = getIntent();
            x.view().inject(this);
            activity_icon_complete.setOnClickListener(this);
            final LayoutInflater mInflater = LayoutInflater.from(this);
            mFlowLayout = (TagFlowLayout) findViewById(R.id.id_flowlayout);
            mFlowLayout.setMaxSelectCount(3);
            mFlowLayout.setAdapter(new TagAdapter<String>(mVals)
            {

            @Override
            public View getView(FlowLayout parent, int position, String s)
            {
                TextView tv = (TextView) mInflater.inflate(R.layout.
                        tv_activity_icon,
                        mFlowLayout, false);
                tv.setText(s);
                return tv;
            }
        });
        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener()
        {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent)
            {
//                Toast.makeText(ActivityIconActivity.this, mVals[position], Toast.LENGTH_SHORT).show();
                //view.setVisibility(View.GONE);
                return true;
            }
        });
        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                //把返回数据存入Intent

                setiocn = selectPosSet;
                Log.e("lchj","qqqqqqqqqq"+setiocn );
//                for(Integer integer:selectPosSet){
//                Log.e("lchj","qqqqqqqqqq"+integer);
//                    icon  =mVals[integer];
//                    listicon.add(icon);
//                    Log.e("lchj","qqqqqqqqqq"+listicon.size());
//                    Toast.makeText(ActivityIconActivity.this, mVals[integer], Toast.LENGTH_SHORT).show();
//                }
            }
        });

    }
    public void onClick(View v){
        /**
         * 完成按钮
         */
        switch(v.getId()){
            case R.id.activity_icon_complete:
                if(setiocn == null){
                    /**
                     * 当不点icon时，或者第一次点击完成时，会返回数据把icon存放的textview清空
                     */
                    setResult(CONTEXT_INCLUDE_CODE, intent);
                    finish();
                }else{
                    /**
                     * 回传icon数据
                     */
                    Log.e("lchj","dddddddd"+setiocn);
                    intent.putExtra("icon",(Serializable) setiocn);
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法
                }

                break;
        }

    }


}
