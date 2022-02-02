package com.example.sitpoll.ui.showPolls;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class showpollsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public showpollsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}