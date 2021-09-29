package cn.zyfvir.springtransactiondemo.respository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.jta.JtaTransactionManager;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

/**
 *
 */
@EnableTransactionManagement
public class DemoDao {
    @Autowired
    TransactionTemplate transactionTemplate;

    @Transactional
    public void method2() {
        // 在这里写 具体的数据库操作调用
    }

    public void method1() {
        transactionTemplate.execute(new TransactionCallback<Void>() {
            @Override
            public Void doInTransaction(TransactionStatus status) {
                // 在这里写 具体的数据库操作调用
                return null;
            }
        });
    }

    public void methodMain() {
        PlatformTransactionManager platformTransactionManager;
        TransactionDefinition transactionDefinition;
        TransactionStatus transactionStatus;

        DataSourceTransactionManager dataSourceTransactionManager;
        JpaTransactionManager jpaTransactionManager;
        JtaTransactionManager jtaTransactionManager;
        HibernateTransactionManager hibernateTransactionManager;
    }
}
