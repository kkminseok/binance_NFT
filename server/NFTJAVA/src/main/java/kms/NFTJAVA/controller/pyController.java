package kms.NFTJAVA.controller;

import kms.NFTJAVA.DTO.DAO;
import kms.NFTJAVA.DTO.NFTRedis;
import kms.NFTJAVA.DTO.user.UserDTO;
import kms.NFTJAVA.service.NFTservice;
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

    private final NFTservice nftservice;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public pyController(NFTservice nftservice){
        this.nftservice = nftservice;
    }
    @GetMapping("/data")
    public Iterable<NFTRedis> testview(Model model){
        Iterable<NFTRedis> findallser = nftservice.findallser();
        if(findallser != null){
//            model.addAttribute("starDTO",nft);
            Iterator<NFTRedis> iterator = findallser.iterator();
            while(iterator.hasNext()){
                NFTRedis nftdata = iterator.next();
                log.info("nftdata : {} + {} + {} ",nftdata.getName() , nftdata.getPrice() , nftdata.getKrw());
            }


            return findallser;
        }
        return null;
    }

    @PostMapping("/data")
    public void test(@ModelAttribute DAO data, Model model){
        log.info("Price = {}",data.getPrice());
        nftservice.setkrw(data);
        nftservice.savecoin(data);
        log.info("한국돈 = {}원",data.getKrw());
        model.addAttribute("starDTO",data);

    }

    @PostMapping("/login")
    public void login(@RequestBody UserDTO userdto, Model model){
        log.info("login user = {} , {}", userdto.getUserId(),userdto.getPassword());
    }




}
