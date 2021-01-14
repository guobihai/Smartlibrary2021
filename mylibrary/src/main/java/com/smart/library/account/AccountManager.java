package com.smart.library.account;

import android.content.Context;
import android.content.SharedPreferences;

import com.smart.library.exception.ExceptionCode;


/**
 * 帐号管理器，存储Authorization token, userId和用户基本信息
 *
 *
 *@Author guobihai
 * @date 2019-11-29
 */
public class AccountManager {
    private static SharedPreferences sharedPreferences;

    private static volatile String USER;
    private static volatile String  INVITESHOPIDXCODE;
    private static volatile String SHOPID;
    private static volatile String TOKEN;
    private static volatile String IMGSHOPIDXCODE;
    private static volatile String LOGINFLAG;
    private static volatile String LOGINTYPE;
    private static volatile String ISNEEDLOGIN;

    private static volatile String ISLOGIN;




    private static volatile String KEY_USER = "user";
    private static volatile String  KEY_INVITESHOPIDXCODE ="inviteshopIdcode";
    private static volatile String KEY_SHOPID = "shopId";
    private static final String KEY_TOKEN = "token";
    private static final String KEY_IMGSHOPIDXCODE = "imgShopIdxCode";
    private static final String KEY_LOGINFLAG = "loginFlag";
    private static final String KEY_LOGINTYPE= "loginType";
    private static volatile String KEY_ISNEEDLOGIN = "isNeedLogin";
    private static volatile String KEY_ISLOGIN = "islogin";

    public static  boolean isNeedLogin = false;

    private AccountManager() {
    }


    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences("account", Context.MODE_PRIVATE);
        //init data
        INVITESHOPIDXCODE = loadString(KEY_INVITESHOPIDXCODE);
        SHOPID = loadString(KEY_SHOPID);
        TOKEN = loadString(KEY_TOKEN);
        IMGSHOPIDXCODE = loadString(KEY_IMGSHOPIDXCODE);
        LOGINFLAG = loadString(KEY_LOGINFLAG);
        LOGINTYPE = loadString(KEY_LOGINTYPE);
        ISLOGIN = loadString(KEY_ISLOGIN);
    }

    /**
     * 增加token的验证，需要的用到到时候就设置值
     * @param isGotoken
     */
    public static  void setIsGotoken(boolean isGotoken){
        if(!isGotoken){
            saveIsNeedLogin("true");
        }else{
            saveIsNeedLogin("");
        }
    }
    /**
     * 返回是否需要Token状态
     * @return
     */
    public static String getIsNeedLogin(){
        return ISNEEDLOGIN;
    }

    /**
     * 保存是否需要token
     * @param isneddlogin
     */
    public static void saveIsNeedLogin(String isneddlogin) {
        ISNEEDLOGIN = isneddlogin;
        saveString(KEY_ISNEEDLOGIN, isneddlogin);
    }


    /**
     *
     * @return
     */
    public static String getIsLogin(){
        return KEY_ISLOGIN;
    }

    public static void saveLogin(String state) {
        ISLOGIN = state;
        saveString(KEY_ISLOGIN, state);
    }



    /**
     * 获取Token
     *
     * @return
     */
    public static String getToken() {
        return TOKEN;
    }

    /**
     * 保存Token
     *
     * @param token
     */
    public static void saveToken(String token) {
        TOKEN = token;
        saveString(KEY_TOKEN, token);
    }


    /**
     * 保存User
     * @param user
     */
    public static void saveUser(String user){
        USER = user;
        saveString(KEY_USER,user);
    }


    /**
     * 获取USer
     * @return
     */
    public static String getUser() {
        return USER;
    }

    /**
     * 邀请码
     * @return
     */
    public static  String getInviteShopIdxCode(){
        return INVITESHOPIDXCODE;
    }


    /**
     * 邀请码
     * @param invitteShopIdxCode
     * @return
     */
    public static  void saveInviteShopIdxCode(String invitteShopIdxCode){
        USER = INVITESHOPIDXCODE;
        saveString(KEY_INVITESHOPIDXCODE,invitteShopIdxCode);
    }

    /**
     * 获取ShopID
     * @return
     */
    public static String getShopId(){
        return SHOPID;
    }


    /**
     * 保存shopId
     * @param shopId
     * @return
     */
    public static  void saveShopId(String shopId){
        SHOPID =KEY_SHOPID;
        saveString(KEY_SHOPID,shopId);
    }


    /**
     * 保存图片二维码图片
     * @return
     */
    public static  String getImgShopIdxCode(){
        return IMGSHOPIDXCODE;
    }


    /**
     * 保存店铺二维码分享图片
     * @param imageSHopIdxCode
     */
    public static  void  saveImageShopIdxCode(String imageSHopIdxCode){
        IMGSHOPIDXCODE = KEY_IMGSHOPIDXCODE;
        saveString(KEY_IMGSHOPIDXCODE,imageSHopIdxCode);
    }


    /**
     * 获取登录状态
     * @return
     */
    public static String getLoginFlag(){
        return LOGINFLAG;
    }


    /**
     * 保存登录状态
     * @param loginFlag
     */
    public static void saveLoginFlag(String loginFlag){
        LOGINFLAG = KEY_LOGINFLAG;
        saveString(KEY_LOGINFLAG,loginFlag);
    }



    /**
     * 获取登录类型
     * @return
     */
    public static String getLoginType(){
        return  LOGINTYPE;
    }


    /**
     * 保存登录状态
     * @param loginType
     */
    public static void saveLoginType(String loginType){
        LOGINTYPE = KEY_LOGINTYPE;
        saveString(KEY_LOGINFLAG,loginType);
    }

    /**
     * 保存数据
     *
     * @param data
     */
    private static void saveString(String key, String data) {
        if (key != null && data != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, data);
            editor.apply();
        }
    }



    /**
     * 加载数据
     *
     * @param key
     * @return
     */
    private static String loadString(String key) {
        return sharedPreferences.getString(key, null);
    }


    /**
     * 清空数据缓存
     */
    public static void clear() {
        USER = null;
        INVITESHOPIDXCODE = null;
        SHOPID = null;
        TOKEN = null;
        IMGSHOPIDXCODE = null;
        LOGINFLAG = null;
        LOGINTYPE = null;
        KEY_ISLOGIN = null;
        sharedPreferences.edit().clear().apply();
    }

    /**
     * token失效重新登录
     * 错误码地址 https://note.youdao.com/group/#/93805645/(folder/438562026//full:md/438747900)
     *
     * @param code
     */
    public static boolean handleInvalidToken(String code) {
        if (ExceptionCode.isInvalidToken(code)) {

//            ARouter.getInstance().build(RouterPath.Passport.ROUTE_PASSPORT_SIGNIN)
//                    .with(postcard.getExtras())
//                    .greenChannel()
//                    .navigation();

            return true;
        }
        return false;
    }

    public static boolean isNeedLogin() {
        return isNeedLogin;
    }

    public static void setNeedLogin(boolean needLogin) {
        isNeedLogin = needLogin;
    }
}
