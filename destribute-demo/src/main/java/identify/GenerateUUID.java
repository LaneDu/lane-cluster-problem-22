package identify;

import java.util.UUID;

/**
 * @author lane
 * @date 2021年05月13日 下午5:50
 */
public class GenerateUUID {

    public static void main(String[] args) {

        String uuid = UUID.randomUUID().toString();
        for (int i = 0; i <10 ; i++) {
            uuid = UUID.randomUUID().toString();
            System.out.println("UUID为："+uuid);
        }

    }

}
