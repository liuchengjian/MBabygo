package liu.chj.mbabygo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import liu.chj.mbabygo.R;

/**
 * 作者：柳成建
 * 日期：2016/11/23 - 14:26
 * 注释：
 */
public class FrientFragment extends Fragment {
    private static final String ARG_NAME = "namelist";
    private List<String> nameList;
    private RecyclerView recyclerView;
    private LayoutInflater inflater;
    private MyAdapter adapter;

    public FrientFragment() {
        // Required empty public constructor
    }


    public static FrientFragment newInstance(ArrayList<String> nameList) {
        FrientFragment fragment = new FrientFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_NAME,nameList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.nameList = getArguments().getStringArrayList(ARG_NAME);
        }
        inflater=LayoutInflater.from(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        recyclerView= (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //adapter
        adapter=new MyAdapter();
        recyclerView.setAdapter(adapter);
        return view;
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{
        View view = null;
        MyViewHolder holder  = null;
        MyViewHolder1 holder1  = null;

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            switch (viewType){
                case 0:
                    view=inflater.inflate(R.layout.list_activity_routing,parent,false);
                     holder = new MyViewHolder(view);
                    break;
                case 1:
                    view=inflater.inflate(R.layout.item_string1,parent,false);
                    holder1 = new MyViewHolder1(view);
                    break;
            }
            return holder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            switch (getItemViewType(position)){
                case 0:
                    holder.bt_day.setText(nameList.get(position));
                    break;
                case 1:
                    holder1.nameText1.setText(nameList.get(position+1));
                    break;
            }
        }

        @Override
        public int getItemCount() {
            return nameList.size();
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
                public TextView nameText;
            public Button bt_day;
            public MyViewHolder(View itemView) {
                super(itemView);
                //get到相关控件的引用
//                nameText= (TextView) itemView.findViewById(R.id.tv);
                bt_day = (Button) itemView.findViewById(R.id.bt_day);
            }
        }
        class MyViewHolder1 extends RecyclerView.ViewHolder{
            public TextView nameText1;
            public MyViewHolder1(View itemView) {
                super(itemView);
                //get到相关控件的引用
                nameText1= (TextView) itemView.findViewById(R.id.tv1);
            }
        }
    }
}
