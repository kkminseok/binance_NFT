package kms.NFTJAVA.Exception;

import kms.NFTJAVA.DTO.DAO;
import org.springframework.context.annotation.Configuration;

@Configuration
public class coinException {

    public boolean savecheck(DAO dao){
        if(dao.getKrw() == null)
            return false;
        return true;
    }
}
