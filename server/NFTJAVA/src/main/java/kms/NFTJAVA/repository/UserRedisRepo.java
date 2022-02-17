package kms.NFTJAVA.repository;

import kms.NFTJAVA.DTO.user.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRedisRepo extends CrudRepository<UserEntity,String> {
}
