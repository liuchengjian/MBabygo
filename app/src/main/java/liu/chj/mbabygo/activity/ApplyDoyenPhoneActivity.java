package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.ui.PhoneUtils;

/**
 * 作者：柳成建
 * 日期：2016/12/29 - 15:04
 * 注释：
 */
public class ApplyDoyenPhoneActivity extends AutoLayoutActivity implements View.OnClickListener{
    @ViewInject(R.id.iv_apply_doyen_phone_back)
    private ImageView iv_apply_doyen_phone_back;
    @ViewInject(R.id.tv_apply_doyen_phone_sure)
    private TextView tv_apply_doyen_phone_sure;
    @ViewInject(R.id.et_apply_doyen_phone)
    private EditText et_apply_doyen_phone;
    private String userphone;
    Intent intent;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_doyen_phone);
        intent = getIntent();
        x.view().inject(this);
        iv_apply_doyen_phone_back.setOnClickListener(this);
        tv_apply_doyen_phone_sure.setOnClickListener(this);
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_apply_doyen_phone_sure:
                if (!isPhone())
                    break;
                intent.putExtra("phone", et_apply_doyen_phone.getText().toString());
                setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                finish();//此处一定要调用finish()方法
                break;
            case R.id. iv_apply_doyen_phone_back:
                finish();
                break;
        }
    }
    /**
     * 判断手机号
     */
    private boolean isPhone() {
        // TODO Auto-generated method stub
        userphone =  et_apply_doyen_phone.getText().toString().trim();
        if (TextUtils.isEmpty(userphone)) {
            Toast.makeText(ApplyDoyenPhoneActivity.this, "请输入手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        boolean result = PhoneUtils.isPhone(userphone);
        if (result == false) {
            Toast.makeText(ApplyDoyenPhoneActivity.this, "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
