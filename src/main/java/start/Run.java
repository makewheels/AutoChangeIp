package start;

import bean.CheckIpResult;
import com.alibaba.fastjson.JSON;
import util.HttpUtil;

/**
 * 全自动检查ip是否被墙，以及谷歌云换ip
 */
public class Run {

    /**
     * 获取本机公网ip
     *
     * @return
     */
    public static String getIp() {
        return HttpUtil.get("https://ifconfig.me/");
    }

    /**
     * 检查ip是否被墙
     *
     * @param ip
     * @return
     */
    public static boolean checkIp(String ip) {
        String json = HttpUtil.get("https://ipcheck.need.sh/api_v2.php?ip=" + ip);
        CheckIpResult result = JSON.parseObject(json, CheckIpResult.class);
        return result.getData().getInside_gfw().getIcmp().getAlive();
    }

    /**
     * 谷歌云shell换ip
     */
    public static void changeIp() {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec("gcloud compute instances delete-access-config test --zone=us-central1-a --access-config-name \"external-nat\"");
            Thread.sleep(4000);
            runtime.exec("gcloud compute instances add-access-config test --project=indigo-altar-239914 --zone=us-central1-a");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //先拿到本机ip
        String oldIp = getIp();
        //检查是否被墙
        boolean canUse = checkIp(oldIp);
        //如果被墙了
//        if (canUse == false){
        Runtime runtime = Runtime.getRuntime();
        //换ip
        changeIp();
        //再次查询ip
        String newIp = getIp();
        //再次查询是否被墙
        canUse = checkIp(newIp);
        if (canUse == false) {
            System.out.println("your father 还是被墙");
//            }
        }
    }
}
