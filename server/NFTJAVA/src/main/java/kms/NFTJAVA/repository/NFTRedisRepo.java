package kms.NFTJAVA.repository;

import kms.NFTJAVA.DTO.NFTRedis;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NFTRedisRepo extends CrudRepository<NFTRedis,String> {
    @Override
    Iterable<NFTRedis> findAll();

    NFTRedis findByName(String name);

}
