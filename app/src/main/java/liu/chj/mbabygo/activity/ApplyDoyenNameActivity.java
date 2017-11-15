package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/29 - 15:04
 * 注释：
 */
public class ApplyDoyenNameActivity extends AutoLayoutActivity implements View.OnClickListener{
    @ViewInject(R.id.iv_apply_doyen_name_back)
    private ImageView iv_apply_doyen_name_back;
    @ViewInject(R.id.tv_apply_doyen_name_sure)
    private TextView tv_apply_doyen_name_sure;
    @ViewInject(R.id.et_apply_doyen_name)
    private EditText et_apply_doyen_name;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doyen_name);
        intent = getIntent();
        x.view().inject(this);
        iv_apply_doyen_name_back.setOnClickListener(this);
        tv_apply_doyen_name_sure.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_apply_doyen_name_sure:
                if( et_apply_doyen_name.getText().toString().length()>1
                        && et_apply_doyen_name.getText().toString().length()<20){

                    intent.putExtra("name",  et_apply_doyen_name.getText().toString());
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法
                }else{
                    Toast.makeText(ApplyDoyenNameActivity.this,"请输入2~20个字",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id. iv_apply_doyen_name_back:
                finish();
                break;
        }
    }
}
