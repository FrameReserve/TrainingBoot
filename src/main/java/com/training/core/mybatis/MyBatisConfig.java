package com.training.core.mybatis;

import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Map;
import java.util.Properties;
import java.util.HashMap;
/**
 * Created by Athos on 2016-10-13.
 */
@Configuration
public class MyBatisConfig{

    @Value("${spring.datasource.readSize}")
    private String dataSourceSize;

    @Resource(name = "writeDataSource")
    private DataSource writeDataSource;

    @Resource(name = "readDataSource")
    private DataSource readDataSource;

    //直接点属性,说我没初始化,加个get方法就可以,这是在逗我
    private DataSource getWriteDataSource(){
        return writeDataSource;
    }

    private DataSource getReadDataSource(){
        return readDataSource;
    }

    /**
     * AbstractRoutingDataSource 这破玩意 继承了AbstractDataSource ,AbstractDataSource又实现了DataSource
     * 所以可以直接丢去构建 SqlSessionFactory
     * @return
     */
    @Bean
    public AbstractRoutingDataSource dataSourceProxy(){
        int size = Integer.parseInt(dataSourceSize);
        MyRoutingDataSource proxy = new MyRoutingDataSource(size);
        Map<Object,Object> dataSourceMap = new HashMap<>();
        DataSource writeSource = getWriteDataSource();
        DataSource readSource = getReadDataSource();

        dataSourceMap.put(TargetDataSource.WRITE.getCode(),writeSource);
        dataSourceMap.put(TargetDataSource.READ.getCode(),readSource);

        proxy.setDefaultTargetDataSource(writeDataSource);
        proxy.setTargetDataSources(dataSourceMap);
        return proxy;
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactorys() {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy());
        bean.setTypeAliasesPackage("com.training");

        //分页插件
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});

        //添加XML目录
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath:**/mapper/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
