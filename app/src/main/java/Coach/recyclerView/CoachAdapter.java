package Coach.recyclerView;

//import android.content.Context;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lenovo.app.R;

import java.util.List;

import Coach.Coach;

//
//import java.util.List;
//public class CoachAdapter extends ArrayAdapter {
//
//    private final int resourceId;
//
//    public CoachAdapter(Context context, int resourceId, List<Coach> objects) {
//        super(context,resourceId,objects);
//        this.resourceId = resourceId; }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent){
//        Coach coach = (Coach)getItem(position);//获取当前的coach实例
//
//        View view = LayoutInflater.from(getContext()).inflate(resourceId,null);//实例化
//        ImageView CoachImage = (ImageView)view.findViewById(R.id.Coach_image);
//        TextView CoachName = (TextView)view.findViewById(R.id.Coach_name);
//        TextView CoachSex = (TextView)view.findViewById(R.id.Coach_sex);
//        TextView CoachYear = (TextView)view.findViewById(R.id.Coach_year);
//        TextView CoachSop = (TextView)view.findViewById(R.id.Coach_sop);
//        CoachImage.setImageResource(coach.getImageId());
//        CoachName.setText(coach.getName());
//        CoachSex.setText(coach.getSex());
//        CoachYear.setText(coach.getYear());
//        CoachSop.setText(coach.getSop());
//        return view;
//    }
//}

public class CoachAdapter extends RecyclerView.Adapter<CoachAdapter.MViewHolder> {

    private Context context;
    private List<Coach> listData;
    private ItemClickListener mItemClickListener;

    public CoachAdapter(Context context, List<Coach> mList) {
        super();
        this.context = context;
        this.listData = mList;
    }

    /**
     * TODO<添加数据，指定其位置>
     */

    public void addData(Coach info, int position) {
        listData.add(position, info);
        notifyItemInserted(position);
        //	notifyDataSetChanged(); //不会触发Item的动画效果，告知数据改变，刷新UI

    }

    /**
     * TODO<添加数据到最后面添加>
     */

    public void addData(Coach info) {
        // listData.add(position, info);
        // notifyItemInserted(position);
        listData.add(info);
        notifyDataSetChanged();
    }

    /**
     * TODO<删除数据，指定其位置>
     */
    public void daleteData(int position) {
        listData.remove(position);
        notifyItemRemoved(position);

    }

    /**
     * TODO<某一位置开始，有itemCount个Item的数据删除>
     */
    public void itemRangeRemoved(int positionStart, int itemCount) {
        for (int i = positionStart; i < itemCount; i++) {
            listData.remove(positionStart);
        }
        notifyItemRangeRemoved(positionStart, itemCount);
        //	notifyDataSetChanged(); //不会触发Item的动画效果，告知数据改变，刷新UI
    }

    /**
     * TODO<某一位置开始，有itemCount个Item的数据插入>
     */
    public void itemRangeInserted(Coach info, int positionStart, int itemCount) {
        for (int i = positionStart; i < itemCount; i++) {
            listData.add(i, info);
        }
        //notifyItemRangeInserted(positionStart, itemCount);
        notifyDataSetChanged();
    }

    public void setItemClickListener(ItemClickListener mItemClickListener) {

        this.mItemClickListener = mItemClickListener;
    }

    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return listData.size();
    }

    @Override
    public MViewHolder onCreateViewHolder(ViewGroup viewGroup, int arg1) {

        View view = View.inflate(
                viewGroup.getContext(),
                R.layout.item_coach, null);
        // 创建一个ViewHolder
        MViewHolder holder = new MViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MViewHolder mViewHolder,
                                 final int postion) {

        mViewHolder.cname.setText(listData.get(postion).getName());
        mViewHolder.sex.setText(listData.get(postion).getSex());
        mViewHolder.year.setText(listData.get(postion).getYear());
        mViewHolder.sop.setText(listData.get(postion).getSop());
        mViewHolder.image.setImageResource(listData.get(postion).getImageId());
        // 为image添加监听回调
        mViewHolder.image.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                if (null != mItemClickListener) {
                    mItemClickListener.onItemSubViewClick(mViewHolder.image,
                            postion);
                }
            }
        });
    }

    public class MViewHolder extends RecyclerView.ViewHolder {
        public TextView cname;
        public TextView sex;
        public TextView year;
        public TextView sop;
        public ImageView image;

        public MViewHolder(final View view) {
            super(view);
            this.cname = (TextView) view.findViewById(R.id.Coach_name);
            this.sex = (TextView) view.findViewById(R.id.Coach_sex);
            this.year = (TextView) view.findViewById(R.id.Coach_year);
            this.sop = (TextView) view.findViewById(R.id.Coach_sop);
            this.image = (ImageView) itemView.findViewById(R.id.Coach_image);
            // 为item添加普通点击回调
            view.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {

                    if (null != mItemClickListener) {
                        mItemClickListener.onItemClick(view, getPosition());
                    }

                }
            });

            // 为item添加长按回调
            view.setOnLongClickListener(new OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    if (null != mItemClickListener) {
                        mItemClickListener.onItemLongClick(view, getPosition());
                    }
                    return true;
                }
            });

        }
    }

}