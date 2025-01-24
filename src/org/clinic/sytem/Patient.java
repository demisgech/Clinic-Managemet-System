package org.clinic.sytem;

import java.util.Date;

class Patient extends Person {
    private String department;
    private Date birthDate;

    public Patient(
            String id,
            String name,
            String contact,
            String address,
            String department,
            Date birthDate,
            String gender,
            String email,
            String password) {
        super(id, name, contact, address, gender, email, password);
        this.department = department;
        this.birthDate = birthDate;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateOfBirth() {
        return birthDate;
    }

    public void setDateOfBirth(Date birthDate) {
        this.birthDate = birthDate;
    }

    private boolean checkPassword(String password, String comfirmPassword) {
        return password.equals(comfirmPassword);
    }

}
