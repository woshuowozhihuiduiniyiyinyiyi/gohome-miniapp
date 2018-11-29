package com.hj.tj.gohome;

import com.hj.tj.gohome.entity.Owner;
import com.hj.tj.gohome.mapper.OwnerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author tangj
 * @description
 * @since 2018/11/29 20:32
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OwnerServiceTest {

    @Resource
    private OwnerMapper ownerMapper;

    @Test
    public void testInsert(){

        Owner owner = new Owner();
        owner.setAvatarUrl("fdasfds");
        owner.setCity("guangzhou");
        owner.setGender(2);
        owner.setOpenId("23532532");
        owner.setPhone("fdafd");
        owner.setWxNickname("fdafdsa");

        ownerMapper.insert(owner);

        System.out.println(owner.getId());
    }
}
