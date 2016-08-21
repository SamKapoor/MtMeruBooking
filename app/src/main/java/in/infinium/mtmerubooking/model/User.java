package in.infinium.mtmerubooking.model;

/**
 * Created by admin on 8/21/2016.
 */
public class User {

    String RegId;
    String CompId;
    String Name;
    String Address;
    String MobileNo;
    String Email;
    String UserName;
    String CityId;
    String CountryId;
    String UserType;
    //               "ApproveBy": null,
//            "ApprovedDate": null,
//            "Status": "P",
//            "StatusText": null,
//            "IsExist": null,
//            "ModifiedDate": null,
//            "ModifiedBy": null,
//            "ModifiedByText": null,
//            "CreatedDate": null,
//            "CreatedBy": null,
//            "CreatedByText": null,
//            "UpdatedBy": null,
//            "UpdatedDate": null,
//            "UpdatedByText": null,
//            "DBMessage": null,
//            "IDs": null,
    String ID;

    public String getRegId() {
        return RegId;
    }

    public void setRegId(String regId) {
        RegId = regId;
    }

    public String getCompId() {
        return CompId;
    }

    public void setCompId(String compId) {
        CompId = compId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getCityId() {
        return CityId;
    }

    public void setCityId(String cityId) {
        CityId = cityId;
    }

    public String getCountryId() {
        return CountryId;
    }

    public void setCountryId(String countryId) {
        CountryId = countryId;
    }

    public String getUserType() {
        return UserType;
    }

    public void setUserType(String userType) {
        UserType = userType;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
