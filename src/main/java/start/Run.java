package start;

import bean.checkip.CheckIpResult;
import com.alibaba.fastjson.JSON;
import util.HttpUtil;
import util.QcloudCosUtil;
import util.SsrUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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
        if (canUse == false) {
            //换ip
            changeIp();
            //再次查询ip
            String newIp = getIp();
            //再次查询是否被墙
            if (checkIp(newIp) == false) {
                System.out.println("your father 还是被墙");
            }
            //更新ssr订阅链接
            String ssrLink = SsrUtil.getSsrLink(newIp, "12000", "1wga0gar564gw00w4e",
                    "台湾", "谷歌云");
            //写到本地文件
            File file = new File("ssrsub");
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(ssrLink.getBytes());
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //上传到腾讯云对象存储
            QcloudCosUtil.saveToQcloud(file, "/subscription/wR2ez5pc4ISN4BIJqczWhvSMIfngJUACHr");
        }
    }
}
