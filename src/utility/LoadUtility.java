package utility;

import javafx.scene.image.Image;

public class LoadUtility {
    //player image
    public static Image down,down2,down3,up,up2,up3,left,left2,left3,right,right2,right3,
                        downleft,downleft2,downleft3,downright,downright2,downright3,
                        upleft,upleft2,upleft3,upright,upright2,upright3, slime_jump_1,
                        slime_jump_2, slime_jump_3,sworddownleft1,sworddownleft2,sworddownleft3,
                        sworddownright1,sworddownright2,sworddownright3,swordupleft1,
                        swordupleft2,swordupleft3, swordupright1,swordupright2,swordupright3;

    //group of load
    static {
        playerLoad();
        attackLoad();
    }


    public static void playerLoad() {
        down = new Image(ClassLoader.getSystemResourceAsStream("image/player/Character_Down.png"));
        up = new Image(ClassLoader.getSystemResourceAsStream("image/player/up.png"));
        left = new Image(ClassLoader.getSystemResourceAsStream("image/player/left.png"));
        right = new Image(ClassLoader.getSystemResourceAsStream("image/player/right.png"));
        down2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/down2.png"));
        down3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/down3.png"));
        up2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/up2.png"));
        up3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/up3.png"));
        left2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/left2.png"));
        left3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/left3.png"));
        right2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/right2.png"));
        right3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/right3.png"));
        upleft = new Image(ClassLoader.getSystemResourceAsStream("image/player/upleft.png"));
        upleft2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/upleft2.png"));
        upleft3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/upleft3.png"));
        upright = new Image(ClassLoader.getSystemResourceAsStream("image/player/upright.png"));
        upright2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/upright2.png"));
        upright3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/upright3.png"));
        downright = new Image(ClassLoader.getSystemResourceAsStream("image/player/downright.png"));
        downright2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/downright2.png"));
        downright3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/downright3.png"));
        downleft = new Image(ClassLoader.getSystemResourceAsStream("image/player/downleft.png"));
        downleft2 = new Image(ClassLoader.getSystemResourceAsStream("image/player/downleft2.png"));
        downleft3 = new Image(ClassLoader.getSystemResourceAsStream("image/player/downleft3.png"));
    }
    public static void attackLoad() {
        sworddownleft1 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_DownLeft1.png"));
        sworddownleft2 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_DownLeft2.png"));
        sworddownleft3 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_DownLeft3.png"));
        sworddownright1 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_DownRight1.png"));
        sworddownright2 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_DownRight2.png"));
        sworddownright3 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_DownRight3.png"));
        swordupleft1 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_UpLeft1.png"));
        swordupleft2 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_UpLeft2.png"));
        swordupleft3 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_UpLeft3.png"));
        swordupright1 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_UpRight1.png"));
        swordupright2 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_UpRight2.png"));
        swordupright3 = new Image(ClassLoader.getSystemResourceAsStream("image/attacks/Sword_UpRight3.png"));
    }
    public static void slimeLoad() {
        slime_jump_1 = new Image(ClassLoader.getSystemResourceAsStream("image/slime/Pink/slime_jump_1.png"));
        slime_jump_2 = new Image(ClassLoader.getSystemResourceAsStream("image/slime/Pink/slime_jump_2.png"));
        slime_jump_3 = new Image(ClassLoader.getSystemResourceAsStream("image/slime/Pink/slime_jump_3.png"));
    }
}
