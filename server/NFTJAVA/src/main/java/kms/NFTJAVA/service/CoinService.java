package kms.NFTJAVA.service;

import kms.NFTJAVA.DTO.coinDTO;
import kms.NFTJAVA.DTO.CoinEntity;
import kms.NFTJAVA.Exception.coinException;
import kms.NFTJAVA.repository.NFTRedisRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoinService {

    private final NFTRedisRepo redisRepo;

    private final coinException excep;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public void setkrw(coinDTO dao){
        if(dao.getDeal_bas_r() != null) {
            //log.info("deal not null : {}",dao.getDeal_bas_r());
            dao.calcurate(dao.getDeal_bas_r());
        }
        //DB에서 꺼내서 넣음.
        else{
            Optional<CoinEntity> coinEntity = redisRepo.findById(dao.getName());
            if(coinEntity.isPresent() && coinEntity.get().getDeal_bas_r() != null){
                dao.calcurate(coinEntity.get().getDeal_bas_r());
            }
            else{
                //DB에도 없으면 초기값 1195으로 잡자
                //log.info("초기값 : 1195");
                dao.calcurate((float) 1195.1234);
            }
        }
    }

    public CoinEntity findcoin(String l) {

        CoinEntity byName = redisRepo.findByName(l);
        return byName;
    }

    public void savecoin(coinDTO dao) {
        //null값이면 넣지않음.
        if(excep.savecheck(dao)){
            CoinEntity coinEntity = new CoinEntity(dao);
            redisRepo.save(coinEntity);
            log.info("save complete");
        }
        else
            log.info("{} save non complete because api server down",dao.getName());
    }
    public Iterable<CoinEntity> findallser(){
        return redisRepo.findAll();
    }


    //내가산 우주선과 시세를 비교함. 로긴


    //내가산 우주선의 가격과 연료값 등을 통해 원금을 언제 뽑아먹는지

    //내가 가진 연료가 떨어지면 등록된 휴대폰으로 푸시 알람을 보냄.

    //



}
