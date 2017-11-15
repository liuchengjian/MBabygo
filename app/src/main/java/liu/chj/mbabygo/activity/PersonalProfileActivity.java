package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airsaid.pickerviewlibrary.CityPickerView;
import com.airsaid.pickerviewlibrary.OptionsPickerView;
import com.airsaid.pickerviewlibrary.TimePickerView;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/12/26 - 9:40
 * 注释：个人资料activity
 */
public class PersonalProfileActivity extends AutoLayoutActivity implements View.OnClickListener{
    /**
     * 生日
     */
    @ViewInject(R.id.birthday)
    private RelativeLayout birthday;
    /**
     * 显示生日
     */
    @ViewInject(R.id.tv_birthday)
    private TextView tv_birthday;
    /**
     * 地区
     */
    @ViewInject(R.id.rl_area)
    private RelativeLayout rl_area;
    /**
     * 显示地区
     */
    @ViewInject(R.id.tv_area)
    private TextView tv_area;
    /**
     * 性别
     */
    @ViewInject(R.id.rl_sex)
    private RelativeLayout rl_sex;
    /**
     * 显示性别
     */
    @ViewInject(R.id.tv_sex)
    private TextView tv_sex;
    /**
     * 性别
     */
    @ViewInject(R.id.rl_personal_name)
    private RelativeLayout rl_personal_name;
    /**
     * 显示性别
     */
    @ViewInject(R.id.tv_personal_name)
    private TextView tv_personal_name;
    /**
     * 自我简介
     */
    @ViewInject(R.id.rl_introduce_oneself)
    private RelativeLayout rl_introduce_oneself;
    /**
     * 显示自我简介
     */
    @ViewInject(R.id.tv_introduce_oneself)
    private TextView tv_introduce_oneself;
    /**
     *行业
     */
    @ViewInject(R.id.profession)
    private RelativeLayout profession;

    /**
     * 显示行业
     */
    @ViewInject(R.id.tv_profession)
    private TextView tv_profession;
    /**
     * 手机绑定
     */
    @ViewInject(R.id.tv_phone_bunding)
    private TextView tv_phone_bunding;
    /**
     *手机绑定
     */
    @ViewInject(R.id.rl_phone_bunding)
    private RelativeLayout rl_phone_bunding;
    @ViewInject(R.id.iv_personal_remove)
    private ImageView iv_personal_remove;

    private static final int PERSONAL_NAME_REVISE = 1;
    private static final int INTRODUCE_ONESELF = 2;
    private static final int PROFESSION = 3;
    private static final int PHONE_BUNDING = 4;
    private Bundle bundle;

    private TimePickerView mTimePickerView;
    private CityPickerView mCityPickerView;
    private OptionsPickerView<String> mOptionsPickerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_profile);
        Intent intent = getIntent();
        /**
         * 初始化，必须放在全局，因为按返回键需要判断
         */
        mCityPickerView = new CityPickerView(this);
        mTimePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
        mOptionsPickerView = new OptionsPickerView<>(this);
        x.view().inject(this);
        //设置监听

        birthday.setOnClickListener(this);
        rl_area.setOnClickListener(this);
        rl_sex.setOnClickListener(this);
        rl_personal_name.setOnClickListener(this);
        rl_introduce_oneself.setOnClickListener(this);
        profession.setOnClickListener(this);
        iv_personal_remove.setOnClickListener(this);
        rl_phone_bunding.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            /**
             * 昵称
             */
            case R.id.rl_personal_name:
                Intent personal_name_revise = new Intent(PersonalProfileActivity.this,ReviseNameActivity.class);
//                startActivity(personal_name_revise);
                bundle = new Bundle();
//                bundle.put("reviseName","");
                personal_name_revise.putExtras(bundle);
                startActivityForResult( personal_name_revise, PERSONAL_NAME_REVISE);
                break;
            /**
             * 生日（时间选择器）
             */
            case R.id.birthday:
                /**
                 * 设置时间
                 */
                SetTimePickerView();
                break;
            case R.id.rl_area:
                /**
                 * 设置地区
                 */
                SetCityPickerView();
                break;
            case R.id.rl_sex:
                /**
                 * 设置性别
                 */
                Setsex();
                break;
            case R.id.rl_introduce_oneself:
                /**
                 * 自我简介
                 */
                Intent introduce_oneself_intent= new Intent(PersonalProfileActivity.this,IntroduceOneselfActivity.class);
//                startActivity(personal_name_revise);
                bundle = new Bundle();
//                bundle.put("reviseName","");
                introduce_oneself_intent.putExtras(bundle);
                startActivityForResult( introduce_oneself_intent, INTRODUCE_ONESELF);
                break;
            case R.id.profession:
                /**
                 * 行业
                 */
                Intent professionintent= new Intent(PersonalProfileActivity.this,ProfessionActivity.class);
                bundle = new Bundle();
//                bundle.put("reviseName","");
                professionintent.putExtras(bundle);
                startActivityForResult( professionintent, PROFESSION);
                break;
            case R.id.iv_personal_remove:
                //返回
                finish();
                break;
            case R.id.rl_phone_bunding:
                Intent phonebundingintent= new Intent(PersonalProfileActivity.this,PhoneBundingActivity.class);
                bundle = new Bundle();
//                bundle.put("reviseName","");
                phonebundingintent.putExtras(bundle);
                startActivityForResult( phonebundingintent, PHONE_BUNDING);
