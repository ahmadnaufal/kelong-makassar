package id.kelongmakassar.kelongmakassar.ui.base;

/**
 * Created by ahmadnaufal on 03/03/18.
 */

public class Presenter<T extends MvpView> {

    private T view;

    public Presenter(T view) {
        this.view = view;
    }

    public void detachView() {
        this.view = null;
    }

    public T getView() {
        return this.view;
    }
}
