package kr.hs.foodmate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class list_Adapter extends BaseAdapter {
    Context context;
    ArrayList<list_item> list_itemArrayList;

    TextView nick_textView;
    TextView title_textView;
    TextView tag_textView;
    ImageView profile_imageView;

    public list_Adapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            nick_textView = (TextView)convertView.findViewById(R.id.board_nick);
            tag_textView = (TextView)convertView.findViewById(R.id.board_tag);
            title_textView  =(TextView)convertView.findViewById(R.id.board_title);
            profile_imageView = (ImageView)convertView.findViewById(R.id.profile_img);
        }

        nick_textView.setText(list_itemArrayList.get(position).getNick());
        title_textView.setText(list_itemArrayList.get(position).getTitle());
        tag_textView.setText(list_itemArrayList.get(position).getTag());
        profile_imageView.setImageResource(list_itemArrayList.get(position).getProfile_img());

        return convertView;
    }
}
