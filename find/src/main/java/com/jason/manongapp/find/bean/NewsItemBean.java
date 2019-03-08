package com.jason.manongapp.find.bean;

import java.io.Serializable;
import java.util.List;

public class NewsItemBean implements Serializable {


    /**
     * body : <div class="main-wrap content-wrap">
     <div class="headline">

     <div class="img-place-holder"></div>



     </div>

     <div class="content-inner">




     <div class="question">
     <h2 class="question-title"></h2>

     <div class="answer">

     <div class="meta">
     <img class="avatar" src="http://pic1.zhimg.com/da8e974dc_is.jpg">
     <span class="author">知乎用户，</span><span class="bio">一句话描述不来</span>
     </div>

     <div class="content">
     <p>主要是一种工作经验吧，我来简单跟你讲一下。</p>
     <p>首先一部影片在放映前 1 天到 7 天左右就会拷入服务器，但并不可以播放，需要 KDM（由服务器序号和数字证书作为依据生成）来进行解密才可以播放，KDM 限制了 CPL（也就是播放内容，预告片，广告，正片，测试，策略等）的生效日期、时间段和影厅，大部分 KDM 由中影和华影放出，早的会在两三天前，晚的可能会在几小时前。</p>
     <p>其次，影片播放需要建立播放列表，列表内包含放映机通道，音频解码器通道，开灯时间关灯时间，4D 还包括了动作包等一系列自动化指令，在播放时会预先加载播放列表。</p>
     <p>最后还有 TMS 系统生成广告内容包加入播放列表，核心排期不存在广告时间，这两套系统之间的差异，例如排片人员排一部片 0：05 放映，TMS 里加入 3 分钟广告的话就是 0：02 开始整个播放列表的播放，保证正片播放时间与核心系统对应，当然一般首映片不太会加广告。</p>
     <p>以上大致基本条件已经了解了，开始介绍为什么 0：05 首映，第一点是虽然有 NTP 服务器做时间校准，但依然有可能存在设备间时间不同步的情况导致播放出现问题，第二点是部分服务器（例如 dorime）提前 3 分钟加载播放列表，整个列表能不能播出依据加载时 KDM 生效情况，这样如果排在 0：00 的话就是 23：57 加载列表，密钥未生效会导致正片播放失败，前几天熊出没的点映就有部分影院犯了这个错。第三点是需要留出一些时间进行手动播放简单测试一下顺便寻找出演职员表开场灯的时间。</p>
     <p>综上所述，0：05 播放是为了更好的保证放映，避免出现放映事故。并且，很多时候我们首映就算有开场灯的准确时间了也不会加入，至少在我们院线我们区域，将整场电影包括演职员表播完再开场灯算是对影片演职员的一种感谢和致敬。</p>
     </div>
     </div>


     <div class="view-more"><a href="http://www.zhihu.com/question/307939917">查看知乎讨论<span class="js-question-holder"></span></a></div>

     </div>


     </div>
     </div><script type=“text/javascript”>window.daily=true</script>
     * image_source : 《罗马》
     * title : 为什么很多电影的首映，都安排在 0:05 开场？
     * image : https://pic4.zhimg.com/v2-6762f6f4c186546322eee9fe1c10bcff.jpg
     * share_url : http://daily.zhihu.com/story/9707254
     * js : []
     * ga_prefix : 021410
     * images : ["https://pic3.zhimg.com/v2-e22cf542f56865d8914ec6aa7230116a.jpg"]
     * type : 0
     * id : 9707254
     * css : ["http://news-at.zhihu.com/css/news_qa.auto.css?v=4b3e3"]
     */

    private String body;
    private String image_source;
    private String title;
    private String image;
    private String share_url;
    private String ga_prefix;
    private int type;
    private int id;
    private List<?> js;
    private List<String> images;
    private List<String> css;

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImage_source() {
        return image_source;
    }

    public void setImage_source(String image_source) {
        this.image_source = image_source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getShare_url() {
        return share_url;
    }

    public void setShare_url(String share_url) {
        this.share_url = share_url;
    }

    public String getGa_prefix() {
        return ga_prefix;
    }

    public void setGa_prefix(String ga_prefix) {
        this.ga_prefix = ga_prefix;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<?> getJs() {
        return js;
    }

    public void setJs(List<?> js) {
        this.js = js;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<String> getCss() {
        return css;
    }

    public void setCss(List<String> css) {
        this.css = css;
    }

    @Override
    public String toString() {
        return "NewsItemBean{" +
                "body='" + body + '\'' +
                ", image_source='" + image_source + '\'' +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", share_url='" + share_url + '\'' +
                ", ga_prefix='" + ga_prefix + '\'' +
                ", type=" + type +
                ", id=" + id +
                ", js=" + js +
                ", images=" + images +
                ", css=" + css +
                '}';
    }
}
