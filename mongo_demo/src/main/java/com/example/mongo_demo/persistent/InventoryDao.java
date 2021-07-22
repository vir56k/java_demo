package com.example.mongo_demo.persistent;

import com.mongodb.client.ListIndexesIterable;
import com.mongodb.client.model.IndexOptions;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.result.UpdateResult;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 *
 */
@Repository
public class InventoryDao {


    @Autowired
    private MongoTemplate mongoTemplate;

    public List<Inventory> getEntityList() {
        Query query = new Query();
        return mongoTemplate.find(query, Inventory.class);
    }


    public Inventory insert(Inventory myEntity) {
        Inventory insert = mongoTemplate.insert(myEntity);
        return insert;
    }

    public long count() {
        Query query = new Query();
        return mongoTemplate.count(query, Inventory.class);
    }

    public Inventory selectOne(String id) {
        return mongoTemplate.findById(id, Inventory.class);
    }

    public List<Inventory> findSome(Object itemName) {
        Query query = new Query();
        query.addCriteria(Criteria.where("item").is(itemName));
        return mongoTemplate.find(query, Inventory.class);
    }

    public Inventory deleteById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findAndRemove(query, Inventory.class);
    }

    public long updateById(String id, String status) {
        Query query = new Query(Criteria.where("_id").is(id));
        Update update = new Update();
        update.set("status", status);
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, Inventory.class);
        long modifiedCount = updateResult.getModifiedCount();
        return modifiedCount;
    }

    // 模糊查询
    public List<Inventory> selectList(String itemName, int pageIndex, int pageSize) {
        // 使用正则的查询
        Query query = new Query(Criteria.where("item").regex("^.*" + itemName + ".*$"))
                .skip((pageIndex - 1) * pageSize)
                .limit(pageSize);
        return mongoTemplate.find(query, Inventory.class);
    }

    public void createIndex(String filedName) {
        IndexOptions indexOptions = new IndexOptions();
        indexOptions.unique(false);//是否唯一索引
        Bson ascending = Indexes.ascending(filedName);
        mongoTemplate.getCollection("inventory").createIndex(ascending, indexOptions);
    }


    public List<String> getAllIndex2() {
        ListIndexesIterable<Document> lst = mongoTemplate.getCollection("inventory").listIndexes();
        List<String> result = StreamSupport.stream(lst.spliterator(), false).map(Document::toString).collect(Collectors.toList());
        return result;
    }

    public List<String> getAllIndex() {
        ListIndexesIterable<Document> lst = mongoTemplate.getCollection("inventory").listIndexes();
        List<String> result = new ArrayList<>();
        StreamSupport.stream(lst.spliterator(), false).forEach(e -> {
            List<String> stringStream = e.entrySet().stream().map(it -> {
                if (it.getKey().equals("name")) {
                    return it.getValue().toString();
                } else {
                    return null;
                }
            }).filter(Objects::nonNull).collect(Collectors.toList());
            result.addAll(stringStream);
        });
        return result;
    }

}
