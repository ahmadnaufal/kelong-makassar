package id.kelongmakassar.kelongmakassar.ui.tutorial;

import java.util.List;

import id.kelongmakassar.kelongmakassar.data.model.Tutorial;
import id.kelongmakassar.kelongmakassar.ui.base.MvpView;

public interface TutorialMvpView extends MvpView {
    void onTutorialListLoaded(List<Tutorial> tutorialList);
}
