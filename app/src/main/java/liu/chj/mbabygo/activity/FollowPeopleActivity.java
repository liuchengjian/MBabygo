package liu.chj.mbabygo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import org.xutils.x;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.SortList.EditTextWithDel;
import liu.chj.mbabygo.SortList.PinyinComparator;
import liu.chj.mbabygo.SortList.PinyinUtils;
import liu.chj.mbabygo.SortList.SideBar;
import liu.chj.mbabygo.SortList.SortAdapter;
import liu.chj.mbabygo.entity.People;
import liu.chj.mbabygo.manager.SystemBarTintManager;

/**
 * 作者：柳成建
 * 日期：2016/12/5 - 9:54
 * 注释：关注的人界面activity
 */
public class FollowPeopleActivity extends AutoLayoutActivity {
    private ListView sortListView;
    private SideBar sideBar;
    private TextView dialog, mTvTitle;
    private SortAdapter adapter;
    private EditTextWithDel mEtSearchName;
    private List<People> SourceDateList;
    Intent intent;
    String[] Data = {"成龙", "房祖名", "刘欢", "章子怡", "张静初", "胡歌", "孙悟空", "猪八戒", "杀生", "啊啊", "德玛西亚", "斗破苍穹", "大主宰", "武动乾坤", "家有二妻"};

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //系统版本大于19
//            setTranslucentStatus(true);
//        }
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        tintManager.setStatusBarTintEnabled(true);
//        tintManager.setStatusBarTintResource(R.color.status_bar);
//        Class clazz = this.getWindow().getClass();
//        try {
//            int darkModeFlag = 0;
//            Class layoutParams = Class.forName("android.view.MiuiWindowManager$LayoutParams");
//            Field field = layoutParams.getField("EXTRA_FLAG_STATUS_BAR_DARK_MODE");
//            darkModeFlag = field.getInt(layoutParams);
//            Method extraFlagField = clazz.getMethod("setExtraFlags", int.class, int.class);
//            if (true) {
//                extraFlagField.invoke(this.getWindow(), darkModeFlag, darkModeFlag);//状态栏透明且黑色字体
//            } else {
//                extraFlagField.invoke(this.getWindow(), 0, darkModeFlag);//清除黑色字体
//            }
//        } catch (Exception e) {
//
//        }
        setContentView(R.layout.activty_follow_people);
        intent = getIntent();
        x.view().inject(this);


        initViews();





    }

    private void initViews() {
        mEtSearchName = (EditTextWithDel) findViewById(R.id.et_search);
        sideBar = (SideBar) findViewById(R.id.lv_sidebar);
        dialog = (TextView) findViewById(R.id.dialog);
//        mTvTitle = (TextView) findViewById(R.id.tv_title);
        sortListView = (ListView) findViewById(R.id.lv_contacts);
        initDatas();
        initEvents();
        setAdapter();
    }

    private void setAdapter() {
        SourceDateList = filledData(Data);
        Collections.sort(SourceDateList, new PinyinComparator());
        adapter = new SortAdapter(this, SourceDateList);
        sortListView.setAdapter(adapter);
    }

    private void initEvents() {
        //设置右侧触摸监听
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                //该字母首次出现的位置
                int position = adapter.getPositionForSection(s.charAt(0));
                if (position != -1) {
                    //点击右侧使列表回到字母行
                    sortListView.setSelection(position);
                }
            }
        });

        //ListView的点击事件
        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                mTvTitle.setText(((People) adapter.getItem(position - 1)).getName());

                Toast.makeText(getApplication(), ((People) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
                //把返回数据存入Intent
                intent.putExtra("strName", "@" + ((People) adapter.getItem(position)).getName());
                setResult(RESULT_OK, intent); //intent为A传来的带有Bundle的intent，当然也可以自己定义新的Bundle
                finish();//此处一定要调用finish()方法
            }
        });

        //根据输入框输入值的改变来过滤搜索
        mEtSearchName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //当输入框里面的值为空，更新为原来的列表，否则为过滤数据列表
                filterData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void initDatas() {
        sideBar.setTextView(dialog);
    }

    /**
     * 根据输入框中的值来过滤数据并更新ListView
     *
     * @param filterStr
     */
    private void filterData(String filterStr) {
        List<People> mSortList = new ArrayList<>();
        if (TextUtils.isEmpty(filterStr)) {
            mSortList = SourceDateList;
        } else {
            mSortList.clear();
            for (People sortModel : SourceDateList) {
                String name = sortModel.getName();
                if (name.toUpperCase().indexOf(filterStr.toString().toUpperCase()) != -1 || PinyinUtils.getPingYin(name).toUpperCase().startsWith(filterStr.toString().toUpperCase())) {
                    mSortList.add(sortModel);
                }
            }
        }
        // 根据a-z进行排序
        Collections.sort(mSortList, new PinyinComparator());
        adapter.updateListView(mSortList);
    }

    private List<People> filledData(String[] date) {
        List<People> mSortList = new ArrayList<>();
        ArrayList<String> indexString = new ArrayList<>();

        for (int i = 0; i < date.length; i++) {
            People sortModel = new People();
            sortModel.setName(date[i]);
            String pinyin = PinyinUtils.getPingYin(date[i]);
            String sortString = pinyin.substring(0, 1).toUpperCase();
            if (sortString.matches("[A-Z]")) {
                sortModel.setSortLetters(sortString.toUpperCase());
                if (!indexString.contains(sortString)) {
                    indexString.add(sortString);
                }
            }
            mSortList.add(sortModel);
        }
        Collections.sort(indexString);
        sideBar.setIndexText(indexString);
        return mSortList;
    }


    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }
}
