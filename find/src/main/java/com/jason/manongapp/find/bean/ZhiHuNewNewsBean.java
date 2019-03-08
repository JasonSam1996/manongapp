package com.jason.manongapp.find.bean;

import java.util.List;

public class ZhiHuNewNewsBean {


    /**
     * date : 20190213
     * stories : [{"images":["https://pic1.zhimg.com/v2-692db9627bffafdd578eb55f2b965274.jpg"],"type":0,"id":9707366,"ga_prefix":"021310","title":"中国菜这么油腻，可胖人还是美国多"},{"images":["https://pic1.zhimg.com/v2-2fb1833cfefd416f14482e19d10bc970.jpg"],"type":0,"id":9707406,"ga_prefix":"021309","title":"我要倾尽全宇宙之水，浇灭太阳"},{"title":"从「修仙」到「三和大神」：日益泛滥的模拟器游戏们","ga_prefix":"021308","images":["https://pic3.zhimg.com/v2-a60ca06125d846f94880ce50ff2f055a.jpg"],"multipic":true,"type":0,"id":9707398},{"title":"通过在线地图软件，我看到很多有意思的东西","ga_prefix":"021307","images":["https://pic2.zhimg.com/v2-10a5ee031fbf058511765bb4206795c1.jpg"],"multipic":true,"type":0,"id":9707423},{"images":["https://pic4.zhimg.com/v2-3ab91a11edddefdff6b46b615ab1d127.jpg"],"type":0,"id":9707431,"ga_prefix":"021307","title":"《流浪地球》背后的资本科幻"},{"images":["https://pic2.zhimg.com/v2-28bf46f72d0942a93dca29e4eed69ec9.jpg"],"type":0,"id":9707391,"ga_prefix":"021306","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"https://pic4.zhimg.com/v2-4e93aeeb7ec8064e335c76d466602e63.jpg","type":0,"id":9707423,"ga_prefix":"021307","title":"通过在线地图软件，我看到很多有意思的东西"},{"image":"https://pic1.zhimg.com/v2-b563fa87924bf28a3cdcb1f9af7b87a8.jpg","type":0,"id":9707431,"ga_prefix":"021307","title":"《流浪地球》背后的资本科幻"},{"image":"https://pic1.zhimg.com/v2-cbf6dba2ef00bad78cf5672214afbc7c.jpg","type":0,"id":9707360,"ga_prefix":"021220","title":"《飞驰人生》的生猛与纯粹，成了这个贺岁档的异类"},{"image":"https://pic2.zhimg.com/v2-566d3aa5a5deabb85eb12f26de859d8d.jpg","type":0,"id":9707261,"ga_prefix":"021210","title":"古龙小说里有哪些把你笑出腹肌的桥段？"},{"image":"https://pic4.zhimg.com/v2-46b66a7b3f3cf3e3f3a5487f21637a7b.jpg","type":0,"id":9707392,"ga_prefix":"021207","title":"「用疟疾治疗癌症」？用纳税人的钱做研究，怎么能这么不严谨"}]
     */

    private String date;
    private List<StoriesBean> stories;
    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    @Override
    public String toString() {
        return "ZhiHuNewNewsBean{" +
                "date='" + date + '\'' +
                ", stories=" + stories.toString() +
                ", top_stories=" + top_stories.toString() +
                '}';
    }

    public static class StoriesBean {
        /**
         * images : ["https://pic1.zhimg.com/v2-692db9627bffafdd578eb55f2b965274.jpg"]
         * type : 0
         * id : 9707366
         * ga_prefix : 021310
         * title : 中国菜这么油腻，可胖人还是美国多
         * multipic : true
         */

        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private boolean multipic;
        private List<String> images;

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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public boolean isMultipic() {
            return multipic;
        }

        public void setMultipic(boolean multipic) {
            this.multipic = multipic;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "StoriesBean{" +
                    "type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    ", multipic=" + multipic +
                    ", images=" + images +
                    '}';
        }
    }

    public static class TopStoriesBean {
        /**
         * image : https://pic4.zhimg.com/v2-4e93aeeb7ec8064e335c76d466602e63.jpg
         * type : 0
         * id : 9707423
         * ga_prefix : 021307
         * title : 通过在线地图软件，我看到很多有意思的东西
         */

        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
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

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        @Override
        public String toString() {
            return "TopStoriesBean{" +
                    "image='" + image + '\'' +
                    ", type=" + type +
                    ", id=" + id +
                    ", ga_prefix='" + ga_prefix + '\'' +
                    ", title='" + title + '\'' +
                    '}';
        }
    }
}
