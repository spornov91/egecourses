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

public class ActivityCourses extends Activity 
{
	String TAG = "spornov91";
	String[][] allcourses;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_courses);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setDisplayShowHomeEnabled(false);
		
		Thread t = new Thread(new Runnable() { 
				public void run()
				{

					String url = "http://z91374e0.beget.tech/api/courses.php";
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
								String idsubject = getIntent().getStringExtra("idsubject");
								allcourses = jsontoarr(sb.toString(), idsubject);

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
											AdapterCourses adapter = new AdapterCourses(getApplicationContext(), allcourses);
											lvMain.setAdapter(adapter);
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
	
	private static String[][] jsontoarr(String str, String idsubject)
	{
		String[][] arr2 = null;
		String[][] arr3 = null;
		try
		{
			//JSONObject obj = new JSONObject(str);
			JSONArray arr = new JSONArray(str);
			arr2 = new String[arr.length()][6];
			for (int n = 0; n < arr.length(); n++)
			{
				JSONObject obj = arr.getJSONObject(n);
				
				if(obj.getString("idsubject").equals(idsubject)){
				arr2[n][0]   = obj.getString("id");
				arr2[n][0+1] = obj.getString("idsubject");
				arr2[n][0+2] = obj.getString("subject");
				arr2[n][0+3] = obj.getString("name");
				arr2[n][0+4] = obj.getString("price");
				arr2[n][0+5] = obj.getString("pay");
				}
				//Log.d("obj.get()",obj.getInt(0).toString());
			} 
			
			// count null row
			
			ArrayList<Integer> notnullindexes = new ArrayList<Integer>();
			
			for (int i = 0; i < arr.length(); i++)
			{
				if(arr2[i][0] != null){
					notnullindexes.add(i);
				}
			}
			
			arr3 = new String[notnullindexes.size()][6];
			for (int i = 0; i < notnullindexes.size(); i++)
			{
				for (int j = 0; j < 6; j++)
				{
				    arr3[i][j] = arr2[notnullindexes.get(i)][j];
				}
			}
			Log.d("arr3",notnullindexes.size()+"");
			
			if(notnullindexes.size() == 0){
				arr3 = new String[1][6];
				arr3[0][0]   = "*";
			    arr3[0][0+1] = "Нет курсов";
			    arr3[0][0+2] = "Попробуйте другие курсы";
				arr3[0][0+3] = "Бесплатно";
				arr3[0][0+4] = "true";
				arr3[0][0+5] = "000";
			}
			//JSONArray arr2 = obj.getJSONArray("english");
			//for(int o = 0;o < arr2[0].length;o++){
			   // Log.d("obj.name",arr2[0][0].toString());
			//}
			

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return arr3;
       };
}
