package liu.chj.mbabygo.activity;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.FragmentAdapter;
import liu.chj.mbabygo.fragment.AddActivityRoutingFragment;
import liu.chj.mbabygo.fragment.AddActivitySynopsisFragment;
import liu.chj.mbabygo.fragment.MyFragment;

/**
 * 作者：柳成建
 * 日期：2016/12/22 - 16:15
 * 注释：填写活动详情activity
 */
public class WriteCommentActivity extends AutoLayoutActivity implements View.OnClickListener{
    @ViewInject(R.id.vp_write_comment)
    private ViewPager vp_write_comment;
    @ViewInject(R.id.write_comment_tabLayout)
    private TabLayout write_comment_tabLayout;
    private FragmentAdapter adapter;
    private List<Fragment>listFragment = new ArrayList<>();
    private List<String>WriteTitle = new ArrayList<>() ;
    Fragment  ASF;
    @ViewInject(R.id.iv1)
    private ImageView iv1;
    @ViewInject(R.id.iv2)
    private ImageView iv2;

    private static final int REQUEST_CODE_PICK_IMAGE = 1023;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 1022;
    private View btn1, btn2, btn3;
    private View.OnClickListener btnListener;

    private static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    private File mCurrentPhotoFile;// 照相机拍照得到的图片
    public static final String TMP_PATH = "temp.jpg";
    String IMAGE;
    Bundle bundle;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_comment);
        Intent intent = getIntent();
        x.view().inject(this);
        Bundle bundle = new Bundle();
        ASF = new AddActivitySynopsisFragment();
        bundle.putString("ss", IMAGE);
        ASF.setArguments(bundle);
        listFragment.add( ASF);
        listFragment.add(new AddActivityRoutingFragment());
        WriteTitle.add("简介");
        WriteTitle.add("行程");
        adapter = new FragmentAdapter(getSupportFragmentManager(),WriteTitle,listFragment);
        // 设置适配器
        vp_write_comment.setAdapter(adapter);
        // 直接绑定viewpager，消除了以前的需要设置监听器的繁杂工作
        write_comment_tabLayout.setupWithViewPager(vp_write_comment);
        iv1.setOnClickListener(this);
        iv2.setOnClickListener(this);
    }

    //    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
////        /*然后在碎片中调用重写的onActivityResult方法*/
////        ASF.onActivityResult(requestCode, resultCode, data);
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv1 :
//                Intent intent = new Intent(Intent.ACTION_PICK);
//                intent.setType("image/*");// 相片类型
//                startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                break;
            case R.id.iv2 :
                // 打开相机
//                openCamera();
                break;

        }
    }
    protected void openCamera() {
        try {
            // Launch camera to take photo for selected contact
            PHOTO_DIR.mkdirs();// 创建照片的存储目录
            mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());// 给新照的照片文件命名
            final Intent intent = getTakePickIntent(mCurrentPhotoFile);
            startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
        } catch (ActivityNotFoundException e) {
        }
//        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
//                Environment.getExternalStorageDirectory(), TMP_PATH)));
//        startActivityForResult(intent, REQUEST_CODE_CAPTURE_CAMEIA);
    }

    public static Intent getTakePickIntent(File f) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        return intent;
    }

    /**
     * 用当前时间给取得的图片命名
     */
    private String getPhotoFileName() {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "'IMG'_yyyy-MM-dd HH:mm:ss");
//        Log.e("lchj","dddddddddd"+dateFormat.format(date) + ".jpg");
        return dateFormat.format(date) + ".jpg";
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        Log.e("lchj","onActivityResult");
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//
//        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
//            Uri uri = data.getData();
////            bundle.putString("ss", getRealFilePath(uri));
////            ASF.setArguments(bundle);
//            IMAGE = getRealFilePath(uri);
//            Log.e("lchj","444444444444444"+getRealFilePath(uri));
////            insertBitmap(getRealFilePath(uri));
//        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
//            Bundle bundle = new Bundle();
//            bundle.putString("dd", mCurrentPhotoFile.getAbsolutePath());
////            ASF.setArguments(bundle);
//            Log.e("lchj","555555555555555"+mCurrentPhotoFile.getAbsolutePath());
//        }
//    }

    /**
     * 根据Uri获取图片文件的绝对路径
     */
    public String getRealFilePath(final Uri uri) {
        if (null == uri) {
            return null;
        }

        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = getContentResolver().query(uri,
                    new String[] { MediaStore.Images.ImageColumns.DATA }, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }

}
