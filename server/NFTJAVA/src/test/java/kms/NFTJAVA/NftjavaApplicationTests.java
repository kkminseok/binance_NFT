package kms.NFTJAVA;

import kms.NFTJAVA.repository.NFTRedisRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
class NftjavaApplicationTests {

	@Autowired
	private NFTRedisRepo repo;

	@Test
	void contextLoads() {
	}

	/*
	@Test
	void basicCrudTest(){


		NFTRedis coindata = new NFTRedis("1","atlas","true","y",0.3423F,54.3F,1230.4F);
		NFTRedis savedata = repo.save(coindata);
		Optional<NFTRedis> findcoin = repo.findById(savedata.getId());

		Assertions.assertThat(findcoin.isPresent()).isEqualTo(true);
		Assertions.assertThat(findcoin.get().getName()).isEqualTo(coindata.getName());
		Assertions.assertThat(findcoin.get().getPrice()).isEqualTo(coindata.getPrice());


	}

	 */

}
