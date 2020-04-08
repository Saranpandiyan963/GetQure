package app.saran.getqure;

public class Patient {

    private String patientName;
    private int age;
    private String gender;
    private String patientId;
    private  String apointed_hospital;


    public Patient() {

    }

    public Patient(String patientName, int age, String gender) {
        this.patientName = patientName;
        this.age = age;
        this.gender = gender;
    }

    public Patient(String patientId,String apointed_hospital){
        this.patientId = patientId;
        this.apointed_hospital= apointed_hospital;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getAge() {
        return age;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getApointed_hospital() {
        return apointed_hospital;
    }

    public void setApointed_hospital(String apointed_hospital) {
        this.apointed_hospital = apointed_hospital;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
