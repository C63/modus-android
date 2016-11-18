package c63.studio.fi.modus.base;

public interface BaseView {
    void onAttach(BasePresenter presenter);

    void onDetach(BasePresenter presenter);
}
