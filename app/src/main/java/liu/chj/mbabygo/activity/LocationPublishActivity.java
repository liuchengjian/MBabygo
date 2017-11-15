package liu.chj.mbabygo.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerActivity;
import cn.bingoogolapple.photopicker.activity.BGAPhotoPickerPreviewActivity;
import cn.bingoogolapple.photopicker.widget.BGASortableNinePhotoLayout;
import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cutpicture.SelectDialog;
import liu.chj.mbabygo.entity.Moment;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * 作者：柳成建
 * 日期：2017/1/9 - 15:27
 * 注释：定位发布界面Activity
 */
public class LocationPublishActivity extends AutoLayoutActivity implements BGASortableNinePhotoLayout.Delegate, View.OnClickListener{
    @ViewInject(R.id.tv_gdmap_iocn_start)
    private TextView tv_gdmap_iocn_start;
    @ViewInject(R.id.tv_gdmap_iocn_show)
    private TextView tv_gdmap_iocn_show;
    private Intent intent;
    @ViewInject(R.id.ll_gdmap_icon)
    private LinearLayout ll_gdmap_icon;
    private static final int REQUEST_CODE_PERMISSION_PHOTO_PICKER = 1;

    private static final int REQUEST_CODE_CHOOSE_PHOTO = 1;
    private static final int REQUEST_CODE_PHOTO_PREVIEW = 2;

    private static final String EXTRA_MOMENT = "EXTRA_MOMENT";
    /**
     * 拖拽排序九宫格控件
     */
    private BGASortableNinePhotoLayout mPhotosSnpl;
    @ViewInject(R.id.et_location)
    private EditText et_location;
    @ViewInject(R.id.iv_location_img)
    private ImageView iv_location_img;
    @ViewInject(R.id.iv_location_publish_publish)
    private ImageView iv_location_publish_publish;
    @ViewInject(R.id.sv_location)
    private ScrollView sv_location;

    public static Moment getMoment(Intent intent) {
        return intent.getParcelableExtra(EXTRA_MOMENT);
    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_publish);
        intent = getIntent();
        x.view().inject(this);
        mPhotosSnpl = (BGASortableNinePhotoLayout )findViewById(R.id.snpl_location_add_photos);
        mPhotosSnpl.setMaxItemCount(9);
        mPhotosSnpl.setEditable(true);
        mPhotosSnpl.setPlusEnable(true);
        mPhotosSnpl.setSortable(true);
        //设置拖拽排序控件的代理
        mPhotosSnpl.setDelegate(this);
        iv_location_img.setOnClickListener(this);
        tv_gdmap_iocn_show.setText("");
        ll_gdmap_icon.setOnClickListener(this);
        sv_location.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    // 触摸屏幕时刻
                    case MotionEvent.ACTION_DOWN:
                        break;
                    // 触摸并移动时刻
                    case MotionEvent.ACTION_MOVE:
                        //实现上下滑动界面关闭输入法键盘
                        setclosetypewriting();
                    case MotionEvent.ACTION_UP:
                        break;
                }
                return false;
            }
        });
    }
    public void onClick(View v){
        if (v.getId() == R.id.iv_location_img) {
            choicePhotoWrapper();
        } else if (v.getId() == R.id.iv_location_publish_publish) {
            String content = et_location.getText().toString().trim();
            if (content.length() == 0 && mPhotosSnpl.getItemCount() == 0) {
                Toast.makeText(this, "必须定位动态或选择照片！", Toast.LENGTH_SHORT).show();
                return;
            }if (mPhotosSnpl.getItemCount() >9){
                Toast.makeText(this, "图片最多九张，请删除多余的照片", Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent();
            intent.putExtra(EXTRA_MOMENT, new Moment(et_location.getText().toString().trim(), mPhotosSnpl.getData()));
            setResult(RESULT_OK, intent);
            finish();
        }
        switch (v.getId()){
            case R.id.ll_gdmap_icon:

                showMapIcon();
                if(TextUtils.isEmpty(tv_gdmap_iocn_show.getText())){
                    tv_gdmap_iocn_start.setVisibility(View.VISIBLE);
                }else {
                    tv_gdmap_iocn_start.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }
    /**
     * 弹出选择icon菜单
     */
    public void showMapIcon() {
        new SelectDialog(LocationPublishActivity.this)
                .builder()
                .setCancelable(true)
                .setCanceledOnTouchOutside(true)
                .addSelectItem("玩耍", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv_gdmap_iocn_show.setText("玩耍");
                                tv_gdmap_iocn_start.setVisibility(View.GONE);
                            }
                        })
                .addSelectItem("美食", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv_gdmap_iocn_show.setText("美食");
                                tv_gdmap_iocn_start.setVisibility(View.GONE);
                            }
                        })
                .addSelectItem("学习", SelectDialog.SelectItemColor.Blue,
                        new SelectDialog.OnSelectItemClickListener() {
                            @Override
                            public void onClick(int which) {
                                tv_gdmap_iocn_show.setText("学习");
                                tv_gdmap_iocn_start.setVisibility(View.GONE);
                            }
                        }).show();
    }
    public void onClickAddNinePhotoItem(
            BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position,
            ArrayList<String> models) {
        choicePhotoWrapper();
    }

    public void onClickDeleteNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        mPhotosSnpl.removeItem(position);
    }

    public void onClickNinePhotoItem(BGASortableNinePhotoLayout sortableNinePhotoLayout, View view, int position, String model, ArrayList<String> models) {
        startActivityForResult(BGAPhotoPickerPreviewActivity.newIntent(this, mPhotosSnpl.getMaxItemCount(), models, models, position, true), REQUEST_CODE_PHOTO_PREVIEW);
    }

    @AfterPermissionGranted(REQUEST_CODE_PERMISSION_PHOTO_PICKER)
    private void choicePhotoWrapper() {
        String[] perms = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {
            // 拍照后照片的存放目录，改成你自己拍照后要存放照片的目录。如果不传递该参数的话就没有拍照功能
            File takePhotoDir = new File(Environment.getExternalStorageDirectory(), "BGAPhotoPickerTakePhoto");

            startActivityForResult(BGAPhotoPickerActivity.newIntent(this,  takePhotoDir, mPhotosSnpl.getMaxItemCount() - mPhotosSnpl.getItemCount(), null, true), REQUEST_CODE_CHOOSE_PHOTO);
        } else {
            EasyPermissions.requestPermissions(this, "图片选择需要以下权限:\n\n1.访问设备上的照片\n\n2.拍照", REQUEST_CODE_PERMISSION_PHOTO_PICKER, perms);
        }
    }
    //
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    public void onPermissionsGranted(int requestCode, List<String> perms) {
    }

    public void onPermissionsDenied(int requestCode, List<String> perms) {
        if (requestCode == REQUEST_CODE_PERMISSION_PHOTO_PICKER) {
            Toast.makeText(this, "您拒绝了「图片选择」所需要的相关权限!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_CHOOSE_PHOTO) {
            mPhotosSnpl.addMoreData(BGAPhotoPickerActivity.getSelectedImages(data));
        } else if (requestCode == REQUEST_CODE_PHOTO_PREVIEW) {
            mPhotosSnpl.setData(BGAPhotoPickerPreviewActivity.getSelectedImages(data));
        }
    }
    /**
     * 关闭输入法
     */
    public void setclosetypewriting(){
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            InputMethodManager im = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(getCurrentFocus()
                            .getApplicationWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);

        }
    }
}
