package kms.NFTJAVA.DTO;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DAO {

    private static long seq = 0L;
    private String id;
    private String name;
    private String enabled;
    private String postOnly;
    private Float price;
    private Float krw;
    private Float deal_bas_r;

    public void calcurate(){
        this.krw = this.deal_bas_r * this.price;
    }

    public DAO(final NFTRedis entity){
        this.id = entity.getId();
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.postOnly = entity.getPostOnly();
        this.price = entity.getPrice();
        this.deal_bas_r = entity.getDeal_bas_r();
    }

    public static NFTRedis toEntity(final DAO dao){
        return NFTRedis.builder()
                .id(dao.getId())
                .name(dao.getName())
                .enabled(dao.getEnabled())
                .postOnly(dao.getPostOnly())
                .price(dao.getPrice())
                .deal_bas_r(dao.getDeal_bas_r())
                .build();
    }

}
