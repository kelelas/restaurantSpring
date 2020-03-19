package com.kelelas.restaurant.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserDTO {
    private String name_ukr;
    private String name_eng;
    private String email;
    private String password;
}
