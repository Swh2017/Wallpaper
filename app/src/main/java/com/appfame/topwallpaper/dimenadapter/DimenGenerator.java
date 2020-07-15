package com.appfame.topwallpaper.dimenadapter;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;


public class DimenGenerator {
    private static final int MAX_SIZE = 480;
    private static final int DESIGN_WIDTH = 480;

    public static void main(String[] args) {
        int[] arr = new int[]{300, 320, 340, 350, 360, 370, 380, 390, 400, 410, 420, 430, 440, 450, 480, 490};
        for (int swdp : arr) {
            makeAll(DESIGN_WIDTH, swdp, "./android/res/");
        }
    }

    public static float px2dip(float pxValue, int sw, int designWidth) {
        float dpValue = (pxValue / (float) designWidth) * sw;
        BigDecimal bigDecimal = new BigDecimal(dpValue);
        float finDp = bigDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).floatValue();
        return finDp;
    }

    private static String makeAllDimens(int swdp, int designWidth) {
        float dpValue;
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>\r\n");
            sb.append("<resources>\r\n");
            sb.append(String.format("<dimen name=\"base_dpi\">%ddp</dimen>\r\n", swdp));
            for (int i = 0; i <= MAX_SIZE; i++) {
                dpValue = px2dip((float) i, swdp, designWidth);
                sb.append(String.format("<dimen name=\"qb_px_%1$d\">%2$.2fdp</dimen>\r\n", i, dpValue));
            }
            sb.append("</resources>\r\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void makeAll(int designWidth, int swdp, String buildDir) {
        try {
            if (swdp < 0) {
                return;
            }
            String folderName = "values-sw" + swdp + "dp";
            File file = new File(buildDir + File.separator + folderName);
            if (!file.exists()) {
                file.mkdirs();
            }

            FileOutputStream fos = new FileOutputStream(file.getAbsolutePath() + "/dimens.xml");
            fos.write(makeAllDimens(swdp, designWidth).getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
