package kms.NFTJAVA.DTO.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("user")
public class UserEntity {


    @Id
    private String uid;
    private String password;
    private String refreshToken;


    public UserEntity(String uid, String password, String refreshToken) {
        this.uid = uid;
        this.password = password;
        this.refreshToken = refreshToken;
    }


    public UserEntity(UserDTO userDTO){
        this.uid = userDTO.getUid();
        this.password = userDTO.getPassword();
        this.refreshToken = userDTO.getRefreshToken();
    }
}
