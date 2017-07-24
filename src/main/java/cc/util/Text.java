package cc.util;
//文本工具类
public class Text {
	//搜索功能中  文本中的关键词变成红色
	public static String textRed(String content,String redKey){
        String newContent = null;
        newContent = content.replace(redKey, "<font color='#ff0000'>"+redKey+"</font>");
		return newContent;
	}
}
