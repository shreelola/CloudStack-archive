package com.cloud.utils.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import junit.framework.TestCase;

import com.cloud.utils.component.ComponentLocator;
import com.cloud.utils.exception.CloudRuntimeException;


public class QueryBuilderTest extends TestCase {
    @Entity
    @Table(name="test")
    public static class TestVO {
        @Id
        @GeneratedValue(strategy=GenerationType.IDENTITY)
        long id;
        
        @Column(name="int")
        int fieldInt;
        
        @Column(name="long")
        Long fieldLong;
        
        @Column(name="string")
        String fieldString;
        
        public String getFieldString() {
            return fieldString;
        }
        
        public int getFieldInt() {
            return fieldInt;
        }
        
        public long getFieldLong() {
            return fieldLong;
        }
        
        public TestVO() {
        }
    }
    
    public static class TestDao extends GenericDaoBase<TestVO, Long> implements GenericDao<TestVO, Long> {
        protected TestDao() {
        }
    }
    
    public void setup() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Transaction.getStandaloneConnection();
            
            pstmt = conn.prepareStatement("CREATE TABLE `cloud`.`test` (" +
                    "`id` bigint unsigned NOT NULL UNIQUE AUTO_INCREMENT," +
                    "`int` int unsigned," +
                    "`long` bigint unsigned," +
                    "`string` varchar(255)," +
                    "PRIMARY KEY (`id`)" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8;");
            
            pstmt.execute();
            
        } catch (SQLException e) {
            throw new CloudRuntimeException("Problem with sql", e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    public void teardown() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = Transaction.getStandaloneConnection();
            
            pstmt = conn.prepareStatement("DROP TABLE IF EXISTS `cloud`.`test`");
            pstmt.execute();
            
        } catch (SQLException e) {
            throw new CloudRuntimeException("Problem with sql", e);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                }
            }
        }
    }
    
    public void testSimpleQueryBuilder() {
        TestDao dao = ComponentLocator.inject(TestDao.class);
        SimpleQueryBuilder<TestVO> qb = new QueryBuilder<TestVO, TestVO>(TestVO.class, TestVO.class);
        qb.selectFields(qb.entity().getFieldLong()).where().field(qb.entity().getFieldInt()).eq("abc");
        
    }

}
