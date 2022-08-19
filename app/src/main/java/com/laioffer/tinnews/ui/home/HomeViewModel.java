package com.laioffer.tinnews.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.laioffer.tinnews.model.Article;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.respository.NewsRepository;

public class HomeViewModel extends ViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<String> countryInput = new MutableLiveData<>();

    public HomeViewModel(NewsRepository newsRepository) {
        this.repository = newsRepository;
    }

    //event
    public void setCountryInput(String country) {
        countryInput.setValue(country);
    }

    //state
    public LiveData<NewsResponse> getTopHeadlines() {
        //observe country input
        //switch map: country input -> topheadlines
        //return topheadlines
        return Transformations.switchMap(countryInput, repository::getTopHeadlines);
    }

    //similar to how we handle the country search input with setCountryInput
    //but difference is we do not need to expose th observing result, so we do
    //not have to Transformations.switchmap(), this is a plain simple direct
    //call to repository
    public void setFavoriteArticleInput(Article article) {
        repository.favoriteArticle(article);
    }

}
