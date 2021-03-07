package com.weiyun;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.weiyun.dto.request.HistoryQueryConditionDTO;
import com.weiyun.entity.TbPeopleCount;
import com.weiyun.service.TbPeopleCountService;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Starix
 * @date 2020-06-18 23:15
 */
public class PeopleCountServiceTests extends BaseTest {

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
        calendar.add(Calendar.DATE, -1);
        for (int i = 0; i < 1; i++) {
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            for (int j = 0; j < 720; j++) {
                TbPeopleCount peopleCount = new TbPeopleCount();
                peopleCount.setAreaCode(1);
                int count = new Random().nextInt(500);
                peopleCount.setDetectedCount(count);
                peopleCount.setOverflow(count > 450 ? true : false);
                peopleCount.setDetectedImage("https://images.unsplash.com/photo-1592107761705-30a1bbc641e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80");
                peopleCount.setOriginImage("https://images.unsplash.com/photo-1592107761705-30a1bbc641e7?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=934&q=80");
                peopleCount.setCreateTime(calendar.getTime());
                list.add(peopleCount);
                calendar.add(Calendar.MINUTE, 2);
            }
            calendar.add(Calendar.DATE, 1);
        }
        peopleCountService.saveBatch(list);
    }

    @Test
    public void testDateConverter(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(2018, 2, 5, 8, 0, 0);
        Date date = calendar.getTime();
        System.out.println(date);
    }

    @Test
    public void testQueryCommonData() {
        HistoryQueryConditionDTO conditionDTO = new HistoryQueryConditionDTO();
        // conditionDTO.setYear(2020);
        // conditionDTO.setMonth(6);
        // conditionDTO.setDay(15);
        // conditionDTO.setStartHour(14);
        // conditionDTO.setEndHour(16);
        conditionDTO.setCurrent(1);
        conditionDTO.setSize(10);
        IPage<TbPeopleCount> iPage = peopleCountService.queryHistoryData(conditionDTO);
        iPage.getRecords().forEach(System.out::println);

    }
}
