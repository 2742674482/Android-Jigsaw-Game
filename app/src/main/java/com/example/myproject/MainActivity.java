package com.example.myproject;

import static java.util.Collections.swap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageButton ib00,ib01,ib02,ib10,ib11,ib12,ib20,ib21,ib22;
    Button restartBtn;
    TextView timeTv;

    //行列数的图片个数
    private int imageX=3;
    private int imageY=3;

    //图片的总数目
    private int imageCount=imageX*imageY;

    //空白区域的位置（变化）
    private int blankSwap =imageCount-1;
    //初始化空白区域的按钮id
    private int blankImgid=R.id.pt_ib_02x02;




    //定义计数时间的变量
    int time =0;

    //存放碎片的数组。便于统一管理
    private int[]image = {R.mipmap.img_xiaoxiong_00x00,R.mipmap.img_xiaoxiong_00x01,R.mipmap.img_xiaoxiong_00x02,
                            R.mipmap.img_xiaoxiong_01x00,R.mipmap.img_xiaoxiong_01x01,R.mipmap.img_xiaoxiong_01x02,
                             R.mipmap.img_xiaoxiong_02x00,R.mipmap.img_xiaoxiong_02x01,R.mipmap.img_xiaoxiong_02x02};

    //声明一个图片数组的下标数组，随机排列这个数组
    private int[]imageIndex=new int[image.length];

    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                time++;
                timeTv.setText("Time :"+time+ "seconds");
                handler.sendEmptyMessageDelayed(1,1000);
            }

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        //打乱碎片的函数
        disruptRandom();
        handler.sendEmptyMessageDelayed(1,1000);


        //接受名字
        Intent intent = getIntent();
        String username = intent.getStringExtra("username");
        TextView textView = findViewById(R.id.textView3);
        textView.setText("Hello,"+username+".This is the picture you need to restore");


    }


    //随机打乱数组当中元素，以不规则的形式进行图片显示
    private void disruptRandom() {
        for (int i = 0; i < imageIndex.length; i++) {
            imageIndex[i] = i;
        }
        //规定20次，随机选择两个角标对应的值进行交换
        int rand1, rand2;
        for (int j = 0; j < 20; j++) {
            //随机生成第一个角标   生成0-8之间的随机数
            rand1 = (int) (Math.random() * (imageIndex.length - 1));
            //第二次随机生成的角标，不能和第一次随机生成的角标相同，如果相同，就不方便交换了
            do {
                rand2 = (int) (Math.random() * (imageIndex.length - 1));
                if (rand1 != rand2) {
                    break;
                }
            } while (true);
            //交换两个角标上对应的值
            swap(rand1, rand2);
        }
        //随机排列到指定的控件上
        ib00.setImageResource(image[imageIndex[0]]);
        ib01.setImageResource(image[imageIndex[1]]);
        ib02.setImageResource(image[imageIndex[2]]);
        ib10.setImageResource(image[imageIndex[3]]);
        ib11.setImageResource(image[imageIndex[4]]);
        ib12.setImageResource(image[imageIndex[5]]);
        ib20.setImageResource(image[imageIndex[6]]);
        ib21.setImageResource(image[imageIndex[7]]);
        ib22.setImageResource(image[imageIndex[8]]);
    }




    //交换数组指定角标上的数据
    private void swap(int rand1, int rand2) {
        int temp=imageIndex[rand1];
        imageIndex[rand1]=imageIndex[rand2];
        imageIndex[rand2]=temp;
    }



    //初始化控件
        private void initView () {
            ib00 = findViewById(R.id.pt_ib_00x00);
            ib01 = findViewById(R.id.pt_ib_00x01);
            ib02 = findViewById(R.id.pt_ib_00x02);
            ib10 = findViewById(R.id.pt_ib_01x00);
            ib11 = findViewById(R.id.pt_ib_01x01);
            ib12 = findViewById(R.id.pt_ib_01x02);
            ib20 = findViewById(R.id.pt_ib_02x00);
            ib21 = findViewById(R.id.pt_ib_02x01);
            ib22 = findViewById(R.id.pt_ib_02x02);
            timeTv = findViewById(R.id.pt_tv_time);
            restartBtn = findViewById(R.id.pt_btn_restart);

        }

    public void onClick(View view) {
        int id = view.getId();
//        九个按钮执行的点击事件的逻辑应该是相同的，如果有空格在周围，可以改变图片显示的位置，否则点击事件不响应
        switch (id) {
            case R.id.pt_ib_00x00:
                move(R.id.pt_ib_00x00,0);
                break;
            case R.id.pt_ib_00x01:
                move(R.id.pt_ib_00x01,1);
                break;
            case R.id.pt_ib_00x02:
                move(R.id.pt_ib_00x02,2);
                break;
            case R.id.pt_ib_01x00:
                move(R.id.pt_ib_01x00,3);
                break;
            case R.id.pt_ib_01x01:
                move(R.id.pt_ib_01x01,4);
                break;
            case R.id.pt_ib_01x02:
                move(R.id.pt_ib_01x02,5);
                break;
            case R.id.pt_ib_02x00:
                move(R.id.pt_ib_02x00,6);
                break;
            case R.id.pt_ib_02x01:
                move(R.id.pt_ib_02x01,7);
                break;
            case R.id.pt_ib_02x02:
                move(R.id.pt_ib_02x02,8);
                break;
        }
    }

    //表示移动指定位置的按钮的函数。将图片与空白区域进行交换
    private void move(int imagebuttonId, int site) {
//            判断选中的图片在第几行
        int sitex = site / imageX;
        int sitey = site % imageY; //第几列
//        获取空白区域的坐标
        int blankx = blankSwap / imageX;
        int blanky = blankSwap % imageY;
//        可以移动的条件有两个
//        1.在同一行，列数相减，绝对值为1，可移动   2.在同一列，行数相减，绝对值为1，可以移动
        int x = Math.abs(sitex-blankx);
        int y = Math.abs(sitey-blanky);
        if ((x==0&&y==1)||(y==0&&x==1)){
//            通过id，查找到这个可以移动的按钮
            ImageButton clickButton = findViewById(imagebuttonId);
            clickButton.setVisibility(View.INVISIBLE);
//            查找到空白区域的按钮
            ImageButton blankButton = findViewById(blankImgid);
//            将空白区域的按钮设置图片
            blankButton.setImageResource(image[imageIndex[site]]);
//            移动之前是不可见的，移动之后，将控件设置为可见
            blankButton.setVisibility(View.VISIBLE);
//            将改变角标的过程记录到存储图片位置数组当中
            swap(site,blankSwap);
//            新的空白区域位置更新等于传入的点击按钮的位置
            blankSwap = site;
            blankImgid = imagebuttonId;
        }

        //判断本次移动完成后，是否完成成功
        judegeGameover();

        }

        //判断是否成功
        private void judegeGameover() {
        boolean loop = true;//定义标志位
            for(int i=0;i<imageIndex.length;i++){
                if(imageIndex[i]!=i){
                    loop=false;
                    break;
                }
            }
            if(loop){
                //拼图成功
                //停止计时
                handler.removeMessages(1);
                //禁止移动图片
                ib00.setClickable(false);
                ib01.setClickable(false);
                ib02.setClickable(false);
                ib10.setClickable(false);
                ib11.setClickable(false);
                ib12.setClickable(false);
                ib20.setClickable(false);
                ib21.setClickable(false);
                ib22.setClickable(false);
                ib22.setImageResource(image[8]);
                ib22.setVisibility(View.VISIBLE);
                //弹出成功消息

                Intent intent = getIntent();
                String username = intent.getStringExtra("username");
                AlertDialog.Builder builder=new AlertDialog.Builder(this);
                builder.setMessage("good job,"+username+"!The time is:"+time+"seconds")
                        .setPositiveButton("yes",null);
                AlertDialog dialog=builder.create();
                dialog.show();
            }

    }


    //restart button
        public void restart (View view){
            //将状态还原
            restore();
            //将评图打乱
            disruptRandom();

            handler.removeMessages(1);
            //时间归零，并重新计时
            time = 0;
            timeTv.setText("Time :" + time + "seconds");
            handler.sendEmptyMessageDelayed(1, 1000);

        }

    private void restore() {
        //游戏重新开始，允许移动碎片，因为成功是设置为不移动，现在改回来
        ib00.setClickable(true);
        ib01.setClickable(true);
        ib02.setClickable(true);
        ib10.setClickable(true);
        ib11.setClickable(true);
        ib12.setClickable(true);
        ib20.setClickable(true);
        ib21.setClickable(true);
        ib22.setClickable(true);
        //还原被点击的图片按钮，变为初始化模样
        ImageButton clickBtn=findViewById(blankImgid);
        clickBtn.setVisibility(View.VISIBLE);
        //默认隐藏第九张图片
        ImageButton blankBtn=findViewById(R.id.pt_ib_02x02);
        blankBtn.setVisibility(View.INVISIBLE);
        blankImgid=R.id.pt_ib_02x02;//初始化空白区域的id
        blankSwap=imageCount-1;

    }
}

