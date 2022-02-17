package kms.NFTJAVA.controller;

import kms.NFTJAVA.DTO.coinDTO;
import kms.NFTJAVA.DTO.coinEntity;
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
    public Iterable<coinEntity> testview(Model model){
        Iterable<coinEntity> findallser = nftservice.findallser();
        if(findallser != null){
//            model.addAttribute("starDTO",nft);
            Iterator<coinEntity> iterator = findallser.iterator();
            while(iterator.hasNext()){
                coinEntity nftdata = iterator.next();
                log.info("nftdata : {} + {} + {} ",nftdata.getName() , nftdata.getPrice() , nftdata.getKrw());
            }


            return findallser;
        }
        return null;
    }

    @PostMapping("/data")
    public void test(@ModelAttribute coinDTO data, Model model){
        log.info("Price = {}",data.getPrice());
        nftservice.setkrw(data);
        nftservice.savecoin(data);
        log.info("한국돈 = {}원",data.getKrw());
        model.addAttribute("starDTO",data);

    }



}
