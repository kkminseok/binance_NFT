package kms.NFTJAVA.controller;

import kms.NFTJAVA.DTO.coinDTO;
import kms.NFTJAVA.DTO.CoinEntity;
import kms.NFTJAVA.service.CoinService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;

@Slf4j
@RestController
public class pyController {

    private final CoinService nftservice;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public pyController(CoinService nftservice){
        this.nftservice = nftservice;
    }
    @GetMapping("/data")
    public Iterable<CoinEntity> testview(Model model){
        Iterable<CoinEntity> findallser = nftservice.findallser();
        if(findallser != null){
//            model.addAttribute("starDTO",nft);
            Iterator<CoinEntity> iterator = findallser.iterator();
            while(iterator.hasNext()){
                CoinEntity nftdata = iterator.next();
                log.info("nftdata : {} + {} + {} ",nftdata.getName() , nftdata.getPrice() , nftdata.getKrw());
            }


            return findallser;
        }
        return null;
    }

    @PostMapping("/data")
    public void test(@ModelAttribute coinDTO data, Model model){
        log.info("data post! Price = {}",data.getPrice());
        log.info("deal : {}",data.getDeal_bas_r());
        nftservice.setkrw(data);
        log.info("data - krw : {}",data.getKrw());
        nftservice.savecoin(data);
        log.info("한국돈 = {}원",data.getKrw());
        model.addAttribute("starDTO",data);

    }



}
