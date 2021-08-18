package planewar;

import java.awt.*;

public class EnemyObj extends obj {

    public EnemyObj() {
        super();
    }

    public EnemyObj(Image img, int x, int y, int width, int height, double speed, GameWin frame) {
        super(img, x, y, width, height, speed, frame);
    }

    @Override
    public void paintSelf(Graphics gImage) {
        super.paintSelf(gImage);
        y += speed;
        //���ҷɻ���ײ���
        if (this.getRec().intersects(this.frame.planeObj.getRec())){
            GameWin.state = 3;
        }
        //�л���Խ����ʧ �ж����� y > 600  �ı�������(-200,-200)
        if (y > 600){
            this.x = -200;
            this.y = 200;
            GameUtils.removeList.add(this);
        }
        //�л���ʧǰ�ƶ���(-200,-200)  �ҷ��ӵ�(-100,100)
        for (ShellObj shellObj: GameUtils.shellObjList) {
            if (this.getRec().intersects(shellObj.getRec())){
                ExplodeObj explodeObj = new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
               shellObj.setX(-100);
               shellObj.setY(100);
               this.x = -200;
               this.y = 200;
               GameUtils.removeList.add(shellObj);
               GameUtils.removeList.add(this);
               GameWin.score++;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
