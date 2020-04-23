package app.saran.getqure;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class HospitalDetails implements Parcelable {

    String hospital_Name;
    String doctor_Name;
    String description;
    int image ;

    public HospitalDetails(String hospital_Name, String doctor_Name, String description) {
        this.hospital_Name = hospital_Name;
        this.doctor_Name = doctor_Name;
        this.description = description;
    }

    protected HospitalDetails(Parcel in) {
        hospital_Name = in.readString();
        doctor_Name = in.readString();
        description = in.readString();
        image = in.readInt();
    }

    public static final Creator<HospitalDetails> CREATOR = new Creator<HospitalDetails>() {
        @Override
        public HospitalDetails createFromParcel(Parcel in) {
            return new HospitalDetails(in);
        }

        @Override
        public HospitalDetails[] newArray(int size) {
            return new HospitalDetails[size];
        }
    };

    public String getHospital_Name() {
        return hospital_Name;
    }

    public String getDoctor_Name() {
        return doctor_Name;
    }

    public String getDescription() {
        return description;
    }

    public static ArrayList<HospitalDetails> createHospitalDetail(int numOfHospital){
        ArrayList<HospitalDetails> hospital = new ArrayList<HospitalDetails>();

        for(int i = 0;i<12;i++)
        {
            hospital.add(new HospitalDetails("Hospital >" + i,"Doctor >" + i,"Description >" + i));
        }

        return hospital;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hospital_Name);
        dest.writeString(doctor_Name);
        dest.writeString(description);
        dest.writeInt(image);
    }
}
