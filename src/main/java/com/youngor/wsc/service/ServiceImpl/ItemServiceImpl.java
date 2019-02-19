package com.youngor.wsc.service.ServiceImpl;

import com.youngor.wsc.common.DateFormatUtils;
import com.youngor.wsc.common.GlobalFuns;
import com.youngor.wsc.common.HttpUtils;
import com.youngor.wsc.entity.Item;
import com.youngor.wsc.repository.ItemRepository;
import com.youngor.wsc.service.ItemService;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemGetResult;
import com.youzan.open.sdk.gen.v3_0_0.model.YouzanItemSearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Auther zhoutf
 * @Date 2019/2/14 9:00
 * @Description
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    @Transactional
    public void saveItemMessage() {

       //首先删除数据库记录
        itemRepository.deleteAllInBatch();
       //获取token,七天内有效
        String token=HttpUtils.getToken().getAccessToken();
        Long count= HttpUtils.getCount(token);
        Long pageNumber=count/50+1;
        Integer sum=0;

        String startTime=DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        for(Long i=1L;i<=pageNumber;i++) {
            YouzanItemSearchResult result = HttpUtils.getItemSearchResult(i,token);
            for (YouzanItemSearchResult.ItemListOpenModel itemListOpenModel : result.getItems()
                    ) {
                YouzanItemGetResult.ItemSkuOpenModel[] skus = HttpUtils.getItemSkuOpenModel(itemListOpenModel.getItemId(),token);
                for (YouzanItemGetResult.ItemSkuOpenModel itemSkuOpenModel : skus
                        ) {
                    Item item = new Item();
                    item.setItemId(new BigDecimal(itemListOpenModel.getItemId()));
                    item.setItemNo(itemListOpenModel.getItemNo());
                    item.setItemCode(itemSkuOpenModel.getItemNo());
                    item.setId(GlobalFuns.getUUID());
                    itemRepository.save(item);
                    // System.out.println(item.toString());
                    sum++;
                }
            }
        }
        System.out.println("总记录数"+sum);
        String  endTime=DateFormatUtils.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        System.out.println("开始时间："+startTime+"  结束时间："+endTime);

    }
}
