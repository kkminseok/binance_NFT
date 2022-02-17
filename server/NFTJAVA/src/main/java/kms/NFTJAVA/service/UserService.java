package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.DTO.user.UserEntity;
import kms.NFTJAVA.repository.UserRedisRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final UserRedisRepo userRedisRepo;

    public UserDTO sigin(UserEntity userEntity){
        //디비에 넣은 값이랑 같은지 확인..
        System.out.println("find user id : " + userEntity.getUid() + userEntity.getPassword());
        Optional<UserEntity> user = userRedisRepo.findById(userEntity.getUid());

        //암호화로 받아야하는 코드로 바꿔야함.
        if(!user.isPresent()){
            System.out.println("sibal");
            return null;
        }
        log.info("user Entity pw : {} , getuser pw : {}",userEntity.getPassword(),user.get().getPassword());
        if(user.get().getPassword().equals(userEntity.getPassword())) {
            log.info("비밀번호 같아용.");
            return UserDTO.builder().uid(user.get().getUid()).password(user.get().getPassword()).build();
        }
        return null;
    }

    public String getServerinfo(){
        return "sign in test";
    }

    public UserEntity userSave(UserDTO userDTO){
        UserEntity user  = new UserEntity(userDTO);
        return userRedisRepo.save(user);
    }

}
