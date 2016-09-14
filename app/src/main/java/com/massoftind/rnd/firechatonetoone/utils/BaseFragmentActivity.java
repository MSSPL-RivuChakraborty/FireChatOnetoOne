package com.massoftind.rnd.firechatonetoone.utils;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by developer on 3/5/16.
 */
public abstract class BaseFragmentActivity extends Fragment {



    public abstract void onCreateViewBaseFragment(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState);
    public abstract void onCreateBaseFragment();
    public abstract void onStartBaseFragment();
    public abstract void onActivityCreatedBaseFragment();
    public abstract void onResumeBaseFragment();
    public abstract void onAttachBaseFragment();
    public abstract void onPauseBaseFragment();
    public abstract void onStopBaseFragment();
    public abstract void onDestroyBaseFragment();
    public abstract void onDestroyViewBaseFragment();
    public abstract void onDetachBaseFragment();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        onCreateViewBaseFragment( inflater,  container,  savedInstanceState);
       return  super.onCreateView(inflater, container, savedInstanceState);

    }

  /*  @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        onAttachBaseFragment();
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateBaseFragment();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onActivityCreatedBaseFragment();
    }

    @Override
    public void onResume() {
        super.onResume();
        onResumeBaseFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        onStartBaseFragment();
    }

    @Override
    public void onPause() {
        super.onPause();
        onPauseBaseFragment();
    }

    @Override
    public void onStop() {
        super.onStop();
        onStopBaseFragment();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onDestroyBaseFragment();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        onDestroyViewBaseFragment();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onDetachBaseFragment();
    }
    /*onAttach
onCreate
onCreateView
onActivityCreated
onStart
onResume
onPause
onStop
onDestroyView
onDestroy
onDetach*/
}
