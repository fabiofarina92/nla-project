package com.fabio.libary.model.person;

import javax.validation.constraints.NotNull;

public class OnLoanSearchCriteria {

    @NotNull(message = "Person ID can not be blank")
    String personId;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String toString() {
        return "OnLoanSearchCriteria{" +
                "personId='" + personId + '\'' +
                '}';
    }
}
