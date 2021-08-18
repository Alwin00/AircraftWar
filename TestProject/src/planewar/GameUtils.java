package planewar;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameUtils {
    //背景图片
    public static Image bgImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    //boss图片
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
    //爆炸图片
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    //我方战斗机图片
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("imgs/plane.png");
    //我方子弹图片
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/shell.png");
    //敌方子弹图片
    public static Image bulletImg = Toolkit.getDefaultToolkit().getImage("imgs/bullet.png");
    //敌机图片
    public static Image enemyImg = Toolkit.getDefaultToolkit().getImage("imgs/enemy.png");

    //所有游戏物体的集合
    public static List<obj> gameObjList = new ArrayList<>();
    //要删除元素的集合
    public static List<obj> removeList = new ArrayList<>();
    //我方子弹的集合
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //敌方子弹的集合
    public static List<BulletObj> bulletObjList = new ArrayList<>();
    //敌机的集合
    public static List<EnemyObj> enemyObjList = new ArrayList<>();
    //爆炸的集合
    public static List<ExplodeObj> explodeObjList = new ArrayList<>();

    //绘制字符串的工具类
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("仿宋",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }
}
