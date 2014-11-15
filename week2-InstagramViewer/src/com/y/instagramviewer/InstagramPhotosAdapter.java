package com.y.instagramviewer;

import java.util.List;

import android.content.Context;
import android.widget.ArrayAdapter;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto>{
		public InstagramPhotosAdapter(Context context, List<InstagramPhoto> photos) {
			super(context,android.R.layout.simple_list_item_1, photos);
		}
}
