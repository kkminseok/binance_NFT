package kms.NFTJAVA.Exception;

import kms.NFTJAVA.DTO.coinDTO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class coinException {

    public boolean savecheck(coinDTO dao){
        if(dao.getKrw() == null)
            return false;
        return true;
    }
}
