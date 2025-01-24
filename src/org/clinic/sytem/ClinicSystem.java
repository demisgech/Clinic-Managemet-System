package org.clinic.sytem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.UUID;

public class ClinicSystem {
    private static ClinicDataStore clinicDataStore = new ClinicDataStore();
    private static Scanner scanner = new Scanner(System.in);

    public ClinicSystem() {
        clinicDataStore = new ClinicDataStore();
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static Date readDate(String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);

        Date date;
        while (true) {
            try {
                System.out.print(prompt);
                date = dateFormat.parse(scanner.next());
                if (date != null)
                    break;
            } catch (Exception e) {
                System.out.println("Error!!! " + e.getMessage());
                System.out.print("Invalid date format (yyyy-MM-dd): ");
            }
        }
        return date;
    }

    private static void readCommonPersonDetails(Person person) {
        person.setName(readString("Name: "));
        person.setContact(readString("Contact: "));
        person.setAddress(readString("Address: "));
        person.setGender(readString("Gender: "));
        person.setEmail(readString("Email: "));
        person.setPassword(readString("Password: "));
    }

    public static void registerPatient() {
        var id = UUID.randomUUID().toString();
        String name = readString("Name: ");
        String contact = readString("Contact: ");
        String address = readString("Address: ");
        String position = readString("Department: ");
        Date birthDate = readDate("Birth Date: ");
        String gender = readString("Gender:");
        String email = readString("Email: ");
        String password = readString("Password: ");
        clinicDataStore
                .addPatient(new Patient(
                        id, name, contact,
                        address, position,
                        birthDate, gender, email,
                        password));
    }

    public static void registerStaffMember() {
        var id = UUID.randomUUID().toString();
        String name = readString("Name:");
        String contact = readString("Contact: ");
        String address = readString("Address: ");
        String position = readString("Position: ");
        String gender = readString("Gender: ");
        String email = readString("Email: ");
        String password = readString("Password: ");
        clinicDataStore
                .addStaffMember(new StaffMember(
                        id, name, contact,
                        address, position,
                        gender, email,
                        password));
    }

    public static void registerMedicalRecord() {
        String patientID = UUID.randomUUID().toString();
        String treatment = readString("Treatment: ");
        String diagnosis = readString("Diagnosis: ");
        Date dateOfVisit = readDate("Date of Visit: ");
        clinicDataStore
                .addMedicalRecord(new MedicalRecord(
                        patientID, dateOfVisit, diagnosis, treatment));
    }

    private boolean authenticate(String email, String password) {
        for (Patient patient : clinicDataStore.getAllPatients().values()) {
            if (patient.getEmail().equals(email) && patient.getPassword().equals(password))
                return true;
        }

        for (StaffMember staffMember : clinicDataStore.getAllStarffMembers().values()) {
            if (staffMember.getEmail().equals(email) && staffMember.getPassword().equals(password))
                return true;
        }
        return false;
    }

    public static void searchForUser() {
        System.out.println("Search for: ");
        System.out.println("1. Patient");
        System.out.println("2.Staff Member");
        System.out.print("Choice: ");
        byte choice = scanner.nextByte();
        if (choice == 1) {
            String userId = readString("Patient Id: ");
            var patient = clinicDataStore.findPatientById(userId);
            if (patient != null)
                displayPatient(patient);
            else
                System.out.println("Sorry! We do not have patient with the id you entered!");
        } else if (choice == 2) {
            String userId = readString("StaffMember Id: ");
            var staffMember = clinicDataStore.findStaffMemberById(userId);
            if (staffMember != null)
                displayStaffMember(staffMember);
            else
                System.out.println("Sorry! We do not have StaffMember with the id you entered!");
        } else {
            System.out.println("Invalid input! Please try again!");
        }
    }

    public static void updatePatient(String id) {
        var patient = clinicDataStore.findPatientById(id);
        if (patient == null) {
            System.out.println("Sorry! Patient with this id is not found!");
            return;
        }
        readCommonPersonDetails(patient);
        patient.setDepartment(readString("Department: "));
        patient.setDateOfBirth(readDate("Birth date: "));

    }

    public static void updateStaffMember(String id) {
        var staffMember = clinicDataStore.findStaffMemberById(id);
        if (staffMember == null) {
            System.out.println("Sorry! Staff member with the id you enterd is not found!");
            return;
        }
        readCommonPersonDetails(staffMember);
        staffMember.setPosition(readString("Position: "));
    }

    public static void main(String[] args) {

        addSampleData();

        System.out.printf("%-40s%n", "------------------------------------");
        System.out.printf("| %-36s |%n", "WELCOME TO OUR UNIVERSITY CLINIC");
        System.out.printf("%-40s%n%n", "------------------------------------");
        performTask();
    }

