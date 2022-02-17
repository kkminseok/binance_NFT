package kms.NFTJAVA.DTO.user;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {
    private String uid;
    private String password;
    private String refreshToken;


}
