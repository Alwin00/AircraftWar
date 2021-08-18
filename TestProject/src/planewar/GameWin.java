package planewar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GameWin extends JFrame {

    //游戏状态 0未开始 1游戏中 2暂停 3.通关失败 4.通关成功
    public static int state = 0;
    //游戏得分
    public static int score = 0;
    //双缓存的图片
    Image offScreenImage = null;
    //窗口的宽度
    int width = 600;
    //窗口的高度
    int height = 600;
    //游戏的重绘次数
    int count = 1;
    //敌机出现的数量
    int enemyCount = 0;

    //背景图对象
    BgObj bgObj = new BgObj(GameUtils.bgImg,0,-2000,2);
    //我方飞机对象
    public PlaneObj planeObj = new PlaneObj(GameUtils.planeImg,290,550,20,30,0,this);
    //敌方boss对象
    public BossObj  bossObj = null;
    //启动方法
    public void launch(){
        //设置窗口是否可见
        this.setVisible(true);
        //设置窗口大小
        this.setSize(width,height);
        //设置窗口位置
        this.setLocationRelativeTo(null);
        //设置窗口标题
        this.setTitle("飞机大战");

        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);

        //游戏的点击启动事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == 1 && state == 0){
                    state = 1;
                }
            }
        });

        //暂停
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 32){
                    switch (state){
                        case 1:
                            state = 2;
                            break;
                        case 2:
                            state = 1;
                            break;
                            default:
                    }
                }
            }
        });

        //重复绘制
        while (true){
            if (state == 1){
                createObj();
                repaint();
            }
            try {
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void paint(Graphics g) {
        if (offScreenImage == null){
            offScreenImage = createImage(width,height);
        }
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,width,height);
        if (state == 0){
            //未开始
            gImage.drawImage(GameUtils.bgImg,0,0,null);
            gImage.drawImage(GameUtils.bossImg,220,120,null);
            gImage.drawImage(GameUtils.explodeImg,270,350,null);
            GameUtils.drawWord(gImage,"点击开始游戏",Color.yellow,40,180,300);
        }
        if (state == 1){
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
            //运行中
            for (int i = 0; i < GameUtils.gameObjList.size(); i++) {
                GameUtils.gameObjList.get(i).paintSelf(gImage);
            }
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
        }
        if (state == 3){
            //失败
            gImage.drawImage(GameUtils.explodeImg,planeObj.getX()-35,planeObj.getY()-50,null);
            GameUtils.drawWord(gImage,"GAME OVER",Color.RED,50,180,300);
        }
        if (state == 4){
            //通关
            gImage.drawImage(GameUtils.explodeImg,bossObj.getX() + 30,bossObj.getY(),null);
            GameUtils.drawWord(gImage,"游戏通关",Color.green,50,190,300);
        }
        GameUtils.drawWord(gImage,score+" 分",Color.green,40,30,100);
        g.drawImage(offScreenImage,0,0,null);
        count++;
        System.out.println(GameUtils.gameObjList.size());
    }

    void createObj() {
        //我方子弹
        if (count % 15 == 0){
            GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg, planeObj.getX() + 3, planeObj.getY() - 16, 14, 29, 5, this));
            GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size() - 1));
        }
        if (count % 15 == 0){
            GameUtils.enemyObjList.add(new EnemyObj(GameUtils.enemyImg,(int)(Math.random()*12)*50,0,49,36,5,this));
            GameUtils.gameObjList.add(GameUtils.enemyObjList.get(GameUtils.enemyObjList.size() -1));
            enemyCount++;
        }
        if (count % 15 == 0 && bossObj != null){
            GameUtils.bulletObjList.add(new BulletObj(GameUtils.bulletImg,bossObj.getX()+76,bossObj.getY()+85,15,25,5,this));
            GameUtils.gameObjList.add(GameUtils.bulletObjList.get(GameUtils.bulletObjList.size() - 1));
        }
        if (enemyCount > 1 && bossObj == null){
            bossObj = new BossObj(GameUtils.bossImg,250,35,155,100,5,this);
            GameUtils.gameObjList.add(bossObj);
        }
    }
    public static void main(String[] args) {
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }



}
