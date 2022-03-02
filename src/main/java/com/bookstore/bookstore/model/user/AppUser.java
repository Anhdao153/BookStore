package com.bookstore.bookstore.model.user;

import com.bookstore.bookstore.config.CustomId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "app_users")
@SQLDelete(sql = "UPDATE app_users SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_users_seq")
    @GenericGenerator(
            name = "app_users_seq",
            strategy = "com.bookstore.bookstore.config.CustomId",
            parameters = {
                    @Parameter(name = CustomId.INCREMENT_PARAM, value = "1"),
                    @Parameter(name = CustomId.VALUE_PREFIX_PARAMETER, value = "AC-"),
                    @Parameter(name = CustomId.NUMBER_FORMAT_PARAMETER, value = "%04d")})
    private String id;
    private String account;
    private String password;
    private Boolean isEnabled = Boolean.TRUE;
    private String verificationCode;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST,targetEntity = Role.class)
    private Set<Role> roles;

    public AppUser() {
    }

    public AppUser(String id, String account, String password, Boolean isEnabled, String verificationCode, Set<Role> roles) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.isEnabled = isEnabled;
        this.verificationCode = verificationCode;
        this.roles = roles;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "id='" + id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", isEnabled=" + isEnabled +
                ", verificationCode='" + verificationCode + '\'' +
                ", roles=" + roles +
                '}';
    }
}
