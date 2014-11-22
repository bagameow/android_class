package com.y.gridimagesearch.adapters;

import java.util.List;

import com.squareup.picasso.Picasso;
import com.y.gridimagesearch.R;
import com.y.gridimagesearch.models.ImageResult;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class ImageResultsAdapter extends ArrayAdapter<ImageResult>{

	public ImageResultsAdapter(Context context, List<ImageResult> results) {
		super(context, android.R.layout.simple_list_item_1, results);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ImageResult imageInfo = getItem(position);
		if (convertView == null) {
			convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image_result, parent, false);
		}
		ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);
		TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
		ivImage.setImageResource(0);
		tvTitle.setText(imageInfo.title);
		Picasso.with(getContext()).load(imageInfo.thumbUrl).into(ivImage);
		return convertView;
		
	}

	
}
