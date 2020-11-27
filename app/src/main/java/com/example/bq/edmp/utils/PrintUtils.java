package com.example.bq.edmp.utils;

import android.bluetooth.BluetoothSocket;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class PrintUtils {
    //定义编码方式
    private static String encoding = null;
    // 通过socket流进行读写
    private OutputStream mOutputStream = null;
    private OutputStreamWriter writer = null;
    public final static int WIDTH_PIXEL = 384;
    public final static int IMAGE_SIZE = 320;

    public PrintUtils(OutputStream outputStream, String encoding) throws IOException {
        this.encoding = encoding;
        mOutputStream = outputStream;
        writer = new OutputStreamWriter(outputStream, encoding);
        initPrinter();
    }

    public void print(byte[] bs) throws IOException {
        mOutputStream.write(bs);
    }

    public void printBitmap(Bitmap bmp) throws IOException {
        bmp = compressPic(bmp);
        byte[] bmpByteArray = draw2PxPoint(bmp);
        printRawBytes(bmpByteArray);
    }

    public void printRawBytes(byte[] bytes) throws IOException {
        mOutputStream.write(bytes);
        mOutputStream.flush();
    }

    //初始化打印机
    public void initPrinter() throws IOException {
        writer.write(0x1b);
        writer.write(0x40);
        writer.flush();
    }

    //打印文本
    public void printText(String text) throws IOException {
        writer.write(text);
        writer.flush();
    }

    /**
     * 设置文本对齐方式
     * align 打印位置： 0：居左（默认） 1：居中 2：居右
     */

    public void printAlignment(int align) throws IOException {
        writer.write(0x1B);
        writer.write(0x61);
        writer.write(align);
        writer.flush();
    }

    //换行
    public void printLine() throws IOException {
        writer.write("\n");
        writer.flush();
    }

    public void printDashLine() throws IOException {
        printText("------------------------------------------------");
    }

    //制表符
    public void printTab(int length) throws IOException {
        for (int i = 0; i < length; i++) {
            writer.write("\t");
        }
        writer.flush();
    }

    //设置行间距 gap:表示行间距为多少个像素点，最大值256
    public void setLineGap(int gap) throws IOException {
        writer.write(0x1B);
        writer.write(0x33);
        writer.write(gap);
        writer.flush();
    }

    /**
     * 灰度图片黑白化，黑色是1，白色是0
     *
     * @param x   横坐标
     * @param y   纵坐标
     * @param bit 位图
     * @return
     */
    public static byte px2Byte(int x, int y, Bitmap bit) {
        if (x < bit.getWidth() && y < bit.getHeight()) {
            byte b;
            int pixel = bit.getPixel(x, y);
            int red = (pixel & 0x00ff0000) >> 16; // 取高两位
            int green = (pixel & 0x0000ff00) >> 8; // 取中两位
            int blue = pixel & 0x000000ff; // 取低两位
            int gray = RGB2Gray(red, green, blue);
            if (gray < 128) {
                b = 1;
            } else {
                b = 0;
            }
            return b;
        }
        return 0;
    }

    /**
     * 图片灰度的转化
     */
    private static int RGB2Gray(int r, int g, int b) {
        int gray = (int) (0.29900 * r + 0.58700 * g + 0.11400 * b);  //灰度转化公式
        return gray;
    }
    /**
     * 把一张Bitmap图片转化为打印机可以打印的字节流
     *
     * @param bmp
     * @return
     */
    public static byte[] draw2PxPoint(Bitmap bmp) {
        //用来存储转换后的 bitmap 数据。为什么要再加1000，这是为了应对当图片高度无法
        //整除24时的情况。比如bitmap 分辨率为 240 * 250，占用 7500 byte，
        //但是实际上要存储11行数据，每一行需要 24 * 240 / 8 =720byte 的空间。再加上一些指令存储的开销，
        //所以多申请 1000byte 的空间是稳妥的，不然运行时会抛出数组访问越界的异常。
        int size = bmp.getWidth() * bmp.getHeight() / 8 + 1000;
        byte[] data = new byte[size];
        int k = 0;
        //设置行距为0的指令
        data[k++] = 0x1B;
        data[k++] = 0x33;
        data[k++] = 0x00;
        // 逐行打印
        for (int j = 0; j < bmp.getHeight() / 24f; j++) {
            //打印图片的指令
            data[k++] = 0x1B;
            data[k++] = 0x2A;
            data[k++] = 33;
            data[k++] = (byte) (bmp.getWidth() % 256); //nL
            data[k++] = (byte) (bmp.getWidth() / 256); //nH
            //对于每一行，逐列打印
            for (int i = 0; i < bmp.getWidth(); i++) {
                //每一列24个像素点，分为3个字节存储
                for (int m = 0; m < 3; m++) {
                    //每个字节表示8个像素点，0表示白色，1表示黑色
                    for (int n = 0; n < 8; n++) {
                        byte b = px2Byte(i, j * 24 + m * 8 + n, bmp);
                        data[k] += data[k] + b;
                    }
                    k++;
                }
            }
            data[k++] = 10;//换行
        }
        return data;

    }

    public Bitmap convertGreyImgByFloyd(Bitmap img) {
        int width = img.getWidth();
        //获取位图的宽
        int height = img.getHeight();
        //获取位图的高 \
        int[] pixels = new int[width * height];
        //通过位图的大小创建像素点数组
        img.getPixels(pixels, 0, width, 0, 0, width, height);
        int[] gray = new int[height * width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int grey = pixels[width * i + j];
                int red = ((grey & 0x00FF0000) >> 16);
                gray[width * i + j] = red;
            }
        }
        int e = 0;
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int g = gray[width * i + j];
                if (g >= 128) {
                    pixels[width * i + j] = 0xffffffff;
                    e = g - 255;
                } else {
                    pixels[width * i + j] = 0xff000000;
                    e = g - 0;
                }
                if (j < width - 1 && i < height - 1) {
                    //右边像素处理
                    gray[width * i + j + 1] += 3 * e / 8;
                    //下
                    gray[width * (i + 1) + j] += 3 * e / 8;
                    //右下
                    gray[width * (i + 1) + j + 1] += e / 4;
                } else if (j == width - 1 && i < height - 1) {
                    //靠右或靠下边的像素的情况
                    //下方像素处理
                    gray[width * (i + 1) + j] += 3 * e / 8;
                } else if (j < width - 1 && i == height - 1) {
                    //右边像素处理
                    gray[width * (i) + j + 1] += e / 4;
                }
            }
        }
        Bitmap mBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        mBitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return mBitmap;
    }
    /**

     对图片进行压缩（去除透明度）
     @param bitmapOrg
     */
    private Bitmap compressPic(Bitmap bitmapOrg) {
// 获取这个图片的宽和高
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
// 定义预转换成的图片的宽度和高度
        int newWidth = IMAGE_SIZE;
        int newHeight = IMAGE_SIZE;
        Bitmap targetBmp = Bitmap.createBitmap(newWidth, newHeight, Bitmap.Config.ARGB_8888);
        Canvas targetCanvas = new Canvas(targetBmp);
        targetCanvas.drawColor(0xffffffff);
        targetCanvas.drawBitmap(bitmapOrg, new Rect(0, 0, width, height), new Rect(0, 0, newWidth, newHeight), null);
        return targetBmp;
    }

    public static void printTest(BluetoothSocket bluetoothSocket, Bitmap bitmap) {
        try {
            PrintUtils pUtil = new PrintUtils(bluetoothSocket.getOutputStream(), "GBK");
            // 店铺名 居中 放大
            pUtil.printAlignment(1);
            pUtil.printText("广州德胜");
            pUtil.printLine();
            pUtil.printAlignment(0);
            pUtil.printLine();

//            pUtil.printText("订单号:123456");
//            pUtil.printLine();

            pUtil.printText("付款人:白泉");
            pUtil.printLine();
            pUtil.printDashLine();
            //打印图片
            pUtil.printAlignment(0);
            pUtil.printBitmap(bitmap);
            pUtil.printLine();

            pUtil.printText("订单金额:99.99        ");
            pUtil.printAlignment(1);
            pUtil.printText("北京烤鸭        ");
            pUtil.printText("数量*1");
            pUtil.printLine();
            // 分隔线
            pUtil.printDashLine();
            pUtil.printLine();
            pUtil.printText("订单金额:99.99");
            pUtil.printLine();
            pUtil.printDashLine();

//            pUtil.printLine(4);

        } catch (IOException e) {

        }
    }
}
