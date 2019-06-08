package start;

import bean.checkip.CheckIpResult;
import com.alibaba.fastjson.JSON;
import util.HttpUtil;
import util.QcloudCosUtil;
import util.SsrUtil;
import util.TimeUtil;

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
        System.out.println("----------");
        System.out.println("开始：" + TimeUtil.getTime());
        System.out.println("正在获取当前ip....");
        //先拿到本机ip
        String oldIp = getIp();
        System.out.println("老ip是：" + oldIp);
        //检查是否被墙
        boolean canUse = checkIp(oldIp);
        System.out.println("已完成检测是否被墙");
        //如果还能用
        if (canUse == true) {
            System.out.println("还能用，没被墙");
            return;
        }
        System.out.println("被墙了。。开始执行换ip程序");
        //如果被墙了
        //换ip
        System.out.println("开始换ip");
        changeIp();
        System.out.println("换ip已完成");
        //再次查询ip
        String newIp = getIp();
        System.out.println("新ip是：" + newIp);
        System.out.println("再次检测是否被墙.....");
        //再次查询是否被墙
        if (checkIp(newIp) == false) {
            System.out.println("your father 还是被墙");
        }
        System.out.println("再次检测完成，没墙，换ip成功！");
        System.out.println("开始更新ssr订阅文件");
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
