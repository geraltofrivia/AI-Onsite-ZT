// https://github.com/shuzheng/zheng/tree/7005c0a775e6d014d1dc8a8a809f7b1c13bf785a/zheng-cms/zheng-cms-dao/src/main/java/com/zheng/cms/dao/model/CmsArticle.java#L278-L304
public class TempClass {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", articleId=").append(articleId);
        sb.append(", topicId=").append(topicId);
        sb.append(", title=").append(title);
        sb.append(", author=").append(author);
        sb.append(", fromurl=").append(fromurl);
        sb.append(", image=").append(image);
        sb.append(", keywords=").append(keywords);
        sb.append(", description=").append(description);
        sb.append(", type=").append(type);
        sb.append(", allowcomments=").append(allowcomments);
        sb.append(", status=").append(status);
        sb.append(", userId=").append(userId);
        sb.append(", readnumber=").append(readnumber);
        sb.append(", top=").append(top);
        sb.append(", systemId=").append(systemId);
        sb.append(", ctime=").append(ctime);
        sb.append(", orders=").append(orders);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }

}