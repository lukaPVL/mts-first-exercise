package com.mipt.lukapavlov.annotations;

import java.util.ArrayList;
import java.util.List;

public class ValidationResult {
    private boolean isValid;
    private List<String> errors;

    public ValidationResult() {
        this.isValid = true;
        this.errors = new ArrayList<>();
    }

    public ValidationResult(boolean isValid, List<String> errors) {
        this.isValid = isValid;
        this.errors = errors;
    }

    public boolean getIsValid() {
        return isValid;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    public void addError(String error) {
        this.errors.add(error);
        this.isValid = false;
    }
}
