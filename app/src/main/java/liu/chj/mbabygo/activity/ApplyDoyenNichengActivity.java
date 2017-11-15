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
public class ApplyDoyenNichengActivity extends AutoLayoutActivity implements View.OnClickListener{
    @ViewInject(R.id.iv_apply_doyen_nicheng_back)
    private ImageView iv_apply_doyen_nicheng_back;
    @ViewInject(R.id.tv_apply_doyen_nicheng_sure)
    private TextView tv_apply_doyen_nicheng_sure;
    @ViewInject(R.id.et_apply_doyen_nicheng)
    private EditText et_apply_doyen_nicheng;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doyen_nicheng);
        intent = getIntent();
        x.view().inject(this);
        iv_apply_doyen_nicheng_back.setOnClickListener(this);
        tv_apply_doyen_nicheng_sure.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_apply_doyen_nicheng_sure:
                if( et_apply_doyen_nicheng.getText().toString().length()>0
                        && et_apply_doyen_nicheng.getText().toString().length()<20){

                    intent.putExtra("nicheng",  et_apply_doyen_nicheng.getText().toString());
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();//此处一定要调用finish()方法
                }else{
                    Toast.makeText(ApplyDoyenNichengActivity.this,"请输入1~20个字",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id. iv_apply_doyen_nicheng_back:
                finish();
                break;
        }
    }
}
