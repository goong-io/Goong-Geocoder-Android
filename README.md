# Goong Gecoder
As the name suggests, you can use this library to show indicator for your `RecyclerView` or any `View` you want easily.

![alt sample](https://user-images.githubusercontent.com/962484/74703802-f8932980-5240-11ea-87e8-62b12261a7e7.png)
### Installing
Use Gradle:
```gradle
implementation 'io.goong.goongsdk.geocoder:goong-android-geocoder:1.0'
```
### How to use?
- Create an API KEY from https://goong.io/
- We can open the geocoder screen with `Activity` or `Fragment`.
##### Start Activity for result:
- Start activity:
```java
  Intent intent = new PlaceAutocomplete.IntentBuilder()
    .apiKey("Your Api Key")
    .placeOptions(new PlaceOptions.Builder()
                    .limit(20)
                    .location(21.0278, 105.8342) // your lat-long to improve search results
                    .build()
    )
    .build(this);
  startActivityForResult(intent, 100);
```
- Get the selected result:
```java
  @Override
  protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      if (requestCode == 100 && data != null && resultCode == Activity.RESULT_OK) {
          PlaceDetails details = PlaceAutocomplete.getPlaceDetail(data);
          Log.e("Test", details.toString());
          Toast.makeText(this, details.name + "", Toast.LENGTH_SHORT).show();
      }
  }
```
##### Add Fragment
```java
  AutocompleteFragment autocompleteFragment = AutocompleteFragment.newInstance("Your API Key",
          new PlaceOptions.Builder().build());
  autocompleteFragment.setOnPlaceSelectedListener(details -> {
        Log.e("Test", details.toString());
        Toast.makeText(this, details.name + "", Toast.LENGTH_SHORT).show();
  });
  // add/replace your fragment into your view
```
##### Place Options:
- We can customize the search results with `PlaceOptions`:
```java
  new PlaceOptions.Builder()
      .limit(20) // limit the results returned
      .location(21.0278, 105.8342) // your lat-long location to improve search results. This is recommended
      .radius(2500) // results will be in the radius from the location you provided above
      .build()
```

