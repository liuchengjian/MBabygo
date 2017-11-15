/*
 * Copyright 2016 Yan Zhenjie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package liu.chj.mbabygo.cehua;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yanzhenjie.recyclerview.swipe.SwipeMenuAdapter;

import java.util.List;

import liu.chj.mbabygo.R;
import liu.chj.mbabygo.entity.Actor;

/**
 * Created by YOLANDA on 2016/7/22.
 */
public class MenuAdapter extends SwipeMenuAdapter<MenuAdapter.DefaultViewHolder> {


    private List<Actor> actors;

    private OnItemClickListener mOnItemClickListener;

    public MenuAdapter(List<Actor> actors) {
        this.actors = actors;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public int getItemCount() {
        return actors == null ? 0 : actors.size();
    }

    @Override
    public View onCreateContentView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_contact_list, parent, false);
    }

    @Override
    public MenuAdapter.DefaultViewHolder onCompatCreateViewHolder(View realContentView, int viewType) {
        return new DefaultViewHolder(realContentView);
    }

    @Override
    public void onBindViewHolder(MenuAdapter.DefaultViewHolder holder, int position) {
        holder.setData(actors.get(position));
        holder.setOnItemClickListener(mOnItemClickListener);
    }

    static class DefaultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        /**
         * 列表是否被标记，默认否
         */
        private Boolean isIcon = false;
        TextView tvname;
        TextView tv_papers_number;
        ImageView iv_icon;
        OnItemClickListener mOnItemClickListener;

        public DefaultViewHolder(View itemView) {

            super(itemView);
            itemView.setOnClickListener(this);
            tvname = (TextView) itemView.findViewById(R.id.tv_name);
            tv_papers_number = (TextView) itemView.findViewById(R.id.tv_papers_number);
            iv_icon = (ImageView) itemView.findViewById(R.id.iv_icon);
            iv_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /**
                     * 判断是否被标记，然后点击完成实现添加联系人完成
                     */
                    if(isIcon ==true){
                        iv_icon.setImageResource(R.mipmap.icon_gou_n2);
                        isIcon = false;
                    }else {
                        iv_icon.setImageResource(R.mipmap.icon_gou_s2);
                        isIcon = true;
                    }
                }
            });
        }


        public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        public void setData(Actor actors) {
            this.tvname.setText(actors.getName());
            this.tv_papers_number.setText(actors.getIdcard());
            this.iv_icon.setImageResource(actors.getIcon());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(getAdapterPosition());
            }
        }
    }

}
