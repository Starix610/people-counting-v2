package com.weiyun;
import com.weiyun.entity.TbPeopleCount;
import com.weiyun.service.TbPeopleCountService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@SpringBootTest
@RunWith(SpringRunner.class)
public class PeopleCountingV2ApplicationTests {

    @Autowired
    private TbPeopleCountService peopleCountService;

    @Test
    public void testField() {
        System.out.println(peopleCountService.lambdaQuery().list());
    }

    @Test
    public void insertPeopleCount(){

        List<TbPeopleCount> list = Lists.newArrayList();
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 10000; i++) {
            TbPeopleCount peopleCount = new TbPeopleCount();
            peopleCount.setAreaCode(1);
            peopleCount.setDetectedCount(new Random().nextInt(500));
            peopleCount.setOverflow(i % 100 == 0 ? true : false);
            peopleCount.setDetectedImage("https://images.unsplash.com/photo-1592107761705-30a1bbc641e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80");
            peopleCount.setOriginImage("https://images.unsplash.com/photo-1592107761705-30a1bbc641e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80");
            peopleCount.setCreateTime(calendar.getTime());
            calendar.add(Calendar.MINUTE, 2);
            list.add(peopleCount);
        }
        peopleCountService.saveBatch(list);
    }


    // TODO: 2020-06-18 单测待修改
    @Test
    public void testPeopleCountService(){
        peopleCountService.queryCommonData(null);
    }

}
