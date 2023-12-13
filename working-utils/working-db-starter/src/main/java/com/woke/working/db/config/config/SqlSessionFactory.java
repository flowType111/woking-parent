package com.woke.working.db.config.config;

import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.toolkit.GlobalConfigUtils;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class SqlSessionFactory {

    @Bean
    public org.apache.ibatis.session.SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        MybatisSqlSessionFactoryBean mybatisSqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
        //获取mybatis-plus全局配置
        GlobalConfig globalConfig = GlobalConfigUtils.defaults();
        //mybatis-plus全局配置设置元数据对象处理器为自己实现的那个
        globalConfig.setMetaObjectHandler(new MyMetaObjectHandler());
        mybatisSqlSessionFactoryBean.setDataSource(dataSource);
        //mybatisSqlSessionFactoryBean关联设置全局配置
        mybatisSqlSessionFactoryBean.setGlobalConfig(globalConfig);
        return mybatisSqlSessionFactoryBean.getObject();
    }

}
