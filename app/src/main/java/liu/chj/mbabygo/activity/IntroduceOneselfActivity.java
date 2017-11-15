package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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
 * 日期：2016/12/26 - 16:05
 * 注释：自我简介activity
 */
public class IntroduceOneselfActivity extends AutoLayoutActivity implements View.OnClickListener{
    @ViewInject(R.id.iv_introduce_oneself_back)
    private ImageView iv_introduce_oneself_back;
    @ViewInject(R.id.tv_introduce_oneself_sure)
    private TextView tv_introduce_oneself_sure;
    @ViewInject(R.id.et_introduce_oneself)
    private EditText et_introduce_oneself;
    private Intent intent;
    private int num = 100;
    @ViewInject(R.id.tv_number)
    private TextView tv_number;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduce_oneself);
        intent = getIntent();
        x.view().inject(this);
        tv_introduce_oneself_sure.setOnClickListener(this);
        iv_introduce_oneself_back.setOnClickListener(this);
        seteditlimit();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_introduce_oneself_sure:
                if(et_introduce_oneself.getText().toString().length()>0
                       ){
                    intent.putExtra("oneself", et_introduce_oneself.getText().toString());
                    setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                    finish();
                }else{
                    Toast.makeText(IntroduceOneselfActivity.this,"请输入至少一个字",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_introduce_oneself_back:
                finish();
                break;
        }
    }

    /**
     * 现在输入框的字数，且实时显示字数(最多一百字)
     */
    public void seteditlimit(){

        et_introduce_oneself .addTextChangedListener(new TextWatcher() {
            private CharSequence wordNum;//记录输入的字数
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                wordNum= s;//实时记录输入的字数
            }

            @Override
            public void afterTextChanged(Editable s) {

                int number = num - s.length();
                //TextView显示剩余字数
                Log.e("lchj","sssss"+number);
                tv_number.setText("" +(100-number)+"/"+"100");
                selectionStart=et_introduce_oneself.getSelectionStart();
                selectionEnd = et_introduce_oneself.getSelectionEnd();
                if (wordNum.length() > num) {
                    //删除多余输入的字（不会显示出来）
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    et_introduce_oneself.setText(s);
                    et_introduce_oneself.setSelection(tempSelection);//设置光标在最后
                }
            }
        });
    }
}
