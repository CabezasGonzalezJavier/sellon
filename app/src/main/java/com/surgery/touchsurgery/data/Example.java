
package com.surgery.touchsurgery.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Example {

    @SerializedName("procedures")
    @Expose
    private List<Procedure> procedures = null;
    @SerializedName("procedure_details")
    @Expose
    private List<ProcedureDetail> procedureDetails = null;

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

    public List<ProcedureDetail> getProcedureDetails() {
        return procedureDetails;
    }

    public void setProcedureDetails(List<ProcedureDetail> procedureDetails) {
        this.procedureDetails = procedureDetails;
    }

}
