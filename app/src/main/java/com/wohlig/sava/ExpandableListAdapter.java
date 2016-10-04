package com.wohlig.sava;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by adiam on 7/20/2016.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<String>> _listDataChild;

    public ExpandableListAdapter(Context _context,
                                 HashMap<String, List<String>> _listDataChild, List<String> _listDataHeader) {
        this._context = _context;
        this._listDataChild = _listDataChild;
        this._listDataHeader = _listDataHeader;
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.lblListHeader);
       // lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView_group);
        if(groupPosition == 0){ imageView.setImageResource(R.drawable.notifygrey);}
        if(groupPosition == 1){imageView.setImageResource(R.drawable.offers);}
        if(groupPosition == 2){imageView.setImageResource(R.drawable.layer7);}
        if(groupPosition == 3){imageView.setImageResource(R.drawable.categories);}
        if(groupPosition == 4){imageView.setImageResource(R.drawable.settings);}
        if(groupPosition == 5){imageView.setImageResource(R.drawable.about);}
        if(groupPosition == 6){imageView.setImageResource(R.drawable.help);}

        ImageView imgArrowDown = (ImageView) convertView.findViewById(R.id.img_down);

        if(getChildrenCount(groupPosition) > 0){
            imgArrowDown.setVisibility(View.VISIBLE);
        }
        else {
            imgArrowDown.setVisibility(View.GONE);
        }

        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.ex_list_item, null);
        }

        TextView txtListChild = (TextView) convertView
                .findViewById(R.id.lblListItem);

        ImageView childImage = (ImageView) convertView.findViewById(R.id.img_ex_list);
        if(groupPosition == 3 && childPosition == 0){childImage.setImageResource(R.drawable.coffee);}
        if(groupPosition == 3 && childPosition == 1){childImage.setImageResource(R.drawable.restaurant1);}
        if (groupPosition == 3 && childPosition == 2){childImage.setImageResource(R.drawable.lunch);}
        if(groupPosition == 3 && childPosition == 3){childImage.setImageResource(R.drawable.bars);}
        if(groupPosition == 3 && childPosition == 4){childImage.setImageResource(R.drawable.gasstation);}

        txtListChild.setText(childText);
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
