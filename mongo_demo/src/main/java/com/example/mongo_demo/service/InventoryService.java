package com.example.mongo_demo.service;

import com.example.mongo_demo.persistent.InventoryDao;
import com.example.mongo_demo.persistent.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *
 */
@Service
public class InventoryService {

    @Autowired
    InventoryDao inventoryDao;

    public List<Inventory> getEntityList() {
        return inventoryDao.getEntityList();
    }

    public Inventory insert(Inventory myEntity) {
        return inventoryDao.insert(myEntity);
    }

    public long getCount() {
        return inventoryDao.count();
    }

    public Inventory selectOne(String id) {
        return inventoryDao.selectOne(id);
    }

    public List<Inventory> findSome(String itemName) {
        return inventoryDao.findSome(itemName);
    }

    public Inventory deleteById(String id) {
        return inventoryDao.deleteById(id);
    }

    public long updateById(String id, String status) {
        return inventoryDao.updateById(id, status);
    }

    public void createIndex(String filedName) {
        inventoryDao.createIndex(filedName);
    }

    public List<String> getAllIndex() {
        return inventoryDao.getAllIndex();
    }

    public List<Inventory> selectList(String itemName, int pageIndex, int pageSize) {
        return inventoryDao.selectList(itemName, pageIndex, pageSize);
    }
}
