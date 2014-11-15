package com.y.instagramviewer;

import java.util.List;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto>{
		public InstagramPhotosAdapter(Context context, List<InstagramPhoto> photos) {
			super(context,R.layout.item_photo, photos);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			InstagramPhoto photo = getItem(position);
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
			}
			
			TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
			ImageView imgPhoto = (ImageView) convertView.findViewById(R.id.imgPhoto);
			imgPhoto.getLayoutParams().height = photo.imageHeight;
			imgPhoto.setImageResource(0);
			Picasso.with(getContext()).load(photo.imageUrl).into(imgPhoto);
			tvCaption.setText(photo.caption + " - " + photo.username);
			
			return convertView;
		}
}
