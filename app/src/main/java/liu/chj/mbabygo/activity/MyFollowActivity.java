package liu.chj.mbabygo.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.MyFollowAdapter;
import liu.chj.mbabygo.entity.Article;
import liu.chj.mbabygo.entity.Follow;
import liu.chj.mbabygo.popupwindow.AnimationUtil;
import liu.chj.mbabygo.popupwindow.ViewUtils;

/**
 * 作者：柳成建
 * 日期：2017/1/3 - 9:49
 * 注释：动态
 */
public class MyFollowActivity extends AutoLayoutActivity implements View.OnClickListener {
    private Intent intent;
    @ViewInject(R.id.iv_my_follow_back)
    private ImageView iv_my_follow_back;
    @ViewInject(R.id.lv_my_follow)
    private ListView lv_my_follow;
    private MyFollowAdapter myFollowAdapter;
    private List<Follow> listfollow = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follow);
        intent = getIntent();
        x.view().inject(this);
        setFollowData();
        setMyFollowAdapter();
//        myFollowAdapter = new MyFollowAdapter(MyFollowActivity.this,listfollow);
//        lv_my_follow.setAdapter(myFollowAdapter);
        iv_my_follow_back.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_my_follow_back:
                finish();
                break;
            default:
                break;
        }
    }

    /**
     * 列表的监听和适配
     */
    public void setMyFollowAdapter() {

        myFollowAdapter = new MyFollowAdapter(MyFollowActivity.this, listfollow);
        if (lv_my_follow.getAdapter() == null) {
            lv_my_follow.setAdapter(myFollowAdapter);
        } else {
            myFollowAdapter.notifyDataSetChanged();
        }
//        lv_my_follow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Toast.makeText(MyFollowActivity.this,"你点了我",Toast.LENGTH_LONG).show();
//            }
//        });
        lv_my_follow.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyFollowActivity.this,"你点了我",Toast.LENGTH_LONG).show();
                return false;
            }
        });

    }

    public void setFollowData() {
        for (int i = 0; i < 10; i++) {
            listfollow.add(new Follow("隋武林", R.mipmap.yuanyuanma1, "我们一起哈皮"));
        }
    }

}
