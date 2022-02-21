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
    private String uname;
    private String password;
    private String refreshToken;


    public UserEntity(String uid,String uname, String password, String refreshToken) {
        this.uid = uid;
        this.uname = uname;
        this.password = password;
        this.refreshToken = refreshToken;
    }


    public UserEntity(UserDTO userDTO){
        this.uid = userDTO.getUid();
        this.uname = userDTO.getUname();
        this.password = userDTO.getPassword();
        this.refreshToken = userDTO.getRefreshToken();
    }
}
