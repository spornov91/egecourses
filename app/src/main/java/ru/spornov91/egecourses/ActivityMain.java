package ru.spornov91.egecourses;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class ActivityMain extends Activity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_main);
		
		Button openTutors = findViewById(R.id.btnOpenTutors);
		Button openCourses = findViewById(R.id.btnOpenCourses);
		
		openTutors.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(getApplicationContext(), ActivityTutors.class);
					startActivity(intent);
				}
		});
		
		openCourses.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View arg0) {
					Intent intent = new Intent(getApplicationContext(), ActivitySubjects.class);
					startActivity(intent);
				}
		});
			
    }
}
