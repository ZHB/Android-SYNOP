package com.previmet.synop.activities;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

/**
 * Created by Vince on 01.01.2015.
 */
public class SearchResultsActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            //Bundle bundle = queryIntent.getExtras();
            //String data = (String) bundle.get("intent_extra_data_key");

            Toast.makeText(this, "SEARCH : " + query, Toast.LENGTH_SHORT).show();

        } else if (Intent.ACTION_VIEW.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);

            Toast.makeText(this, "VIEW : " + query, Toast.LENGTH_SHORT).show();
            // Handle a suggestions click (because the suggestions all use ACTION_VIEW)

        }
    }

}