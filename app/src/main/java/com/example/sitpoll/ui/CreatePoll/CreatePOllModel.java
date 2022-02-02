package com.example.sitpoll.ui.CreatePoll;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CreatePOllModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CreatePOllModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}