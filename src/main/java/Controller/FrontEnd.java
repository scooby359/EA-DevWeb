/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Business.DevRestClient;
import Models.Developers;
import java.util.List;
import javax.ejb.Stateful;
import Models.PasswordValidation;
import Models.PasswordValidationReponse;
import java.io.Serializable;
import java.util.Map;
import java.util.ResourceBundle;
import javax.faces.bean.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Chris
 */
@SessionScoped
@ManagedBean(name = "frontEnd")
public class FrontEnd implements Serializable {

    Developers selected;
    List<Developers> developers;
    PasswordValidation passwordVal;
    PasswordValidationReponse passwordValResponse;

     @PostConstruct
    public void init() {
         System.out.println("FrontEnd:PostConstruct");
        selected = new Developers();

        // Get request params to share resources between views
        Map<String, String> params = FacesContext
                .getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap();

        // Get project name passed over from last view
        String newDeveloperName = params.get("developer");
        System.out.println("PostConstruct: developerName = " + newDeveloperName);
        
        // If found, get object from service
        if (newDeveloperName != null) {
            DevRestClient client = getClient();
            selected = (Developers) client.find(newDeveloperName);
            client.close();
        }
    }
    
    private DevRestClient getClient() {
        return new DevRestClient();
    }

    public List<Developers> getItems() {
        System.out.println("FrontEnd getItems called");
        DevRestClient client = getClient();
        developers = client.findAllDevs();
        client.close();
        return developers;
    }

    public Developers getSelected() {
        if (selected == null) {
            return new Developers();
        } else {
            return this.selected;
        }
    }

    public String viewDeveloper(Developers dev) {
        // Set selected developer and return view
        selected = dev;
        return "View";
    }

    public String destroy(Developers dev) {
        // Delete dev from db, update all list, and return list
        DevRestClient client = getClient();
        client.remove(dev.getUserId());
        developers = client.findAll();
        logMessage("DevelopersDeleted");
        client.close();
        return "List";
    }

    public String update() {
        // Update db, refresh selected dev with changes and return view

        DevRestClient client = getClient();
        client.edit(selected, selected.getUserId());
        developers = client.findAll();

        // No function mapped in to find by user id - brute force it instead
        for (Developers developer : developers) {
            if (developer.getUserId().equals(selected.getUserId())) {
                selected = developer;
            }
        }
        client.close();
        logMessage("DevelopersUpdated");
        return "View";

    }

    public String create() {
        DevRestClient client = getClient();
        client.create(selected);
        logMessage("DevelopersCreated");
        return "View";
    }

    public String prepareCreate() {
        return "Create";
    }

    public String prepareEdit(Developers dev) {
        // Set selected dev, prepeare a new dev instance, and return edit
        return "Edit";
    }

    public String prepareList() {
        return "List";
    }

    public String prepareValidation(Boolean fromIndex) {
        return fromIndex ? "validation/Validation" : "Validation";
    }
    
    public PasswordValidation getPasswordValidation() {
        return passwordVal;
    }

    public String validate() {
        DevRestClient client = getClient();
        // TODO
        return "Validated";
        
        /*
        try {
            passwordValResponse.setValidated(sb.validatePassword(passwordVal));
            return "Validated";
        } catch (Exception e) {
            logError(e);
            passwordValResponse.setValidated(Boolean.FALSE);
            return "Validated";
        }
        */
    }

    public PasswordValidationReponse getPasswordValidationResponse() {
        return passwordValResponse;
    }
    
    public void logError(Exception e) {
        addErrorMessage(e, ResourceBundle.getBundle("/bundle").getString("PersistenceErrorOccured"));
    }

    public void logMessage(String message) {
        addSuccessMessage(ResourceBundle.getBundle("/bundle").getString(message));
    }

    public static void addErrorMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg);
        FacesContext.getCurrentInstance().addMessage(null, facesMsg);
    }

    public static void addSuccessMessage(String msg) {
        FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg);
        FacesContext.getCurrentInstance().addMessage("successInfo", facesMsg);
    }

    public static void addErrorMessage(Exception ex, String defaultMsg) {
        String msg = ex.getLocalizedMessage();
        if (msg != null && msg.length() > 0) {
            addErrorMessage(msg);
        } else {
            addErrorMessage(defaultMsg);
        }
    }

    public static void addErrorMessages(List<String> messages) {
        for (String message : messages) {
            addErrorMessage(message);
        }
    }
}
