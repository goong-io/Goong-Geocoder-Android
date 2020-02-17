package com.goong.geocoder.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.goong.geocoder.places.data.options.PlaceAutocomplete;
import com.goong.geocoder.places.data.options.PlaceOptions;
import com.goong.geocoder.places.data.remote.entity.PlaceDetails;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_search).setOnClickListener(v -> {
            // Activiy
            Intent intent = new PlaceAutocomplete.IntentBuilder()
                    .apiKey("Your Api Key")
                    .placeOptions(new PlaceOptions.Builder()
                                    .limit(20)
//                            .radius(radius) //radius in KM
                                    .location(21.0278, 105.8342) // your lat-long to improve search results
                                    .build()
                    )
                    .build(this);
            startActivityForResult(intent, 100);

//            // Fragment
//            AutocompleteFragment autocompleteFragment = AutocompleteFragment.newInstance("Your API Key",
//                    new PlaceOptions.Builder().build());
//            autocompleteFragment.setOnPlaceSelectedListener(details -> {
//                // Get details here
//            });
//            // add/replace your fragment into your view
        });

    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && data != null && resultCode == Activity.RESULT_OK) {
            PlaceDetails details = PlaceAutocomplete.getPlaceDetail(data);
            Log.e("Test", details.toString());
            Toast.makeText(this, details.name + "", Toast.LENGTH_SHORT).show();
        }
    }
}
