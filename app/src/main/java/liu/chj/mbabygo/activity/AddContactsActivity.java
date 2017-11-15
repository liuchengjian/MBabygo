package liu.chj.mbabygo.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.recyclerview.swipe.Closeable;
import com.yanzhenjie.recyclerview.swipe.OnSwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;
import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.cehua.ListViewDecoration;
import liu.chj.mbabygo.cehua.MenuAdapter;
import liu.chj.mbabygo.cehua.OnItemClickListener;
import liu.chj.mbabygo.entity.Actor;

/**
 * 作者：柳成建
 * 日期：2016/12/6 - 17:01
 * 注释：添加联系人界面
 */
public class AddContactsActivity extends AutoLayoutActivity implements View.OnClickListener{
    //返回按钮
    @ViewInject(R.id.iv_add_contact_back)
    private ImageView mAddBack;
    //新增联系人按钮
    @ViewInject(R.id.tv_add_contact)
    private TextView tv_add_contact;

    private SwipeMenuRecyclerView mSwipeMenuRecyclerView;

    private Activity mContext;

    private MenuAdapter mMenuAdapter;

    private List<Actor> listactors;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contacts);
        Intent intent = getIntent();
        x.view().inject(this);
        mSwipeMenuRecyclerView = (SwipeMenuRecyclerView)findViewById(R.id.smr_contacts);
        mSwipeMenuRecyclerView.setLayoutManager(new LinearLayoutManager(this));// 布局管理器。
        mSwipeMenuRecyclerView.setHasFixedSize(true);// 如果Item够简单，高度是确定的，打开FixSize将提高性能。
        mSwipeMenuRecyclerView.setItemAnimator(new DefaultItemAnimator());// 设置Item默认动画，加也行，不加也行。
        mSwipeMenuRecyclerView.addItemDecoration(new ListViewDecoration());// 添加分割线。
        //设置监听
        mContext = this;

        listactors = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            listactors.add(new Actor("圆圆妈妈","5111000012552525",R.mipmap.icon_gou_n2));
        }
        for (int i = 5; i < 10; i++) {
            listactors.add(new Actor("圆圆","53333333333333333",R.mipmap.icon_gou_n2));
        }
        // 为SwipeRecyclerView的Item创建菜单就两句话，不错就是这么简单：
        // 设置菜单创建器。
        mSwipeMenuRecyclerView.setSwipeMenuCreator(swipeMenuCreator);
        // 设置菜单Item点击监听。
        mSwipeMenuRecyclerView.setSwipeMenuItemClickListener(menuItemClickListener);

        mMenuAdapter = new MenuAdapter(listactors);
        mMenuAdapter.setOnItemClickListener(onItemClickListener);
        mSwipeMenuRecyclerView.setAdapter(mMenuAdapter);

        setlisten();

    }

    /**
     * 实现监听器
     */
    public void setlisten(){
        mAddBack.setOnClickListener(this);
        tv_add_contact.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()){
            /**
             * 新增联系人
             */
            case R.id.tv_add_contact:
                Intent intent = new Intent(AddContactsActivity.this,ActorActivity.class);
                startActivity(intent);
                break;
            /**
             * 返回按钮
             */
            case R.id.iv_add_contact_back:
                finish();
                break;
        }
    }
    /**
     * 菜单创建器。在Item要创建菜单的时候调用。实现侧滑左右按键
     */
    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = getResources().getDimensionPixelSize(R.dimen.item_height);;

            // MATCH_PARENT 自适应高度，保持和内容一样高；也可以指定菜单具体高度，也可以用WRAP_CONTENT。
            int height = getResources().getDimensionPixelSize(R.dimen.item_height);
//            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            // 添加左侧的，如果不添加，则左侧不会出现菜单。
//            {
//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)// 点击的背景。
//                        .setImage(R.mipmap.icon_didian2) // 图标。
//                        .setWidth(width) // 宽度。
//                        .setHeight(height); // 高度。
//                swipeLeftMenu.addMenuItem(addItem); // 添加一个按钮到左侧菜单。
//
//                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_red)
//                        .setImage(R.mipmap.ic_action_close)
//                        .setWidth(width)
//                        .setHeight(height);
//
//                swipeLeftMenu.addMenuItem(closeItem); // 添加一个按钮到左侧菜单。
//            }

            // 添加右侧的，如果不添加，则右侧不会出现菜单。
            {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_red)
                        .setImage(R.mipmap.icon_didian2)
                        .setText("删除") // 文字，还可以设置文字颜色，大小等。。
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
                        .setBackgroundDrawable(R.drawable.selector_purple)
                        .setImage(R.mipmap.ic_launcher)
                        .setText("取消") // 文字，还可以设置文字颜色，大小等。
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

//                SwipeMenuItem addItem = new SwipeMenuItem(mContext)
//                        .setBackgroundDrawable(R.drawable.selector_green)
//                        .setText("添加")
//                        .setTextColor(Color.WHITE)
//                        .setWidth(width)
//                        .setHeight(height);
//                swipeRightMenu.addMenuItem(addItem); // 添加一个按钮到右侧菜单。
            }
        }
    };

    private OnItemClickListener onItemClickListener = new OnItemClickListener() {
        @Override
        public void onItemClick(int position) {
            /**
             * 点击列表实现业务逻辑，目前没有业务
             */
//            Toast.makeText(mContext, "我是第" + position + "条。", Toast.LENGTH_SHORT).show();
        }
    };

    /**
     * 菜单点击监听。实现按钮删除、取消等
     */
    private OnSwipeMenuItemClickListener menuItemClickListener = new OnSwipeMenuItemClickListener() {
        /**
         * Item的菜单被点击的时候调用。
         * @param closeable       closeable. 用来关闭菜单。
         * @param adapterPosition adapterPosition. 这个菜单所在的item在Adapter中position。
         * @param menuPosition    menuPosition. 这个菜单的position。比如你为某个Item创建了2个MenuItem，那么这个position可能是是 0、1，
         * @param direction       如果是左侧菜单，值是：SwipeMenuRecyclerView#LEFT_DIRECTION，如果是右侧菜单，值是：SwipeMenuRecyclerView#RIGHT_DIRECTION.
         */
        @Override
        public void onItemClick(Closeable closeable, int adapterPosition, int menuPosition, int direction) {
            closeable.smoothCloseMenu();// 关闭被点击的菜单。

//            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 右侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mContext, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//            }
            // TODO 如果是删除：推荐调用Adapter.notifyItemRemoved(position)，不推荐Adapter.notifyDataSetChanged();
            if (menuPosition == 0) {
                // 删除按钮被点击。
                listactors.remove(adapterPosition);
                mMenuAdapter.notifyItemRemoved(adapterPosition);
            }
        }
    };

//    @Override
//    public boolean onPrepareOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.menu_all_activity, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        if (item.getItemId() == android.R.id.home) {
//            finish();
//        } else if (item.getItemId() == R.id.menu_open_rv_menu) {
//            mSwipeMenuRecyclerView.smoothOpenRightMenu(0);
//        }
//        return true;
//    }
}
