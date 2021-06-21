package identify;

import redis.clients.jedis.Jedis;

/**
 * @author lane
 * @date 2021年05月13日 下午7:14
 */
public class GenerateRedisId {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("81.68.211.196",6379);
    try {
        for (int i = 0; i < 10; i++) {
            Long id = jedis.incr("id");
            System.out.println("使用redis自动生成ID：" + id);
        }
    }finally {
        if (null != jedis) {
            jedis.close();
        }

    }
    }


}
