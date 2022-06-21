package ru.minnegaliev.springapp.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Assignment {

    private Integer id;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String subjectOrder;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String signControl;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String signPerformance;

    @NotEmpty
    @Size(min = 2, max = 100)
    private String textOrder;

    public Assignment() {
    }

    public Assignment(String subjectOrder, String signControl, String signPerformance,String textOrder) {
        this.subjectOrder = subjectOrder;
        this.signControl = signControl;
        this.signPerformance = signPerformance;
        this.textOrder = textOrder;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectOrder() {
        return subjectOrder;
    }

    public void setSubjectOrder(String subjectOrder) {
        this.subjectOrder = subjectOrder;
    }

    public String getSignControl() {
        return signControl;
    }

    public void setSignControl(String signControl) {
        this.signControl = signControl;
    }

    public String getSignPerformance() {
        return signPerformance;
    }

    public void setSignPerformance(String signPerformance) {
        this.signPerformance = signPerformance;
    }

    public String getTextOrder() {
        return textOrder;
    }

    public void setTextOrder(String textOrder) {
        this.textOrder = textOrder;
    }
}
