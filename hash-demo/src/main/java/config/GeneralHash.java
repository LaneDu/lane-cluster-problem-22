package config;

/**
 * 普通哈希
 * @author lane
 * @date 2021年05月12日 下午7:52
 */
public class GeneralHash {

    public static void main(String[] args) {
        //定义客户端
       String[] clients = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};
        //定义服务器的数量0，1，2
        int serverCount = 5;
        //根据ip的hash对服务器数量取模获取其index为对应的服务器
        for (String ip:clients) {
            //取hash值
            int i = ip.hashCode();
            //取绝对值
            int abs = Math.abs(i);
            //对应的服务器
            int index = abs % serverCount;

            System.out.println("客户端IP为："+ip+"路由的服务器是："+index);

        }






    }






}
