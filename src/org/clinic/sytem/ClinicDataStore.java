package org.clinic.sytem;

import java.util.Map;
import java.util.HashMap;

class ClinicDataStore {
    private final Map<String, Patient> patients;
    private final Map<String, StaffMember> staffMembers;
    private final Map<String, MedicalRecord> medicalRecords;

    public ClinicDataStore() {
        patients = new HashMap<>();
        staffMembers = new HashMap<>();
        medicalRecords = new HashMap<>();
    }

    // Patients
    public void addPatient(Patient patient) {
        this.patients.put(patient.getId(), patient);
    }

    public void updatePatientInfo(String id, Patient patient)
            throws IllegalArgumentException {
        if (findPatientById(id) == null)
            throw new IllegalArgumentException(
                    "Sorry! Patient with id: " + id + " doesn't exist in our database!");
        this.patients.put(id, patient);
    }

    public void removePatient(String id) {
        this.patients.remove(id);
    }

    public Patient findPatientById(String id) {
        return this.patients.get(id);
    }

    public Map<String, Patient> getAllPatients() {
        return patients;
    }

    // StaffMembers
    public void addStaffMember(StaffMember staffMember) {
        this.staffMembers.put(staffMember.getId(), staffMember);
    }

    public void updateStaffMemberInfo(String id, StaffMember staffMember)
            throws IllegalArgumentException {
        if (findPatientById(id) == null)
            throw new IllegalArgumentException(
                    "Sorry! StaffMember with id: " + id + " doesn't exist in our database!");
        this.staffMembers.put(id, staffMember);
    }

    public void removeStaffMember(String id) {
        this.staffMembers.remove(id);
    }

    public StaffMember findStaffMemberById(String id) {
        return this.staffMembers.get(id);
    }

    public Map<String, StaffMember> getAllStarffMembers() {
        return staffMembers;
    }

    // MedicalRecords
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecords.put(medicalRecord.getPatientID(), medicalRecord);
    }

    public void updateMedicalRecord(String id, MedicalRecord medicalRecord)
            throws IllegalArgumentException {
        if (findPatientById(id) == null)
            throw new IllegalArgumentException(
                    "Sorry! MedicalRecord with id: " + id + " doesn't exist in our database!");
        this.medicalRecords.put(id, medicalRecord);
    }

    public void removeMedicalRecord(String patientId) {
        this.medicalRecords.remove(patientId);
    }

    public MedicalRecord finMedicalRecordByPatientId(String patientId) {
        return this.medicalRecords.get(patientId);
    }

    public Map<String, MedicalRecord> getAllMedicalRecods() {
        return medicalRecords;
    }
}
