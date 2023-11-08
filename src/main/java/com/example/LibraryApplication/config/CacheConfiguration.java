package com.example.LibraryApplication.config;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Hanan Al-Hajri
 * @created 2023/11/08
 **/

@Configuration
public class CacheConfiguration {

    @Bean
    CacheManagerCustomizer<ConcurrentMapCacheManager> customizer() {
        return new ConcurrentCustomizer();
    }
    static class ConcurrentCustomizer implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

        @Override
        public void customize(ConcurrentMapCacheManager cacheManager) {
            //null values are allowed by default, to prevent this customize it as follows
            cacheManager.setAllowNullValues(false);

            //how's the values are stored, it is by default false so no serializable are required.
            //to set it to true, IF YOU UNCOMMENT -> you have to make your entities implements serializable
//            cacheManager.setStoreByValue(true);

            System.out.println("customizer is invoked.");
        }
    }
}
