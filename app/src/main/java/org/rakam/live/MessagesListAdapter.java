package org.rakam.live;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class MessagesListAdapter extends BaseAdapter {

    private Context context;
    private List<ChatMessage> messagesItems;

    public MessagesListAdapter(Context context, List<ChatMessage> navDrawerItems) {
        this.context = context;
        this.messagesItems = navDrawerItems;
    }

    @Override
    public int getCount() {
        return messagesItems.size();
    }

    @Override
    public ChatMessage getItem(int position) {
        return messagesItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("InflateParams")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ChatMessage m = messagesItems.get(position);

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

        if (messagesItems.get(position).isFromOperator()) {
            convertView = mInflater.inflate(R.layout.chat_message_right_side,
                    null);
        } else {
            convertView = mInflater.inflate(R.layout.chat_message_left_side,
                    null);
        }

        TextView lblFrom = (TextView)    convertView.findViewById(R.id.messageFrom);
        TextView txtMsg = (TextView) convertView.findViewById(R.id.messageContent);
        txtMsg.setText(m.getMessage());
        lblFrom.setText(DateUtils.getRelativeDateTimeString(context, m.getDate().getTime(), DateUtils.MINUTE_IN_MILLIS, DateUtils.WEEK_IN_MILLIS, DateUtils.FORMAT_SHOW_TIME));

        return convertView;
    }
}
