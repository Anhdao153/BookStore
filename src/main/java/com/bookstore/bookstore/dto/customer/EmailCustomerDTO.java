package com.bookstore.bookstore.dto.customer;

public class EmailCustomerDTO {
    private String usernameAccount;
    private String customerName;

    public EmailCustomerDTO(String usernameAccount, String customerName) {
        this.usernameAccount = usernameAccount;
        this.customerName = customerName;
    }

    public EmailCustomerDTO() {
    }

    public String getUsernameAccount() {
        return usernameAccount;
    }

    public void setUsernameAccount(String usernameAccount) {
        this.usernameAccount = usernameAccount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
