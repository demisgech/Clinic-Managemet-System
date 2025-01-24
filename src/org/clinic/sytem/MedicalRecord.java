package org.clinic.sytem;

import java.util.Date;

class MedicalRecord {

    private Date dateOfVisit;
    private String diagnosis;
    private String treatment;
    private String patientID;

    public MedicalRecord(
            String patientID,
            Date dateOfVisit,
            String diagnosis,
            String treatment) {
        this.dateOfVisit = dateOfVisit;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.patientID = patientID;
    }

    public Date getDateOfVisit() {
        return dateOfVisit;
    }

    public void setDateOfVisit(Date dateOfVisit) {
        this.dateOfVisit = dateOfVisit;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getPatientID() {
        return patientID;
    }

    public void setPatientID(String patientID) {
        this.patientID = patientID;
    }
}
