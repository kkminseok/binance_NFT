package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.user.UserDTO;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDTO sigin(String userId, String password){
        //대충 디비에 넣은 값이랑 같은지 확인..
        return UserDTO.builder().userId(userId).password(password).build();
    }

    public String getServerinfo(){
        return "sign in test";
    }

}