    private static void addSampleData() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {

            // Create a sample patient and staff
            StaffMember sampleStaff1 = new StaffMember(
                    UUID.randomUUID().toString(),
                    "Dr. Henok Abebe",
                    "0987654321",
                    "123 Bole Rd, Addis Ababa", "Doctor",
                    "henokabeb@gmail.com", "henokabebe", "Male");
            clinicDataStore.addStaffMember(sampleStaff1);
            StaffMember sampleStaff2 = new StaffMember(
                    UUID.randomUUID().toString(),
                    "Nurse Meseret Teshome",
                    "0912345678",
                    "789 Meskel Flower, Addis Ababa", "Nurse",
                    "meseretteshome@gmail.com", "meseretteshome", "female");
            clinicDataStore.addStaffMember(sampleStaff2);

            Patient samplePatient1 = new Patient(
                    UUID.randomUUID().toString(),
                    "Abebe Chala", "0987654321",
                    "456 Gerji St, Addis Ababa",
                    "Cardiology", dateFormat.parse("1990-01-01"),
                    "Female", "abebechala@gmail.com", "abebechala");
            clinicDataStore.addPatient(samplePatient1);
            Patient samplePatient2 = new Patient(
                    UUID.randomUUID().toString(),
                    "Dawit Tadesse", "0923456789",
                    "123 Churchill Ave, Addis Ababa", "Neurology",
                    dateFormat.parse("1985-05-15"), "Male",
                    "dawittadesse@gmail.com", "dawittadesse");
            clinicDataStore.addPatient(samplePatient2);

            // Create a sample medical record
            MedicalRecord sampleRecord1 = new MedicalRecord(
                    samplePatient1.getId(),
                    dateFormat.parse("2023-01-01"), "Hypertension",
                    "Medication");
            clinicDataStore.addMedicalRecord(sampleRecord1);

        } catch (ParseException e) {
            System.out.println("An error occurred while parsing sample data: " + e.getMessage());

        }
    }

    private static void performTask() {
        int choice;
        while (true) {
            displayMenu();
            System.out.print("Choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    registerPatient();
                    ;
                    break;
                case 2:
                    registerStaffMember();
                    break;
                case 3:
                    registerMedicalRecord();
                    break;
                case 4:
                    searchForUser();
                    break;
                case 5:
                    updatePatient(null);
                    break;
                case 6:
                    deleteMedicalRecord(null);
                    break;
                case 7:
                    displayAllPatients();
                    break;
                case 8:
                    displayAllStaffMembers();
                    break;
                case 9:
                    displayAllMedicalRecords();
                    break;
                default:
                    System.out.println("\t\t\tInvalid choice. Please try again.");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("1) Register Patient");
        System.out.println("2) Register StaffMember");
        System.out.println("3) Insert medical record");
        System.out.println("4) Search for a Patient || StaffMember ");
        System.out.println("5) Update Record ");
        System.out.println("6) Delete record ");
        System.out.println("7) Display all Patients");
        System.out.println("8) Display all StaffMember");
        System.out.println("9) Display all Medical Records");
        System.out.println("-----------------------------");
        System.out.println("Choose an option: [1,2,3,4,5,6,7,8,9]:");
    }

    public static void deletePatient(String patientId) {
        clinicDataStore.removePatient(patientId);
    }

    public static void deleteStaffMember(String staffMemberId) {
        clinicDataStore.removePatient(staffMemberId);
    }

    public static void deleteMedicalRecord(String id) {
        clinicDataStore.removePatient(id);
    }

    public static void displayAllPatients() {
        System.out.printf("%-15s %-20s %-15s %-20s %-15s %-15s %-20s %-20s%n",
                "ID", "Name", "Contact", "Address", "Department", "Birth Date", "Gender", "Email");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        for (Patient patient : clinicDataStore.getAllPatients().values()) {
            displayPatient(patient);
        }
    }

    private static void displayPatient(Patient patient) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("%-15s %-20s %-15s %-20s %-15s %-15s %-20s %-20s%n",
                patient.getId(), patient.getName(), patient.getContact(), patient.getAddress(),
                patient.getDepartment(), dateFormat.format(patient.getDateOfBirth()), patient.getGender(),
                patient.getEmail());
        System.out.println();
    }

    public static void displayAllStaffMembers() {
        System.out.printf("%-20s %-20s %-25s %-15s %-20s%n",
                "StaffMember-ID", "StaffMember-Name", "StaffMember Position", "Contact", "Address");
        System.out.println(
                "-------------------------------------------------------------------------------------------------------------------------------------");
        for (var staffMember : clinicDataStore.getAllStarffMembers().values()) {
            displayStaffMember(staffMember);
        }
    }

    private static void displayStaffMember(StaffMember staffMember) {
        System.out.printf("%-20s %-20s %-25s %-15s %-20s%n",
                staffMember.getId(), staffMember.getName(), staffMember.getPosition(),
                staffMember.getContact(), staffMember.getAddress());
    }

    public static void displayAllMedicalRecords() {
        System.out.printf("%-20s %-20s %-20s %-20s%n",
                "Date of Visit", "Diagnosis", "Treatment", "Patient ID");
        System.out.println(
                "---------------------------------------------------------------------------------------------");
        for (MedicalRecord record : clinicDataStore.getAllMedicalRecods().values()) {
            displayMedicalRecord(record);
        }
    }

    private static void displayMedicalRecord(MedicalRecord record) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        System.out.printf("%-20s %-20s %-20s %-20s%n",
                dateFormat.format(record.getDateOfVisit()), record.getDiagnosis(), record.getTreatment(),
                record.getPatientID());

    }

    public void login() {
        String email = readString("Email: ");
        String password = readString("Password: ");
        if (authenticate(email, password)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid email or password. Please try again!");
        }
    }
}
