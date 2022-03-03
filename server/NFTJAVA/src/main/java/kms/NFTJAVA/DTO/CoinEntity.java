package kms.NFTJAVA.DTO;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@RedisHash("coin")
public class CoinEntity {
    @Id
    private String name;
    private String enabled;
    private String postOnly;
    private Float price;
    private Float krw;
    private Float deal_bas_r;


    public CoinEntity(String name, String enabled, String postOnly, Float price, Float krw, Float deal_bas_r) {
        this.name = name;
        this.enabled = enabled;
        this.postOnly = postOnly;
        this.price = price;
        this.krw = krw;
        this.deal_bas_r = deal_bas_r;
    }

    public CoinEntity(coinDTO coindto){
        this.name = coindto.getName();
        this.enabled = coindto.getEnabled();
        this.postOnly = coindto.getPostOnly();
        this.price = coindto.getPrice();
        this.krw = coindto.getKrw();
        this.deal_bas_r = coindto.getDeal_bas_r();
    }
}

