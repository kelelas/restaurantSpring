package com.kelelas.restaurant.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table( name="users",
        uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "name_ukr", nullable = false)
    private String nameUkr;
    @Column(name = "name_eng", nullable = false)
    private String nameEng;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(nullable = false)
    private String email;
    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoleType role;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Column(name = "balance", nullable = false)
    private int balance;



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = new ArrayList<>();
        list.add(role);
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }
}