//                startActivity(phonebundingintent);
                break;
            default:
                break;
        }
    }
    /**
     * 地点滚轴
     */
    private void SetCityPickerView(){
        // 设置点击外部是否消失
//        mCityPickerView.setCancelable(true);


        // 设置取消文字
//        mCityPickerView.setCancelText("我是取消文字");
        // 设置取消文字颜色
//        mCityPickerView.setCancelTextColor(Color.GRAY);

        // 设置确定文字
//        mCityPickerView.setSubmitText("我是确定文字");
        // 设置确定文字颜色
//        mCityPickerView.setSubmitTextColor(Color.BLACK);
        // 设置取消文字大小
        mCityPickerView.setCancelTextSize(14f);
        // 设置确定文字大小
        mCityPickerView.setSubmitTextSize(14f);
        // 设置头部背景
//        mCityPickerView.setHeadBackgroundColor(Color.RED);
        //         设置标题
        mCityPickerView.setTitle("");
        mCityPickerView.setCyclic(true,false,true);
        // 设置滚轮字体大小
        mCityPickerView.setTextSize(18f);
        mCityPickerView.setSubmitTextColor(getResources().getColor(R.color.text_blue_color));
        mCityPickerView.setCancelTextColor(getResources().getColor(R.color.text_blue_color));

        mCityPickerView.setOnCitySelectListener(new CityPickerView.OnCitySelectListener() {

            @Override
            public void onCitySelect(String str) {
//                Toast.makeText(PersonalProfileActivity.this, str, Toast.LENGTH_SHORT).show();
                tv_area.setText(str);
            }
        });
        mCityPickerView.show();
    }

    /**
     * 时间滚轴
     */
    private void SetTimePickerView() {


        // 设置是否循环
        mTimePickerView.setCyclic(true);
        // 设置时间可选范围(结合 setTime 方法使用,必须在)
        Calendar calendar = Calendar.getInstance();
        mTimePickerView.setRange(calendar.get(Calendar.YEAR) - 80, calendar.get(Calendar.YEAR));
        // 设置选中时间
        mTimePickerView.setTime(new Date());
        // 设置滚轮文字大小
        // 放在选择时间之后
        mTimePickerView.setTextSize(4f);
        mTimePickerView.setCancelTextSize(14f);
        // 设置确定文字大小
        mTimePickerView.setSubmitTextSize(14f);
        mTimePickerView.setSubmitTextColor(getResources().getColor(R.color.text_blue_color));
        mTimePickerView.setCancelTextColor(getResources().getColor(R.color.text_blue_color));
        mTimePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.CHINA);
//                Toast.makeText(PersonalProfileActivity.this, format.format(date), Toast.LENGTH_SHORT).show();
                /**
                 * 显示生日
                 */
                tv_birthday.setText(format.format(date));
            }
        });
        mTimePickerView.show();
    }
    public void Setsex(){
        final ArrayList<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        // 设置数据
        mOptionsPickerView.setPicker(list);
        mOptionsPickerView.setCancelTextSize(14f);
        // 设置确定文字大小
        mOptionsPickerView.setSubmitTextSize(14f);
        mOptionsPickerView.setSubmitTextColor(getResources().getColor(R.color.text_blue_color));
        mOptionsPickerView.setCancelTextColor(getResources().getColor(R.color.text_blue_color));
        // 设置选项单位
//        mOptionsPickerView.setLabels("性");
        mOptionsPickerView.setOnOptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int option1, int option2, int option3) {
                String sex = list.get(option1);
//                Toast.makeText(PersonalProfileActivity.this, sex, Toast.LENGTH_SHORT).show();
                    tv_sex.setText(sex);
            }
        });
        mOptionsPickerView.show();
    }

    /**
     *
     * @param keyCode
     * @param event
     * @return返回键去掉弹框
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if(mTimePickerView.isShowing()){
                mTimePickerView.dismiss();
                return true;
            }

            if(mCityPickerView.isShowing()){
                mCityPickerView.dismiss();
                return true;
            }

            if(mOptionsPickerView.isShowing()){
                mOptionsPickerView.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==PERSONAL_NAME_REVISE && resultCode == RESULT_OK){
            /**
             * 回传添加icon数据
             */
            bundle=data.getExtras(); //data为B中回传的Intent
            String reviseName=bundle.getString("reviseName");
            Log.e("lchj","aaaaaaaaaa"+reviseName);
            tv_personal_name.setText(reviseName);
        }else if(requestCode ==INTRODUCE_ONESELF && resultCode == RESULT_OK){
            /**
             * 回传添加icon数据
             */
            bundle=data.getExtras(); //data为B中回传的Intent
            String oneself=bundle.getString("oneself");
            Log.e("lchj","aaaaaaaaaa"+oneself);
            tv_introduce_oneself.setText(oneself);
        }else if (requestCode ==PROFESSION && resultCode == RESULT_OK){
            /**
             * 回传添加icon数据
             */
            bundle=data.getExtras(); //data为B中回传的Intent
            String profession=bundle.getString("profession");
            if (profession==null){
                tv_profession.setText("未填写");
            }else{

                tv_profession.setText(profession);
            }
        }else if(requestCode ==PHONE_BUNDING && resultCode == RESULT_OK){
            bundle=data.getExtras(); //data为B中回传的Intent
            String phone_bunding=bundle.getString("phone");
            if (phone_bunding==null){
                tv_phone_bunding.setText("未绑定");
            }else{
                tv_phone_bunding.setText(phone_bunding);
            }
        }
    }
}
