# Clinic Management System

## Project Structure

```java
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
    ...
    ...
    ...
    public MedicalRecord finMedicalRecordByPatientId(String patientId) {
        return this.medicalRecords.get(patientId);
    }

    public Map<String, MedicalRecord> getAllMedicalRecods() {
        return medicalRecords;
    }
}

```

**ClinicSystem.java**

```java

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

    ...
    ...
    ...
}
```

**1. clone this repository:**

```sh
git clone https://github.com/demisgech/Clinic-Managemet-System.git

```

**2. Navigate to your file location**

**3. Compile All Java Filse:**

```sh
javac -d bin src/org/clinic/system/*.java
```

**4. Run the Main Class:**

```sh
java -cp bin org.clinic.system.ClinicSystem
```
