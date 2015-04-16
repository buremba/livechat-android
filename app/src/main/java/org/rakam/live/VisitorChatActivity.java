package org.rakam.live;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class VisitorChatActivity extends ActionBarActivity {
    FragmentPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitor_chat);

        final ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

        final Visitor[] visitors = (Visitor[]) getIntent().getExtras().getSerializable(Constants.USER_IDS);

        mSectionsPagerAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                return ActiveChatListFragment.newInstance(visitors[position]);
            }

            @Override
            public int getCount() {
                return visitors.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return visitors[position].getMember_name();
            }
        };

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_visitor_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class ActiveChatListFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static RakamClient service;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ActiveChatListFragment newInstance(Visitor visitor) {
            ActiveChatListFragment fragment = new ActiveChatListFragment();
            Bundle args = new Bundle();
            args.putString(Constants.USER_ID, visitor.getMember_name());
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_visitor_chat, container, false);
            String userId = getArguments().getString(Constants.USER_ID);

            ApiManager.getService().getMessages(userId)
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<List<ChatMessage>>() {
                        @Override
                        public void call(List<ChatMessage> messages) {
                            ListView listMsg = (ListView) rootView.findViewById(R.id.message_list);
                            MessagesListAdapter adapter = new MessagesListAdapter(getActivity(), messages);
                            listMsg.setAdapter(adapter);
                        }
                    });

            return rootView;
        }
    }

}
