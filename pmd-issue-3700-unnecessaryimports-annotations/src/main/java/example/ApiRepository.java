package example;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional; // this is indeed unused

public interface ApiRepository extends PagingAndSortingRepository<Api, Long> {

    @Query("select id from zk.apigw.manager.bean.entity.Api api where api.status = zk.apigw.common.api.ApiStatus.PUBLISHED")
    Iterable<Long> findAllPublishedApiIds();
}
