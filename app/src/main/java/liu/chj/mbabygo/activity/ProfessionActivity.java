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
public class ProfessionActivity extends AutoLayoutActivity implements View.OnClickListener{
    private List<Profession>listprofession = new ArrayList<>();
    private ProfessionAdapter mAdapter;
    private Intent intent;
    @ViewInject(R.id.tv_profession_sure)
    private TextView tv_profession_sure;
    String srt = null;
    private String[]Strprofession ={"IT/互联网/通信","文化/艺术",
            "影视/娱乐","金融","医药/健康","媒体/公关","工业/制造业"
            ,"零售","教育/科研","其他"};
    @ViewInject(R.id.lv_profession)
    private ListView lv_profession;
    @ViewInject(R.id.iv_profession_back)
    private ImageView iv_profession_back;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profession);
        intent = getIntent();
        x.view().inject(this);
        setProfession();
        tv_profession_sure.setOnClickListener(this);
        iv_profession_back.setOnClickListener(this);

    }
    public void setProfession(){
        for(int i=0;i<Strprofession.length;i++){

            listprofession.add(new Profession(Strprofession[i]));
        }
        mAdapter = new ProfessionAdapter(ProfessionActivity.this,listprofession);
       if(lv_profession.getAdapter()==null){
           lv_profession.setAdapter(mAdapter);
       } else {
            mAdapter.notifyDataSetChanged();
        }
        lv_profession.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mAdapter.setSelectedPosition(position);
                mAdapter.notifyDataSetInvalidated();
                srt = Strprofession[position];
                Log.e("lchj","lchk"+srt);

            }
        });
    }
    public void onClick(View v){
       switch (v.getId()){
           case R.id.tv_profession_sure:
               intent.putExtra("profession", srt);
               setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
               finish();
               break;
           case R.id.iv_profession_back:
               finish();
               break;
       }
    }
}
