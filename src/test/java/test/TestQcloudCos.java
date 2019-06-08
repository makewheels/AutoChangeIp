package test;

import util.QcloudCosUtil;

import java.io.File;

public class TestQcloudCos {
    public static void main(String[] args) {
        File file = new File("D:\\workSpace\\sts\\QcloudCOS\\.classpath");
        QcloudCosUtil.saveToQcloud(file,"/subscription/wR2ez5pc4ISN4BIJqczWhvSMIfngJUACHr");
    }
}
