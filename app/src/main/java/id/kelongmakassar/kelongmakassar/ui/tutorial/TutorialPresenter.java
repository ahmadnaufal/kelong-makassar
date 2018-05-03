package id.kelongmakassar.kelongmakassar.ui.tutorial;

import java.util.List;

import id.kelongmakassar.kelongmakassar.data.DataManager;
import id.kelongmakassar.kelongmakassar.data.model.Tutorial;
import id.kelongmakassar.kelongmakassar.ui.base.Presenter;

public class TutorialPresenter extends Presenter<TutorialMvpView> {

    private final DataManager mDataManager;

    TutorialPresenter(TutorialMvpView view) {
        super(view);
        mDataManager = new DataManager();
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    void loadTutorials() {
        List<Tutorial> tutorialList = mDataManager.getTutorials();
        getView().onTutorialListLoaded(tutorialList);
    }
}
