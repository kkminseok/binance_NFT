package kms.NFTJAVA.repository;

import kms.NFTJAVA.DTO.CoinEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRedisRepo extends CrudRepository<CoinEntity,String> {
    @Override
    Iterable<CoinEntity> findAll();

    CoinEntity findByName(String name);

}
