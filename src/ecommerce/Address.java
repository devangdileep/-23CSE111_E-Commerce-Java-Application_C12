package ecommerce;
public class Address {
    private Long addressId;
    private String street, city, state, zipCode, country;
    public Address() {}
    public Address(Long id, String street, String city, String state, String zipCode, String country) {
        this.addressId = id;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.country = country;
    }
    public Address(Address other) {
        this(other.addressId, other.street, other.city, other.state, other.zipCode, other.country);
    }
    public String getFullAddress() {
        return street + ", " + city + ", " + state + " - " + zipCode + ", " + country;
    }
    public boolean validate() {
        return street != null && !street.isEmpty() && zipCode != null && !zipCode.isEmpty();
    }
    public void update(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    public String toString() {
        return getFullAddress();
    }
}
