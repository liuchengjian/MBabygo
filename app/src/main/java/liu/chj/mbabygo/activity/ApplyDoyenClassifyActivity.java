package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.ProfessionAdapter;
import liu.chj.mbabygo.entity.Profession;

/**
 * 作者：柳成建
 * 日期：2016/12/26 - 17:15
 * 注释：个人资料的行业界面Activity
 */
public class ApplyDoyenClassifyActivity extends AutoLayoutActivity implements View.OnClickListener{
    private List<Profession>list_doyen_classify = new ArrayList<>();
    private ProfessionAdapter mAdapter;
    private Intent intent;
    @ViewInject(R.id.tv_apply_doyen_classify_sure)
    private TextView tv_apply_doyen_classify_sure;
    String srt = null;
    private String[]doyenclassify ={"育儿达人","旅游达人",
            "美食达人","学习达人","运动达人"};
    @ViewInject(R.id.lv_apply_doyen_classify)
    private ListView lv_apply_doyen_classify;
    @ViewInject(R.id.iv_apply_doyen_classify_back)
    private ImageView iv_apply_doyen_classify_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doyen_classify);
        intent = getIntent();
        x.view().inject(this);
        setProfession();
        tv_apply_doyen_classify_sure.setOnClickListener(this);
        iv_apply_doyen_classify_back.setOnClickListener(this);

    }
    public void setProfession(){
        for(int i=0;i<doyenclassify.length;i++){

            list_doyen_classify.add(new Profession(doyenclassify[i]));
        }
        mAdapter = new ProfessionAdapter(ApplyDoyenClassifyActivity.this,list_doyen_classify);
       if(lv_apply_doyen_classify.getAdapter()==null){
           lv_apply_doyen_classify.setAdapter(mAdapter);
       } else {
            mAdapter.notifyDataSetChanged();
        }
        lv_apply_doyen_classify.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setSelectedPosition(position);
                mAdapter.notifyDataSetInvalidated();
                srt = doyenclassify[position];

            }
        });
    }
    public void onClick(View v){
       switch (v.getId()){
           case R.id.tv_apply_doyen_classify_sure:
               intent.putExtra("classify", srt);
               setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
               finish();
               break;
           case R.id.iv_apply_doyen_classify_back:
               finish();
               break;
       }
    }
}
