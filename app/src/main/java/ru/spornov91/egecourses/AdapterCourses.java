package ru.spornov91.egecourses;

	import android.app.*;
	import android.view.*;
	import android.widget.*;
	import ru.spornov91.egecourses.*;
	import android.graphics.drawable.*;
import android.content.*;

public class AdapterCourses extends ArrayAdapter<String>
	{  

		private final Context context;  
	    LayoutInflater inflater;
		private final String[][] obj;
		//private final Drawable[] imgid;  

	public AdapterCourses (Context context, String[][] obj) { //Drawable[] imgid 
			super(context, R.layout.adapter_courses, obj);  
			// TODO Auto-generated constructor stub  

			this.context=context;  
			this.obj=obj;  
		    inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

		public View getView(int position,View view,ViewGroup parent) {  
			View rowView=inflater.inflate(R.layout.adapter_courses, null,true);  

			TextView idcourse = (TextView) rowView.findViewById(R.id.acidcourse); 
			TextView titleText = (TextView) rowView.findViewById(R.id.actitle);  
			//ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);  
			TextView subtitleText = (TextView) rowView.findViewById(R.id.acsubtitle);  

			
//			arr2[n][0]   = obj.getString("id");
//			arr2[n][0+1] = obj.getString("subject");
//			arr2[n][0+2] = obj.getString("name");
//			arr2[n][0+3] = obj.getString("price");
//			arr2[n][0+4] = obj.getString("pay");
			
			idcourse.setText(obj[position][0]);  
			titleText.setText(obj[position][1]);  
			//imageView.setImageDrawable(imgid[position]);  
			subtitleText.setText(obj[position][2]);  

			return rowView;  

		};  
	}  
