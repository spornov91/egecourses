package ru.spornov91.egecourses;

import android.app.*;
import android.os.*;
import android.widget.*;
import java.net.*;
import java.io.*;
import android.util.*;
import android.view.*;
import org.json.*;
import java.util.*;
import android.widget.AdapterView.*;
import android.content.*;

public class ActivitySubjects extends Activity 
{
	String TAG = "spornov91";
	String[][] allsubjects;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_subjects);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		Thread t = new Thread(new Runnable() { 
				public void run()
				{

					String url = "http://z91374e0.beget.tech/api/subjects.php";
					HttpURLConnection c = null;
					try
					{
						URL u = new URL(url);
						c = (HttpURLConnection) u.openConnection();
						c.setRequestMethod("GET");
						c.addRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:221.0) Gecko/20100101 Firefox/31.0"); // add this line to your code

						c.connect();
						int status = c.getResponseCode();
						Log.d("status", "" + status);
						switch (status)
						{
							case 200:
							case 201:
								BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream()));
								StringBuilder sb = new StringBuilder();
								String line;
								while ((line = br.readLine()) != null)
								{
									sb.append(line + "\n");
								}
								br.close();
								allsubjects = jsontoarr(sb.toString());

						}
					}
					catch (MalformedURLException ex)
					{

					}
					catch (IOException ex)
					{

					}
					finally
					{
						if (c != null)
						{
							try
							{
								c.disconnect();

								new Handler(getMainLooper()).post(
									new Runnable() {
										@Override
										public void run()
										{
											ListView lvMain = findViewById(R.id.lvAllCourses);
											AdapterSubjects adapter = new AdapterSubjects(getApplicationContext(), allsubjects);
											lvMain.setAdapter(adapter);
											lvMain.setOnItemClickListener(new OnItemClickListener() {
													@Override
													public void onItemClick(AdapterView<?> parent, View view, int position, long id)
													{
														//String item1 = (String)((TextView) view).getText(); error
														//Integer idCourse = getIdCourseByPosition();
														Log.d("id =",allsubjects[position][0].toString());
														Intent intent = new Intent(getApplicationContext(), ActivityCourses.class);
														intent.putExtra("idsubject", allsubjects[position][0].toString());
														startActivity(intent);
													}
												});
										}
									});
							}
							catch (Exception ex)
							{

							}
						}
					}
				};
			});
		t.start();

    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				super.onBackPressed();
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private static Integer getIdCourseByPosition(){
		return 1;
	};
	
	private static String[][] jsontoarr(String str)
	{
		String[][] arr2 = null;
		try
		{
			//JSONObject obj = new JSONObject(str);
			JSONArray arr = new JSONArray(str);
			arr2 = new String[arr.length()][3];
			for (int n = 0; n < arr.length(); n++)
			{
				JSONObject obj = arr.getJSONObject(n);
				arr2[n][0]   = obj.getString("id");
				arr2[n][0+1] = obj.getString("name");
				arr2[n][0+2] = obj.getString("price");
				//Log.d("obj.get()",obj.getInt(0).toString());
			} 
			//JSONArray arr2 = obj.getJSONArray("english");


		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return arr2;

		};
}
