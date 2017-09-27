package com.surgery.touchsurgery.procedure;

import com.surgery.touchsurgery.BasePresenter;
import com.surgery.touchsurgery.BaseView;
import com.surgery.touchsurgery.data.Example;
import com.surgery.touchsurgery.data.ProcedureDetail;

/**
 * Created by javierg on 27/09/2017.
 */

public class ProcedureContract {

    interface Presenter extends BasePresenter {
        void fetch();

    }

    interface View extends BaseView<Presenter> {
        void showProcedures(Example list);

        void showError();

        void setLoadingIndicator(boolean active);

    }
}
