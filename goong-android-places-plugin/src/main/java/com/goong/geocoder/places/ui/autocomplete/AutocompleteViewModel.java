package com.goong.geocoder.places.ui.autocomplete;

import com.goong.geocoder.places.data.options.PlaceOptions;
import com.goong.geocoder.places.data.remote.entity.Place;
import com.goong.geocoder.places.data.remote.entity.PlaceDetails;
import com.goong.geocoder.places.data.remote.entity.PredictionPlace;
import com.goong.geocoder.places.data.repository.AutocompleteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;

public final class AutocompleteViewModel extends ViewModel {
    private String apiKey;
    private PlaceOptions placeOptions;

    private AutocompleteRepository autocompleteRepository;
    private MutableLiveData<List<Place>> autoCompleteData = new MutableLiveData<>();
    private MutableLiveData<PlaceDetails> placeDetailsData = new MutableLiveData<>();
    private MutableLiveData<Boolean> progressBarVisibility = new MutableLiveData<>();

    private PublishSubject<String> onSearchChangedSubject = PublishSubject.create();
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private Disposable disposable;
    private Disposable placeDetailDisposable;

    public AutocompleteViewModel() {
        super();
        autocompleteRepository = new AutocompleteRepository();
        initSearchListener();
    }

    void init(@NonNull final String apiKey, @Nullable PlaceOptions placeOptions) {
        Objects.requireNonNull(apiKey);
        this.apiKey = apiKey;
        this.placeOptions = placeOptions != null ? placeOptions : new PlaceOptions.Builder().build();
    }

    private void initSearchListener() {
        Disposable disposable = onSearchChangedSubject.debounce(100, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::getAutoComplete, error -> {
                });
        compositeDisposable.add(disposable);
    }

    public void getPlaceDetail(String placeId) {
        if (placeDetailDisposable != null && !placeDetailDisposable.isDisposed()) {
            placeDetailDisposable.dispose();
        }
        placeDetailDisposable = autocompleteRepository.getPlaceDetails(apiKey, placeId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(dis -> progressBarVisibility.setValue(true))
                .doFinally(() -> progressBarVisibility.setValue(false))
                .subscribe(detail -> placeDetailsData.setValue(detail), error -> {
                });
    }

    private void getAutoComplete(final String search) {
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

        disposable = autocompleteRepository
                .getAutocomplete(apiKey, search, placeOptions.getLatitude(),
                        placeOptions.getLongitude(), placeOptions.getLimit(), placeOptions.getRadius())
                .map(this::mapPlaces)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(dis -> progressBarVisibility.setValue(true))
                .doOnSuccess(detail -> progressBarVisibility.setValue(false))
                .subscribe(predictions -> autoCompleteData.setValue(predictions), error -> {
                });

    }

    private List<Place> mapPlaces(List<PredictionPlace> predictionPlaces) {
        List<Place> places = new ArrayList<>();
        for (final PredictionPlace predictionPlace : predictionPlaces) {
            places.add(predictionPlace);
            if (predictionPlace.hasChildren) {
                places.addAll(predictionPlace.children);
            }
        }
        return places;
    }

    void onSearchChanged(final String search) {
        if (search == null || search.length() < 2) {
            return;
        }
        onSearchChangedSubject.onNext(search);
    }

    public MutableLiveData<List<Place>> getAutoCompleteData() {
        return autoCompleteData;
    }

    public MutableLiveData<PlaceDetails> getPlaceDetailsData() {
        return placeDetailsData;
    }

    public MutableLiveData<Boolean> getProgressBarVisibility() {
        return progressBarVisibility;
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        compositeDisposable.clear();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (placeDetailDisposable != null && !placeDetailDisposable.isDisposed()) {
            placeDetailDisposable.dispose();
        }
    }
}
