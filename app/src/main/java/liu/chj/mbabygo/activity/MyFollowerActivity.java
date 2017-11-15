package liu.chj.mbabygo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.adapter.MyFollowAdapter;
import liu.chj.mbabygo.adapter.MyFollowerRecycleAdapter;
import liu.chj.mbabygo.cehua.ListViewDecoration;
import liu.chj.mbabygo.cehua.OnItemClickListener;
import liu.chj.mbabygo.cehua.followerDecoration;
import liu.chj.mbabygo.cutpicture.SelectDialog;
import liu.chj.mbabygo.entity.Follow;

/**
 * 作者：柳成建
 * 日期：2017/1/3 - 9:49
 * 注释：动态
 */
public class MyFollowerActivity extends AutoLayoutActivity implements View.OnClickListener {
    private Intent intent;
    @ViewInject(R.id.iv_my_follower_back)
    private ImageView iv_my_follower_back;
    @ViewInject(R.id.rv_my_follower)
    private RecyclerView rv_my_follower;
    private MyFollowerRecycleAdapter madpter;
    private List<Follow>listfollower = new ArrayList<>();
    private Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_follower);
        intent = getIntent();
        context = MyFollowerActivity.this;
        x.view().inject(this);
        iv_my_follower_back.setOnClickListener(this);
        rv_my_follower.setLayoutManager(new LinearLayoutManager(this));
        setFollowerData();
        madpter = new MyFollowerRecycleAdapter(listfollower);
        madpter.setOnItemClickListener(onItemClickListener);
        rv_my_follower.setHasFixedSize(true);
        rv_my_follower.setAdapter(madpter);
        rv_my_follower.addItemDecoration(new followerDecoration());// 添加分割线。
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_my_follower_back:
                finish();
                break;
            default:
                break;
        }
    }
    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(final int position) {
            /**
             * 点击列表实现业务逻辑，目前没有业务
             */
            new SelectDialog(context)
                    .builder()
                    .setCancelable(true)
                    .setTitle("确定不关注此人？")
                    .setCanceledOnTouchOutside(true)
                    .addSelectItem("确定", SelectDialog.SelectItemColor.Blue,
                            new SelectDialog.OnSelectItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    listfollower.remove(position);
                                    madpter.notifyItemRemoved(position);
                                }
                            }).show();
//            Toast.makeText(MyFollowerActivity.this, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };
    private void setFollowerData(){
        for(int i=0;i<10;i++){

            listfollower.add(new Follow("暗杀教室",R.mipmap.yuanyuanma1,"我们一起去看风景"));
        }
    }


}
