package org.clinic.sytem;

class StaffMember extends Person {
    private String position;

    public StaffMember(
            String id,
            String name,
            String contact,
            String address,
            String position,
            String email,
            String password,
            String gender) {
        super(id, name, contact, address, gender, email, password);
        this.position = position;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    private boolean checkPassword(String password, String comfirmPassword) {
        return password.equals(comfirmPassword);
    }
}
