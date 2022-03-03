package kms.NFTJAVA.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class coinDTO {

    private static long seq = 0L;
    private String name;
    private String enabled;
    private String postOnly;
    private Float price;
    private Float krw;
    private Float deal_bas_r;

    public void calcurate(Float bas_r){
        this.krw = bas_r * this.price;
    }

    public coinDTO(final CoinEntity entity){
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.postOnly = entity.getPostOnly();
        this.price = entity.getPrice();
        this.deal_bas_r = entity.getDeal_bas_r();
    }

    public static CoinEntity toEntity(final coinDTO coindto){
        return CoinEntity.builder()
                .name(coindto.getName())
                .enabled(coindto.getEnabled())
                .postOnly(coindto.getPostOnly())
                .price(coindto.getPrice())
                .deal_bas_r(coindto.getDeal_bas_r())
                .build();
    }

}
