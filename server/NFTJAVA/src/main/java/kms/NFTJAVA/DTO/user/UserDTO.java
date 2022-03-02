package kms.NFTJAVA.DTO.user;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String uid;
    private String uname;
    private String password;
    private String email;
    private String token;


}
