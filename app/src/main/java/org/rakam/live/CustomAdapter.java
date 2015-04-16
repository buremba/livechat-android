package org.rakam.live;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter implements Filterable {

    Context context;
    List<Visitor> visitors;

    CustomAdapter(Context context, List<Visitor> visitors) {
        this.context = context;
        this.visitors = visitors;
    }

    @Override
    public int getCount() {
        return visitors.size();
    }

    @Override
    public Object getItem(int position) {
        return visitors.get(position);
    }

    @Override
    public long getItemId(int position) {
        return visitors.indexOf(getItem(position));
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                return null;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

            }
        };
    }

    /* private view holder class */
    private class ViewHolder {
        ImageView profile_pic;
        TextView member_name;
        TextView status;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.person_list_item, null);
            holder = new ViewHolder();

            holder.member_name = (TextView) convertView
                    .findViewById(R.id.member_name);
            holder.profile_pic = (ImageView) convertView
                    .findViewById(R.id.profile_pic);
            holder.status = (TextView) convertView.findViewById(R.id.status);

            Visitor row_pos = visitors.get(position);

            Picasso.with(context).load(row_pos.getProfile_pic_id()).into(holder.profile_pic);
            holder.member_name.setText(row_pos.getMember_name());
            holder.status.setText(row_pos.getStatus());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
