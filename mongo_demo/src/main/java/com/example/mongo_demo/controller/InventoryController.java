package com.example.mongo_demo.controller;

import com.example.mongo_demo.persistent.Inventory;
import com.example.mongo_demo.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 */
@RestController
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/get")
    public List<Inventory> demo() {
        return inventoryService.getEntityList();
    }

    @GetMapping("/selectOne")
    public Inventory selectOne(String id) {
        return inventoryService.selectOne(id);
    }

    /**
     * 分页
     *
     * @return
     */
    @GetMapping("/selectList")
    public List<Inventory> selectList() {
        String itemName = "石头";
        int pageIndex = 2;
        int pageSize = 3;
        return inventoryService.selectList(itemName, pageIndex, pageSize);
    }

    @GetMapping("/insert")
    public String insert() {
        Inventory inventory = new Inventory();
        inventory.setItem("石头#1");
        inventory.setStatus("Z");
        long count = inventoryService.getCount();
        Inventory insertResult = inventoryService.insert(inventory);
        long countAfter = inventoryService.getCount();

        return String.format("之前是%s, 之后后%s, obj = %s", count, countAfter, insertResult);
    }

    @GetMapping("/findSome")
    public List<Inventory> findSome() {
        String itemName = "石头#1";
        return inventoryService.findSome(itemName);
    }

    @GetMapping("/deleteById")
    public Inventory deleteById() {
        String id = "60f856ce1ca7b050701589f4";
        return inventoryService.deleteById(id);
    }

    @GetMapping("/updateById")
    public String updateById() {
        String id = "60f856d61ca7b050701589f5";
        String status = "未知";
        long modifiedCount = inventoryService.updateById(id, status);

        Inventory resut = selectOne(id);
        return String.format("modifiedCount = %s,  obj = %s", modifiedCount, resut);

    }

    // 创建索引
    @GetMapping("/createIndex")
    public List<String> createIndex() {
        String filedName = "item";//字段名
        inventoryService.createIndex(filedName);
        List<String> allIndex = inventoryService.getAllIndex();
        return allIndex;
    }
}
