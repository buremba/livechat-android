package org.rakam.live;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


public class ProjectListActivity extends ActionBarActivity {

    private RakamClient service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_list);

        final String access_token = super.getIntent().getStringExtra(Constants.USER_ACCESS_TOKEN);

        RequestInterceptor requestInterceptor = new RequestInterceptor() {
            @Override
            public void intercept(RequestFacade request) {
                request.addHeader("user", access_token);
            }
        };

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://www.mocky.io/v2/")
                .setRequestInterceptor(requestInterceptor)
                .build();
        service = restAdapter.create(RakamClient.class);

        final ListView listview = (ListView) findViewById(R.id.listView);

        service.getProjects()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Project>>() {
                    @Override
                    public void call(final List<Project> projects) {
                        listview.setAdapter(new ArrayAdapter<Project>(ProjectListActivity.this,
                                android.R.layout.simple_list_item_1, projects.toArray(new Project[projects.size()])));
                        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                Project project = projects.get(position);
                                Intent myIntent = new Intent(ProjectListActivity.this, OnlineUsersActivity.class);
                                myIntent.putExtra(Constants.USER_ACCESS_TOKEN, access_token);
                                myIntent.putExtra(Constants.ACTIVE_PROJECT, project.id);
                                startActivity(myIntent);
                            }
                        });
                    }
                });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_project_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
