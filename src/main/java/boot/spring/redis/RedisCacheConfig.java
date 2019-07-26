package boot.spring.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisCacheConfig  extends CachingConfigurerSupport {
//    @Bean
//    public JedisConnectionFactory redisConnectionFactory() {
//        JedisConnectionFactory redisConnectionFactory = new JedisConnectionFactory();
//        redisConnectionFactory.setHostName("localhost");
//        redisConnectionFactory.setPort(6379);
//        return redisConnectionFactory;
//    }

//    @Bean
//    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory cf) {
//        RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
//        // 使用Jackson2JsonRedisSerialize 替换默认序列化
//        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer =
//                new Jackson2JsonRedisSerializer(Object.class);
//        redisTemplate.setDefaultSerializer(jackson2JsonRedisSerializer);
//        redisTemplate.setConnectionFactory(cf);
//        return redisTemplate;
//    }
@Bean
public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    return redisTemplate;

}
//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String, String> redisTemplate) {
//        RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
//        // Number of seconds before expiration. Defaults to unlimited (0)
//       //cacheManager.setDefaultExpiration(3000); // Sets the default expire time (in seconds)
//        return cacheManager;
//    }
}
