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

    public void calcurate(){
        this.krw = this.deal_bas_r * this.price;
    }

    public coinDTO(final coinEntity entity){
        this.name = entity.getName();
        this.enabled = entity.getEnabled();
        this.postOnly = entity.getPostOnly();
        this.price = entity.getPrice();
        this.deal_bas_r = entity.getDeal_bas_r();
    }

    public static coinEntity toEntity(final coinDTO coindto){
        return coinEntity.builder()
                .name(coindto.getName())
                .enabled(coindto.getEnabled())
                .postOnly(coindto.getPostOnly())
                .price(coindto.getPrice())
                .deal_bas_r(coindto.getDeal_bas_r())
                .build();
    }

}
