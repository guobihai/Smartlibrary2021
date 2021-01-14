package com.smart.library.utils.usb;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.usb.UsbDevice;
import android.hardware.usb.UsbManager;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import com.github.mjdev.libaums.UsbMassStorageDevice;
import com.github.mjdev.libaums.fs.FileSystem;
import com.github.mjdev.libaums.fs.UsbFile;
import com.github.mjdev.libaums.fs.UsbFileInputStream;
import com.github.mjdev.libaums.partition.Partition;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author guobihai
 * 创建日期：2020/12/11
 * desc：U盘文件拷贝
 */
public class UsbOtgMananger {
    private static UsbOtgMananger minstance;
    private Context mContext;
    private static final String ACTION_USB_PERMISSION = "com.android.example.USB_PERMISSION";
    private UsbMassStorageDevice[] storageDevices;
    private UsbFile cFolder;

    public UsbOtgMananger() {
    }

    public synchronized static UsbOtgMananger getInstance() {
        if (minstance == null) {
            minstance = new UsbOtgMananger();
        }
        return minstance;
    }


    public void setConctext(Context context) {
        this.mContext = context;
    }

    public void registerUDiskReceiver() {
        //监听otg插入 拔出
        IntentFilter usbDeviceStateFilter = new IntentFilter();
        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_ATTACHED);
        usbDeviceStateFilter.addAction(UsbManager.ACTION_USB_DEVICE_DETACHED);
        //注册监听自定义广播
        usbDeviceStateFilter.addAction(ACTION_USB_PERMISSION);
        mContext.registerReceiver(mOtgReceiver, usbDeviceStateFilter);
        //注册监听自定义广播
    }

    public void unRegisterUDiskReceiver() {
        mContext.unregisterReceiver(mOtgReceiver);
    }

    private BroadcastReceiver mOtgReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action) {
                case ACTION_USB_PERMISSION://接受到自定义广播
                    UsbDevice usbDevice = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    //允许权限申请
                    if (intent.getBooleanExtra(UsbManager.EXTRA_PERMISSION_GRANTED, false)) {
                        if (usbDevice != null) {
                            //用户已授权，可以进行读取操作
                            readDevice(getUsbMass(usbDevice));
                        } else {
                            //    showToastMsg("没有插入U盘");
                        }
                    } else {
                        // showToastMsg("未获取到U盘权限");
                    }
                    break;
                case UsbManager.ACTION_USB_DEVICE_ATTACHED://接收到U盘设备插入广播
                    UsbDevice device_add = intent.getParcelableExtra(UsbManager.EXTRA_DEVICE);
                    if (device_add != null) {
                        //接收到U盘插入广播，尝试读取U盘设备数据
                        redUDiskDevsList();
                    }
                    break;
                case UsbManager.ACTION_USB_DEVICE_DETACHED://接收到U盘设设备拔出广播
                    Log.i("songkunjian", "ACTION===拉出U盘");
                    //  showToastMsg("U盘已拔出");
                    break;
            }
        }
    };

    /**
     * @description U盘设备读取
     * @author ldm
     * @time 2017/9/1 17:20
     */
    private void redUDiskDevsList() {
        //设备管理器
        UsbManager usbManager = (UsbManager) mContext.getSystemService(Context.USB_SERVICE);
        //获取U盘存储设备
        storageDevices = UsbMassStorageDevice.getMassStorageDevices(mContext);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, new Intent(ACTION_USB_PERMISSION), 0);
        //一般手机只有1个OTG插口
        for (UsbMassStorageDevice device : storageDevices) {
            //读取设备是否有权限
            if (usbManager.hasPermission(device.getUsbDevice())) {
                readDevice(device);//读取USB信息
            } else {
                //没有权限，进行申请
                //   Log.i("songkunjian","READDEVICE===:"+ grantAutomaticPermission(device.getUsbDevice()));
                usbManager.requestPermission(device.getUsbDevice(), pendingIntent);
            }
        }
        if (storageDevices.length == 0) {
            //  showToastMsg("请插入可用的U盘");
        }
    }

    private void readDevice(UsbMassStorageDevice device) {
        try {
            device.init();//初始化
            //设备分区
            Partition partition = device.getPartitions().get(0);
            //文件系统
            FileSystem currentFs = partition.getFileSystem();
            currentFs.getVolumeLabel();//可以获取到设备的标识
            Log.i("songkunjian", "读取U盘信息====:" + currentFs.getCapacity() + "  "
                    + currentFs.getOccupiedSpace() + " " + currentFs.getFreeSpace() + " " + currentFs.getChunkSize());
            Log.i("songkunjian", "发送结束界面===:");
//            EventBus.getDefault().post(new MessageEvent(MyDataType.U_DRIVE));
            //通过FileSystem可以获取当前U盘的一些存储信息，包括剩余空间大小，容量等等
            cFolder = currentFs.getRootDirectory();//设置当前文件对象为根目录
            if (cFolder == null) return;
//
            // EventBus.getDefault().post(new MessageEvent(MyDataType.COPYSTRAST));
            new Thread() {
                @Override
                public void run() {
                    super.run();
                    try {
                        Thread.sleep(1000);
                        CopyFile(cFolder);//开始删除手机某个原来名字的文件夹（自己定义）
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
            //
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void CopyFile(UsbFile usbFile) {
        File file = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DianQiu");
        if (file.exists()) {
//            EventBus.getDefault().post(new MessageEvent(MyDataType.FILEDETEING));//开始删除原文件
            deleteFile(file);
        }
        file.mkdir();
        // file.getAbsolutePath();
        UsbFile[] usbFiles = new UsbFile[0];
        try {
            usbFiles = usbFile.listFiles();
            for (UsbFile usbfile : usbFiles) {
                if (usbfile.getName().equals("DianQiu")) {
                    prossgerzise = 0;
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Thread.sleep(1000);
//                                EventBus.getDefault().post(new MessageEvent(MyDataType.FILEDETEED));//完成删除原文件
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }).start();
//                    EventBus.getDefault().post(new MessageEvent(MyDataType.COPYSTRAST, getUsbFileLength(usbfile)));
                    copy(usbfile, file.getAbsolutePath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //删除源文件
    private void deleteFile(File file) {

        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                File f = files[i];
                deleteFile(f);
            }
            file.delete();//如要保留文件夹，只删除文件，请注释这行
        } else if (file.exists()) {
            // Log.i("songkunjian","文件大小::"+file.length());
            file.delete();
        }
    }

    /**
     * 复制文件
     *
     * @param fromFile 要复制的文件目录
     * @param toFile   要粘贴的文件目录
     * @return 是否复制成功
     */
    private static int mIdent = 0;

    public static boolean copy(UsbFile fromFile, String toFile) {
        if (fromFile == null) return false;
        //要复制的文件目录
        UsbFile[] currentFiles = new UsbFile[0];
        //如果存在则获取当前目录下的全部文件 填充数组
        try {
            currentFiles = fromFile.listFiles();
            mIdent = currentFiles.length;
        } catch (IOException e) {
            e.printStackTrace();
        }
        //目标目录
        File targetDir = new File(toFile);
        //创建目录
        if (targetDir.exists()) {
            targetDir.delete();
        }
        targetDir.mkdirs();
        //遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
            {
                copy(fromFile, toFile + currentFiles[i].getName() + "/");
            } else//如果当前项为文件则进行文件拷贝
            {
                CopySdcardFile(currentFiles[i], toFile + "/" + currentFiles[i].getName());
            }
        }
        return true;
    }

    //文件拷贝
    //要复制的目录下的所有非子目录(文件夹)文件拷贝
    public static boolean CopySdcardFile(UsbFile cFolder, String toFile) {
//        EventBus.getDefault().post(new MessageEvent(MyDataType.COPYING, cFolder.getLength()));//正在拷贝文件
        try {
            InputStream inusb = new UsbFileInputStream(cFolder);
            OutputStream fosto = new FileOutputStream(toFile);
            byte bt[] = new byte[1024 * 10];
            int c;
            while ((c = inusb.read(bt)) != -1) {
                fosto.write(bt, 0, c);
            }
            mIdent--;
            Log.i(" songkunjian", " 完成拷贝==:" + mIdent);
            if (mIdent <= 0) {
                Log.i(" songkunjian", " 最终完成拷贝==:");
//                EventBus.getDefault().post(new MessageEvent(MyDataType.COPYSTOP));
            }
            fosto.flush();
            inusb.close();
            fosto.close();
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    //获取USB文件夹的大小 以便显示拷贝进度条
    private long prossgerzise = 0;

    public long getUsbFileLength(UsbFile fromFile) {
        if (fromFile == null) return 0;
        //要复制的文件目录
        UsbFile[] currentFiles = new UsbFile[0];
        //如果存在则获取当前目录下的全部文件 填充数组
        try {
            currentFiles = fromFile.listFiles();

        } catch (IOException e) {
            e.printStackTrace();
        }
        //遍历要复制该目录下的全部文件
        for (int i = 0; i < currentFiles.length; i++) {
            if (currentFiles[i].isDirectory())//如果当前项为子目录 进行递归
            {
                getUsbFileLength(currentFiles[i]);
            } else//如果当前项为文件则进行文件拷贝
            {
                prossgerzise += currentFiles[i].getLength();
            }
        }
        return prossgerzise;
    }

    private UsbMassStorageDevice getUsbMass(UsbDevice usbDevice) {
        for (UsbMassStorageDevice device : storageDevices) {
            if (usbDevice.equals(device.getUsbDevice())) {
                return device;
            }
        }
        return null;
    }
}
