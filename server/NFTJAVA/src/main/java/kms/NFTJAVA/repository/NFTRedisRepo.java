package kms.NFTJAVA.repository;

import kms.NFTJAVA.DTO.coinEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRedisRepo extends CrudRepository<coinEntity,String> {
    @Override
    Iterable<coinEntity> findAll();

    coinEntity findByName(String name);

}
