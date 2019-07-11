package com.shanghai.logistics.ui.adapter.home;





/**
 * @author chensong
 * @date 2019/4/16 13:43
 */
//public class TopNewsAdapter extends BaseQuickAdapter<, BaseViewHolder> {
//    public TopNewsAdapter(@Nullable List<> data) {
//        super(R.layout.item_article_list, data);
//    }
//
//
//    @Override
//    protected void convert(BaseViewHolder helper,  item) {
//        Date date = new Date(item.getPublishTime());
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String time = format.format(date);
//        String top;
//        if (item.getType() == 1) {
//            top = "置顶";
//        } else {
//            top = "";
//        }
//        helper.setText(R.id.tv_news_detail_title, item.getTitle())
//                .setText(R.id.tv_author, item.getAuthor() + "/")
//                .setText(R.id.tv_time, time + "发布")
//                .setText(R.id.tv_news_title, item.getSuperChapterName())
//                .setText(R.id.tv_top, top);
//
//    }
//}
