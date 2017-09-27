package com.surgery.touchsurgery.detail;

import com.surgery.touchsurgery.BasePresenter;
import com.surgery.touchsurgery.BaseView;
import com.surgery.touchsurgery.data.Example;
import com.surgery.touchsurgery.data.ProcedureDetail;
import com.surgery.touchsurgery.procedure.ProcedureContract;

/**
 * Created by javierg on 27/09/2017.
 */

public class DetailContract {

    interface Presenter extends BasePresenter {
        void fetch();
    }

    interface View extends BaseView<DetailContract.Presenter> {
        void showProcedures(ProcedureDetail detail);

        void showError();

        void setLoadingIndicator(boolean active);

    }
}
