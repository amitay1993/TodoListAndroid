package com.example.justdoit.ViewModel;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.justdoit.Model.User;
import com.example.justdoit.Repository.Repository;

public class SignUpViewModel extends ViewModel {

    private Repository mRepository;

    public SignUpViewModel(Context context) {
        this.mRepository=Repository.getInstance(context);
    }

    private MutableLiveData<Boolean> mSignUpSucceed;
    private MutableLiveData<Boolean> mSignUpFailed;

    public MutableLiveData<Boolean> getSignUpSucceed() {
        if(mSignUpSucceed == null){
            mSignUpSucceed =new MutableLiveData<>();
            attachLoginListener();
        }
        return mSignUpSucceed;
    }

    public MutableLiveData<Boolean> getSignUpFailed() {
        if(mSignUpFailed ==null){
            mSignUpFailed =new MutableLiveData<>();
            attachLoginListener();
        }
        return mSignUpFailed;
    }

    private void attachLoginListener() {
        mRepository.setSignUpListener(new Repository.RepositorySignUpInterface() {

            @Override
            public void onUserSignUpSucceed() {
                mSignUpSucceed.setValue(true);
            }

            @Override
            public void onUserSignUpSFailed() {
                mSignUpFailed.setValue(false);
            }
        });
    }

    public void signUpUser(User user) {
        mRepository.signUpUser(user);
    }

    public String getAuthKey() {
        return mRepository.getAuthKey();
    }
}