package com.nerkingames.mineclicker.Views.Buttons.UpWorldButtons;

import android.view.View;
import android.widget.RelativeLayout;

import io.reactivex.Completable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by vladyslav on 06.03.18.
 */

public interface IViewGetter {
    View getThisView();
    int getvWidth();
    int getvHeight();
    Completable getWorker();
    void setClicked(boolean clicked);
    Disposable getDisposable();
    void setDisposable(Disposable disposable);
    Observer<Long> finishLoad();
    void setClickabled();
    void setViewContainer(RelativeLayout rl);
}
