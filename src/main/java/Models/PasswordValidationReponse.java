/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 * Password Validation Response - Needs a model to allow automatic return of 
 * a JSON object
 * @author Chris
 */
public class PasswordValidationReponse {
    private Boolean validated;

    public PasswordValidationReponse() {
    }
    
    public PasswordValidationReponse(Boolean validated) {
        this.validated = validated;
    }
    
    public Boolean getValidated() {
        return validated;
    }

    public void setValidated(Boolean validated) {
        this.validated = validated;
    }
}
