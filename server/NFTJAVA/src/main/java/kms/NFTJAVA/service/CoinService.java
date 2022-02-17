package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.coinDTO;
import kms.NFTJAVA.DTO.coinEntity;
import kms.NFTJAVA.Exception.coinException;
import kms.NFTJAVA.repository.NFTRedisRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinService {

    private final NFTRedisRepo redisRepo;

    private final coinException excep;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void setkrw(coinDTO dao){
        if(dao.getDeal_bas_r() != null)
            dao.calcurate();
    }

    public coinEntity findcoin(String l) {

        coinEntity byName = redisRepo.findByName(l);
        return byName;
    }

    public void savecoin(coinDTO dao) {
        //null값이면 넣지않음.
        if(excep.savecheck(dao)){
            coinEntity coinEntity = new coinEntity(dao);
            redisRepo.save(coinEntity);
            log.info("save complete");
        }
        else
            log.info("{} save non complete because api server down",dao.getName());
    }
    public Iterable<coinEntity> findallser(){
        return redisRepo.findAll();
    }


    //내가산 우주선과 시세를 비교함. 로긴


    //내가산 우주선의 가격과 연료값 등을 통해 원금을 언제 뽑아먹는지

    //내가 가진 연료가 떨어지면 등록된 휴대폰으로 푸시 알람을 보냄.

    //



}
