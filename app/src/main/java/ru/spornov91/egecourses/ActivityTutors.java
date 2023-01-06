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

public class ActivityTutors extends Activity 
{
	String TAG = "spornov91";
	String[] names;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_tutors);
		
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
								names = jsontoarr(sb.toString());

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
											ListView lvMain = findViewById(R.id.lvAllTutors);
											ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),
																									android.R.layout.simple_list_item_1, names);
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

	private static String[] jsontoarr(String str)
	{
		ArrayList<String> list = new ArrayList<String>();
		try
		{
			//JSONObject obj = new JSONObject(str);
			JSONArray arr = new JSONArray(str);
			for (int n = 0; n < arr.length(); n++)
			{
				JSONObject obj = arr.getJSONObject(n);
				//Log.d("array",""+obj);

				JSONArray arr3 = obj.getJSONArray("tutors");
				for (int t = 0; t < arr3.length(); t++)
				{
					JSONObject obj3 = arr3.getJSONObject(t);
					String _name = obj3.getString("name");
					// Log.d("obj.name",_name);
					list.add(_name);
				}
			} 

		}
		catch (JSONException e)
		{
			e.printStackTrace();
		}

		return list.toArray(new String[0]);
    };
}
