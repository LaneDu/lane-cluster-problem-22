package config;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希有虚拟节点，主要就是添加服务器节点的时候添加虚拟节点
 * @author lane
 * @date 2021年05月13日 上午11:12
 */
public class ConsisiHashWithVirtual {

    public static void main(String[] args) {

        //1. 初始化服务器节点到哈希环上
        //定义服务器
        String[] servers = new String[]{"102.12.0.11","171.127.1.22","106.162.2.33"};
        //初始化sortedMap，是一个有序到map，tail方法可以获取大于某个key的map集合
        SortedMap<Integer,String> serverMap = new TreeMap<>();
        //对应到哈希环上
        for (String server: servers) {
            //求哈希
            int serverHash = server.hashCode();
            //绝对值
            serverHash = Math.abs(serverHash);
            //存入sortedMap当中
            serverMap.put(serverHash,server);
            //给每个服务器节点添加3个虚拟节点
            for (int i = 0; i < 3; i++) {
                int virtualHash = Math.abs((server+"#"+i).hashCode());
                serverMap.put(virtualHash,"虚拟节点映射的服务器："+server);
            }
        }
        //2. 定义客户端
        String[] clients = new String[]{"10.78.12.3","113.25.63.1","126.12.3.8"};
        //3. 获取客户端的哈希，并获取对应的服务器

        for (String client:clients) {
            int clientHash = Math.abs(client.hashCode());
            //获取大于此key的客户端map
            SortedMap<Integer, String> tailMap = serverMap.tailMap(clientHash);
            //判断是否为空，若是则为服务器端第一个节点服务器
            //否则就是大于此key的map第一个节点服务器
            if (tailMap.isEmpty()){
                // 取哈希环上的顺时针第⼀台服务器
                Integer firstKey = serverMap.firstKey();
                System.out.println("客户端IP为："+client+"对应的服务器为："+serverMap.get(firstKey));

            }else{
                Integer firstKey = tailMap.firstKey();
                System.out.println("客户端IP为："+client+"对应的服务器为："+tailMap.get(firstKey));

            }

        }







    }








}
