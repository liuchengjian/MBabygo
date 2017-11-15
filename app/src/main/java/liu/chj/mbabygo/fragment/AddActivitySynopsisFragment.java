package liu.chj.mbabygo.fragment;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.editandimage.RichTextEditor;

/**
 * 作者：柳成建
 * 日期：2016/12/23 - 9:24
 * 注释：活动详情简介添加界面Fragment
 */
public class AddActivitySynopsisFragment extends Fragment{
    private static final int REQUEST_CODE_PICK_IMAGE = 1023;
    private static final int REQUEST_CODE_CAPTURE_CAMEIA = 1022;
    @ViewInject(R.id.richEditor)
    private RichTextEditor editor;
    private View btn1, btn2, btn3;
    private View.OnClickListener btnListener;

    private static final File PHOTO_DIR = new File(
            Environment.getExternalStorageDirectory() + "/DCIM/Camera");
    private File mCurrentPhotoFile;// 照相机拍照得到的图片
    public static final String TMP_PATH = "temp.jpg";
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view =inflater.inflate(R.layout.fragment_add_activity_synopsis,null);
//     editor = (RichTextEditor) view.findViewById(R.id.richEditor);
        x.view().inject(this, view);
        Bundle bundle = getArguments();//从activity传过来的Bundle
//        if(bundle!=null){
        String a= bundle.getString("ss");
////            editor.insertImage(a);
        Log.e("lchj","sssssssss"+a);
////            String b =  bundle.getString("dd");
////            editor.insertImage(b);
//            Log.e("lchj","dddddddddddddd");
//        }
        btnListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                editor.hideKeyBoard();
                if (v.getId() == btn1.getId()) {
                    // 打开系统相册
                    Intent intent = new Intent(Intent.ACTION_PICK);
                    intent.setType("image/*");// 相片类型
                    startActivityForResult(intent, REQUEST_CODE_PICK_IMAGE);
                } else if (v.getId() == btn2.getId()) {
                    // 打开相机
                    openCamera();
                } else if (v.getId() == btn3.getId()) {
                    List<RichTextEditor.EditData> editList = editor.buildEditData();
                    // 下面的代码可以上传、或者保存，请自行实现
                    dealEditData(editList);
                }
            }
        };

        btn1 =view.findViewById(R.id.button1);
        btn2 = view.findViewById(R.id.button2);
        btn3 = view.findViewById(R.id.button3);

        btn1.setOnClickListener(btnListener);
        btn2.setOnClickListener(btnListener);
        btn3.setOnClickListener(btnListener);
        return view;
    }


    /**
     * 负责处理编辑数据提交等事宜，请自行实现
     */
    protected void dealEditData(List<RichTextEditor.EditData> editList) {
        for (RichTextEditor.EditData itemData : editList) {
            if (itemData.inputStr != null) {
                Log.d("RichEditor", "commit inputStr=" + itemData.inputStr);
            } else if (itemData.imagePath != null) {
                Log.d("RichEditor", "commit imgePath=" + itemData.imagePath);
            }

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != getActivity().RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_PICK_IMAGE) {
            Uri uri = data.getData();
            insertBitmap(getRealFilePath(uri));
        } else if (requestCode == REQUEST_CODE_CAPTURE_CAMEIA) {
            insertBitmap(mCurrentPhotoFile.getAbsolutePath());
        }
    }

    /**
     * 添加图片到富文本剪辑器
     *
     * @param imagePath
     */
    private void insertBitmap(String imagePath) {
        editor.insertImage(imagePath);
    }

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
            Cursor cursor = getActivity().getContentResolver().query(uri,
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
