package kms.NFTJAVA.DTO;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@RedisHash("coin")
public class NFTRedis {

    private String id;
    @Id
    private String name;
    private String enabled;
    private String postOnly;
    private Float price;
    private Float krw;
    private Float deal_bas_r;


    public NFTRedis(String id, String name, String enabled, String postOnly, Float price, Float krw, Float deal_bas_r) {
        this.id = id;
        this.name = name;
        this.enabled = enabled;
        this.postOnly = postOnly;
        this.price = price;
        this.krw = krw;
        this.deal_bas_r = deal_bas_r;
    }

    public NFTRedis(DAO dao){
        this.id = dao.getId();
        this.name = dao.getName();
        this.enabled = dao.getEnabled();
        this.postOnly = dao.getPostOnly();
        this.price = dao.getPrice();
        this.krw = dao.getKrw();
        this.deal_bas_r = dao.getDeal_bas_r();
    }
}

