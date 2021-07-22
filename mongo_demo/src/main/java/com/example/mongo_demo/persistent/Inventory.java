package com.example.mongo_demo.persistent;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * 存货
 */
@Data
@Document(collection = "inventory")
public class Inventory implements Serializable {
    @Id
    String _id;
    String item;
    String status;
    String size;//" : { "h" : 14, "w" : 21, "uom" : "cm" }, "instock" : [ { "warehouse" : "A", "qty" : 5 } ] }
    List<String> instock;

}
