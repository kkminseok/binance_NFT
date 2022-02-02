package kms.NFTJAVA.DTO;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
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
    public DAO(){

    }

}
