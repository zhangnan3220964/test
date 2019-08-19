package com.zn.basemodule.util.http.retrofit;

/**
 * http响应参数实体类
 * 通过Gson解析属性名称需要与服务器返回字段对应,或者使用注解@SerializedName
 * 备注:这里与服务器约定返回格式
 */
public class HttpResponse {





    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    /**
     * code : 0
     * msg : 成功
     * data : {"plugins":[{"game_version":"1.61","plugin_desc":"自动提示|按需要提示","plugin_author":"","plugin_title":"1056","plugin_download_url":"http://biuzone.com:10002/public/uploads/plugins/5cacaf223132a.gs","remarks":[{"id":"1364","nickname":"","head_icon":"http://thirdqq.qlogo.cn/qqapp/1106384882/2E8DF2A17FCC155088B2398D16DD50C3/100","remark_content":"装头像了","remark_time":"1","remark_count":"4264"},{"id":"2180","nickname":"国力","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/cyQ3mMViaVWFWN7bz54y9Gs57UZS0ibJhicTwWTEmjj63r1tstoJP6qJhcEia1aCicPgvSwE8V7IQghYCIVD1q29ObA","remark_content":"卡了","remark_time":"5","remark_count":"2943"},{"id":"3416","nickname":"无名氏","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/hAAZTewNWmCqFLlz81lAEspSnoZyU0mCG7lyL96Fq5crbLWbuB1EVwlGJHfJHPx3PSTxdPlH07fgtPZjLhqic9g/132","remark_content":"","remark_time":"6","remark_count":"814"},{"id":"3052","nickname":"意志力","head_icon":"http://thirdqq.qlogo.cn/qqapp/1106384882/0F0A9604181841EE626254A90DAB6F82/100","remark_content":"好玩","remark_time":"17","remark_count":"1335"},{"id":"4806","nickname":"lu","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/CMMpOU86ZMoia60lvQe6Ticfz6QzBtGF7R6eMm9kmz4icK2dLZHlL4lu78xvf7o9QTwUhYkTtiayVOqeZyjMnCeoTA/","remark_content":"\\","remark_time":"29","remark_count":"3637"},{"id":"3100","nickname":"祝铭","head_icon":"http://thirdqq.qlogo.cn/qqapp/1106384882/B2234246B5CC483DD242E5DB174AA991/100","remark_content":"是机器人吗","remark_time":"35","remark_count":"2519"},{"id":"3391","nickname":"韶华未既","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/O6u7Q1EDibGhLsD4XqgpCp7NdC1JysOAiaabadD6QW5hia9vBken9ENKD7G4tsul37X5ADhWa15V5iaaGHsfe0CJdg/","remark_content":"我一捡箱子就卡住了","remark_time":"38","remark_count":"152"},{"id":"6839","nickname":"斯凯特","head_icon":"http://thirdqq.qlogo.cn/g?b=oidb&k=5zocwQYzC50TYc500VK9yg&s=100","remark_content":"6","remark_time":"39","remark_count":"2512"},{"id":"571","nickname":"y","head_icon":"http://thirdwx.qlogo.cn/mmopen/vi_32/Uw9iayibXVgxj7N1AEMtnia84PxGnGibEUTC5gPmhibYEROKRA7HvDFt6H2kNK5A3U9iarSuBjbBlpzD1UDaZT1PAsn","remark_content":"到底","remark_time":"43","remark_count":"326"},{"id":"7032","nickname":"孤傲","head_icon":"http://thirdqq.qlogo.cn/g?b=oidb&k=ibyQA5WicLkTfunmNPjCetuA&s=100","remark_content":"我能打开，但是这个点不了。","remark_time":"43","remark_count":"3096"}],"rewards":[{"nickname":"desert. 荒芜","amount":"6.66","reward_time":"1"},{"nickname":"靠自己才是真正的女王","amount":"12.88","reward_time":"3"},{"nickname":"box_nqqd1549983675732","amount":"1","reward_time":"5"},{"nickname":"box_crba1545009056997","amount":"0.01","reward_time":"10"},{"nickname":"box_ie0n1549325031680","amount":"0.01","reward_time":"16"},{"nickname":"如果可以早一点 *","amount":"12.88","reward_time":"16"},{"nickname":"box_q56s155230846898","amount":"5.28","reward_time":"18"},{"nickname":"box_3mms1543163554229","amount":"0.01","reward_time":"22"},{"nickname":"box_njns1551464582694","amount":"0.06","reward_time":"26"},{"nickname":"君生我已老@","amount":"5.21","reward_time":"58"}]}],"game_desc":""}
     * suggests : []
     */

    private int code;
    private String msg;
    private Object data;

}
