package liu.chj.mbabygo.adapter;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
/**
 *基本Basedapetr
 * @author lchj
 * @param <T>
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter {
	private Context context;
	private List<T> data;
	private LayoutInflater inflater;

	public BaseAdapter(Context context, List<T> data) {
		super();
		setContext(context);
		setData(data);
		setLayoutInflater();
	}

	public void setContext(Context context) {
		if(context == null) {
			throw new IllegalArgumentException("参数Context不允许为null！！！");
		}
		this.context = context;
	}

	public Context getContext() {
		return this.context;
	}

	public void setData(List<T> data) {
		if(data == null) {
			data = new ArrayList<T>();
		}
		this.data = data;
	}

	public List<T> getData() {
		return this.data;
	}

	public void setLayoutInflater() {
		inflater = LayoutInflater.from(this.context);
	}

	public LayoutInflater getLayoutInflater() {
		return this.inflater;
	}

	public int getCount() {
		return data.size();
	}

	public Object getItem(int position) {
		return null;
	}

	public long getItemId(int position) {
		return 0;
	}

}
